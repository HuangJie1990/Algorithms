package c2;

/**
 * @author
 * @create 2018-01-05-10:51
 **/
public class Selection {

    private Selection() {
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            //比较N-j次，共比较（N-1)N/2次
            for (int j = i + 1; j < N; j++) {
                if (Sort.less(a[j], a[min])) min = j;
            }
            //每次外循环交换一次，最后一次循环最后一个元素自交换，共交换N次
            Sort.exch(a, i, min);
            assert Sort.isSorted(a, 0, i);
        }
    }
}
