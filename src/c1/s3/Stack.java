package c1.s3;

public abstract class Stack<Item> implements Iterable<Item> {
    protected int n;

    public abstract void push(Item item) throws IllegalArgumentException;

    public abstract Item pop();

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }
}
