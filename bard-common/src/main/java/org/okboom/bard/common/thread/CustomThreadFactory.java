package org.okboom.bard.common.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * thread factory
 * @author tookbra
 */
@Slf4j
public class CustomThreadFactory implements ThreadFactory {

    /**
     * thread group atomic
     */
    private AtomicInteger threadCount = new AtomicInteger(1);

    private final String prefix;

    /**
     * exception throw log
     */
    private static final Thread.UncaughtExceptionHandler UNCAUGHT_EXCEPTION_HANDLER =
            (t, e) -> log.error("Uncaught throwable in thread: {}", t.getName(), e);

    public CustomThreadFactory(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        final Thread t = new Thread(r, prefix + threadCount.getAndIncrement());
        t.setUncaughtExceptionHandler(UNCAUGHT_EXCEPTION_HANDLER);
        return t;
    }
}
