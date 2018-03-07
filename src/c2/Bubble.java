package c2;

/**
 * @author
 * @create 2018-01-05-13:34
 **/
public class Bubble {

    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = a.length - 1; j > i; j--) {
                if (Sort.less(a[j], a[j - 1]))
                    Sort.exch(a, j - 1, j);
            }
            assert Sort.isSorted(a, 0, i);
        }
        assert Sort.isSorted(a);
    }
}
