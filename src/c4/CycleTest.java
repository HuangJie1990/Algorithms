package c4;

import c4.s2.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class CycleTest {

    public static void main(String[] args){

        Graph graph=new Graph(new In(args[0]));
        Cycle cycle=new Cycle(graph);
        StdOut.println(cycle.hasCycle());

        Digraph digraph=new Digraph(new In(args[0]));
        DirectedCycle directedCycle=new DirectedCycle(digraph);
        StdOut.println(directedCycle.hasCycle());
        for(int i:directedCycle.cycle()){
            StdOut.print(i+" ");
        }

        DepthFirstOrder depthFirstOrder=new DepthFirstOrder(digraph);
        for(int i:depthFirstOrder.pre()){
            StdOut.print(i+" ");
        }
        StdOut.println();
        for(int i:depthFirstOrder.post()){
            StdOut.print(i+" ");
        }
        StdOut.println();
        for(int i:depthFirstOrder.reversePost()){
            StdOut.print(i+" ");
        }
        StdOut.println();


        TwoColor twoColor=new TwoColor(graph);
        StdOut.println(twoColor.isTwoColorable());
    }
}
