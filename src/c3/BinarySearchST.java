package c3;

import edu.princeton.cs.algs4.Queue;

import java.util.NoSuchElementException;

public class BinarySearchST<Key extends Comparable<Key>, Value> extends SortST<Key, Value> {

    private static final int InitialCapacity = 2;
    private Key[] keys;
    private Value[] values;
    private int n;
    public BinarySearchST() {
        this(InitialCapacity);
    }

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Key[] keysTemp = (Key[]) new Comparable[capacity];
        Value[] valuesTemp = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            keysTemp[i] = keys[i];
            valuesTemp[i] = values[i];
        }
        keys = keysTemp;
        values = valuesTemp;
    }

    @Override
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return keys[0];
    }

    @Override
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return keys[n - 1];
    }

    //largest key less than or equal to key
    @Override
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        int i = rank(key);
        //如果keys中存在key，返回key
        if (i < n && key.compareTo(keys[i]) == 0) return keys[i];
        /*如果keys中不存在key，分两种情况
        1,rank(key)==0,返回null；
        2,rank(key)>0, 返回keys[i-1].
         */
        if (i == 0) return null;
        else return keys[i - 1];
    }

    //大于等于key的最小键,类似于小数的向上取整
    @Override
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        int i = rank(key);
        if (i == n) return null;
        return keys[i];
    }

    //如果keys中存在key，keys[rank(key)]=key,否则keys[rank(key)]>key>keys[rank(key)-1]
    @Override
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        int lo = 0, hi = n - 1;
        //符号表中不存在key的时候，lo>hi时结束循环，返回lo
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
                //符号表中存在key的时候，keys[mid]==key时结束循环，返回mid
            else return mid;
        }
        return lo;
    }

    @Override
    public Key select(int k) {
        if (k < 0 || k >= n) throw new IllegalArgumentException("called select() with invalid argument: \" + k");
        return keys[k];
    }

    @Override
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("called deletemin() with empty symbol table");
        delete(min());
    }

    @Override
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("called deleteMax() with empty symbol table");
        delete(max());
    }

    @Override
    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<>();
        if (lo.compareTo(hi) > 0) return queue;
        /*if (contains(hi)) {
            for (int i = rank(lo); i <= rank(hi); i++) {
                queue.enqueue(keys[i]);
            }
            return queue;
        } else {
            for (int i = rank(lo); i < rank(hi); i++) {
                queue.enqueue(keys[i]);
            }
            return queue;
        }*/

        for (int i = rank(lo); i < rank(hi); i++) {
            queue.enqueue(keys[i]);
        }
        if (contains(hi)) queue.enqueue(hi);
        return queue;
    }

    @Override
    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (value == null) {
            delete(key);
            return;
        }
        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0) {
            values[i] = value;
            return;
        }
        //恰好填充满时，不扩展数组大小。下一次put操作时，若为新元素，先扩展数组大小，再put
        if (n == keys.length) resize(2 * keys.length);
        for (int j = n; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        n++;
        assert check();
    }

    @Override
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0) return values[i];
        return null;
    }

    @Override
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        int i = rank(key);
        if (i == n || key.compareTo(keys[i]) != 0) return;
        for (int j = i; j < n - 1; j++) {
            keys[j] = keys[j + 1];
            values[j] = values[j + 1];
        }
        keys[n - 1] = null;
        values[n - 1] = null;
        n--;
        //元素数量是数组大小1/4时，立即缩小数组大小。
        if (n > 0 && n == keys.length / 4) resize(keys.length / 2);
        assert check();
    }

    @Override
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    private boolean check() {
        return isSort() && rankCheck();
    }

    private boolean isSort() {
        for (int i = 1; i < n; i++) {
            if (keys[i].compareTo(keys[i + 1]) < 0) return false;
        }
        return true;
    }

    private boolean rankCheck() {
        for (int i = 0; i < n; i++) {
            if (i != rank(select(i))) return false;
        }
        for (int i = 0; i < n; i++) {
            if (keys[i].compareTo(select(rank(keys[i]))) != 0) return false;
        }
        return true;
    }
}
