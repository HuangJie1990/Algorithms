package ChapterTwo;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Insertion {

    private Insertion() {
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void show(Comparable[] a) {
        for (Comparable c :
                a) {
            StdOut.print(c + " ");
        }
    }

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exchange(a, j - 1, j);
            }
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
                exchange(a, j - 1, j);
            }
            assert isSorted(a, lo, i);
        }
        assert isSorted(a, lo, hi);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            if (less(a[i + 1], a[i])) return false;
        }
        return true;
    }

    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    public static void main(String[] args) {
        String[] s = new String[1];
        while (!StdIn.isEmpty()) {
            for (int i = 0; i < s.length; i++) {
                s[i] = StdIn.readString();
            }
        }
        StdRandom.shuffle(s);
        Insertion.show(s);
        StdOut.println();
        Insertion.sort(s);
        Insertion.show(s);
    }
}
