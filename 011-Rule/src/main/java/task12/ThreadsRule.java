package task12;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.*;

import static junit.framework.TestCase.assertFalse;

/**
 * Simple junit rule, checks did registered threads die
 */
public class ThreadsRule implements TestRule {
    private final List<Thread> threads = new ArrayList<>();
    private final Map<Thread, Throwable> throwableMap = new HashMap<>();

    @Override
    public Statement apply(final Statement statement, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                statement.evaluate();
                for (Thread thread : threads) {
                    Throwable throwableThread = throwableMap.get(thread);
                    if (throwableThread != null) {
                        String message = "throwable " + throwableThread + ", in thread " + thread;
                        throw new AssertionError(message, throwableThread);
                    }
                    String message = "thread alive: " + thread;
                    assertFalse(message, thread.isAlive());
                }
            }
        };
    }

    /**
     * register a thread in this rule
     */
    public void registerThread(Thread thread) {
        thread.setUncaughtExceptionHandler(new ThrowableHandler());
        threads.add(thread);
    }

    private class ThrowableHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            throwableMap.put(t, e);
        }
    }
}