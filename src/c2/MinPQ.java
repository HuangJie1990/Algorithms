package c2;

import edu.princeton.cs.algs4.Transaction;

public class MinPQ<Transantion> extends PriorityQueue<Transaction> {
    private Transaction[] a;
    private int N;

    @Override
    void insert(Transaction transaction) {
        if (transaction == null) throw new IllegalArgumentException();
        a[++N] = transaction;
        swim(N);
    }

    @Override
    void delMax() {
        throw new UnsupportedOperationException("delMax is unsupported");
    }

    @Override
    void delMin() {

    }

    @Override
    Transaction max() {
        throw new UnsupportedOperationException("max is unsupported");
    }

    @Override
    Transaction min() {
        return null;
    }

    @Override
    int size() {
        return N;
    }

    private void swim(int k) {
        while (k > 1 && Sort.greater(k / 2, k)) {
            Sort.exch(a, k / 2, k);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && Sort.greater(j, j + 1));
        }
    }
}
