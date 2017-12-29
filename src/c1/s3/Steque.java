package c1.s3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Steque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int n;

    public Steque() {
    }

    public void push(Item item) {
        if (item == null) throw new IllegalArgumentException("argument to enqueue(Item item) is null");
        Node oldfirst = first;
        first = new Node(item, oldfirst);
        if (isEmpty()) last = first;
        n++;
    }

    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("stack is empty");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;
        return item;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("argument to push(Item item) is null");
        Node oldlast = last;
        last = new Node(item, null);
        if (isEmpty()) first = last;
        else oldlast.next = last;
        n++;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new StequeIterator();
    }

    private class Node {
        Item item;
        Node next;

        public Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    private class StequeIterator implements Iterator<Item> {
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

    public static void main(String[] args) {
        Steque<Character> steque = new Steque<>();
        while (!StdIn.isEmpty()) {
            Character character = StdIn.readChar();
            if (Character.isUpperCase(character)) steque.push(character);
            else if (Character.isLowerCase(character)) steque.enqueue(character);
            else if (character.equals('-')) StdOut.print(steque.pop() + " ");
        }
        StdOut.println("(" + steque.size() + " left on stack)");
        for (Character s : steque) {
            StdOut.print(s + " ");
        }
    }
}
