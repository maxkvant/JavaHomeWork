package task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Class for Coordinator, where you can add BroadcastSenders, BroadcastReceivers, Filters.
 * BroadcastSenders sends messages, Filters validate them, and BroadcastReceivers receive valid messages.
 */
public class BroadcastCoordinator {
    private final int queueCapacity = 1000;
    private final BlockingQueue<MarkedMessage> queue = new ArrayBlockingQueue<>(queueCapacity, false);
    private final List<Thread> threads = new ArrayList<>();
    private final Map<String, List<BroadcastReceiver> > mapReceivers = new HashMap<>();
    private final Map<String, List<Filter> > mapFilters = new HashMap<>();
    private boolean running = false;

    public BroadcastCoordinator() {
        Thread thread = new Thread(new QueueConsumer());
        threads.add(thread);
    }

    /**
     * starts this coordinator (run threads), if not started
     */
    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        threads.forEach(Thread::start);
    }

    /**
     * interrupts all threads in this coordinator
     */
    public synchronized void shutdown() {
        running = false;
        threads.forEach(Thread::interrupt);
    }

    /**
     * adds sender to this coordinator.
     * if coordinator is running, sender starts immediately,
     * otherwise sender starts after start of this coordinator
     */
    public synchronized void add(BroadcastSender sender) {
        final String topic = sender.getTopic();
        sender.setCoordinator((message) -> queue.add(new MarkedMessage(message, topic)));

        Thread thread = new Thread(sender);
        threads.add(thread);
        if (running) {
            thread.start();
        }
    }

    /**
     * adds receiver to this coordinator.
     */
    public synchronized void add(BroadcastReceiver receiver) {
        __add(receiver, mapReceivers);
    }

    /**
     * adds filter to this coordinator.
     * receivers won't receive messages, which don't pass this filter.
     */
    public synchronized void add(Filter filter) {
        __add(filter, mapFilters);
    }

    private <T extends HasTopics> void __add(T receiver, Map<String, List<T> > map) {
        for (String topic : receiver.getTopics()) {
            map.putIfAbsent(topic, new ArrayList<>());
            map.get(topic).add(receiver);
        }
    }

    private static class MarkedMessage {
        final Object message;
        final String topic;

        MarkedMessage(Object message, String topic) {
            this.message = message;
            this.topic = topic;
        }
    }

    private class QueueConsumer implements Runnable {
        public void run() {
            try {
                while (true) {
                    MarkedMessage markedMessage = queue.take();
                    String topic = markedMessage.topic;
                    Object message = markedMessage.message;

                    synchronized (BroadcastCoordinator.this) {
                        mapReceivers.putIfAbsent(topic, new ArrayList<>());
                        mapFilters.putIfAbsent(topic, new ArrayList<>());

                        List<Filter> filters = mapFilters.get(topic);
                        List<BroadcastReceiver> receivers = mapReceivers.get(topic);

                        boolean pass = true;
                        for (Filter filter : filters) {
                            pass &= filter.filter(message);
                        }
                        if (pass) {
                            for (BroadcastReceiver receiver : receivers) {
                                receiver.receive(message);
                            }
                        }
                    }
                }
            } catch (InterruptedException e) {
                //ignored
            }
        }
    }
}
