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
        Integer[] a = new Integer[10];
        for (int i = 0; i < 10; i++) {
            a[i] = temp[i];
        }
        Stopwatch stopwatch=new Stopwatch();
        Bubble.sort(a);
        Sort.show(a);
        StdOut.println();
        StdOut.println(stopwatch.elapsedTime());
    }
}
