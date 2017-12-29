package ChapterThree;

public abstract class ST<Key, Value> {

    abstract void put(Key key, Value value);

    abstract Value get(Key key);

    abstract void delete(Key key);

    abstract boolean contains(Key key);

    abstract boolean isEmpty();

    abstract int size();

    abstract Iterable<Key> keys();
}
