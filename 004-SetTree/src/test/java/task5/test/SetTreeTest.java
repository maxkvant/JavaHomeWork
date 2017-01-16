package task5.test;
import task5.SetTree;

import static org.junit.Assert.*;

public class SetTreeTest {
    @org.junit.Test
    public void addContainsTest() {
        SetTree<String> set = new SetTree<>();
        assertFalse(set.contains("5"));
        set.add("5");
        assertTrue(set.contains("5"));
        set.add("6");
        set.add("8");
        set.add("7");
        set.add("4");
        set.add("2");
        set.add("3");
        set.add("1");
        assertTrue(set.contains("1"));
        assertTrue(set.contains("5"));
        assertTrue(set.contains("7"));
        assertFalse(set.contains("a"));
    }

    @org.junit.Test
    public void addSizeTest() {
        SetTree<String> set = new SetTree<>();
        assertEquals(0, set.size());
        set.add("5");
        assertEquals(1, set.size());
        set.add("5");
        assertEquals(1, set.size());
        set.add("6");
        assertEquals(2, set.size());
        set.add("8");
        set.add("7");
        assertEquals(4, set.size());
        set.add("4");
        assertEquals(5, set.size());
        set.add("2");
        set.add("3");
        set.add("1");
        assertEquals(8, set.size());
    }
}