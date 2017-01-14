package task;

import org.junit.Test;
import task.test_classes.*;

import java.util.*;

import static org.junit.Assert.assertArrayEquals;

public class BroadcastTest {
    @Test
    public void TestCoordinator() throws Exception {
        Receiver1.count = new int[3];

        BroadcastCoordinator coordinator = new BroadcastCoordinator();

        coordinator.add(new Sender1());
        coordinator.add(new Sender2());
        coordinator.add(new Sender3());

        coordinator.add(new Filter1());

        coordinator.add(new Receiver1());
        coordinator.add(new Receiver1());

        coordinator.start();
        coordinator.start();

        Thread.currentThread().sleep(100);
        coordinator.shutdown();
        assertArrayEquals(new int[]{4, 2, 4}, Receiver1.count);
    }

    @Test
    public void TestAddStartShutdown() throws Exception {
        BroadcastCoordinator coordinator = new BroadcastCoordinator();

        coordinator.add(new Sender1());

        coordinator.start();

        coordinator.add(new Sender2());

        coordinator.start();

        Thread.currentThread().sleep(100);

        coordinator.shutdown();
        coordinator.shutdown();
    }

    @Test
    public void TestShutdown() {
        BroadcastCoordinator coordinator = new BroadcastCoordinator();
        coordinator.shutdown();
        coordinator.start();
    }

    @Test
    public void TestLoader() throws Exception {
        Receiver1.count = new int[3];

        BroadcastLoader loader = new BroadcastLoader();
        loader.load("build/classes/test/task/test_classes", "task.test_classes");
        loader.start();
        Thread.currentThread().sleep(100);
        loader.shutdown();
        assertArrayEquals(new int[]{2, 1, 2}, Receiver1.count);
    }
}