package task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BroadcastCoordinator {
    private final int queueCapacity = 1000;
    private final BlockingQueue<MarkedMessage> queue = new ArrayBlockingQueue<MarkedMessage>(queueCapacity, false);
    private final List<Thread> threads = new ArrayList<>();
    private final Map<String, List<BroadcastReceiver> > mapReceivers = new HashMap<>();
    private final Map<String, List<Filter> > mapFilters = new HashMap<>();

    BroadcastCoordinator() {
        Thread thread = new Thread(new QueueConsumer());
        threads.add(thread);
    }

    public void start() {
        threads.forEach(Thread::start);
    }

    public synchronized void shutdown() {
        threads.forEach(Thread::interrupt);
    }

    public void add(BroadcastSender sender) {
        final String topic = sender.getTopic();
        sender.setCoordinator((message) -> queue.add(new MarkedMessage(message, topic)));

        synchronized (threads) {
            Thread thread = new Thread(sender);
            threads.add(thread);
        }
    }

    public void add(BroadcastReceiver receiver) {
        __add(receiver, mapReceivers);
    }

    public void add(Filter filter) {
        __add(filter, mapFilters);
    }

    private <T extends HasTopics> void __add(T receiver, Map<String, List<T> > map) {
        for (String topic : receiver.getTopics()) {
            map.putIfAbsent(topic, new ArrayList<T>());
            map.get(topic).add(receiver);
        }
    }

    private class MarkedMessage {
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
