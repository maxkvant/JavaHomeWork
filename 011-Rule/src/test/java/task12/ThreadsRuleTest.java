package task12;

import junit.framework.AssertionFailedError;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.RuleChain;

public class ThreadsRuleTest {
    private ThreadsRule threadsRule = new ThreadsRule();
    private ExpectedException exceptionRule = ExpectedException.none();

    @Rule
    public RuleChain rules = RuleChain
            .outerRule(exceptionRule)
            .around(threadsRule);

    private class MyRunnable implements Runnable {
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testOk() throws InterruptedException {
        Thread thread1 = new Thread(new MyRunnable());
        Thread thread2 = new Thread(new MyRunnable());
        threadsRule.registerThread(thread1);
        threadsRule.registerThread(thread2);
        thread1.start();
        thread2.start();
        thread2.join();
        thread2.join();
    }

    @Test
    public void testThreadNotFinished() {
        exceptionRule.expect(AssertionFailedError.class);
        exceptionRule.handleAssertionErrors();
        Thread thread = new Thread(new MyRunnable());
        threadsRule.registerThread(thread);
        thread.start();
    }

    @Test
    public void testExceptionInThread() throws InterruptedException {
        exceptionRule.expect(AssertionError.class);
        exceptionRule.handleAssertionErrors();
        Thread thread = new Thread(() -> {throw new RuntimeException();});
        threadsRule.registerThread(thread);
        thread.start();
        thread.join();
    }
}