package c2;

import java.util.Iterator;

/**
 * @author
 * @create 2018-01-14-18:22
 **/
public class MaxPQ<Key extends Comparable<Key>> extends PriorityQueue<Key> {
    private Key[] a;
    private int N;

    public MaxPQ() {
        this(1);
    }

    public MaxPQ(int n) {
        super();
        a = (Key[]) new Comparable[n + 1];
    }

    @Override
    void insert(Key key) {
        if (N + 1 == a.length) resize(2 * a.length);
        a[++N] = key;
        swim(N);
    }

    @Override
    Key delMax() {
        if (isEmpty())
            return null;
        Key key = a[1];
        a[1] = a[N];
        a[N--] = null;
        if (N + 1 == a.length / 4) resize(a.length / 2);
        sink(1);
        return key;
    }

    @Override
    Key delMin() {
        throw new UnsupportedOperationException("delMin is unsupported");
    }

    @Override
    Key max() {
        if (isEmpty())
            return null;
        return a[1];
    }

    @Override
    Key min() {
        throw new UnsupportedOperationException("min is unsupported");
    }

    @Override
    int size() {
        return N;
    }

    @Override
    boolean isEmpty() {
        return N == 0;
    }

    @Override
    public Iterator<Key> iterator() {
        return new pqIterator();
    }

    private void sink(int k) {
        while (k <= N / 2) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            Sort.exch(a, k, j);
            k = j;
        }
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            Sort.exch(a, k / 2, k);
            k /= 2;
        }
    }

    private void resize(int capacity) {
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 0; i <= N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    private boolean less(int v, int w) {
        return Sort.less(a[v], a[w]);
    }

    private class pqIterator implements Iterator<Key> {
        private int current = 1;

        @Override
        public boolean hasNext() {
            return current <= N;
        }

        @Override
        public Key next() {
            return a[current++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove is umsupported");
        }
    }
}
