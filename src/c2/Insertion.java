package c2;

/**
 * @author
 * @create 2018-01-05-9:36
 **/
public class Insertion {
    private Insertion() {
    }

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 1 && Sort.less(a[j], a[j - 1]); j--) {
                Sort.exch(a, j, j - 1);
            }
            assert Sort.isSorted(a, 0, i);
        }
        assert Sort.isSorted(a);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            for (int j = i; j >= 1 && Sort.less(a[j], a[j - 1]); j--) {
                Sort.exch(a, j, j - 1);
            }
            assert Sort.isSorted(a, 0, i);
        }
        assert Sort.isSorted(a, lo, hi);
    }
}
