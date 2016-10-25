package task8.test;

import org.omg.PortableInterceptor.INACTIVE;
import task8.Predicate;

import static org.junit.Assert.*;

public class PredicateTest {
    @org.junit.Test
    public void test() throws Exception {
        Predicate p2 = new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer x) {
                return x % 2 == 0;
            }
        };
        Predicate p3 = new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer x) {
                return x % 3 == 0;
            }
        };
        for (int i = 0; i <= 15; i++) {
            assertEquals(i % 2 == 0, p2.apply(i));
            assertEquals((i % 2 == 0 && i % 3 == 0), p3.and(p2).apply(i));
            assertEquals((i % 2 == 0 || i % 3 == 0), p2.or(p3).apply(i));
            assertEquals(!(i % 3 == 0), p3.not().apply(i));
        }
    }

}