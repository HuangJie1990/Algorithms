package w2;

import java.util.Iterator;

/**
 * @author
 * @create 2018-01-02-16:53
 **/
public class StackWithMax<Item extends Comparable<Item>> implements Iterable<Item> {

    private static final int DEFAULT_CAPACITY = 2;
    private Item[] items;
    private Item[] max;
    private int N;

    public StackWithMax() {
        this(DEFAULT_CAPACITY);
    }

    public StackWithMax(int n) {
        items = (Item[]) new Object[n];
        max = (Item[]) new Object[n];
    }

    private void resize(int cap) {
        Item[] itemp = (Item[]) new Object[cap];
        Item[] mtemp = (Item[]) new Object[cap];
        for (int i = 0; i < N; i++) {
            itemp[i] = items[i];
            mtemp[i] = max[i];
        }
        items = itemp;
        max = mtemp;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void push(Item item){

    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }
}
