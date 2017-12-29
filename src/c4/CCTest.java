package c4;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class CCTest {

    public static void main(String[] args){
        Graph graph=new Graph(new In(args[0]));
        CC cc=new CC(graph);

        int M=cc.count();
        StdOut.println(M+" components");

        Bag<Integer>[] components=(Bag<Integer>[]) new Bag[M];
        for (int i = 0; i < M; i++) {
            components[i]=new Bag<Integer>();
        }
        for (int i = 0; i < graph.V(); i++) {
            components[cc.id(i)].add(i);
        }
        for (int i = 0; i < M; i++) {
            for(int w:components[i]){
                StdOut.print(w+" ");
            }
            StdOut.println();
        }
    }
}
