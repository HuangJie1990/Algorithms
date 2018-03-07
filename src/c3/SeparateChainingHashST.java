package c3;

import edu.princeton.cs.algs4.Queue;

public class SeparateChainingHashST<Key, Value> {

    private int N;
    private int M;
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < st.length; i++) {
            st[i] = new SequentialSearchST<Key, Value>();
        }
    }

    public SeparateChainingHashST() {
        this(997);
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        st[hash(key)].put(key, value);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (SequentialSearchST<Key, Value> sequentialSearchST : st) {
            for (Key key : sequentialSearchST.keys()) {
                queue.enqueue(key);
            }
        }
        return queue;
    }
}
