package ChapterTwo;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Merge {

    private static Comparable[] aux;

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];//2 access
        }

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];//2 access
            else if (j > hi) a[k] = aux[i++];//2 access
            else if (less(aux[i], aux[j])) a[k] = aux[i++];//2 access
            else a[k] = aux[j++];//2 access
        }
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }
}
