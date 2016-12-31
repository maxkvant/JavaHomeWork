package task3;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /**
     * Puts string in trie, if absent.
     * Returns: was string absent.
     */

    public boolean add(String element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (!contains(element)) {
            TrieNode vertex = root;
            for (int i = 0; i < element.length(); i++) {
                char c = element.charAt(i);
                vertex.size++;
                vertex = vertex.updateNext(c);
            }
            vertex.size++;
            vertex.isTerminal = true;
            return true;
        }
        return false;
    }

    /**
     * checks whether a string is present.
     */

    public boolean contains(String element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        TrieNode vertex0 = getNode(element);
        return vertex0 != null && vertex0.isTerminal;
    }

    /**
     * Removes string from trie, if present.
     * Returns: was string present.
     */

    public boolean remove(String element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (contains(element)) {
            TrieNode vertex = root;
            for (int i = 0; i < element.length(); i++) {
                vertex.size--;
                vertex = vertex.getNext(element.charAt(i));
            }
            vertex.size--;
            vertex.isTerminal = false;
            return true;
        }
        return false;
    }

    /**
     * Returns: how many strings contains.
     */

    public int size() {
        return root.size;
    }

    /**
     * Returns: how many strings starts with prefix.
     */

    public int howManyStartsWithPrefix(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException();
        }
        TrieNode vertex0 = getNode(prefix);
        return vertex0 == null ? 0 : vertex0.size;
    }

    /**
     * Saves in OutputStream state of the object.
     * @throws IOException
     */

    public void serialize(OutputStream out) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException();
        }
        try(DataOutputStream stream = new DataOutputStream(out)) {
            stream.writeInt(root.size);
            StringBuilder stringBuilder = new StringBuilder();
            serializeDfs(root, stringBuilder, stream);
            out.flush();
        }
    }

    /**
     * Restores from InputStream state of the object, saved by 'serialize'.
     * @throws IOException
     */

    public void deserialize(InputStream in) throws IOException {
        if (in == null) {
            throw new IllegalArgumentException();
        }
        try(DataInputStream stream = new DataInputStream(in)) {
            int len = stream.readInt();
            for (int i = 0; i < len; i++) {
                String s = stream.readUTF();
                add(s);
            }
        }
    }

    private TrieNode getNode(String element) {
        TrieNode v = root;
        for (int i = 0; v != null && i < element.length(); i++) {
            v = v.getNext(element.charAt(i));
        }
        return v;
    }

    private void serializeDfs(TrieNode vertex, StringBuilder curStr, DataOutputStream stream)
            throws IOException, IndexOutOfBoundsException {
        if (vertex == null) {
            return;
        }
        if (vertex.isTerminal) {
            stream.writeUTF(curStr.toString());
        }
        for (Map.Entry<Character,TrieNode> entry : vertex.next.entrySet()) {
            curStr.append(entry.getKey());
            serializeDfs(entry.getValue(), curStr, stream);
            curStr.deleteCharAt(curStr.length() - 1);
        }
    }

    private static class TrieNode {
        private final TreeMap<Character, TrieNode> next = new TreeMap<>();
        private int size = 0;
        private boolean isTerminal = false;

        private TrieNode getNext(char c) {
            return next.get(c);
        }

        private TrieNode updateNext(char c) {
            next.putIfAbsent(c, new TrieNode());
            return next.get(c);
        }
    }
}
