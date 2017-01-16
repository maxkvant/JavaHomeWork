package task;

/**
 * Interface for Senders of messages with a fixed topic
 * @see java.lang.Runnable
 */

public interface BroadcastSender extends Runnable {
    /**
     * returns topic of messages
     * should always return a constant topic
     */
    String getTopic();

    /**
     * Sets receiver, where sender sends messages
     */
    void setCoordinator(Receiver receiver);
}
