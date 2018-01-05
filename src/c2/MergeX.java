package c2;

/**
 * @author
 * @create 2018-01-05-19:14
 **/
public class MergeX {

    private static final int CUT_OFF = 7;

    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (lo >= hi - CUT_OFF) {
            Insertion.sort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        if (Sort.notGreater(a[mid], a[mid + 1])) return;
        Merge.merge(a, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] a){
        int n=a.length;
        Comparable[] aux=new Comparable[n];
        sort(a,aux,0,n-1);
    }
}
