package c1.s5;

public abstract class UF {
    protected int[] id;
    protected int count;

    /*
    add connection between p and q
     */
    public abstract void union(int p, int q);

    /*
    component identifier for p(0 to N-1)
     */
    public abstract int find(int i);

    /*
    are p and q in the same component?
     */
    public abstract boolean connected(int p, int q);

    /*
    number of components
     */
    public int count() {
        return count;
    }
}
