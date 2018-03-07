package c2;

/**
 * @author
 * @create 2018-01-05-19:50
 **/
public class MergeBU {

    static Comparable[] aux;

    public static void sort(Comparable[] a) {
        int n = a.length;
        aux = new Comparable[n];
        for (int size = 1; size < n; size += size) {
            for (int lo = 0; lo < n - size; lo += size * 2) {
                int mid = lo + size - 1, hi = Math.min(lo + size * 2 - 1, n - 1);
                merge(a, lo, mid, hi);
            }
        }
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        Merge.merge(a, aux, lo, mid, hi);
    }
}
