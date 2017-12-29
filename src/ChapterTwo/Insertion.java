package ChapterTwo;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Insertion {

    private Insertion() {
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
//    public static void sort(Comparable[] a) {
//        int N = a.length;
//        for (int i = 1; i < N; i++) {
//            for (int j = i; j > 0; j--) {
//                if (less(a[j], a[j - 1])) {
//                    exch(a, j, j - 1);
//                    show(a);
//                    System.out.println();
//                } else {
//                    break;
//                }
//            }
//            System.out.println();
//        }
//    }

    public static void sort(Comparable[] a) {
//        StdRandom.shuffle(a);
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }
}
