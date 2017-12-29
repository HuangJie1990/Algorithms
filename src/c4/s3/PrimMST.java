package c4.s3;

import edu.princeton.cs.algs4.*;

public class PrimMST extends MST {

    private boolean[] marked;
    private Edge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;
    private double weight;

    public PrimMST(EdgeWeightedGraph graph) {
        marked = new boolean[graph.V()];
        distTo = new double[graph.V()];
        edgeTo = new Edge[graph.V()];
        pq = new IndexMinPQ<>(graph.V());
        for (int i = 0; i < graph.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        distTo[0] = 0.0;
        pq.insert(0, distTo[0]);
        while (!pq.isEmpty()) {
            weight += pq.minKey();
            visit(graph, pq.delMin());
        }
    }

    @Override
    public Iterable<Edge> edges() {
        Bag<Edge> bag = new Bag<>();
        for (Edge edge : edgeTo) {
            if (edge != null) bag.add(edge);
        }
        return bag;
    }

    @Override
    public double weight() {
        return weight;
    }

    private void visit(EdgeWeightedGraph graph, int v) {
        marked[v] = true;
        for (Edge edge : graph.adj(v)) {
            int w = edge.other(v);
            if (marked[w]) continue;
            if (edge.weight() < distTo[w]) {
                edgeTo[w] = edge;
                distTo[w] = edge.weight();
                if (pq.contains(w)) pq.changeKey(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    public static void main(String[] args){
        In in=new In(args[2]);
        EdgeWeightedGraph graph=new EdgeWeightedGraph(in);
        StdOut.println(graph);
        MST mst=new PrimMST(graph);
        for(Edge e:mst.edges()){
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
    }
}
