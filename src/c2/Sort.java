package c2;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author
 * @create 2018-01-05-9:40
 **/
public class Sort {

    private Sort() {
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    private static int cmp;

    public static boolean less(Comparable v, Comparable w) {
        cmp++;
        return v.compareTo(w) < 0;
    }

    public static int cmpCount() {
        return cmp;
    }

    public static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    public static boolean equal(Comparable v, Comparable w) {
        return v.compareTo(w) == 0;
    }

    public static boolean notLess(Comparable v, Comparable w) {
        return v.compareTo(w) >= 0;
    }

    public static boolean notGreater(Comparable v, Comparable w) {
        return v.compareTo(w) <= 0;
    }

    public static void show(Comparable[] a) {
        for (Comparable c :
                a) {
            StdOut.print(c + "\n");
        }
    }

    public static void show(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            StdOut.print(a[i] + "\n");
        }
    }

    public static void exch(Comparable[] a, int v, int w) {
        Comparable temp = a[v];
        a[v] = a[w];
        a[w] = temp;
    }
}
