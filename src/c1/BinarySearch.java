package c1;

import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class BinarySearch {

    /*
    this class should not be initialized.
     */
    private BinarySearch() {
    }
    private static int depth;

    public static int rank(int key, int[] a) {
        depth=0;
        return rank(key, a, 0, a.length - 1);
    }

    /*public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (key > a[mid]) lo = mid + 1;
            else if (key < a[mid]) hi = mid - 1;
            else return mid;
        }
        return -1;
    }*/

    private static int rank(int key, int[] a, int lo, int hi) {
        String value=String.format("lo: %d, hi: %d",lo,hi);
        String format="%"+(depth+value.length())+"s\n";
        StdOut.printf(format,value);
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key > a[mid]){
            depth++;
            return rank(key, a, mid + 1, hi);
        }
        else if (key < a[mid]){
            depth++;
            return rank(key, a, lo, mid - 1);
        }
        else return mid;
    }

    public static void main(String[] args) {
        int[] a = new int[10];
        StdOut.println(rank(2, a));
        a = new int[]{0, 6, 6, 6, 6, 6, 12, 14, 16, 18};
        StdOut.println(rank(9, a));
        StdOut.println(rank(-1, a));
        StdOut.println(rank(6, a));
        StdOut.println(rank(20, a));
    }
}
