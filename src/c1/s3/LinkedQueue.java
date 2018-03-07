package c1.s3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<Item> extends Queue<Item> {
    private Node first;
    private Node last;

    @Override
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("argument to push(Item item) is null");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        n++;
    }

    @Override
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("queue is empty");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedQueueIterator();
    }

    private class Node {
        Item item;
        Node next;
    }

    private class LinkedQueueIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
