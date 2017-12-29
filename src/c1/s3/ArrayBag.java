package c1.s3;

import java.util.Iterator;

public class ArrayBag<Item> extends Bag<Item> {
    private Item[] items;
    private static final int DEFAULT_CAPACITY = 1;

    public ArrayBag() {
        super();
        items = (Item[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayBag(int cap) {
        super();
        if (cap < 1) throw new IllegalArgumentException("cap must bigger than 0.");
        items = (Item[]) new Object[cap];
    }

    @Override
    public void add(Item item) {
        if (item == null) throw new IllegalArgumentException("argument to add(Item item) is null");
        if (n == items.length) resize(2 * items.length);
        items[n++] = item;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayBagIterator();
    }

    private void resize(int cap) {
        Item[] temps = (Item[]) new Object[cap];
        for (int i = 0; i < n; i++) {
            temps[i] = items[i];
        }
        items = temps;
    }

    private class ArrayBagIterator implements Iterator<Item> {
        private int i = n;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return items[--i];
        }
    }
}
