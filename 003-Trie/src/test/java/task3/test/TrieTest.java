package task3.test;

import task3.Trie;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

public class TrieTest {
    @org.junit.Test
    public void addTest() throws Exception {
        Trie trie = new Trie();
        assertTrue(trie.add(""));
        assertFalse(trie.add(""));
        assertTrue(trie.contains(""));
        assertFalse(trie.contains("abacaba"));
        assertTrue(trie.add("abacaba"));
        assertTrue(trie.contains("abacaba"));
    }

    @org.junit.Test
    public void sizeTest() throws Exception {
        Trie trie = new Trie();
        assertEquals(0, trie.size());
        trie.add("abacaba");
        assertEquals(1, trie.size());
        trie.add("aba");
        assertEquals(2, trie.size());
        trie.add("abba");
        assertEquals(3, trie.size());
        trie.add("aba");
        assertEquals(3, trie.size());
        trie.add("aca");
        assertEquals(4, trie.size());
        trie.remove("abacaba");
        assertEquals(3, trie.size());
        trie.remove("aca");
        assertEquals(2, trie.size());
        trie.add("abacaba");
        assertEquals(3, trie.size());
    }

    @org.junit.Test
    public void removeTest() {
        Trie trie = new Trie();
        trie.add("abacaba");
        assertFalse(trie.remove("a"));
        assertFalse(trie.remove("abacabaaa"));
        assertFalse(trie.remove("abacabc"));
        assertTrue(trie.remove("abacaba"));
    }

    @org.junit.Test
    public void howManyStartsWithPrefixTest() throws Exception {
        Trie trie = new Trie();
        trie.add("abac");
        trie.add("abaa");
        trie.add("abcd");
        trie.add("");
        assertEquals(4, trie.howManyStartsWithPrefix(""));
        assertEquals(3, trie.howManyStartsWithPrefix("a"));
        assertEquals(3, trie.howManyStartsWithPrefix("ab"));
        assertEquals(2, trie.howManyStartsWithPrefix("aba"));
        assertEquals(1, trie.howManyStartsWithPrefix("abc"));
        assertEquals(1, trie.howManyStartsWithPrefix("abcd"));
        assertEquals(0, trie.howManyStartsWithPrefix("abcde"));
        assertEquals(0, trie.howManyStartsWithPrefix("f"));
    }

    @org.junit.Test
    public void serializeTest() throws Exception {
        Trie trie = new Trie();
        trie.add("abac");
        trie.add("abaa");
        trie.add("abcd");
        trie.add("");
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(10000)) {
            trie.serialize(outputStream);
            trie.deserialize(new ByteArrayInputStream(outputStream.toByteArray()));
            assertEquals(3, trie.howManyStartsWithPrefix("a"));
        }
    }
}