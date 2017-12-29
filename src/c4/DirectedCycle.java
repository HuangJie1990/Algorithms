package c4;

import c4.s2.Digraph;
import edu.princeton.cs.algs4.Stack;

public class DirectedCycle {

    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle(Digraph digraph) {
        marked = new boolean[digraph.V()];
        edgeTo = new int[digraph.V()];
        onStack = new boolean[digraph.V()];
        cycle = new Stack<Integer>();
        for (int v = 0; v < digraph.V(); v++) {
            if (!marked[v]) dfs(digraph, v);
        }
    }

    private void dfs(Digraph digraph, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : digraph.adj(v)) {
            if (hasCycle()) return;
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(digraph, w);
            } else if (onStack[w]) {
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle.size() != 0;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
