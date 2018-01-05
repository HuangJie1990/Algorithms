package c2;

/**
 * @author
 * @create 2018-01-05-18:34
 **/
public class Merge {

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        assert Sort.isSorted(a, lo, mid);
        assert Sort.isSorted(a, mid + 1, hi);
        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (Sort.less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
        assert Sort.isSorted(a, lo, hi);
    }

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        merge(a, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[n];
        sort(a, aux, 0, n - 1);
    }
}
