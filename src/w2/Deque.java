package w2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int n;

    public Deque() {
    }

    ;

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("argument to addFirst is null");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty()) last=first;
        else {
            first.next = oldFirst;
            oldFirst.pre = first;
        }
        n++;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("argument to addLast is null");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) first = last;
        else {
            oldLast.next = last;
            last.pre = oldLast;
        }
        n++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("deque is empty");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;
        else first.pre = null;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("deque is empty");
        Item item = last.item;
        last = last.pre;
        n--;
        if (isEmpty()) first = null;
        else last.next = null;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class Node {
        Item item;
        Node pre;
        Node next;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if(current==null) throw new NoSuchElementException("there is no more items in deque");
            Item item=current.item;
            current=current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove is unsupported");
        }
    }

    public static void main(String[] args){

    }
}
