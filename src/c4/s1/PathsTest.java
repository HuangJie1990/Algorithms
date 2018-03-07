package c4.s1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class PathsTest {

    public static void main(String[] args) {
        Graph graph = new Graph(new In(args[0]));
        System.out.print(graph.toString());
        int s = Integer.parseInt(args[1]);
        IterativeDepthFirstSearch dfs = new IterativeDepthFirstSearch(graph, s);
        for (int i = 0; i < graph.V(); i++) {
            StdOut.print(s + " to " + i + " : ");
            if (dfs.hasPathTo(i)) {
                for (int w : dfs.pathTo(i)) {
                    if (w == s) {
                        StdOut.print(s);
                    } else {
                        StdOut.print("-" + w);
                    }
                }
            }
            StdOut.println();
        }


        /* BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(graph, s);
        for (int i = 0; i < graph.V(); i++) {
            StdOut.print(s + " to " + i + " : ");
            if (breadthFirstPaths.hasPathTo(i)) {
                for (int w : breadthFirstPaths.pathTo(i)) {
                    if (w == s) {
                        StdOut.print(s);
                    }
                    else {
                        StdOut.print("-" + w);
                    }
                }
            }
            StdOut.println();
        }*/

       /* Digraph digraph = new Digraph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        DepthFirstPaths depthFirstPaths = new DepthFirstPaths(digraph, s);
        for (int i = 0; i < digraph.V(); i++) {
            StdOut.print(s + " to " + i + " : ");
            if (depthFirstPaths.hasPathTo(i)) {
                for (int w : depthFirstPaths.pathTo(i)) {
                    if (w == s) {
                        StdOut.print(s);
                    }
                    else {
                        StdOut.print("-" + w);
                    }
                }
            }
            StdOut.println();
        }*/

       /* BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(digraph, s);
        for (int i = 0; i < digraph.V(); i++) {
            StdOut.print(s + " to " + i + " : ");
            if (breadthFirstPaths.hasPathTo(i)) {
                for (int w : breadthFirstPaths.pathTo(i)) {
                    if (w == s) {
                        StdOut.print(s);
                    }
                    else {
                        StdOut.print("-" + w);
                    }
                }
            }
            StdOut.println();
        }*/
    }
}
