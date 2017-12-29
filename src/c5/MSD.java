package c5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 高位优先的字符串排序
 */
public class MSD {

    private static Alphabet alphabet = Alphabet.LOWERCASE;
    private static int R = alphabet.R();
    private static final int M = 0;
    private static String[] aux;


    private static int charAt(String a, int d) {
        assert d >= 0 && d <= a.length();
        if (d < a.length()) return a.charAt(d);
        return -1;
    }

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[a.length];
        sort(a, 0, N - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo + M) return;
        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++) {
            count[alphabet.charAt(a[i], d) + 2]++;
        }
        for (int i = 0; i < R + 1; i++) {
            count[i + 1] += count[i];
        }
        for (int i = lo; i <= hi; i++) {
            aux[count[alphabet.charAt(a[i], d) + 1]++] = a[i];
        }
        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i - lo];
        }
        for (int r = 0; r < R; r++) {
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }

    public static void main(String[] args) {
        In in = new In("src\\c5\\lsd.txt");
        String[] a = in.readAllStrings();
        sort(a);
        for (String s : a) StdOut.println(s);
    }

}
