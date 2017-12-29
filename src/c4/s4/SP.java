package c4.s4;

public abstract class SP {
    public abstract double distTo(int v);
    public abstract boolean hasPathTo(int v);
    public abstract Iterable<DirectedEdge> pathTo(int v);
}
