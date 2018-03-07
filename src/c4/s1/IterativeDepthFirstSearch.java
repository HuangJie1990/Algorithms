package c4.s1;

import edu.princeton.cs.algs4.Stack;

/**
 * 非迭代的深度优先搜索算法
 *
 * @author
 * @create 2018-02-27-11:34
 **/
public class IterativeDepthFirstSearch extends DFS {
    private final int s;
    private boolean[] marked;
    private int count;
    private int[] edgeTo;

    public IterativeDepthFirstSearch(Graph graph, int s) {
        this.s = s;
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        count++;
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!marked[v]) {
                marked[v] = true;
                for (int w : graph.adj(v)) {
                    if (!marked[w]) {
                        stack.push(w);
                        edgeTo[w] = v;
                        count++;
                    }
                }
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public int count() {
        return count;
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
