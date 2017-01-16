package task;

import java.util.Set;

public class SimpleSender implements BroadcastSender {
    private Receiver receiver;
    private String topic = "topic";

    public SimpleSender(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setCoordinator(Receiver receiver) {
        this.receiver = receiver;
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            receiver.receive(i);
        }
    }
}
