package c2;

import edu.princeton.cs.algs4.StdRandom;

public class Quick {

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo + 1, j = hi;
        while (true) {
            while (Sort.less(a[i], a[lo])) {
                i++;
                if (i > hi) break;
            }
            while (Sort.less(a[lo], a[j])) {
                j--;
                if (j < lo) break;
            }
            if (i >= j) break;
            Sort.exch(a, i, j);
            i++;
            j--;
        }
        Sort.exch(a, lo, j);
        return j;
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (lo < hi) {
            int j = partition(a, lo, hi);
            if (j > k) hi = j - 1;
            else if (j < k) lo = j + 1;
            else return a[k];
        }
        return a[k];
    }
}
