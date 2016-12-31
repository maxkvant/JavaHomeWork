package task0;
import java.math.BigInteger;

/**
 * HashTable implementation
 * Expected amortized time complexity is O(1) per query
 */

public class HashTable {
    private ListNode[] lists;
    private int size = 0;

    public HashTable() {
        this.clear();
    }

    /**
     * returns size : how many keys HashTable contains
     */
    public int size() {
        return size;
    }

    /**
     * clears HashTable
     */
    public void clear() {
        HashTable hashTable = new HashTable(3);
        lists = hashTable.lists;
        size = hashTable.size;
    }

    /**
     * returns value of key, if key is present in this hashTable,
     * otherwise returns null
     */
    public String get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        return  lists[key.hashCode() % lists.length].get(key);
    }

    /**
     * returns key is present in this hashTable
     */

    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        return get(key) != null;
    }

    /**
     * removes key from hashTable
     * returns value if key, if key was present,
     * otherwise returns null
     */

    public String remove(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        String res = lists[key.hashCode() % lists.length].remove(key);
        if (res == null) {
            return null;
        }
        size--;
        return res;
    }

    /**
     * associates value with key in this hashMap
     * returns previous associated value with key, if key was present,
     * otherwise returns null
     */

    public String put(String key, String val) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        String res = lists[key.hashCode() % lists.length].put(key, val);
        norm();
        if (res == null) {
            size++;
            return null;
        }
        return res;
    }

    private void norm() {
        if (size > 3 * lists.length) {
            HashTable hashTable = new HashTable(nextPrime(lists.length * 5));
            for (ListNode subList : lists) {
                for (ListNode node = subList.next(); node != subList; node = node.next()) {
                    hashTable.put(node.getKey(), node.getVal());
                }
            }
            lists = hashTable.lists;
            size = hashTable.size;
        }
    }

    private HashTable(int reserved) {
        reserved = nextPrime(reserved);
        lists = new ListNode[reserved];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new ListNode();
        }
    }

    private int nextPrime(int x) {
        return BigInteger.valueOf(x).nextProbablePrime().intValue();
    }

    public static class ListNode {
        private ListNode prev;
        private ListNode next;
        private String val;
        private String key;

        public ListNode() {
            this.next = this;
            this.prev = this;
            this.val = this.key = null;
        }

        private ListNode getNode(String key) {
            for (ListNode i = next; i != this; i = i.next) {
                if (i.key.equals(key)) {
                    return i;
                }
            }
            return null;
        }

        public String get(String key) {
            ListNode cur = getNode(key);
            return cur == null ? null : cur.val;
        }

        public String remove(String key) {
            ListNode cur = getNode(key);
            if (cur != null && cur.key != null) {
                ListNode curPrev = cur.prev;
                cur.next.prev = cur.next;
                curPrev.next = curPrev;
                return cur.val;
            }
            return null;
        }

        public String put(String key, String val) {
            ListNode cur = getNode(key);
            if (cur != null) {
                String res = cur.val;
                cur.val = val;
                return res;
            } else {
                ListNode newNode = new ListNode();
                newNode.key = key;
                newNode.val = val;
                newNode.next = next;
                newNode.prev = this;
                next.prev = newNode;
                this.next = newNode;
                return null;
            }
        }

        public ListNode next() {
            return this.next;
        }

        public String getKey() {
            return this.key;
        }

        public String getVal() {
            return this.val;
        }
    }
}