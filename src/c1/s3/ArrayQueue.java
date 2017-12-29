package c1.s3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<Item> extends Queue<Item> {
    private Item[] items;
    private static final int DEFAULT_CAPACITY = 1;

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayQueue(int cap) {
        if (cap < 1) throw new IllegalArgumentException("cap must bigger than 0");
        items = (Item[]) new Object[cap];
    }

    @Override
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("argument to push(Item item) is null");
        if (n == items.length) resize(2 * items.length);
        items[n++] = item;
    }

    @Override
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("queue is empty");
        Item item = items[0];
        for (int i = 0; i < n - 1; i++) {
            items[i] = items[i + 1];
        }
        items[--n] = null;
        if (n > 0 && n == items.length / 4) resize(items.length / 2);
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayQueueIterator();
    }

    private void resize(int cap) {
        Item[] temps = (Item[]) new Object[cap];
        for (int i = 0; i < n; i++) {
            temps[i] = items[i];
        }
        items = temps;
    }

    private class ArrayQueueIterator implements Iterator<Item> {
        private int N = n;
        private int i;

        @Override
        public boolean hasNext() {
            return i < N;
        }

        @Override
        public Item next() {
            return items[i++];
        }
    }
}
