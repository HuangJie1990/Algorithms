package ChapterOne.BagStackQueue;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item> {

    private Item[] items;
    private int n;

    public ResizingArrayStack() {
        items = (Item[]) new Object[2];
    }

    public static void main(String[] args) {
        ResizingArrayStack<Integer> resizingArrayStack = new ResizingArrayStack<>();
        for (int i = 0; i < 17; i++) {
            resizingArrayStack.push(i);
            StdOut.println(String.format("Push %d into stack, the size of stack is %d, the capacity of stack is %d", i, resizingArrayStack.size(), resizingArrayStack.capacity()));
        }

        for (int i : resizingArrayStack) {
            StdOut.println(i);
        }

        while (!resizingArrayStack.isEmpty()) {
            int i = resizingArrayStack.pop();
            StdOut.println(String.format("Pop %d out stack, the size of stack is %d, the capacity of stack is %d", i, resizingArrayStack.size(), resizingArrayStack.capacity()));
        }
    }

    private void resize(int cap) {
        Item[] temp = (Item[]) new Object[cap];
        for (int i = 0; i < n; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    public void push(Item item) {
        if (n == items.length) {
            resize(2 * items.length);
        }
        items[n++] = item;
    }

    public Item pop() {
        Item item = items[--n];
        if (n > 0 && n == items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    public int capacity() {
        return items.length;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseIterator();
    }

    private class ReverseIterator implements Iterator<Item> {

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
