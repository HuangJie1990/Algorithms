package c4;

import c4.s1.Graph;
import c4.s2.Digraph;
import edu.princeton.cs.algs4.Stack;

public class DepthFirstPaths {
    private boolean marked[];
    private int edgeTo[];
    private final int s;

    public DepthFirstPaths(Digraph digraph, int s) {
        marked = new boolean[digraph.V()];
        edgeTo = new int[digraph.V()];
        this.s = s;
        dfs(digraph, s);
    }

    public DepthFirstPaths(Graph graph, int s) {
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        this.s = s;
        dfs(graph, s);
    }

    private void dfs(Digraph digraph, int s) {
        marked[s] = true;
        for (int w : digraph.adj(s)) {
            if (!marked[w]) {
                edgeTo[w] = s;
                dfs(digraph, w);
            }
        }
    }

    private void dfs(Graph graph, int s) {
        marked[s] = true;
        for (int w : graph.adj(s)) {
            if (!marked[w]) {
                edgeTo[w] = s;
                dfs(graph, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
