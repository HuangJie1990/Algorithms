package c4.s2;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Digraph {

    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Digraph(int v) {
        if (v < 0) throw new IllegalArgumentException("Number of vertices in a digraph must be nonnegative");
        V = v;
        E = 0;
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    public Digraph(In in) {
        this(in.readInt());
        int e=in.readInt();
        for (int i = 0; i < e; i++) {
            int v=in.readInt();
            int w=in.readInt();
            addEdge(v,w);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        E++;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int i = 0; i < V; i++) {
            for (int w : adj[i]) {
                R.addEdge(w, i);
            }
        }
        return R;
    }
}
