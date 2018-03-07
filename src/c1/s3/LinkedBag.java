package c1.s3;

import java.util.Iterator;

public class LinkedBag<Item> extends Bag<Item> {

    private Node first;

    public LinkedBag() {
        super();
    }

    @Override
    public void add(Item item) {
        if (item == null) throw new IllegalArgumentException("argument to add(Item item) is null");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedBagIterator();
    }

    private class Node {
        Item item;
        Node next;
    }

    private class LinkedBagIterator implements Iterator<Item> {
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
