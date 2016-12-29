package task11;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static junit.framework.TestCase.assertEquals;


public class ThreadPoolImplTest {
    @org.junit.Test
    public void supplyTest() throws Exception {
        ThreadPool threadPool = new ThreadPoolImpl(4);
        List<LightFuture<Integer> > res = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final Integer n = i;
            Supplier<Integer> supplier = () -> n * 2;
            res.add(threadPool.submit(supplier).thenApply(x -> x * 3));
        }
        for (int i = 0; i < 10; i++) {
            assertEquals((Integer) (i * 6), res.get(i).get());
        }
        threadPool.shutdown();
    }

    @org.junit.Test
    public void shutdownTest() {
        final int n = 4;
        ThreadPool threadPool = new ThreadPoolImpl(4);
        List<LightFuture<Void> > res = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Supplier<Void> supplier = () -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (threads) {
                    threads.add(Thread.currentThread());
                }
                return null;
            };
            res.add(threadPool.submit(supplier));
        }

        for (int i = 0; i < 10; i++) {
            res.get(i).get();
        }

        threadPool.shutdown();
        assertEquals(n, threads.stream().mapToLong((thread) -> thread.getId()).distinct().count());
        assertEquals((Boolean) true, threads.
                stream().
                map((thread) -> !thread.isAlive()).
                reduce((x,y) -> x && y).
                orElse(false));
    }
}