package task7;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements set<E>, MyTreeSet<E>.
 * Implementation is based on not balanced binary tree.
 */

public class MyTreeSetImpl<E extends Comparable> extends AbstractSet<E> implements MyTreeSet<E> {
    @Nullable private Node<E> root = null;
    private int size = 0;
    private Comparator<E> comparator = null;

    public MyTreeSetImpl() {
        comparator = Comparable::compareTo;
    }

    public MyTreeSetImpl(Comparator<E> cmp) {
        comparator = cmp;
    }


    /**
     * adds val in set
     * if val absent returns true
     * otherwise false
     */
    @Override
    public boolean add(@NotNull E val) {
        boolean res;
        if (root == null) {
            root = new Node<E>(val);
            res = true;
        } else {
            res = add(root, val);
        }
        if (res) {
            size++;
        }
        return res;
    }

    /**
     * Checks is val present in this set
     */
    @Override
    public boolean contains(@NotNull Object val) {
        return contains(root, (E) val) != null;
    }

    /**
     * returns iterator over the elements this set in ascending order.
     */
    @Override
    public Iterator<E> iterator() {
        return new MyTreeSetIterator();
    }

    /**
     * returns how many elements set contains
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * returns an iterator over the elements in this set in ascending order.
     */
    public Iterator<E> descendingIterator() {
        return descendingSet().iterator();
    }

    @Override
    public MyTreeSet<E> descendingSet() {
        MyTreeSetImpl<E> res = new MyTreeSetImpl<E>()  {
            @Override
            protected boolean getReversed() {
                return !MyTreeSetImpl.this.getReversed();
            }

            private Comparator<E> comparator = (e, t1) -> -MyTreeSetImpl.this.getComparator().compare(e, t1);
            @Override
            protected Comparator<E> getComparator() {
                return comparator;
            }
        };
        res.root = root;
        res.size = size;
        return res;
    }

    /**
     * returns the smallest element in this set
     */
    @Override
    public E first() {
        Node<E> node = root;
        if (node == null) {
            return null;
        }
        while (getL(node) != null) {
            node = getL(node);
        }
        return node.value;
    }

    /**
     * returns the biggest element in this set
     */
    @Override
    public E last() {
        return descendingSet().first();
    }

    /**
     * returns the greatest element in this set less than or equal to the given element, or null if there is no such element.
     */
    @Override
    public E lower(@NotNull E val) {
        return descendingSet().higher(val);
    }

    /**
     * returns the greatest element in this set strictly less than the given element, or null if there is no such element.
     */
    @Override
    public E floor(@NotNull E val) {
        return descendingSet().ceiling(val);
    }

    /**
     * returns the least element in this set greater than or equal to the given element, or null if there is no such element.
     */
    @Override
    public E ceiling(@NotNull E val) {
        Node<E> res = ceiling(root, val, false);
        return (root == null || res == null) ? null : res.value;
    }

    /**
     * returns the least element in this set strictly greater than the given element, or null if there is no such element.
     */
    @Override
    public E higher(@NotNull E val) {
        Node<E> res = ceiling(root, val, true);
        return (res == null) ? null : res.value;
    }

    /**
     * if val1 present removes val1 from set and returns true,
     * otherwise returns false
     */

    @Override
    public boolean remove(@NotNull Object val1) {
        E val = (E)val1;
        Node<E> cur = contains(root, val);
        if (cur == null) {
            return false;
        }
        if (getL(cur) == null && getR(cur) == null) {
            if (cur.parent == null) {
                root = null;
            } else if (getL(cur.parent) == cur) {
                setL(cur.parent, null);
            } else {
                setR(cur.parent, null);
            }
        } else if ((getL(cur) == null) != (getR(cur) == null)) {
            Node<E> child = getL(cur) != null ? getL(cur) : getR(cur);
            if (cur.parent == null) {
                root = child;
                root.parent = null;
            } else if (getL(cur.parent) == cur) {
                setL(cur.parent, child);
            } else {
                setR(cur.parent, child);
            }
        } else {
            Node<E> next = ceiling(cur, val, true);
            remove(next.value);
            cur.value = next.value;
            size++;
        }
        size--;
        return true;
    }

    protected boolean getReversed() {
        return false;
    }
    protected Comparator<E> getComparator() {
        return comparator;
    }

    @Nullable
    private Node<E> getL(@NotNull Node<E> vertex) {
        return !getReversed() ? vertex.L : vertex.R;
    }

    @Nullable
    private Node<E> getR(@NotNull Node<E> vertex) {
        return !getReversed() ? vertex.R : vertex.L;
    }


    private void setL(@NotNull Node vertex, @Nullable Node node) {
        if (!getReversed()) {
            vertex.L = node;
        } else {
            vertex.R = node;
        }
        if (node != null) {
            node.parent = vertex;
        }
    }

    private void setR(@NotNull Node vertex, @Nullable Node node) {
        if (!getReversed()) {
            vertex.R = node;
        } else {
            vertex.L = node;
        }
        if (node != null) {
            node.parent = vertex;
        }
    }


    @Nullable
    private Node<E> contains(@Nullable Node<E> node, @NotNull E val) {
        if (node == null) {
            return null;
        }
        int cmp = getComparator().compare(val, node.value);
        if (cmp == 0) {
            return node;
        }
        if (cmp < 0) {
            return contains(getL(node), val);
        }
        if (cmp > 0) {
            return contains(getR(node), val);
        }
        return null;
    }

    private boolean add(@NotNull Node<E> node, @NotNull E val) {
        int cmp = getComparator().compare(val, node.value);
        if (cmp == 0) {
            return false;
        }
        if (cmp < 0) {
            if (getL(node) != null) {
                return add(getL(node), val);
            } else {
                setL(node, new Node<E>(val));
                return true;
            }
        }
        if (cmp > 0) {
            if (getR(node) != null) {
                return add(getR(node), val);
            } else {
                setR(node, new Node<E>(val));
                return true;
            }
        }
        return false;
    }

    @Nullable
    private Node<E> ceiling(@Nullable Node<E> node, @NotNull E val, boolean strictly) {
        if (node == null) {
            return null;
        }
        int cmp = getComparator().compare(val, node.value);
        if (cmp > 0 || (cmp == 0 && strictly)) {
            return ceiling(getR(node), val, strictly);
        }
        if (cmp < 0 || (cmp == 0 && !strictly)) {
            Node<E> res = ceiling(getL(node), val, strictly);
            return res == null ? node : res;
        }
        return null;
    }

    private static class Node<E> {
        @NotNull private E value;
        @Nullable private Node<E> parent = null;
        @Nullable private Node<E> L = null;
        @Nullable private Node<E> R = null;
        private Node(@NotNull E val) {
            this.value = val;
        }
    }

    private class MyTreeSetIterator implements Iterator<E> {
        @Nullable private Node<E> cur;
        private MyTreeSetIterator() {
            cur = root == null ? null : contains(root, MyTreeSetImpl.this.first());
        }

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public E next() {
            if (cur == null) {
                throw new NoSuchElementException();
            }
            E res = cur.value;
            if (getR(cur) != null) {
                cur = MyTreeSetImpl.this.ceiling(cur, cur.value, true);
            } else {
                while (cur.parent != null && getR(cur.parent) == cur) {
                    cur = cur.parent;
                }
                cur = cur.parent;
            }
            return res;
        }
    }
}