package c1.s3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<Item> extends Stack<Item> {
    private Node first;
    private Node last;

    @Override
    public void push(Item item) throws IllegalArgumentException {
        if (item == null) throw new IllegalArgumentException("argument to push(Item item) is null");
        Node oldFirst = first;
        first = new Node();
        first.item=item;
        if(isEmpty()) last=first;
        else first.next=oldFirst;
        n++;
    }

    @Override
    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("stack is empty");
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    private class Node {
        Item item;
        Node next;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedStackIterator();
    }

    private class LinkedStackIterator implements Iterator<Item> {
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
