package org.okboom.bard.gateway.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.okboom.bard.gateway.netty.handler.ProxyHandler;
import org.okboom.bard.gateway.netty.thread.ServerGroup;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author tookbra
 */
@Slf4j
public final class ProxyServer {


    private final ServerGroup serverGroup;
    private final Integer MAX_CONTENT_LENGTH = 1024 * 1024 * 10;
    private final Integer idleConnectionTimeout = 70;
    private final Integer port;
    private final AtomicBoolean stopped = new AtomicBoolean(false);
    private final ChannelGroup allChannels = new DefaultChannelGroup("HTTP-Proxy-Server", GlobalEventExecutor.INSTANCE);
    private final Thread jvmShutdownHook = new Thread(() -> abort(), "bard-shutdown-hook");

    public ProxyServer(ProxyServer.Builder builder) {
        this.serverGroup = builder.serverGroup;
        this.port = builder.port;
    }

    public void registerChannel(Channel channel) {
        allChannels.add(channel);
    }

    public void start() {
        if(serverGroup == null) {
            throw new RuntimeException("can not found serverGroup");
        }
        doStart();
    }

    public void abort() {
        doStop(true);
    }

    /**
     * Stops the server and all related clones. Waits for traffic to stop before shutting down.
     */
    public void stop() {
        doStop(true);
    }

    private void doStart() {
        ServerBootstrap serverBootstrap = new ServerBootstrap().group(
                serverGroup.getProxyAcceptorPoolForTransport(),
                serverGroup.getProxyWorkerPoolForTransport());
        serverBootstrap.channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024 * 4)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_REUSEADDR, true)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ChannelPipeline pipeline =  ch.pipeline();
                        pipeline.addLast("idleStateHandler", new IdleStateHandler(0, 0, idleConnectionTimeout));
                        pipeline.addLast("httpDecode", new HttpRequestDecoder());
                        pipeline.addLast("httpEncode",new HttpResponseEncoder());
                        pipeline.addLast("compressor", new HttpContentCompressor());
                        pipeline.addLast("aggregator", new HttpObjectAggregator(MAX_CONTENT_LENGTH));
                        pipeline.addLast("bizHandler", new ProxyHandler());
                    }
                });
        ChannelFuture future = serverBootstrap.bind(port).addListener((ChannelFutureListener) f -> {
            if (f.isSuccess()) {
                registerChannel(f.channel());
                log.info("bind port:{} successful", port);
            }
        }).awaitUninterruptibly();

        Runtime.getRuntime().addShutdownHook(jvmShutdownHook);
    }

    /**
     * Performs cleanup necessary to stop the server. Closes all channels opened by the server and unregisters this
     * server from the server group.
     * @param graceful when true, waits for requests to terminate before stopping the server
     */
    private void doStop(boolean graceful) {
        if (stopped.compareAndSet(false, true)) {
            if (graceful) {
                log.info("Shutting down proxy server gracefully");
            } else {
                log.info("Shutting down proxy server immediately (non-graceful)");
            }
            closeAllChannels(graceful);
            try {
                Runtime.getRuntime().removeShutdownHook(jvmShutdownHook);
            } catch (IllegalStateException e) {
            }

            log.info("Done shutting down proxy server");
        }
    }

    /**
     * Closes all channels opened by this proxy server.
     * @param graceful when false, attempts to shutdown all channels immediately and ignores any channel-closing exceptions
     */
    private void closeAllChannels(boolean graceful) {
        log.info("Closing all channels " + (graceful ? "(graceful)" : "(non-graceful)"));

        ChannelGroupFuture future = allChannels.close();

        // if this is a graceful shutdown, log any channel closing failures. if this isn't a graceful shutdown, ignore them.
        if (graceful) {
            try {
                future.await(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.warn("Interrupted while waiting for channels to shut down gracefully.");
            }

            if (!future.isSuccess()) {
                for (ChannelFuture cf : future) {
                    if (!cf.isSuccess()) {
                        log.info("Unable to close channel.  Cause of failure for {} is {}", cf.channel(), cf.cause());
                    }
                }
            }
        }
    }

    public static ProxyServer.Builder builder() {
        return new ProxyServer.Builder();
    }

    public static final class Builder {
        int port;
        int proxyAcceptorThreads;
        int proxyWorkerThreads;
        int idleConnectionTimeout;
        ServerGroup serverGroup;

        public ProxyServer.Builder port(int port) {
            this.port = port;
            return this;
        }

        public ProxyServer.Builder serverGroup(ServerGroup serverGroup) {
            this.serverGroup = serverGroup;
            return this;
        }

        public ProxyServer.Builder proxyAcceptorThreads(int proxyAcceptorThreads) {
            this.serverGroup = serverGroup;
            return this;
        }

        public ProxyServer.Builder proxyWorkerThreads(int proxyWorkerThreads) {
            this.serverGroup = serverGroup;
            return this;
        }

        public ProxyServer.Builder idleConnectionTimeout(int idleConnectionTimeout) {
            this.idleConnectionTimeout = idleConnectionTimeout;
            return this;
        }

        public ProxyServer build() {
            if (this.serverGroup == null) {
                serverGroup = new ServerGroup(proxyAcceptorThreads, proxyWorkerThreads);
            }
            return new ProxyServer(this);
        }
    }
}
