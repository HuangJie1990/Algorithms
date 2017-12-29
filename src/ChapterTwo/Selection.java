package ChapterTwo;

import edu.princeton.cs.algs4.StdOut;

public class Selection {

    private Selection() {
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void sort(Comparable[] a) {

        int N = a.length;

        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i; j < N; j++) {
                if (less(a[j], a[min])) min = j;//比较N-1-i次
//                System.out.println(min + "," + j + ";");
            }
            exch(a, i, min);//交换N次
        }
    }
}
