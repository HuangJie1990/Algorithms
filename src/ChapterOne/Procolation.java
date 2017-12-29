package ChapterOne;

import edu.princeton.cs.algs4.StdRandom;

public class Procolation {

    private int id[];
    private int size[];
    private boolean isOpen[];
    private double p;

    public Procolation(int N, double p) {
        int count = N * N + 2;
        this.p = p;
        id = new int[count];
        size = new int[count];
        isOpen = new boolean[count - 2];
        for (int i = 0; i < count; i++) {
            id[i] = i;
        }

        for (int i = 0; i < count - 2; i++) {
            isOpen[i] = StdRandom.bernoulli(p);

            if (i >= 0 && i < N) union(i, count - 2);
            if (i >= count - 2 - N && i < count - 2) union(i, count - 1);

            if (isOpen[i]) {
                if (i > 0 && i < N) {
                    if (isOpen[i - 1]) union(i - 1, i);
                }

                if (i > N) {
                    if (i % N == 0) {
                        if (isOpen[i - N]) union(i - N, i);
                    } else {
                        if (isOpen[i - N]) union(i - N, i);
                        if (isOpen[i - 1]) union(i - 1, i);
                    }
                }
            }
        }

/*        for (int i = 0; i < N; i++) {
            union(i, count - 2);
        }

        for (int i = count - 2 - N; i < count - 2; i++) {
            union(i, count - 1);
        }

        for (int i = 0; i < count - 2; i++) {
            if (isOpen[i]) {
                if (i - N >= 0 && isOpen[i - N]) {
                    union(i - N, i);
                }
                if (i - 1 >= 0 && isOpen[i - 1]) {
                    union(i - 1, i);
                }
                if (i + 1 < count - 2 && isOpen[i + 1]) {
                    union(i + 1, i);
                }
                if (i + N < count - 2 && isOpen[i + N]) {
                    union(i + N, i);
                }
            }
        }*/
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    private void union(int p, int q) {
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
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
}
