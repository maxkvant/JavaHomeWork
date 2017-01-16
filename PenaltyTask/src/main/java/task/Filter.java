package task;

import java.util.Set;

/**
 * Checks messages with topics from getTopics()
 */

public interface Filter extends HasTopics {
    /**
     * returns: does message pass filter
     */
    boolean filter(Object message);

    /**
     * returns set of topics, which is validated by this filter
     * should always return constant set
     */
    @Override
    Set<String> getTopics();
}