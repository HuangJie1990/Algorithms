package c1.s3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<Item> extends Stack<Item> {
    private static final int DEFAULT_CAPACITY = 1;
    private Item[] items;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayStack(int cap) {
        if (cap < 1) throw new IllegalArgumentException("cap must bigger than 0");
        items = (Item[]) new Object[cap];
    }


    @Override
    public void push(Item item) throws IllegalArgumentException {
        if (item == null) throw new IllegalArgumentException("argument to push(Item item) is null");
        if (n == items.length) resize(2 * items.length);
        items[n++] = item;
    }

    @Override
    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("stack is empty");
        Item item = items[--n];
        items[n] = null;
        if (n > 0 && n == items.length / 4) resize(items.length / 2);
        return item;
    }

    private void resize(int cap) {
        Item[] temps = (Item[]) new Object[cap];
        for (int i = 0; i < n; i++) {
            temps[i] = items[i];
        }
        items = temps;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayStackIterator();
    }

    private class ArrayStackIterator implements Iterator<Item> {
        private int i = n;

        @Override
        public boolean hasNext() {
            return n > 0;
        }

        @Override
        public Item next() {
            return items[--n];
        }
    }
}
