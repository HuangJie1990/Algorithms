package c2;

public class ThreeWay {

    private ThreeWay() {
    }

    private static void partition(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        Comparable v = a[lo];
        int lt = lo, gt = hi, i = lo;
        while (i <= gt) {
            int cmp = v.compareTo(a[i]);
            if (cmp < 0) {
                Sort.exch(a, i, gt);
                gt--;
            } else if (cmp > 0) {
                Sort.exch(a, i, lt);
                i++;
                lt++;
            } else i++;
        }
        partition(a, lo, lt - 1);
        partition(a, gt + 1, hi);
    }

    public static void sort(Comparable[] a) {
        partition(a, 0, a.length - 1);
    }

    public static Comparable[] select(Comparable[] a, Comparable key, int lo, int hi) {
        if (lo >= hi) return null;
        Comparable v = key;
        int lt = lo, gt = hi, i = lo;
        while (i <= gt) {
            int cmp = v.compareTo(a[i]);
            if (cmp > 0) {
                lt++;
                i++;
            }
            else if (cmp < 0) {
                gt--;
            } else i++;
        }
        int n = gt - lt + 1;
        Comparable[] keys = new Comparable[n];
        for (int j = 0; j < n; j++) {
            keys[j] = a[j + lt];
        }
        return keys;
    }
}
