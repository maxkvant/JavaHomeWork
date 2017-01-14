package task;

public interface BroadcastSender extends Runnable {
    public String getTopic();
    void setCoordinator(Receiver receiver);
}
