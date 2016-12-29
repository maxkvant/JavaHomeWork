package task11;

import java.util.function.Supplier;

/**
 * Simple Thread Pool interface
 */

public interface ThreadPool {
    /**
     * returns task of supplier execution, which could be completed in future
     */
    <T> LightFuture <T> submit(Supplier<T> supplier);
    /**
     * interrupts all threads, created this instance of ThreadPool
     */
    void shutdown();
}
