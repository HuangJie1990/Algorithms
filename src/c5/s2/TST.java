package c5.s2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 基于三项单词查找树的符号表
 *
 * @author huangjie
 * @create 2018-03-08-10:12
 **/
public class TST<Value> extends StringST<Value> {
    private Node<Value> root;
    private int N;

    @Override
    void put(String key, Value value) {
        if (key == null) throw new IllegalArgumentException("key argument to put is null");
        if (value == null) {
            delete(key);
        }
        if (!contains(key)) N++;
        root = put(root, key, value, 0);
    }

    private Node<Value> put(Node<Value> x, String key, Value value, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node<Value>();
            x.c = c;
        }
        if (c < x.c) x.left = put(x.left, key, value, d);
        else if (c > x.c) x.right = put(x.right, key, value, d);
        else if (d < key.length() - 1) x.mid = put(x.mid, key, value, d + 1);
        else x.value = value;
        return x;
    }

    @Override
    Value get(String key) {
        if (key == null) throw new IllegalArgumentException("argument to get is null");
        if (key.length() == 0) throw new IllegalArgumentException("key must has length >= 1");
        Node<Value> x = get(root, key, 0);
        if (x == null) return null;
        return x.value;
    }

    private Node<Value> get(Node<Value> x, String key, int d) {
        if (x == null) return null;
        if (key.length() == 0) throw new IllegalArgumentException("key must has length >= 1");
        char c = key.charAt(d);
        if (c < x.c) return get(x.left, key, d);
        else if (c > x.c) return get(x.right, key, d);
        else if (d < key.length() - 1) return get(x.mid, key, d + 1);
        else return x;
    }

    @Override
    void delete(String key) {
        if (key == null) throw new IllegalArgumentException("argument to delete is null");
        if (key.length() == 0) throw new IllegalArgumentException("key must has length >= 1");
        if (contains(key)) {
            N--;
            root = delete(root, key, 0);
        }
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        char c = key.charAt(d);
        if (c < x.c) x.left = delete(x.left, key, d);
        else if (c > x.c) x.right = delete(x.right, key, d);
        else if (d < key.length() - 1) x.mid = delete(x.mid, key, d + 1);
        else x.value = null;
        return x;
    }

    @Override
    boolean contains(String key) {
        if (key == null) throw new IllegalArgumentException("argument is null");
        return get(key) != null;
    }

    @Override
    boolean isEmpty() {
        return N == 0;
    }

    @Override
    String longestPrefixOf(String query) {
        if (query == null) throw new IllegalArgumentException("argument to longestPrefixOf() is null");
        if (query.length() == 0) return null;
        int length = 0;
        Node<Value> x = root;
        int i = 0;
        while (x != null && i < query.length()) {
            char c = query.charAt(i);
            if (c < x.c) x = x.left;
            else if (c > x.c) x = x.right;
            else {
                i++;
                if (x.value == null) length = i;
                x = x.mid;
            }
        }
        return query.substring(0, length);
    }

    @Override
    Iterable<String> keysWithPrefix(String prefix) {
        if (prefix == null) throw new IllegalArgumentException("prefix is null");
        Queue<String> queue = new Queue<>();
        Node<Value> x = get(root, prefix, 0);
        if (x == null) return null;
        if (x.value != null) queue.enqueue(prefix);
        collect(x.mid, new StringBuilder(prefix), queue);
        return queue;
    }

    private void collect(Node<Value> x, StringBuilder prefix, Queue<String> queue) {
        if (x == null) return;
        collect(x.left, prefix, queue);
        if (x.value != null) queue.enqueue(prefix.toString() + x.c);
        collect(x.mid, prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }

    @Override
    Iterable<String> keysThatMatch(String pattern) {
        Queue<String> queue = new Queue<>();
        collect(root, new StringBuilder(), 0, pattern, queue);
        return queue;
    }

    private void collect(Node<Value> x, StringBuilder prefix, int i, String pattern, Queue<String> queue) {
        if (x == null) return;
        char c = pattern.charAt(i);
        if (c == '.' || c < x.c) collect(x.left, prefix, i, pattern, queue);
        if (c == '.' || c == x.c) {
            if (i == pattern.length() - 1 && x.value != null) queue.enqueue(prefix.toString() + x.c);
            if (i < pattern.length() - 1) {
                collect(x.mid, prefix.append(x.c), i + 1, pattern, queue);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
        if (c == '.' || c > x.c) collect(x.right, prefix, i, pattern, queue);
    }

    @Override
    int size() {
        return N;
    }

    @Override
    Iterable<String> keys() {
        Queue<String> queue = new Queue<>();
        collect(root, new StringBuilder(), queue);
        return queue;
    }

    private class Node<Value> {
        char c;
        Node<Value> left, mid, right;
        Value value;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        TST<Integer> tst = new TST<>();
        int i = 0;
        while (!in.isEmpty()) {
            String s = in.readString();
            tst.put(s, i++);
        }
        for (String key :
                tst.keys()) {
            StdOut.print(key + ", ");
        }
        StdOut.println();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.startsWith("-")) {
                tst.delete(s.substring(1));
                StdOut.println("delete key " + s + ", the size is: " + tst.size());
            } else if (s.startsWith("+")) {
                tst.put(s.substring(1), i++);
                StdOut.println("put key " + s + ", the size is: " + tst.size());
            } else if (s.startsWith("*")) {
                for (String key : tst.keysWithPrefix(s.substring(1))) StdOut.print(key + ", ");
                StdOut.println();
            } else if (s.startsWith("/")) {
                for (String match : tst.keysThatMatch(s.substring(1))) StdOut.print(match + ", ");
                StdOut.println();
            } else if (s.startsWith(".")) {
                StdOut.println(tst.longestPrefixOf(s.substring(1)));
            } else StdOut.println(s + "'s value is: " + tst.get(s));
        }
    }
}
