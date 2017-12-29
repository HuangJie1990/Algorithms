package ChapterOne;

public class WeightedQuickUnionWithCompressionUF {
    private int[] id;
    private int[] size;

    public WeightedQuickUnionWithCompressionUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int proot = root(p);
        int qroot = root(q);
        if (size[p] < size[q]) {
            id[proot] = qroot;
            size[q] += size[p];
        } else {
            id[qroot] = proot;
            size[p] += size[q];
        }
    }

    private int root(int i) {
        while (id[i] != i) {
            id[i]=id[id[i]];
            i = id[i];
        }
        return i;
    }
}
