package w2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private Item[] items;

    public RandomizedQueue() {
        items = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("argument to enqueue is null");
        if (n == items.length) resize(2 * items.length);
        items[n++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("queue is empty");
        int index = StdRandom.uniform(n);
        Item item = items[index];
        items[index] = items[--n];
        items[n] = null;
        if (n > 0 && n == items.length / 4) resize(items.length / 2);
        return item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("queue is empty");
        int index = StdRandom.uniform(n);
        return items[index];
    }


    @Override
    public Iterator<Item> iterator() {
        return new RandomizeIterator();
    }

    private class Node {
        Item item;
    }

    private void resize(int cap) {
        Item[] temp = (Item[]) new Object[cap];
        for (int i = 0; i < n; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    private class RandomizeIterator implements Iterator<Item> {
        private int current;
        private Item[] aux;

        public RandomizeIterator() {
            aux = (Item[]) new Object[n];
            for (int i = 0; i < n; i++) {
                aux[i] = items[i];
            }
            StdRandom.shuffle(aux);
        }

        @Override
        public boolean hasNext() {
            return current < aux.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("there is no more items");
            Item item = aux[current];
            current++;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove is unsupported");
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();

            if (item.equals("-") && !queue.isEmpty()) {
                StdOut.print(queue.dequeue() + " ");
            } else if (item.equals("--") && !queue.isEmpty()) {
                StdOut.print(queue.sample() + " ");
            } else if (item.equals("show")) {
                for (String s : queue) {
                    StdOut.println(s);
                }
            } else if (item.equals("size")) {
                StdOut.println("(" + queue.size() + " left on stack)");
            } else if (item.equals("exit")) {
                break;
            } else {
                queue.enqueue(item);
            }
        }

        StdOut.println("(" + queue.size() + " left on stack)");
    }
}
