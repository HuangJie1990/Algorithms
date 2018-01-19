package c3;

public abstract class SortST<Key extends Comparable<Key>, Value> extends ST<Key, Value> {

    abstract public Key min();

    abstract public Key max();

    abstract public Key floor(Key key);

    abstract public Key ceiling(Key key);

    abstract public int rank(Key key);

    abstract public Key select(int k);

    abstract public void deleteMin();

    abstract public void deleteMax();

    abstract public int size(Key lo, Key hi);

    abstract public Iterable<Key> keys(Key lo, Key hi);
}
