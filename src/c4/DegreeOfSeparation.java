package c4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author
 * @create 2018-02-25-10:44
 **/
public class DegreeOfSeparation {

    public static void main(String args[]) {
        SymbolGraph sg = new SymbolGraph(args[0], args[1]);
        Graph graph = sg.G();

        String source = args[2];
        if (!sg.contains(source)) {
            StdOut.println(source + " not in database.");
            return;
        }

        int s = sg.index(source);
        BreadthFirstPaths bfs = new BreadthFirstPaths(graph, s);
        while (!StdIn.isEmpty()) {
            String sink = StdIn.readLine();
            if (sg.contains(sink)) {
                int t = sg.index(sink);
                if (bfs.hasPathTo(t))
                    for (int v : bfs.pathTo(t)) StdOut.println("    " + sg.name(v));
            } else StdOut.println("Not in database.");
        }
    }
}
