package c1.s5;

public class QuickUnionUF extends UF {

    public QuickUnionUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        count = N;
    }

    @Override
    public void union(int p, int q) {
        int proot = root(p);
        int qroot = root(q);
        if (proot == qroot) return;
        id[proot] = qroot;
        count--;
    }

    @Override
    public int find(int i) {
        return root(i);
    }

    @Override
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    protected int root(int i) {
        while (i != id[i]) i = id[i];
        return i;
    }
}
