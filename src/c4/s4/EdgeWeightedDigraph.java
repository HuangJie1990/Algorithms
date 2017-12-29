package c4.s4;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj;
    private Bag<DirectedEdge> edges;

    public EdgeWeightedDigraph(int v) {
        V = v;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<DirectedEdge>();
        }
        edges = new Bag<>();
    }

    public EdgeWeightedDigraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            DirectedEdge edge = new DirectedEdge(v, w, weight);
            addEdge(edge);
            edges.add(edge);
        }
    }

    public void addEdge(DirectedEdge edge) {
        int v = edge.from();
        adj[v].add(edge);
        E++;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        return edges;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(V + " vertices " + E + " edges\n");
        for (int i = 0; i < V; i++) {
            sb.append(i + ": ");
            for (DirectedEdge edge : adj[i]) {
                sb.append(edge + "  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
