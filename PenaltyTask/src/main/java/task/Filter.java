package task;

import java.util.Set;

public interface Filter extends HasTopics {
    boolean filter(Object message);
}