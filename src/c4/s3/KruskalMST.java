package c4.s3;

import edu.princeton.cs.algs4.*;

/**
 * 最小生成树的Kruskal算法
 *
 * @author huangjie
 * @create 2018-02-27-10:37
 **/
public class KruskalMST extends MST {
    private Queue<Edge> mst;
    private double weight;
    private Queue<Edge> mfes;

    public KruskalMST(EdgeWeightedGraph graph) {
        mst = new Queue<>();
        mfes = new Queue<>();
        MinPQ<Edge> pq = new MinPQ();
        UF uf = new UF(graph.V());
        for (Edge e : graph.edges()) pq.insert(e);
        //貌似两个条件满足一个就可以
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) {
                mfes.enqueue(e);
                continue;
            }
            uf.union(v, w);
            mst.enqueue(e);
            weight += e.weight();
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph graph = new EdgeWeightedGraph(in);
        StdOut.println(graph);
        MST mst = new KruskalMST(graph);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
        StdOut.println();
        for (Edge e : mst.mfes()) {
            StdOut.println(e);
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

    public Iterable<Edge> mfes() {
        return mfes;
    }
}
