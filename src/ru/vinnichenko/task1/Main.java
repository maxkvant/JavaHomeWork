package ru.vinnichenko.task1;
import ru.vinnichenko.task1.HashTable.*;

public class Main {
    public static void main(String [] args) {
        HashTable hashTable = new HashTable();

        System.out.print(String.valueOf("1".hashCode() % 7) + " " + String.valueOf("a".hashCode() % 7));

        hashTable.put("1", "2");
        hashTable.put("1", "3");
        hashTable.put("a", "b");

        System.out.println(hashTable.size());

        System.out.println(hashTable.get("1"));
        System.out.println(hashTable.get("b"));
        System.out.println(hashTable.get("a"));

        System.out.println(hashTable.remove("a"));
        System.out.println(hashTable.get("a"));
        System.out.println(hashTable.size());

        hashTable.clear();
        System.out.println(hashTable.get("1"));
        System.out.println(hashTable.size());
    }
}
