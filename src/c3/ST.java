package c3;

public abstract class ST<Key, Value> {

    abstract public void put(Key key, Value value);

    abstract public Value get(Key key);

    abstract public void delete(Key key);

    abstract public boolean contains(Key key);

    abstract public boolean isEmpty();

    abstract public int size();

    abstract public Iterable<Key> keys();
}
