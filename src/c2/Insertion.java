package c2;

/**
 * @author
 * @create 2018-01-05-9:36
 **/
public class Insertion {
    private Insertion() {
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            /*这种写法任何情况下都比较i次，总共比较(N-1)N/2次
            for (int j = i; j > 0; j--) {
                if (Sort.less(a[j], a[j - 1])) {
                    Sort.exch(a, j, j - 1);
                }
            }
            */
            //这种写法只有在最坏情况下比较(N-1)N/2次，最好情况下比较(N-1)次
            /*for (int j = i; j > 0 && Sort.less(a[j], a[j - 1]); j--) {

                //最坏情况下交换(N-1)N/2次，最好情况下交换0次
                Sort.exch(a, j, j - 1);
            }*/
            int j = i;
            while (j > 0 && Sort.less(a[j], a[j - 1])) {
                Sort.exch(a, j, j - 1);
                j--;
            }
            assert Sort.isSorted(a, 0, i);
        }
        assert Sort.isSorted(a);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            for (int j = i; j >= lo + 1 && Sort.less(a[j], a[j - 1]); j--) {
                Sort.exch(a, j, j - 1);
            }
            assert Sort.isSorted(a, lo, i);
        }
        assert Sort.isSorted(a, lo, hi);
    }
}
