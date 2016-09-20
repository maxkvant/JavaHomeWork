package ru.vinnichenko.task1.HashTable;
import java.math.BigInteger;

public class HashTable {
    private ListNode[] arr;
    private int size;

    public int size() {
        return size;
    }

    private HashTable(int reserved) {
        reserved = nextPrime(reserved);
        size = 0;
        arr = new ListNode[reserved];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new ListNode();
        }
    }

    public void clear() {
        HashTable hashTable = new HashTable(3);
        arr = hashTable.arr;
        size = hashTable.size;
    }

    public HashTable() {
        this.clear();
    }

    public String get(String key) {
        return  arr[key.hashCode() % arr.length].get(key);
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public String remove(String key) {
        String res = arr[key.hashCode() % arr.length].remove(key);
        if (res == null) {
            return null;
        }
        size--;
        return res;
    }

    public String put(String key, String val) {
        String res = arr[key.hashCode() % arr.length].put(key, val);
        if (res == null) {
            size++;
            return null;
        }
        return res;
    }

    private  void norm() {
        if (size > 3 * arr.length) {
            HashTable hashTable = new HashTable(nextPrime(arr.length * 5));
            for (ListNode subList : arr) {
                for (ListNode node = subList.next(); node != subList; node = node.next()) {
                    hashTable.put(node.getKey(), node.getVal());
                }
            }
            arr = hashTable.arr;
            size = hashTable.size;
        }
    }

    private int nextPrime(int x) {
        return BigInteger.valueOf(x).nextProbablePrime().intValue();
    }
}