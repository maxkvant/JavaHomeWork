package task;

public interface BroadcastSender extends Runnable {
    String getTopic();
    void setCoordinator(Receiver receiver);
}
