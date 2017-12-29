package ChapterThree;

import java.util.NoSuchElementException;

public class RedBlackBST<Key extends Comparable<Key>, Value> extends SortST<Key, Value> {

    private Node root;

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        Key key;
        Value value;
        Node left, right;
        int N;
        boolean color;

        public Node(Key key, Value value, int n, boolean color) {
            this.key = key;
            this.value = value;
            N = n;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color;
    }

    private Node rotateLeft(Node h) {
        if (h == null) return null;
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateRight(Node h) {
        if (h == null) return null;
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    //Complete
    @Override
    Key min() {
        if (root == null) throw new NoSuchElementException("called min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node h) {
        return h.left == null ? h : h.left;
    }

    //Complete
    @Override
    Key max() {
        if (root == null) throw new NoSuchElementException("called max() with empty symbol table");
        return max(root).key;
    }

    //Complete
    private Node max(Node h) {
        return h.right == null ? h : h.right;
    }

    //Complete
    @Override
    Key floor(Key key) {
        if (root == null) throw new NoSuchElementException("called floor() on empty symbol table");
        if (key == null) throw new IllegalArgumentException("argument to floor is null");
        Node t = floor(root, key);
        return t == null ? null : t.key;
    }

    //Complete
    private Node floor(Node h, Key key) {
        if (h == null) return null;
        int cmp = key.compareTo(h.key);
        if (cmp == 0) return h;
        if (cmp < 0) return floor(h.left, key);
        Node t = floor(h.right, key);
        return t != null ? t : h;
    }

    //Complete
    @Override
    Key ceiling(Key key) {
        if (root == null) throw new NoSuchElementException("called ceiling() on empty symbol table");
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        Node t = ceiling(root, key);
        return t == null ? null : t.key;
    }

    //Complete
    private Node ceiling(Node h, Key key) {
        if (h == null) return null;
        int cmp = key.compareTo(h.key);
        if (cmp == 0) return h;
        else if (cmp > 0) return ceiling(h.right, key);
        else {
            Node t = floor(h.left, key);
            return t != null ? t : h;
        }
    }

    //Complete
    @Override
    int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(root, key);
    }

    //Complete
    private int rank(Node h, Key key) {
        if (h == null) return 0;
        int cmp = key.compareTo(h.key);
        if (cmp < 0) return rank(h.left, key);
        else if (cmp > 0) return 1 + size(h.left) + rank(h.right, key);
        return size(h.left);
    }

    @Override
    Key select(int k) {
        return null;
    }

    private Node select(Node h, int k) {
        if (h == null) return null;
        int t = size(h.left);
        if (t > k) return select(h.left, k);
        else if (t < k) return select(h.right, k - t - 1);
        else return h;
    }

    @Override
    void deleteMin() {

    }

    @Override
    void deleteMax() {

    }

    //Complete
    @Override
    int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        return rank(hi) - rank(lo);
    }

    @Override
    Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    //Complete
    @Override
    void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("first argument to put is null");
        if (value == null) {
            delete(key);
            return;
        }
        root = put(root, key, value);
        root.color = BLACK;
    }

    //Complete
    private Node put(Node h, Key key, Value value) {
        if (h == null) return new Node(key, value, 1, RED);
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, value);
        else if (cmp > 0) h.right = put(h.left, key, value);
        else h.value = value;
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    //Complete
    @Override
    Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    //Complete
    private Value get(Node h, Key key) {
        if (h == null) return null;
        int cmp = key.compareTo(h.key);
        if (cmp < 0) return get(h.left, key);
        else if (cmp > 0) return get(h.right, key);
        else return h.value;
    }

    @Override
    void delete(Key key) {

    }

    //Complete
    @Override
    boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    //Complete
    @Override
    boolean isEmpty() {
        return root == null;
    }

    //Complete
    @Override
    int size() {
        return size(root);
    }

    //Complete
    private int size(Node x) {
        return x == null ? 0 : x.N;
    }

    @Override
    Iterable<Key> keys() {
        return null;
    }
}
