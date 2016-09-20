package ru.vinnichenko.task1.test;
import ru.vinnichenko.task1.HashTable.HashTable;

import static junit.framework.TestCase.assertEquals;

//import static org.junit.Assert.*;

public class HashTableTest {
    @org.junit.Test
    public void size() throws Exception {
        HashTable hashTable = new HashTable();
        assertEquals(0, hashTable.size());
        hashTable.put("1", "2");
        assertEquals(1, hashTable.size());
        hashTable.put("1", "3");
        assertEquals(1, hashTable.size());
        hashTable.put("a", "b");
        assertEquals(2, hashTable.size());
        hashTable.put("e", "f");
        assertEquals(3, hashTable.size());
        hashTable.remove("c");
        assertEquals(3, hashTable.size());
        hashTable.remove("1");
        assertEquals(2, hashTable.size());
        hashTable.clear();
        assertEquals(0, hashTable.size());
    }

    @org.junit.Test
    public void get() throws Exception {
        HashTable hashTable = new HashTable();
        assertEquals(null, hashTable.get("1"));
        hashTable.put("1", "2");
        assertEquals("2", hashTable.get("1"));
        hashTable.put("1", "3");
        assertEquals("3", hashTable.get("1"));
        hashTable.put("a", "b");
        hashTable.put("e", "f");
        hashTable.remove("1");
        assertEquals("b", hashTable.get("a"));
        hashTable.clear();
        assertEquals(null, hashTable.get("e"));
    }

    @org.junit.Test
    public void contains() throws Exception {
        HashTable hashTable = new HashTable();
        assertEquals(false, hashTable.contains("a"));
        hashTable.put("a", "b");
        assertEquals(true, hashTable.contains("a"));
    }

    @org.junit.Test
    public void put() throws Exception {
        HashTable hashTable = new HashTable();
        for (int i = 0; i < 200; i++) {
            hashTable.put(String.valueOf(i), String.valueOf(i + 1));
            assertEquals(String.valueOf(i + 1), hashTable.get(String.valueOf(i)));
        }
    }
}