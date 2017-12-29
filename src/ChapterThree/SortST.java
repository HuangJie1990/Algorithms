package ChapterThree;

public abstract class SortST<Key extends Comparable<Key>, Value> extends ST<Key,Value> {

    abstract Key min();

    abstract Key max();

    abstract Key floor(Key key);

    abstract Key ceiling(Key key);

    abstract int rank(Key key);

    abstract Key select(int k);

    abstract void deleteMin();

    abstract void deleteMax();

    abstract int size(Key lo, Key hi);

    abstract Iterable<Key> keys(Key lo, Key hi);
}
