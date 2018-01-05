package c2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * @author
 * @create 2018-01-05-10:03
 **/
public class SortTest {

    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] temp = in.readAllInts();
        int n = temp.length;
        Stopwatch stopwatch = new Stopwatch();
        for (int i = 0; i < 100; i++) {
            Integer[] a = new Integer[n];
            for (int j = 0; j < n; j++) {
                a[j] = temp[j];
            }
            MergeBU.sort(a);
//            Sort.show(a);
        }
        StdOut.println();
        StdOut.println(stopwatch.elapsedTime());
    }
}
