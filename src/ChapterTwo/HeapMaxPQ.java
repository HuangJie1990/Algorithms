package ChapterTwo;

import java.util.Iterator;

public class HeapMaxPQ<Key extends Comparable<Key>> implements Iterable<Key> {

    private Key[] items;
    private int N;

    public HeapMaxPQ(int max) {
        items = (Key[]) new Comparable[max + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        if (N == items.length - 1) delMax();
        items[++N] = v;
        swim(N);
    }

    public Key delMax() throws ArrayIndexOutOfBoundsException{
        if(items[1]==null){
            throw new ArrayIndexOutOfBoundsException();
        }
        Key max = items[1];
        exchange(1, N--);
        items[N + 1] = null;
        sink(1);
        return max;
    }

    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    private void exchange(int i, int j) {
        Key temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exchange(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exchange(k, j);
            k = j;
        }
    }

    public Key max() {
        return items[1];
    }

    public int capacity() {
        return items.length - 1;
    }

    @Override
    public Iterator<Key> iterator() {
        return new ArrayIterator<Key>();
    }

    private class ArrayIterator<Key> implements Iterator<Key> {
        private int n = 1;

        @Override
        public boolean hasNext() {
            if (n >= items.length) return false;
            else return items[n] != null;
        }

        @Override
        public Key next() {
            return (Key) items[n++];
        }
    }
}
