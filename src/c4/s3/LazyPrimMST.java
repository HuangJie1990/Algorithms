package c4.s3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 最小生成树的Prim算法的延时实现
 *
 * @author huangjie
 * @create 2018-02-27-9:17
 **/
public class LazyPrimMST extends MST {
    private boolean[] marked;
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;
    private double weight;

    public LazyPrimMST(EdgeWeightedGraph graph) {
        marked = new boolean[graph.V()];
        mst = new Queue<>();
        pq = new MinPQ<>();

        visit(graph, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;
            mst.enqueue(e);
            weight += e.weight();
            if (!marked[v]) visit(graph, v);
            if (!marked[w]) visit(graph, w);
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph graph = new EdgeWeightedGraph(in);
        StdOut.println(graph);
        MST mst = new LazyPrimMST(graph);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }

    @Override
    public double weight() {
        return weight;
    }

    @Override
    public Iterable<Edge> mfes() {
        return null;
    }

    private void visit(EdgeWeightedGraph graph, int v) {
        marked[v] = true;
        for (Edge e : graph.adj(v)) {
            if (!marked[e.other(v)]) pq.insert(e);
        }
    }
}
