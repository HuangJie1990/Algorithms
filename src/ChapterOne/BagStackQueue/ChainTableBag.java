package ChapterOne.BagStackQueue;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class ChainTableBag<Item> implements Iterable<Item> {

    private int n;
    private Node first;

    public static void main(String[] args) {

        ChainTableBag<Integer> chainTableBag = new ChainTableBag<>();
        for (int i = 0; i < 10; i++) {
            chainTableBag.add(i);
            StdOut.println(String.format("Add %d into bag, the size of bag is %d", i, chainTableBag.size()));
        }

        for (int i : chainTableBag) {
            StdOut.println(i);
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new BagIterator();
    }

    public void add(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private class Node {
        Item item;
        Node next;
    }

    private class BagIterator implements Iterator<Item> {
        Node firstNode = first;

        @Override
        public boolean hasNext() {
            return firstNode != null;
        }

        @Override
        public Item next() {
            Item item = firstNode.item;
            firstNode = firstNode.next;
            return item;
        }
    }
}
