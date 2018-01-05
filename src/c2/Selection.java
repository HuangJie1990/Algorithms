package c2;

/**
 * @author
 * @create 2018-01-05-10:51
 **/
public class Selection {

    private Selection(){}

    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i; j < a.length; j++) {
                if (Sort.less(a[j], a[min])) min = j;
            }
            Sort.exch(a, i, min);
            assert Sort.isSorted(a, 0, i);
        }
    }
}
