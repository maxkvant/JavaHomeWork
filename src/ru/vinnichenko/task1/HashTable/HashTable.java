package ru.vinnichenko.task1.HashTable;
import java.math.BigInteger;

public class HashTable {
    private ListNode[] lists;
    private int size;

    public int size() {
        return size;
    }

    private HashTable(int reserved) {
        reserved = nextPrime(reserved);
        size = 0;
        lists = new ListNode[reserved];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new ListNode();
        }
    }

    public void clear() {
        HashTable hashTable = new HashTable(3);
        lists = hashTable.lists;
        size = hashTable.size;
    }

    public HashTable() {
        this.clear();
    }

    public String get(String key) {
        return  lists[key.hashCode() % lists.length].get(key);
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public String remove(String key) {
        String res = lists[key.hashCode() % lists.length].remove(key);
        if (res == null) {
            return null;
        }
        size--;
        return res;
    }

    public String put(String key, String val) {
        String res = lists[key.hashCode() % lists.length].put(key, val);
        if (res == null) {
            size++;
            return null;
        }
        return res;
    }

    private  void norm() {
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

    private int nextPrime(int x) {
        return BigInteger.valueOf(x).nextProbablePrime().intValue();
    }
}