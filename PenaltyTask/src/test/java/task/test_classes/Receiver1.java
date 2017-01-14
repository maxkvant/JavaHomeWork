package task.test_classes;

import task.BroadcastReceiver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Receiver1 implements BroadcastReceiver {
    public static int[] count = new int[3];

    public Set<String> getTopics() {
        return new HashSet<String>(Arrays.asList("1", "2"));
    }

    public void receive(Object message) {
        count[(Integer) message]++;

        System.out.println();
    }
}
