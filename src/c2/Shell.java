package c2;

/**
 * @author
 * @create 2018-01-05-11:07
 **/
public class Shell {

    private Shell() {
    }

    public static void sort(Comparable[] a) {
        int h = 1;
        while (h < a.length / 3) {
            h = 3 * h + 1;
        }

        while (h > 0) {
            for (int i = h; i < a.length; i++) {
                for (int j = i; j >= h && Sort.less(a[j], a[j - h]); j -= h) {
                    Sort.exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
        assert Sort.isSorted(a);
    }
}
