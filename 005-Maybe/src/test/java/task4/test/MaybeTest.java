package task4.test;
import task4.Maybe;
import task4.Maybe.MaybeException;

import java.util.function.Function;

import static org.junit.Assert.*;

public class MaybeTest {
    @org.junit.Test(expected = MaybeException.class)
    public void justTest() throws Exception {
        Maybe<Integer> maybe = Maybe.just(new Integer(1));
        assertEquals((Integer) 1, maybe.get());
        Maybe.just(null);
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
        assertEquals("1", maybe1.get());
        maybe1 = Maybe.nothing();
        maybe1.get();
    }

    @org.junit.Test
    public void isPresent() throws Exception {
        Maybe<Integer> maybe = Maybe.nothing();
        assertFalse(maybe.isPresent());
        Maybe<String> maybe2 = Maybe.just("abacaba");
        assertTrue(maybe2.isPresent());
    }

    @org.junit.Test
    public void mapTest() throws Exception {
        Function<Integer, Integer> square = (x) -> x * x;
        Maybe<Integer> maybe = Maybe.just(new Integer(100));
        assertEquals((Integer) 10000, maybe.map(square).get());

        Function<Integer, String> squareStr = (x) -> String.valueOf(x * x);
        assertEquals("10000",  maybe.map(squareStr).get());

        Maybe<Integer> nothing = Maybe.nothing();
        assertFalse(nothing.map(squareStr).isPresent());
    }

}