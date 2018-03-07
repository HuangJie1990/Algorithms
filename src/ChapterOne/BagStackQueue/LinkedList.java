package ChapterOne.BagStackQueue;

import java.util.Iterator;

public class LinkedList<Item> implements Iterable<Item> {

    private Node head;
    private int n;

    public Node head() {
        return head;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void add(Item data) {
        if (isEmpty()) head = new Node(data);
        else {
            Node last = head;
            while (last.next != null) last = last.next;
            last.next = new Node(data);
        }
        n++;
    }

    public void deleteLast() {
        Node tempHead = head;
        while (tempHead.next.next != null) tempHead = tempHead.next;
        tempHead.next = null;
        n--;
    }

    public void delete(int k) throws IllegalArgumentException {
        if (k < 1 || k > n) throw new IllegalArgumentException("k");
        else if (k == 1) head = head.next;
        else {
            Node preNode = head;
            while (k - 2 != 0) {
                preNode = preNode.next;
                k--;
            }
            preNode.next = preNode.next.next;
        }
        n--;
    }

    public boolean find(Item key) {
        Node tempHead = head;
        while (tempHead != null) {
            if (tempHead.data.equals(key)) return true;
            tempHead = tempHead.next;
        }
        return false;
    }

    public void remove(Node head, Item item) {
        if (head == null) return;
        Node p = head;
        Node pre = null;
        while (p != null) {
            if (p.data.equals(item)) this.head.next = this.head.next;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    private class Node {
        Item data;
        Node next;

        public Node() {
        }

        public Node(Item data) {
            this.data = data;
        }
    }

    private class LinkedListIterator implements Iterator<Item> {

        Node tempHead = head;

        @Override
        public boolean hasNext() {
            return tempHead != null;
        }

        @Override
        public Item next() {
            Item item = tempHead.data;
            tempHead = tempHead.next;
            return item;
        }
    }
}
