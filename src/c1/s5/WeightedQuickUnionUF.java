package c1.s5;

public class WeightedQuickUnionUF extends QuickUnionUF {
    private int[] size;

    public WeightedQuickUnionUF(int N) {
        super(N);
        size = new int[N];
        for (int i = 0; i < N; i++) {
            size[i] = 1;
        }
    }

    @Override
    public void union(int p, int q) {
        int proot = root(p);
        int qroot = root(q);
        if (proot == qroot) return;
        if (size[p] < size[q]) {
            id[proot] = qroot;
            size[qroot] += size[proot];
        } else {
            id[qroot] = proot;
            size[proot] += size[qroot];
        }
        count--;
    }
}
