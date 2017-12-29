package c1.s5;

public class WQUPC extends WeightedQuickUnionUF {
    public WQUPC(int N) {
        super(N);
    }

    @Override
    protected int root(int i) {
        while (i != id[i]) i = id[i];
        return i;
    }
}
