package ChapterTwo;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;

public class NoSortedArrayMaxPQ<Key extends Comparable<Key>> implements Iterable<Key> {

    private Key[] items;
    private int n;

    public NoSortedArrayMaxPQ() {
        items = (Key[]) new Comparable[1];
    }

    public NoSortedArrayMaxPQ(int max) {
        items = (Key[]) new Comparable[max];
    }

    public NoSortedArrayMaxPQ(Key[] a) {
        items = (Key[]) new Comparable[a.length];
        for (int i = 0; i < a.length; i++) {
            items[i] = a[i];
            n++;
        }
    }

    public void Insert(Key v) {
        push(v);
    }

    public Key max() {
        int max = n - 1;
        for (int i = 0; i < n - 1; i++) {
            if (less(items[max], items[i])) max = i;
        }
        return items[max];
    }

    public Key delMax() {
        int max = n - 1;
        for (int i = 0; i < n - 1; i++) {
            if (less(items[max], items[i])) max = i;
        }
        Key item=items[max];
        for (int i = max; i < n-1; i++) {
            items[i]=items[i+1];
        }
        items[n-1]=item;
        return pop();
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public int capacity() {
        return items.length;
    }

    private void resize(int cap) {
        Key[] temp = (Key[]) new Comparable[cap];
        for (int i = 0; i < size(); i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    private void push(Key v) {
        if (n == capacity()) resize(2 * capacity());
        items[n] = v;
        n++;
    }

    private Key pop() {
        n--;
        Key key = items[n];
        if (n > 0 && n == capacity() / 4) resize(capacity() / 2);
        return key;
    }

    private boolean less(Key a, Key b) {
        return a.compareTo(b) < 0;
    }

    private void exch(Key[] a, int i, int j) {
        Key temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    @Override
    public Iterator<Key> iterator() {
        return new ReverseIterator();
    }

    private class ReverseIterator implements Iterator<Key> {

        private int i = n;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Key next() {
            return items[--i];
        }
    }
}
