package c4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class SearchTest {
    public static void main(String[] args) {
        Graph graph = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
       /* Search search = new Search(graph, s);

        for (int v = 0; v < graph.V(); v++) {
            if (search.marked(v)) {
                StdOut.print(v + " ");
            }
        }
        StdOut.println();

        if (search.count() < graph.V()) {
            StdOut.println("Not connected");
        }*/


       DepthFirstSearch depthFirstSearch=new DepthFirstSearch(graph,s);
        for (int v = 0; v < graph.V(); v++) {
            if(depthFirstSearch.marked(v)){
                StdOut.print(v + " ");
            }
        }
        StdOut.println();

        if(depthFirstSearch.count()<graph.V()){
            StdOut.println("Not connected");
        }
    }
}
