package c2;

public abstract class PriorityQueue<Key extends Comparable<Key>> {
    abstract void insert(Key key);

    abstract void delMax();

    abstract void delMin();

    abstract Key max();

    abstract Key min();

    abstract int size();
}
