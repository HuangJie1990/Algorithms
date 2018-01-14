package c2;

public abstract class PriorityQueue<Key extends Comparable<Key>> implements Iterable<Key> {
    abstract void insert(Key key);

    abstract Key delMax();

    abstract Key delMin();

    abstract Key max();

    abstract Key min();

    abstract int size();

    abstract boolean isEmpty();
}
