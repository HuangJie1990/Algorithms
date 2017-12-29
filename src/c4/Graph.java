package c4;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Graph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Graph(int v) {
        V = v;
        E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    public Graph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        for (int i = 0; i < V; i++) {
            s += i + ": ";
            for (int w : this.adj(i)) {
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }

    public static int degree(Graph graph, int v) {
        int degree = 0;
        for (int w : graph.adj(v)) degree++;
        return degree;
    }

    public static int maxDegree(Graph graph) {
        int max = 0;
        for (int i = 0; i < graph.V(); i++) {
            if (degree(graph, i) > max) {
                max = degree(graph, i);
            }
        }
        return max;
    }

    public static int avgDegree(Graph graph) {
        return 2 * graph.E() / graph.V();
    }

    public static int numberOfSelfLoops(Graph graph) {
        int count = 0;
        for (int i = 0; i < graph.V(); i++) {
            for (int w : graph.adj(i)) {
                if (i == w) {
                    count++;
                }
            }
        }
        return count / 2;
    }
}
