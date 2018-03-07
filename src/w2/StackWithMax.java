package w2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author
 * @create 2018-01-02-16:53
 **/
public class StackWithMax<Item extends Comparable<Item>> implements Iterable<Item> {

    private static final int DEFAULT_CAPACITY = 2;
    private Item[] items;
    private Item[] max;
    private int N;

    public StackWithMax() {
        this(DEFAULT_CAPACITY);
    }

    public StackWithMax(int n) {
        items = (Item[]) new Comparable[n];
        max = (Item[]) new Comparable[n];
    }

    public static void main(String[] args) {
        StackWithMax<Integer> stack = new StackWithMax<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.println(stack.pop());
            else if (s.equals("show")) {
                for (int i : stack
                        ) {
                    StdOut.print(i + " ");
                }
                StdOut.println();
            } else if (s.equals("max")) StdOut.println(stack.max());
            else if (s.equals("size")) StdOut.println(stack.size() + " items left on stack");
            else stack.push(Integer.parseInt(s));
        }
        StdOut.println(stack.size() + " items left on stack");
    }

    private void resize(int cap) {
        Item[] itemp = (Item[]) new Comparable[cap];
        Item[] mtemp = (Item[]) new Comparable[cap];
        for (int i = 0; i < N; i++) {
            itemp[i] = items[i];
            mtemp[i] = max[i];
        }
        items = itemp;
        max = mtemp;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void push(Item item) {
        if (item == null) throw new IllegalArgumentException("argument to push is null");
        if (isEmpty()) {
            items[N] = item;
            max[N] = item;
            N++;
        } else {
            if (N == items.length)
                resize(2 * items.length);
            items[N] = item;
            max[N] = larger(max[N - 1], item);
            N++;
        }
    }

    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("stack is empty");
        Item item = items[--N];
        items[N] = null;
        max[N] = null;
        return item;
    }

    public Item max() {
        if (isEmpty()) throw new NoSuchElementException("stack is empty");
        return max[N - 1];
    }

    private Item larger(Item i, Item j) {
        int cmp = i.compareTo(j);
        if (cmp < 0) return j;
        return i;
    }

    @Override
    public Iterator<Item> iterator() {
        return new StackIterator<>();
    }

    private class StackIterator<Item> implements Iterator<Item> {
        private int current = N;

        @Override
        public boolean hasNext() {
            return current > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("there is no more items");
            return (Item) items[--current];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove is unsupported");
        }
    }
}
