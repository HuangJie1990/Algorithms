package w1;

/**
 * @author
 * @create 2017-12-30-21:09
 **/
public class WeightedQuickUnionUF {

    private int[] id;
    private int[] size;
    private int[] max;
    private int count;

    public WeightedQuickUnionUF(int n) {
        id = new int[n];
        size = new int[n];
        max = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
            max[i] = i;
        }
    }

    public void union(int p, int q) {
        validate(p);
        validate(q);
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (size[i] < size[j]) {
            id[i] = j;
            size[j] += size[i];
            max[j] = Math.max(max[i],max[j]);
        } else {
            id[j] = i;
            max[i] = Math.max(max[i],max[j]);
            size[i] += size[i];
        }
        count--;
    }

    public int root(int p) {
        while (p != id[p]) p = id[p];
        return p;
    }

    public int find(int p) {
        validate(p);
        return max[root(p)];
    }

    public boolean connected(int p, int q) {
        validate(p);
        validate(q);
        return root(p) == root(q);
    }

    public int count() {
        return count;
    }

    private void validate(int p) {
        int length = id.length;
        if (p < 0 || p >= length)
            throw new IllegalArgumentException("index must varies in the interval [0, " + (length - 1) + "]");
    }
}
