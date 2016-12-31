package task11;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Simple Thread Pool implementation
 * @see ThreadPool
 */

public class ThreadPoolImpl implements ThreadPool {
    private final Queue<LightFutureAbstract> queue  = new ArrayDeque<>();
    private final List<Thread> threads = new ArrayList<>();

    /**
     * creates ThreadPool with n threads, which can consume tasks
     */
    public ThreadPoolImpl(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < n; i++) {
            Thread thread = new Thread(new Consumer());
            threads.add(thread);
            thread.start();
        }
    }

    @Override
    public <T> LightFuture<T> submit(Supplier<T> supplier) {
        LightFutureImpl<T> future = new LightFutureImpl<>(supplier);
        synchronized (queue) {
            queue.add(future);
            queue.notifyAll();
        }
        return future;
    }

    private abstract class LightFutureAbstract<T> implements LightFuture<T> {
        protected volatile boolean ready;
        protected volatile T result;
        protected volatile Exception exception;

        @Override
        public synchronized boolean isReady() {
            return this.ready;
        }

        @Override
        public synchronized T get() throws LightExecutionException {
            try {
                while (!isReady()) {
                    wait();
                }
                if (exception != null) {
                    throw exception;
                }
            } catch (Exception e) {
                throw new LightExecutionException(e);
            }
            return result;
        }

        public abstract void execute();

        @Override
        public synchronized <U> LightFuture<U> thenApply(Function<T, U> function) {
            final LightFuture<T> callee = this;
            LightFutureAbstract<U> future = new LightFutureThenApply<T, U>(function, this);
            synchronized (queue) {
                queue.add(future);
            }
            return future;
        }
    }

    private class LightFutureImpl<T> extends LightFutureAbstract<T> {
        private final Supplier<T> supplier;

        LightFutureImpl(Supplier<T> supplier) {
            this.supplier = supplier;
        }

        @Override
        public void execute() {
            try {
                result = supplier.get();
            } catch (Exception e) {
                exception = e;
            } finally {
                ready = true;
            }
        }
    }

    private class LightFutureThenApply<X, T> extends LightFutureAbstract<T> {
        private final Function<X, T> function;
        private final LightFuture<X> callee;

        LightFutureThenApply(Function<X, T> function, LightFuture<X> callee) {
            this.function = function;
            this.callee = callee;
        }

        @Override
        public void execute() {
            try {
                result = function.apply(callee.get());
            } catch (LightExecutionException e) {
                exception = (Exception) e.getCause();
            } finally {
                ready = true;
            }
        }
    }

    private class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    final LightFutureAbstract future;
                    synchronized (queue) {
                        while (queue.isEmpty()) {
                            queue.wait();
                        }
                        future = queue.remove();
                    }
                    synchronized (future) {
                        future.execute();
                        future.notifyAll();
                    }
                    synchronized (queue) {
                        queue.notify();
                    }
                }
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    @Override
    public synchronized void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
