package c4.s3;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 加权无向图的数据类型
 *
 * @author huangjie
 * @create 2018-02-26-20:08
 **/
public class EdgeWeightedGraph {

    private final int V;
    private int E;
    private Bag<Edge>[] adj;
    private Bag<Edge> edges;

    public EdgeWeightedGraph(int v) {
        V = v;
        edges = new Bag<>();
        adj = (Bag<Edge>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<Edge>();
        }
    }

    public EdgeWeightedGraph(In in) {
        this(in.readInt());
        //在addEdge()中有E++；所以这里用一个本地变量E来存储边的数目
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

    public Iterable<Edge> edges() {
        return edges;
    }

//    存在多次计算问题
//    public Iterable<Edge> edges() {
//        Bag<Edge> bag = new Bag<>();
//        for (int v = 0; v < V; v++) {
//            for (Edge e :
//                    adj[v]) {
//                if (e.other(v) > v) bag.add(e);
//            }
//        }
//        return bag;
//    }

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
