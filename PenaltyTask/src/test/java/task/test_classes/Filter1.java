package task.test_classes;

import task.Filter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Filter1 implements Filter {
    public Set<String> getTopics() {
        return new HashSet<String>(Collections.singleton("1"));
    }

    public boolean filter(Object message) {
        return (message instanceof Integer && (Integer)message % 2 == 0);
    }
}
