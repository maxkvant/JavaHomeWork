package task12;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple junit rule, checks did registered threads die
 */

public class ThreadsRule implements TestRule {
    private final List<Thread> threads = new ArrayList<Thread>();

    @Override
    public Statement apply(final Statement statement, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                statement.evaluate();
                System.out.println("after");
                for (Thread thread : threads) {
                    if (thread.isAlive()) {
                        throw new ThreadsRuleException("thread: " + String.valueOf(thread));
                    }
                }
            }
        };
    }

    /**
     * register a thread in this rule
     */
    public void registerThread(Thread thread) {
        threads.add(thread);
    }
}