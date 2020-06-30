package org.okboom.bard.gateway.netty.thread;

import io.netty.channel.EventLoopGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author tookbra
 */
@Slf4j
public final class ServerGroup {

    /**
     * The default number of threads to accept incoming requests from clients. (Requests are serviced by worker threads,
     * not acceptor threads.)
     */
    public static final int DEFAULT_INCOMING_ACCEPTOR_THREADS = 4;

    /**
     * The default number of threads to service incoming requests from clients.
     */
    public static final int DEFAULT_INCOMING_WORKER_THREADS = 8;

    /**
     * eventLoopGroup manager
     */
    private final ThreadPoolsManager threadPoolsManager;
    /**
     * server stop flag
     */
    private final AtomicBoolean stopped = new AtomicBoolean(false);

    public ServerGroup(int acceptorThreads, int workerThreads) {
        this.threadPoolsManager = new ThreadPoolsManager(acceptorThreads, workerThreads);
    }

    /**
     * close EventLoopGroup graceful
     * @param graceful
     */
    private void shutdown(boolean graceful) {
        if (!stopped.compareAndSet(false, true)) {
            log.info("Shutdown requested, but ServerGroup is already stopped. Doing nothing.");
            return;
        }

        log.info("Shutting down server group event loops " + (graceful ? "(graceful)" : "(non-graceful)"));
        List<EventLoopGroup> allEventLoopGroups = threadPoolsManager.getAllEventLoops();
        allEventLoopGroups.forEach(t -> {
            if(graceful) {
                t.shutdownGracefully();
            } else {
                t.shutdownGracefully(0, 0, TimeUnit.SECONDS);
            }
        });

        if (graceful) {
            allEventLoopGroups.forEach(t -> {
                try {
                    t.awaitTermination(60, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    log.warn("Interrupted while shutting down event loop");
                }
            });
        }
        log.debug("Done shutting down server group");
    }

    /**
     *
     * @return
     */
    public EventLoopGroup getProxyAcceptorPoolForTransport() {
        return threadPoolsManager.getProxyAcceptorPool();
    }

    /**
     *
     * @return
     */
    public EventLoopGroup getProxyWorkerPoolForTransport() {
        return threadPoolsManager.getProxyWorkerPool();
    }
}
