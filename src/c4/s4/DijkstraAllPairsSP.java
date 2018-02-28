package c4.s4;

/**
 * 任意定点对之间的最短路径
 *
 * @author
 * @create 2018-02-28-15:21
 **/
public class DijkstraAllPairsSP {

    private DijkstraSP[] all;

    public DijkstraAllPairsSP(EdgeWeightedDigraph digraph) {
        all = new DijkstraSP[digraph.V()];
        for (int v = 0; v < digraph.V(); v++) {
            all[v] = new DijkstraSP(digraph, v);
        }
    }

    public double dist(int s, int t) {
        return all[s].distTo(t);
    }

    public boolean hasPath(int s, int t) {
        return all[s].hasPathTo(t);
    }

    public Iterable<DirectedEdge> path(int s, int t) {
        return all[s].pathTo(t);
    }
}
