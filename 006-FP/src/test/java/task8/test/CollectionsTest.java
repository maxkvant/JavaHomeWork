package task8.test;

import task8.*;

import java.util.ArrayList;
import java.util.Arrays;


import static org.junit.Assert.*;

public class CollectionsTest {
    private final Iterable<String> a = new ArrayList<String>(Arrays.asList(new String[]{"a", "b", "c", "d"}));

    private final Function2<String, String, String> concat = new Function2<String, String, String>() {
        @Override
        public String apply(String s1, String s2) {
            return "<" + s1 + s2 + ">";
        }
    };

    private final Function1<String, String> brackets = new Function1<String, String>() {
        @Override
        public String apply(String s) {
            return "(" + s + ")";
        }
    };

    private final Predicate<String> isNotC = new Predicate<String>() {
        @Override
        public Boolean apply(String s) {
            return !s.equals("c");
        }
    };


    @org.junit.Test
    public void foldlTest() throws Exception {
        assertEquals("<<<<a>b>c>d>", Collections.foldl(concat, "", a));
    }

    @org.junit.Test
    public void foldrTest() throws Exception {
        assertEquals("<a<b<c<d>>>>", Collections.foldr(concat, "", a));
    }

    @org.junit.Test
    public void filterTest() throws Exception {
        assertEquals("<<<a>b>d>", Collections.foldl(concat, "", Collections.filter(isNotC, a)));
    }

    @org.junit.Test
    public void takeWhileTest() throws Exception {
        assertEquals("<<a>b>", Collections.foldl(concat, "", Collections.takeWhile(isNotC, a)));
    }

    @org.junit.Test
    public void takeUnlessTest() throws Exception {
        assertEquals(Collections.foldl(concat, "", Collections.takeUnless(isNotC, a)), "");
        assertEquals(Collections.foldl(concat, "", Collections.takeWhile(Predicate.ALWAYS_FALSE, a)), "<<<<a>b>c>d>");
    }

    @org.junit.Test
    public void mapTest() throws Exception {
        assertEquals("<<<<(a)>(b)>(c)>(d)>", Collections.foldl(concat, "", Collections.map(brackets, a)));

    }
}