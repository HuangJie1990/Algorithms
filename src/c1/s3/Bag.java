package c1.s3;

public abstract class Bag<Item> implements Iterable<Item> {
    protected int n;

    public Bag() {
    }

    public abstract void add(Item item) throws IllegalArgumentException;

    public abstract boolean isEmpty();

    public int size() {
        return n;
    }
}
