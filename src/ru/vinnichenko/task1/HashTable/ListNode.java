package ru.vinnichenko.task1.HashTable;

public class ListNode {
    private ListNode prev;
    private ListNode next;
    private String val;
    private String key;

    ListNode() {
        this.prev = this.next = this;
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
        if (cur != null) {
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
            return val;
        } else {
            ListNode newNode = new ListNode();
            newNode.key = key;
            newNode.val = val;

            prev.next = newNode;
            next.prev = newNode;
            newNode.next = next;
            newNode.prev = prev;
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