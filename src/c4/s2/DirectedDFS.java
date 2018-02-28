package c4.s2;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 有向图的可达性
 *
 * @author huangjie
 * @create 2018-02-28-17:17
 **/
public class DirectedDFS {

    private boolean[] marked;

    public DirectedDFS(Digraph digraph, int s) {
        marked = new boolean[digraph.V()];
        dfs(digraph, s);
    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        for (int w : digraph.adj(v)) if (!marked[w]) dfs(digraph, w);
    }

    private DirectedDFS(Digraph digraph, Iterable<Integer> sources) {
        marked = new boolean[digraph.V()];
        for (int v : sources) dfs(digraph, v);
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public static void main(String[] args) {
        Digraph digraph = new Digraph(new In(args[0]));
        StdOut.println(digraph);

        Bag<Integer> sources = new Bag<>();
        for (int i = 1; i < args.length; i++) {
            sources.add(Integer.parseInt(args[i]));
        }

        DirectedDFS dfs = new DirectedDFS(digraph, sources);

        for (int v = 0; v < digraph.V(); v++) {
            if (dfs.marked(v)) {
                StdOut.print(v + " ");
            }
        }
        StdOut.println();
    }
}
