package task5;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Simple implementation of generic set.
 * Data structure: unbalanced binary tree.
 */

public class SetTree<T extends Comparable> {
    private @Nullable Node root;
    private int size;

    public SetTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * adds in set value, if absent.
     */

    public void add(@NotNull T value) {
        if (contains(value)) {
            return;
        }
        size++;
        if (root == null) {
            root = new Node<T>(value);
            return;
        }
        root.add(value);
    }

    /**
     * If value is present returns true.
     * otherwise returns false.
     */

    public boolean contains(@NotNull T value) {
        return get(value, root) != null;
    }

    /**
     * Returns how many values this contains.
     */

    public int size() {
        return size;
    }

    private @Nullable Node<T> get(@NotNull T val, @Nullable Node<T> v) {
        if (v == null) {
            return null;
        }
        int cmp = val.compareTo(v.value);
        if (cmp > 0) {
            return get(val, v.right);
        }
        if (cmp < 0) {
            return get(val, v.left);
        }
        return v;
    }

    private static class Node<T extends Comparable> {
        private @NotNull T value;
        private @Nullable Node<T> left;
        private @Nullable Node<T> right;

        private Node(@NotNull T value) {
            this.value = value;
            left = null;
            right = null;
        }

        private void add(@NotNull T val) {
            int cmp = val.compareTo(value);
            if (cmp > 0) {
                if (right == null) {
                    right = new Node<T>(val);
                } else {
                    right.add(val);
                }
            } else {
                if (left == null) {
                    left = new Node<T>(val);
                } else {
                    left.add(val);
                }
            }
        }
    }
}
