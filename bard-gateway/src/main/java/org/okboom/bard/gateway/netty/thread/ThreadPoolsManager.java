package org.okboom.bard.gateway.netty.thread;

import com.google.common.collect.ImmutableList;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.Getter;
import org.okboom.bard.common.thread.CustomThreadFactory;

import java.util.List;

/**
 * @author tookbra
 */
@Getter
public class ThreadPoolsManager {

    /**
     * boss group
     */
    private final NioEventLoopGroup proxyAcceptorPool;

    /**
     * work group
     */
    private final NioEventLoopGroup proxyWorkerPool;


    public ThreadPoolsManager(int acceptorThreads, int workerThreads) {
        proxyAcceptorPool = new NioEventLoopGroup(acceptorThreads, new CustomThreadFactory("proxyAcceptor"));
        proxyWorkerPool = new NioEventLoopGroup(acceptorThreads, new CustomThreadFactory("proxyWorker"));
    }

    /**
     *
     * @return all EventLoopGroup
     */
    public List<EventLoopGroup> getAllEventLoops() {
        return ImmutableList.<EventLoopGroup>of(proxyAcceptorPool, proxyWorkerPool);
    }
}
