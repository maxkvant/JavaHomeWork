package ru.vinnichenko.task1.HastTable;
import java.math.BigInteger;

public class HashTable {
    private class Pair {
        String key;
        String val;
        Pair(String key, String val) {
            this.key = key;
            this.val = val;
        }
    }

    private Pair[][] arr;
    private int sz;

    public int size() {
        return sz;
    }

    private HashTable(int reserved) {
        reserved = nextPrime(reserved);
        sz = 0;
        arr = new Pair [reserved][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Pair[0];
        }
    }

    public void clear() {
        HashTable hashTable = new HashTable(7);
        arr = hashTable.arr;
        sz = hashTable.sz;
    }

    public HashTable() {
        this.clear();
    }

    public String get(String key) {
        for (Pair x : arr[key.hashCode() % arr.length]) {
            if (x.key.equals(key)) {
                return x.val;
            }
        }
        return null;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public String remove(String key) {
        if (!contains(key)) {
            return null;
        }
        String res = get(key);
        int ind = key.hashCode() % arr.length;
        Pair [] bucket = arr[ind];
        arr[ind] = new Pair[bucket.length - 1];
        int j = 0;
        for (Pair p : bucket) {
            if (!p.key.equals(key)) {
                arr[ind][j] = p;
                j++;
            }
        }
        sz--;
        return res;
    }

    public void put(String key, String val) {
        int ind = key.hashCode() % arr.length;
        Pair [] bucket = arr[ind];
        for (Pair p : bucket) {
            if (p.key.equals(key)) {
                p.val = val;
                return;
            }
        }
        sz++;
        arr[ind] = new Pair[bucket.length + 1];
        System.arraycopy(bucket, 0, arr[ind], 0, bucket.length);
        arr[ind][bucket.length] = new Pair(key, val);
        norm();
    }

    private  void norm() {
        if (sz > 5 * arr.length) {
            HashTable hashTable = new HashTable(nextPrime(arr.length * 5));
            for (Pair[] subarr : arr) {
                for (Pair p : subarr) {
                    hashTable.put(p.key, p.val);
                }
            }
            arr = hashTable.arr;
            sz = hashTable.sz;
        }
    }

    private int nextPrime(int x) {
        return BigInteger.valueOf(x).nextProbablePrime().intValue();
    }
}