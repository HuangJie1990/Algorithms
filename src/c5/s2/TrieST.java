package c5.s2;

import c5.Alphabet;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class TrieST<Value> extends StringST<Value> {

    private static Alphabet alphabet = Alphabet.LOWERCASE;
    private static int R = alphabet.R();
    private Node root;
    private int N;

    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] a = in.readAllStrings();
        TrieST<Integer> st = new TrieST<>();
        for (int i = 0; i < 7; i++) {
            st.put(a[i], i);
        }
        StdOut.println(st.get("by"));
        for (String s : st.keys()) StdOut.println(s);
        StdOut.println("----------------------");
        for (String s : st.keysWithPrefix("sh")) StdOut.println(s);
        StdOut.println("----------------------");
        for (String s : st.keysThatMatch("shj")) StdOut.println(s);

        String pat = "ABABAC";
        Alphabet alphabet = Alphabet.UPPERCASE;
        int N = alphabet.R();
        int M = pat.length();
        int[][] dfa = new int[N][M];

        dfa[alphabet.charAt(pat, 0)][0] = 1;
        for (int X = 0, j = 1; j < M; j++) {
            for (int c = 0; c < N; c++) {
                dfa[c][j] = dfa[c][X];
            }
            dfa[alphabet.charAt(pat, j)][j] = j + 1;
            X = dfa[alphabet.charAt(pat, j)][X];
        }

        for (int[] ints : dfa) {
            for (int i : ints) {
                StdOut.print(i + " ");
            }
            StdOut.println();
        }
    }

    private int charAt(String s, int d) {
        if (d < s.length()) return alphabet.toIndex(s.charAt(d));
        return -1;
    }

    @Override
    void put(String key, Value value) {
        if (key == null) throw new IllegalArgumentException("Argument to put() is null");
        if (value == null) {
            delete(key);
            N--;
            return;
        }
        root = put(root, key, value, 0);
        N++;
    }

    private Node put(Node x, String key, Value value, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.value = value;
            return x;
        }
        int c = charAt(key, d);
        x.next[c] = put(x.next[c], key, value, d + 1);
        return x;
    }

    @Override
    Value get(String key) {
        if (key == null) throw new IllegalArgumentException("Argument to get() is null");
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.value;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        int c = charAt(key, d);
        return get(x.next[c], key, d + 1);
    }

    @Override
    void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) x.value = null;
        else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }
        if (x.value != null) return x;

        for (char c = 0; c < R; c++) {
            if (x.next[c] != null) return x;
        }
        return null;
    }

    @Override
    boolean contains(String key) {
        return get(key) != null;
    }

    @Override
    boolean isEmpty() {
        //return size(root) == 0;
        return size() == 0;
    }

    @Override
    String longestPrefixOf(String s) {
        return null;
    }

    @Override
    Iterable<String> keysWithPrefix(String prefix) {
        if (prefix == null) throw new IllegalArgumentException("Argument to keysWithPrefix is null");
        Queue<String> queue = new Queue<>();
        collect(get(root, prefix, 0), prefix, queue);
        return queue;
    }

    @Override
    Iterable<String> keysThatMatch(String pat) {
        Queue<String> queue = new Queue<>();
        collect(root, "", pat, queue);
        return queue;
    }

    private void collect(Node x, String prefix, String pat, Queue<String> queue) {
        int d = prefix.length();
        if (x == null) return;
        if (d == pat.length() && x.value != null) queue.enqueue(prefix);
        if (d == pat.length()) return;

        char next = pat.charAt(d);
        for (char c = 0; c < R; c++) {
            if (next == '.' || alphabet.toIndex(next) == c) {
                collect(x.next[c], prefix + alphabet.toChar(c), pat, queue);
            }
        }
    }

   /* private int size(Node x) {
        if (x == null) return 0;
        int count = 0;
        if (x.value != null) count++;
        for (char c = 0; c < 256; c++) {
            count += size(x.next[c]);
        }
        return count;
    }*/

    @Override
    int size() {
        //延时递归方法size()
        //return size(root);
        return N;
    }

    @Override
    Iterable<String> keys() {
        return keysWithPrefix("");
    }

    private void collect(Node x, String prefix, Queue<String> queue) {
        if (x == null) return;
        if (x.value != null) queue.enqueue(prefix);
        for (char c = 0; c < R; c++) {
            collect(x.next[c], prefix + alphabet.toChar(c), queue);
        }
    }

    private static class Node {
        private Object value;
        private Node[] next = new Node[R];
    }
}
