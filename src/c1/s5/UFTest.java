package c1.s5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import w1.WeightedQuickUnionUF;

public class UFTest {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        Stopwatch stopwatch=new Stopwatch();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
            }
        }
        for (int i = 0; i < N; i++) {
            StdOut.println(String.format("root(%d) is %d, max(%d) is %d",i,uf.root(i),i,uf.find(i)));
        }
        StdOut.println(uf.count());
        StdOut.println(stopwatch.elapsedTime());
    }
}
