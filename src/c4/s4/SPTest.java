package c4.s4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class SPTest {

    public static void main(String[] args) {
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(new In(args[0]));
        StdOut.println(digraph);
        int s = Integer.parseInt(args[1]);

        SP sp=new DijkstraSP(digraph,s,7);

        for (int t = 0; t < digraph.V(); t++) {
            StdOut.print(s + " to " + t);
            StdOut.printf(" (%4.2f): ", sp.distTo(t));
            if (sp.hasPathTo(t)) {
                for (DirectedEdge edge : sp.pathTo(t))
                    StdOut.print(edge + "  ");
            }
            StdOut.println();
        }
    }
}
