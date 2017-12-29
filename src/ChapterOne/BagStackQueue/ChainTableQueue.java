package ChapterOne.BagStackQueue;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class ChainTableQueue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int n;

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        n++;
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        n--;
        return item;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class Node {
        Item item;
        Node next;
    }

    private class QueueIterator implements Iterator<Item> {

        @Override
        public boolean hasNext() {
            return first != null;
        }

        @Override
        public Item next() {
            Item item = first.item;
            first = first.next;
            return item;
        }
    }

    public static void main(String[] args) {

        ChainTableQueue<Integer> chainTableQueue = new ChainTableQueue<>();
        for (int i = 0; i < 10; i++) {
            chainTableQueue.enqueue(i);
            StdOut.println(String.format("Enqueue %d into queue, the size of queue is %d", i, chainTableQueue.size()));
        }

        for (int i : chainTableQueue) {
            StdOut.println(i);
        }

        while (chainTableQueue.isEmpty()){

        }
    }
}
