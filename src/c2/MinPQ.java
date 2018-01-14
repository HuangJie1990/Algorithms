package c2;

import java.util.Iterator;

public class MinPQ<Key extends Comparable<Key>> extends PriorityQueue<Key> {
    private Key[] a;
    private int N;

    public MinPQ() {
        this(1);
    }

    public MinPQ(int n) {
        super();
        a = (Key[]) new Comparable[n + 1];
    }

    @Override
    void insert(Key key) {
        if (N + 1 == a.length) resize(2 * a.length);
        a[++N] = key;
        swim(N);
        assert isHeadSorted();
    }

    @Override
    Key delMax() {
        throw new UnsupportedOperationException("delMax is unsupported");
    }

    @Override
    Key delMin() {
        if (isEmpty()) return null;
        Key key = a[1];
        a[1] = a[N];
        a[N--] = null;
        if (N + 1 == a.length / 4) resize(a.length / 2);
        sink(1);
        assert isHeadSorted();
        return key;
    }

    @Override
    Key max() {
        throw new UnsupportedOperationException("max is unsupported");
    }

    @Override
    Key min() {
        if (isEmpty())
            return null;
        return a[1];
    }

    @Override
    int size() {
        return N;
    }

    @Override
    boolean isEmpty() {
        return N == 0;
    }

    private void swim(int k) {
        while (k > 1 && less(k, k / 2)) {
            Sort.exch(a, k, k / 2);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (k <= N / 2) {
            int j = 2 * k;
            if (j < N && less(j + 1, j)) j++;
            if (!less(j, k)) break;
            Sort.exch(a, k, j);
            k = j;
        }
    }

    private boolean less(int v, int w) {
        return Sort.less(a[v], a[w]);
    }

    private void resize(int capacity) {
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 0; i <= N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    @Override
    public Iterator<Key> iterator() {
        return new pqIterator();
    }

    private boolean isHeadSorted() {
        for (int i = 1; i <= N / 2; i++) {
            for (int j = 2 * i; j <= 2 * i + 1 && j <= N; j++) {
                if (less(j, i)) return false;
            }
        }
        return true;
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
            throw new UnsupportedOperationException("remove is unsupported");
        }
    }
}
