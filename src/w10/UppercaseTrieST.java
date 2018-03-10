package w10;

/**
 * @author
 * @create 2018-03-10-17:44
 **/
public class UppercaseTrieST<Value> {

    private static final int R = 26;
    private int N;
    private Node root;

    public UppercaseTrieST() {

    }

    public boolean contains(String key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        if (key.length() == 0) throw new IllegalArgumentException("key must has length > 0");
        return get(key) != null;
    }

    public boolean containsPrefix(String prefix) {
        Node x = get(root, prefix, 0);
        if (x == null) return false;
        StringBuilder sb = new StringBuilder(prefix);
        boolean b = false;
        for (char c = 'A'; c <= 'Z'; c++) {
            collect(x.next[indexOf(c)], sb.append(c), b);
            if (b) break;
            sb.deleteCharAt(sb.length() - 1);
        }
        return b;
    }

    private void collect(Node x, StringBuilder prefix, boolean b) {
        if (x == null) {
            b = false;
            return;
        }
        if (x.value != null) {
            b = true;
            return;
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            collect(x.next[indexOf(c)], prefix.append(c), b);
        }
    }

    public void put(String key, Value value) {
        if (key == null) throw new IllegalArgumentException("argument to put() is null");
        if (key.length() == 0) throw new IllegalArgumentException("key must has length > 0");
        root = put(root, key, value, 0);
        N++;
    }

    private Node put(Node x, String key, Value value, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            if (x.value == null) N++;
            x.value = value;
            return x;
        }
        char c = key.charAt(d);
        x.next[indexOf(c)] = put(x.next[indexOf(c)], key, value, d + 1);
        return x;
    }

    public Value get(String key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        if (key.length() == 0) throw new IllegalArgumentException("key must has length > 0");
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.value;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[indexOf(c)], key, d + 1);
    }

    private static class Node {
        private Object value;
        private Node[] next = new Node[R];
    }

    private int indexOf(char c) {
        return c - 'A';
    }
}
