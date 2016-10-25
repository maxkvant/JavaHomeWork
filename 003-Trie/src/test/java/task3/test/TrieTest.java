package task3.test;

import junit.framework.AssertionFailedError;
import org.junit.Assert;
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
        assertEquals(trie.size(), 0);
        trie.add("abacaba");
        assertEquals(trie.size(), 1);
        trie.add("aba");
        assertEquals(trie.size(), 2);
        trie.add("abba");
        assertEquals(trie.size(), 3);
        trie.add("aba");
        assertEquals(trie.size(), 3);
        trie.add("aca");
        assertEquals(trie.size(), 4);
        trie.remove("abacaba");
        assertEquals(trie.size(), 3);
        trie.remove("aca");
        assertEquals(trie.size(), 2);
        trie.add("abacaba");
        assertEquals(trie.size(), 3);
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
        assertEquals(trie.howManyStartsWithPrefix(""), 4);
        assertEquals(trie.howManyStartsWithPrefix("a"), 3);
        assertEquals(trie.howManyStartsWithPrefix("ab"), 3);
        assertEquals(trie.howManyStartsWithPrefix("aba"), 2);
        assertEquals(trie.howManyStartsWithPrefix("abc"), 1);
        assertEquals(trie.howManyStartsWithPrefix("abcd"), 1);
        assertEquals(trie.howManyStartsWithPrefix("abcde"), 0);
        assertEquals(trie.howManyStartsWithPrefix("f"), 0);
    }

    @org.junit.Test
    public void serializeTest() throws Exception {
        Trie trie = new Trie();
        trie.add("abac");
        trie.add("abaa");
        trie.add("abcd");
        trie.add("");
        ByteArrayOutputStream outstr = new ByteArrayOutputStream(10000);
        trie.serialize(outstr);
        trie.deserialize(new ByteArrayInputStream(outstr.toByteArray()));
        assertEquals(trie.howManyStartsWithPrefix("a"), 3);
    }
}