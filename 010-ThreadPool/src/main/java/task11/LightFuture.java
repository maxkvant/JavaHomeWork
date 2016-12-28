package task11;

import java.util.function.Function;


/**
 * interface of task which could be completed in future
 */
public interface LightFuture<T> {
    /**
     * returns is task completed
     */
    boolean isReady();

    /**
     * waits util task would be completed
     * if is Exception e, during task execution, returns LightExecutionException(e),
     * otherwise return result of task execution
     */
    T get() throws LightExecutionException;

    /**
     * returns new task, which fetch result of task execution, and applies function to it
     */
    <R> LightFuture<R> thenApply(Function<T, R> function);
}
