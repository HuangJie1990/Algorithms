package c3;

import edu.princeton.cs.algs4.Queue;

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
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        assert isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        assert isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        assert !isRed(h);
        assert isRed(h.left);
        assert isRed(h.right);
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = RED;
    }

    //Same as BST
    @Override
    public Key min() {
        if (root == null) throw new NoSuchElementException("called min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node h) {
        return h.left == null ? h : min(h.left);
    }

    //Same as BST
    @Override
    public Key max() {
        if (root == null) throw new NoSuchElementException("called max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node h) {
        return h.right == null ? h : max(h.right);
    }

    //Same as BST
    @Override
    public Key floor(Key key) {
        if (root == null) throw new NoSuchElementException("called floor() on empty symbol table");
        if (key == null) throw new IllegalArgumentException("argument to floor is null");
        Node t = floor(root, key);
        return t == null ? null : t.key;
    }

    //Same as BST
    private Node floor(Node h, Key key) {
        if (h == null) return null;
        int cmp = key.compareTo(h.key);
        if (cmp == 0) return h;
        if (cmp < 0) return floor(h.left, key);
        Node t = floor(h.right, key);
        return t != null ? t : h;
    }

    //Same as BST
    @Override
    public Key ceiling(Key key) {
        if (root == null) throw new NoSuchElementException("called ceiling() on empty symbol table");
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        Node t = ceiling(root, key);
        return t == null ? null : t.key;
    }

    //Same as BST
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

    //Same as BST
    @Override
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(root, key);
    }

    //Same as BST
    private int rank(Node h, Key key) {
        if (h == null) return 0;
        int cmp = key.compareTo(h.key);
        if (cmp < 0) return rank(h.left, key);
        else if (cmp > 0) return size(h.left) + 1 + rank(h.left, key);
        else return size(h.left);
    }

    //Same as BST
    @Override
    public Key select(int k) {
        Node t = select(root, k);
        return t != null ? t.key : null;
    }

    //Same as BST
    private Node select(Node h, int k) {
        if (h == null) return null;
        int t = size(h.left);
        if (t > k) return select(h.left, k);
        else if (t < k) return select(h.right, k - t - 1);
        else return h;
    }

    @Override
    public void deleteMin() {

    }

    @Override
    public void deleteMax() {

    }

    //Same as BST
    @Override
    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        return rank(hi) - rank(lo);
    }

    //Same as BST
    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    //Same as BST
    private void keys(Node h, Queue<Key> queue, Key lo, Key hi) {
        if (h == null) return;
        int cmplo = lo.compareTo(h.key);
        int cmphi = hi.compareTo(h.key);
        if (cmplo < 0) keys(h.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(h.key);
        if (cmphi > 0) keys(h.right, queue, lo, hi);
    }

    //Complete
    @Override
    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("first argument to put is null");

    }

    //Complete
    private Node put(Node h, Key key, Value value) {

    }

    //Same as BST
    @Override
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    //Same as BST
    private Value get(Node h, Key key) {
        if (h == null) return null;
        int cmp = key.compareTo(h.key);
        if (cmp < 0) return get(h.left, key);
        else if (cmp > 0) return get(h.right, key);
        else return h.value;
    }

    @Override
    public void delete(Key key) {

    }

    //Same as BST
    @Override
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    //Same as BST
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    //Same as BST
    @Override
    public int size() {
        return size(root);
    }

    //Same as BST
    private int size(Node x) {
        return x == null ? 0 : x.N;
    }

    //Same as BST
    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }
}
