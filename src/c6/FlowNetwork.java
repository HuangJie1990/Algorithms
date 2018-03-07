package c6;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * @author huangjie
 * @create 2018-03-06-13:17
 **/
public class FlowNetwork {
    private final int V;
    private int E;
    private Bag<FlowEdge>[] adj;

    public FlowNetwork(int V) {
        if (V < 0) throw new IllegalArgumentException("number of vertices in a FlowNetwork must be nonnegative");
        this.V = V;
        E = 0;
        adj = (Bag<FlowEdge>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    public FlowNetwork(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double cap = in.readDouble();
            addEdge(new FlowEdge(v, w, cap));
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(FlowEdge edge) {
        int v = edge.from(), w = edge.to();
        adj[v].add(edge);
        adj[w].add(edge);
        E++;
    }

    public Iterable<FlowEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<FlowEdge> edges() {
        Bag<FlowEdge> bag = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (FlowEdge edge : adj[v]) {
                if (edge.other(v) > v) bag.add(edge);
            }
        }
        return bag;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(V + " vertices, " + E + " edges\n");
        for (int v = 0; v < V; v++) {
            sb.append(v + ": ");
            for (FlowEdge edge :
                    adj[v]) {
                sb.append(edge + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
