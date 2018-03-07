package c6;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 最短增广路径的Ford-Fulkerson最大流量算法
 *
 * @author huangjie
 * @create 2018-03-06-14:57
 **/
public class FordFulkerson {

    //在剩余网络中是否存在从s到v的路径？
    private boolean[] marked;

    //从s到v的最短路径上的最后一条边
    private FlowEdge[] edgeTo;

    //当前最大流量
    private double value;

    private int s;
    private int t;

    public FordFulkerson(FlowNetwork G, int s, int t) {
        this.s = s;
        this.t = t;
        //找出从s到t的流量网络G的最大流量配置
        while (hasAugmentingPath(G, s, t)) {
            //利用所有存在的增广路径
            //计算当前的瓶颈容量
            double bottle = Double.POSITIVE_INFINITY;
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                bottle = Math.min(bottle, edgeTo[v].residualCapacity(v));
            }

            //增大流量
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                edgeTo[v].addResidualFlowTo(v, bottle);
            }

            value += bottle;
        }
    }

    public static void main(String[] args) {
        FlowNetwork G = new FlowNetwork(new In(args[0]));
        int s = 0, t = G.V() - 1;
        FordFulkerson maxflow = new FordFulkerson(G, s, t);

        StdOut.println("Max flow from " + s + " to " + t);
        for (int v = 0; v < G.V(); v++) {
            for (FlowEdge e : G.adj(v)) {
                if ((v == e.from()) && e.flow() > 0)
                    StdOut.println(" " + e);
            }
        }
        StdOut.println("Max flow value = " + maxflow.value());
    }

    public double value() {
        return value;
    }

    public boolean inCut(int v) {
        return marked[v];
    }

    private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
        marked = new boolean[G.V()];
        edgeTo = new FlowEdge[G.V()];
        Queue<Integer> q = new Queue<>();

        marked[s] = true;
        q.enqueue(s);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (FlowEdge e :
                    G.adj(v)) {
                int w = e.other(v);
                if (e.residualCapacity(w) > 0 && !marked[w]) {
                    edgeTo[w] = e;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
        return marked[t];
    }

    private boolean localEq(FlowNetwork G, int v) {
        //检查定点v的局部平衡
        double EPSILON = 1E-11;
        double netflow = 0.0;
        for (FlowEdge e : G.adj(v)) {
            if (v == e.from()) netflow -= e.flow();
            else netflow += e.flow();
        }
        return Math.abs(netflow) < EPSILON;
    }

    private boolean isFeasible(FlowNetwork G) {
        //确认每条边的流量非负且不大于边的容量
        for (int v = 0; v < G.V(); v++) {
            for (FlowEdge e : G.adj(v)) {
                if (e.flow() < 0 || e.flow() > e.capacity()) return false;
            }
        }

        //检查每个顶点的局部平衡
        for (int v = 0; v < G.V(); v++) {
            if (v != s && v != t && !localEq(G, v)) return false;
        }

        return true;
    }
}
