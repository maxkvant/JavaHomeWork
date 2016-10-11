package task5.test;
import task5.SetTree;

import static org.junit.Assert.*;

public class SetTreeTest {
    @org.junit.Test
    public void addTest() throws Exception {
        SetTree<String> set = new SetTree<String>();
        assertFalse(set.contains("5"));
        assertEquals(set.size(), 0);
        set.add("5");
        assertEquals(set.size(), 1);
        set.add("5");
        assertTrue(set.contains("5"));
        assertEquals(set.size(), 1);
        set.add("6");
        assertEquals(set.size(), 2);
        set.add("8");
        set.add("7");
        assertEquals(set.size(), 4);

        set.add("4");
        assertEquals(set.size(), 5);
        set.add("2");
        set.add("3");
        set.add("1");
        assertEquals(set.size(), 8);
        assertTrue(set.contains("1"));
        assertTrue(set.contains("5"));
        assertTrue(set.contains("7"));
        assertFalse(set.contains("a"));
    }


}