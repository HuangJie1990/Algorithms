package c4.s3;

import edu.princeton.cs.algs4.*;

public class Kruskal extends MST {

    private Queue<Edge> mst;
    private double weight;

    public Kruskal(EdgeWeightedGraph graph) {
        mst = new Queue<>();
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge edge : graph.edges()) {
            pq.insert(edge);
        }
        UF uf = new UF(graph.V());
        while (!pq.isEmpty()) {
            Edge edge = pq.delMin();
            int v = edge.either();
            int w = edge.other(v);
            if (uf.connected(v, w)) continue;
            else {
                mst.enqueue(edge);
                uf.union(v, w);
                weight += edge.weight();
            }
        }
    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }

    @Override
    public double weight() {
        return weight;
    }

    public static void main(String[] args) {
        In in = new In(args[2]);
        EdgeWeightedGraph graph = new EdgeWeightedGraph(in);
        StdOut.println(graph);
        MST mst = new Kruskal(graph);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
    }
}
