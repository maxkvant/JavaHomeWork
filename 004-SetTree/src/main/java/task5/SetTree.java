package task5;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators;

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
            return get(val, v.R);
        }
        if (cmp < 0) {
            return get(val, v.L);
        }
        return v;
    }

    private static class Node<T extends Comparable> {
        private @NotNull T value;
        private @Nullable Node<T> L;
        private @Nullable Node<T> R;
        private Node(@NotNull T value) {
            this.value = value;
            L = null;
            R = null;
        }
        private void add(@NotNull T val) {
            int cmp = val.compareTo(value);
            if (cmp > 0) {
                if (R == null) {
                    R = new Node<T>(val);
                } else {
                    R.add(val);
                }
            } else {
                if (L == null) {
                    L = new Node<T>(val);
                } else {
                    L.add(val);
                }
            }
        }
    };
}
