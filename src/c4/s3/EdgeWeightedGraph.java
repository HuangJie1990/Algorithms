package c4.s3;

import c4.s3.Edge;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedGraph {

    private final int V;
    private int E;
    private Bag<Edge>[] adj;
    private Bag<Edge> edges;

    public EdgeWeightedGraph(int v) {
        V = v;
        edges=new Bag<>();
        adj = (Bag<Edge>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<Edge>();
        }
    }

    public EdgeWeightedGraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            addEdge(new Edge(v, w, weight));
        }
    }

    public void addEdge(Edge e) {
        edges.add(e);
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges(){return edges;}

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(V + " vertices " + E + " edges\n");
        for (int i = 0; i < V; i++) {
            sb.append(i + ": ");
            for (Edge edge : adj[i]) {
                sb.append(edge + "  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
