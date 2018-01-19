package c2;


/**
 * @author
 * @create 2018-01-15-10:23
 **/
public class MergeS {
    private MergeS() {
    }

    public static void sort(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[1 + (n - 1) / 2];
        sort(a, aux, 0, n - 1);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert Sort.isSorted(a, lo, mid);
        assert Sort.isSorted(a, mid + 1, hi);

        int i = lo, j = mid + 1, ll = mid - lo + 1, rl = hi - mid;
        for (int k = 0; k <= ll; k++) {
            if (i > mid) {
                break;
            } else if (j > hi) {
                moveRange(a, i, mid, rl);
                System.arraycopy(aux, 0, a, lo, rl + i - lo);
                break;
            } else if (i + j == 2 * (mid + 1)) {
                moveRange(a, i, mid, j - mid - 1);
                System.arraycopy(aux, 0, a, lo, ll);
                sort(a, aux, mid + 1, hi);
                break;
            } else {
                if (Sort.less(a[i], a[j])) aux[k] = a[i++];
                else aux[k] = a[j++];
            }
        }
        assert Sort.isSorted(a, lo, hi);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        if (Sort.notGreater(a[mid], a[mid + 1])) return;
        merge(a, aux, lo, mid, hi);
    }

    private static void move(Comparable[] a, int i, int distance) {
        a[i + distance] = a[i];
    }

    private static void moveRange(Comparable[] a, int i, int j, int distance) {
        for (int k = j; k >= i; k--) {
            move(a, k, distance);
        }
    }
}
