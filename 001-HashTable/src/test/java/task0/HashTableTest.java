package task0;

import static org.junit.Assert.assertEquals;

public class HashTableTest {
    @org.junit.Test
    public void sizePutRemoveTest() throws Exception {
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
        hashTable.put("Hello World", "world");
        assertEquals(1, hashTable.size());
    }

    @org.junit.Test
    public void getPutRemoveTest() throws Exception {
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
        hashTable.put("Hello World!", "world");
        assertEquals("world", hashTable.get("Hello World!"));
    }

    @org.junit.Test
    public void containsPutTest() throws Exception {
        HashTable hashTable = new HashTable();
        assertEquals(false, hashTable.contains("a"));
        hashTable.put("a", "b");
        assertEquals(true, hashTable.contains("a"));
    }

    @org.junit.Test
    public void bigPutTest() throws Exception {
        HashTable hashTable = new HashTable();
        final int n = 10000;
        for (int i = 0; i < n; i++) {
            hashTable.put(i + "", (i + 1) + "");
        }
        for (int i = 0; i < n; i++) {
            assertEquals((i + 1) + "", hashTable.get(i + ""));
        }
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void getExceptionTest() {
        new HashTable().get(null);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void putExceptionTest() {
        new HashTable().put(null, "hello, World");
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void containsExceptionTest() {
        new HashTable().contains(null);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void removeExceptionTest() {
        new HashTable().remove(null);
    }
}