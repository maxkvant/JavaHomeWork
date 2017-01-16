package task7.test;

import task7.MyTreeSet;
import task7.MyTreeSetImpl;

import java.util.Iterator;

import static org.junit.Assert.*;

public class MyTreeSetImplTest {
    int [] elemens = new int [] {4, 2, 1, 3, 6, 5, 7};
    MyTreeSet<Integer> initSet(MyTreeSet<Integer> set) {
        assertEquals(set.size(), 0);
        for (int e : elemens) {
            set.add(e);
        }
        for (int e : elemens) {
            assertTrue(set.contains(e));
        }
        System.out.println();
        assertEquals(set.size(), elemens.length);
        return set;
    }

    @org.junit.Test
    public void addTest() throws Exception {
        MyTreeSet<Integer> set = new MyTreeSetImpl<Integer>();
        assertFalse(set.contains(1));
        initSet(set);
    }

    @org.junit.Test
    public void iteratorTest() throws Exception {
        MyTreeSet<Integer> set = initSet(new MyTreeSetImpl<>());
        Iterator<Integer> it = set.iterator();
        for (int i = 1; i <= 7; i++) {
            assertEquals(it.next(), (Integer)i);
        }
        assertFalse(it.hasNext());
    }

    @org.junit.Test
    public void descendingIteratorTest() throws Exception {
        MyTreeSet<Integer> set = initSet(new MyTreeSetImpl<>()).descendingSet();
        Iterator<Integer> it = set.iterator();
        for (int i = 7; i >= 1; i--) {
            assertEquals(it.next(), (Integer)i);
        }
        assertFalse(it.hasNext());
    }

    @org.junit.Test
    public void descendingSetTest() throws Exception {
        MyTreeSet<Integer> set = (new MyTreeSetImpl<Integer>()).descendingSet();
        initSet(set);
    }

    @org.junit.Test
    public void firstTest() throws Exception {
        assertEquals(initSet(new MyTreeSetImpl<>()).first(), (Integer)1);
        assertEquals(initSet(new MyTreeSetImpl<>()).descendingSet().first(), (Integer)7);
    }

    @org.junit.Test
    public void lastTest() throws Exception {
        assertEquals(initSet(new MyTreeSetImpl<>()).last(), (Integer)7);
        assertEquals(initSet(new MyTreeSetImpl<>()).descendingSet().last(), (Integer)1);
    }

    @org.junit.Test
    public void floorTest() throws Exception {
        MyTreeSet<Integer> set1 = initSet(new MyTreeSetImpl<>());
        MyTreeSet<Integer> set2 = initSet((new MyTreeSetImpl<Integer>()).descendingSet());
        MyTreeSet<Integer> set3 = initSet(new MyTreeSetImpl<>()).descendingSet();

        for (int i = 1; i < 8; i++) {
            assertEquals((int)set1.ceiling(i), i);
        }
        assertEquals(null, set1.ceiling(8));
        assertEquals(1, (int)set1.ceiling(0));

        for (int i = 1; i < 7; i++) {
            assertEquals((int)set1.higher(i), i+1);
        }
        assertEquals(null, set1.higher(7));
        assertEquals(1, (int)set1.higher(-100));
        assertEquals(1, (int)set1.ceiling(-100));

        assertEquals(set2.higher(4), (Integer)3);


        assertEquals(set3.higher(4), (Integer)3);

        assertEquals((int)set1.floor(4), 4);
        assertEquals((int)set1.lower(4), 3);

        MyTreeSet<Integer> set4 = set3.descendingSet();
        assertEquals(set4.higher(4), (Integer)5);
    }

    @org.junit.Test
    public void ComparatorTest() {
        String[] a = new String[] {"a", "A", "b", "B", "C", "c"};
        MyTreeSet<String> set = new MyTreeSetImpl<>(String.CASE_INSENSITIVE_ORDER);
        for (String s : a) {
            set.add(s);
        }
        assertEquals(set.higher("a"), "b");
        assertEquals(set.higher("A"), "b");
        assertEquals(set.higher("B"), "C");
        assertEquals(set.size(), 3);
    }

    @org.junit.Test
    public void removeTest() {
        MyTreeSetImpl<Integer> set = new MyTreeSetImpl<Integer>();

        assertEquals(set.size(), 0);
        initSet(set);
        assertEquals(set.size(), 7);
        for (int i : elemens) {
            assertTrue(set.contains(i));
            set.remove(i);
            assertFalse(set.contains(i));
        }


        assertEquals(set.size(), 0);
        initSet(set);
        assertEquals(set.size(), 7);
        for (int i = 1; i <= 7; i++) {
            assertTrue(set.contains(i));
            set.remove(i);
            assertFalse(set.contains(i));
        }
    }
}