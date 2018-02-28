package c4.s4;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

/**
 * DijkstraSP算法
 *
 * @author huangjie
 * @create 2018-02-28-10:32
 **/
public class DijkstraSP extends SP {

    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph digraph, int v) {
        distTo = new double[digraph.V()];
        for (int i = 0; i < digraph.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        edgeTo = new DirectedEdge[digraph.V()];
        pq = new IndexMinPQ<>(digraph.V());
        distTo[v] = 0.0;
        pq.insert(v, 0.0);
        while (!pq.isEmpty()) {
            relax(digraph, pq.delMin());
        }
    }

    public DijkstraSP(EdgeWeightedDigraph digraph, int v, int w) {
        distTo = new double[digraph.V()];
        for (int i = 0; i < digraph.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        edgeTo = new DirectedEdge[digraph.V()];
        pq = new IndexMinPQ<>(digraph.V());
        distTo[v] = 0.0;
        pq.insert(v, 0.0);
        while (!pq.isEmpty()) {
            int t = pq.delMin();
            if(t==w) break;
            relax(digraph,t);
        }
    }

    @Override
    public double distTo(int v) {
        return distTo[v];
    }

    @Override
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    @Override
    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge edge = edgeTo[v]; edge != null; edge = edgeTo[edge.from()]) {
            path.push(edge);
        }
        return path;
    }

    private void relax(DirectedEdge edge) {
        int v = edge.from();
        int w = edge.to();
        if (distTo[w] > distTo[v] + edge.weight()) {
            edgeTo[w] = edge;
            distTo[w] = distTo[v] + edge.weight();
        }
    }

    private void relax(EdgeWeightedDigraph digraph, int v) {
        for (DirectedEdge edge : digraph.adj(v)) {
            int w = edge.to();
            if (distTo[w] > distTo[v] + edge.weight()) {
                edgeTo[w] = edge;
                distTo[w] = distTo[v] + edge.weight();
                if (pq.contains(w)) pq.changeKey(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }
}
