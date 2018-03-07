package c5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Quick3String {

    private static int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }

    public static void sort(String[] a) {
        sort(a, 0, a.length - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < v) exchange(a, lt++, i++);
            else if (t > v) exchange(a, gt--, i);
            else i++;
        }
        sort(a, lo, lt - 1, d);
        if (v >= 0) sort(a, lt, gt, d + 1);
        sort(a, gt + 1, hi, d);
    }

    private static void exchange(String[] a, int v, int w) {
        String temp = a[v];
        a[v] = a[w];
        a[w] = temp;
    }

    public static void main(String[] args) {
        In in = new In("src\\c5\\msd.txt");
        String[] a = in.readAllStrings();
        sort(a);
        for (String s : a) StdOut.println(s);
    }
}
