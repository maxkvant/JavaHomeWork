package task12;

import org.junit.Rule;
import org.junit.Test;

public class ThreadsRuleTest {
    @Rule
    public ThreadsRule rule = new ThreadsRule();

    public class MyRunnable implements Runnable {
        MyRunnable() {
        }
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test1() throws InterruptedException {
        Thread thread1 = new Thread(new MyRunnable());
        Thread thread2 = new Thread(new MyRunnable());
        rule.registerThread(thread1);
        rule.registerThread(thread2);
        thread1.start();
        thread2.start();
        thread2.join();
        thread2.join();
    }

    @Test
    public void test2() { //this test should fail
        Thread thread = new Thread(new MyRunnable());
        rule.registerThread(thread);
        thread.start();
    }
}