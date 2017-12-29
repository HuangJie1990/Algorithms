package c1.s3;

public abstract class Queue<Item> implements Iterable<Item> {
    protected int n;

    public abstract void enqueue(Item item);

    public abstract Item dequeue();

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }
}
