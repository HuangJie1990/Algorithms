package ChapterOne;

import edu.princeton.cs.algs4.StdOut;

public class ProcolationTest {

    public static void main(String[] args) {
        int count = 0;
        for (int i = 0; i < 10000; i++) {
            Procolation procolation = new Procolation(100, 0.592746);
            if (procolation.connected(10000, 10001)) count++;
        }
        StdOut.println(count);
        double q = (double) count / 10000;
        StdOut.print(q);
    }
}
