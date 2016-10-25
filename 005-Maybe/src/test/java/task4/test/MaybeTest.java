package task4.test;
import java.util.function.Function;

import static org.junit.Assert.*;
import task4.*;
import task4.Maybe.MaybeException;

public class MaybeTest {
    @org.junit.Test
    public void justTest() throws Exception {
        Maybe<Integer> maybe = Maybe.just(new Integer(1));
        assertTrue(maybe.get().equals(new Integer(1)));
    }

    @org.junit.Test
    public void nothing() throws Exception {
        Function<Integer, String> squareStr = (x) -> String.valueOf(x * x);
        Maybe<Integer> nothing = Maybe.nothing();
        assertFalse(nothing.isPresent());
        assertFalse(nothing.map(squareStr).isPresent());
    }

    @org.junit.Test(expected = MaybeException.class)
    public void get() throws Exception {
        Maybe<String> maybe1 = Maybe.just("1");
        assertTrue(maybe1.get().equals("1"));
        maybe1 = Maybe.nothing();
        maybe1.get();
    }

    @org.junit.Test
    public void isPresent() throws Exception {
        Maybe<Integer> maybe = Maybe.just(null);
        assertFalse(maybe.isPresent());
        Maybe<String> maybe2 = Maybe.just("abacaba");
        assertTrue(maybe2.isPresent());
    }

    @org.junit.Test
    public void mapTest() throws Exception {
        Function<Integer, Integer> square = (x) -> x * x;
        Maybe<Integer> maybe = Maybe.just(new Integer(100));
        assertTrue((maybe.map(square).get()).equals(100 * 100));

        Function<Integer, String> squareStr = (x) -> String.valueOf(x * x);
        assertTrue((maybe.map(squareStr).get()).equals("10000"));

        Maybe<Integer> nothing = Maybe.nothing();
        assertFalse(nothing.map(squareStr).isPresent());
    }

}