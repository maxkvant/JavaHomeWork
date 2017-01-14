package task;

/**
 * Interface for simple receiver of messages
 */

public interface Receiver {
    /**
     * implement this method, to do something on receive message.
     */
    void receive(Object message);
}
