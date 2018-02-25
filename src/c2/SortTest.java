package c2;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author
 * @create 2018-01-05-10:03
 **/
public class SortTest {

    public static void main(String[] args) {

        Integer[] a = new Integer[]{
                5, 6, 3, 4, 7, 8, 1, 2, 9, 1, 0, 0, 0
        };
        for (int i = 0; i < a.length; i++) {
            Insertion.sort(a);
        }
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }
}
