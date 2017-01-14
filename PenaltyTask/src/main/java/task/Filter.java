package task;

public interface Filter extends HasTopics {
    boolean filter(Object message);
}