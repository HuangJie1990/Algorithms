package ChapterOne.BagStackQueue;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class ChainTableStack<Item> implements Iterable<Item> {

    private Node first;
    private int n;

    public static void main(String[] args) {

        ChainTableStack<Integer> chainTableStack = new ChainTableStack();
        for (int i = 0; i < 10; i++) {
            chainTableStack.push(i);
            StdOut.println(String.format("Push %d into stack, the size of stack is %d", i, chainTableStack.size()));
        }
        for (int i : chainTableStack) {
            StdOut.println(String.format("Display %d of stack, the size of stack is %d", i, chainTableStack.size()));
        }

//        chainTableStack.removeLast();

        chainTableStack.delete(5);
        StdOut.println();
        StdOut.println(chainTableStack.find(chainTableStack.first(), 5));
        StdOut.println();

        for (int i : chainTableStack) {
            StdOut.println(String.format("Display %d of stack, the size of stack is %d", i, chainTableStack.size()));
        }
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    public Node first() {
        return first;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    //E1.3.7
    public Item peek() {
        Item item = first.item;
        return item;
    }

    //1.3.19
    public void removeLast() {
        Node firstNode = first;
        while (firstNode.next.next != null) {
            firstNode = firstNode.next;
        }
        firstNode.next = null;
        n--;
    }

    //1.3.20
    //规定从第0个开始，k的有效值是0到n-1
    public void delete(int k) throws IllegalArgumentException {
        Node preNode;
        if (k > n - 1 || k < 0) {
            throw new IllegalArgumentException(String.format("k in %s.delete(k) is illegal", this.getClass()));
        } else if (k == 0) {
            first = first.next;
            n--;
        } else {
            preNode = first;
            while (k - 1 > 0) {
                preNode = preNode.next;
                k--;
            }
            preNode.next = preNode.next.next;
            n--;
        }
    }

    public boolean find(Node node, Item key) {
        for (Item item : this) {
            if (item.equals(key)) return true;
        }
        return false;
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

    private class Node {
        Item item;
        Node next;
    }

    private class ReverseIterator implements Iterator<Item> {

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
