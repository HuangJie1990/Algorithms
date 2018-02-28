package c4.s4;

/**
 * 无环加权有向图的最短路径算法
 *
 * @author huangjie
 * @create 2018-02-28-17:14
 **/
public class AcyclicSP extends SP {

    public AcyclicSP(EdgeWeightedDigraph digraph) {

    }

    @Override
    public double distTo(int v) {
        return 0;
    }

    @Override
    public boolean hasPathTo(int v) {
        return false;
    }

    @Override
    public Iterable<DirectedEdge> pathTo(int v) {
        return null;
    }
}
