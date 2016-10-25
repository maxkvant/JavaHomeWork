package task8.test;

import task8.*;

import java.util.ArrayList;
import java.util.Arrays;


import static org.junit.Assert.*;

public class CollectionsTest {
    private final Iterable<String> a = new ArrayList(Arrays.asList(new String[]{"a", "b", "c", "d"}));

    private final Function2<String, String, String> concat = new Function2<String, String, String>() {
        @Override
        public String apply(String s1, String s2) {
            return "<" + s1 + s2 + ">";
        }
    };

    private final Predicate<String> isNotC = new Predicate<String>() {
        @Override
        public Boolean apply(String s) {
            return !s.equals("c");
        }
    };

    private final Collections<String> collections = new Collections<String>();

    @org.junit.Test
    public void foldlTest() throws Exception {
        assertEquals("<<<<a>b>c>d>", collections.foldl(concat, "", a));
    }

    @org.junit.Test
    public void foldrTest() throws Exception {
        assertEquals("<a<b<c<d>>>>", collections.foldr(concat, "", a));
    }

    @org.junit.Test
    public void filterTest() throws Exception {
        assertEquals(collections.foldl(concat, "", collections.filter(isNotC, a)), "<<<a>b>d>");
    }

    @org.junit.Test
    public void takeWhileTest() throws Exception {
        assertEquals(collections.foldl(concat, "", collections.takeWhile(isNotC, a)), "<<a>b>");
    }

    @org.junit.Test
    public void takeUnlessTest() throws Exception {
        assertEquals(collections.foldl(concat, "", collections.takeUnless(isNotC, a)), "");
        assertEquals(collections.foldl(concat, "", collections.takeWhile(Predicate.ALWAYS_FALSE, a)), "<<<<a>b>c>d>");
    }

}