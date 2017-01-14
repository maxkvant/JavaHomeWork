package task;

import java.util.Set;

/**
 * Interface for receiver of messages by topics from getTopics()
 * @see task.Receiver
 */

public interface BroadcastReceiver extends Receiver, HasTopics {
    /**
     * returns set of topics, which sender receives
     * should always return a constant set
     */
    @Override
    Set<String> getTopics();
}