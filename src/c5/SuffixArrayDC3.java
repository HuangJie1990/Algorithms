package c5;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author huangjie
 * @create 2018-04-09-10:50
 **/
public class SuffixArrayDC3 {

    public static void createSuffixArray(String str, int[] sa) {
        if (sa.length != str.length())
            throw new IllegalArgumentException();

        int N = str.length();

        int[] s = new int[N + 3];
        int[] SA = new int[N + 3];

        for (int i = 0; i < N; i++) {
            s[i] = str.charAt(i);
        }

        makeSuffixArray(s, SA, N, 256);

        for (int i = 0; i < N; i++) {
            sa[i] = SA[i];
        }
    }

    private static int assignNames(int[] s, int[] s12, int[] SA12, int n0, int n12, int K) {
        radixPass(s12, SA12, s, 2, n12, K);
        radixPass(SA12, s12, s, 1, n12, K);
        radixPass(s12, SA12, s, 0, n12, K);

        int name = 0;
        int c0 = -1, c1 = -1, c2 = -1;
        for (int i = 0; i < n12; i++) {
            if (s[SA12[i]] != c0 || s[SA12[i] + 1] != c1 || s[SA12[i] + 2] != c2) {
                name++;
                c0 = s[SA12[i]];
                c1 = s[SA12[i] + 1];
                c2 = s[SA12[i] + 2];
            }

            if (SA12[i] % 3 == 1)
                s12[SA12[i] / 3] = name;
            else
                s12[SA12[i] / 3 + n0] = name;
        }
        return name;
    }

    public static void makeSuffixArray(int[] s, int[] SA, int n, int K) {
        int n0 = (n + 2) / 3;
        int n1 = (n + 1) / 3;
        int n2 = n / 3;
        int t = n0 - n1;
        int n12 = n1 + n2 + t;

        int[] s12 = new int[n12 + 3];
        int[] SA12 = new int[n12 + 3];
        int[] s0 = new int[n0];
        int[] SA0 = new int[n0];

        // generate position in s for items in s12, the "+t" adds a dummy S1 suffix if n%3==1 at that point, the size of s12 is n12
        for (int i = 0, j = 0; i < n + t; i++) {
            if (i % 3 != 0)
                s12[j++] = i;
        }

        int K12 = assignNames(s, s12, SA12, n0, n12, K);

        computerS12(s12, SA12, n12, K12);
        computerS0(s, s0, SA0, SA12, n0, n12, K);
        merge(s, s12, SA, SA0, SA12, n, n0, n12, t);

    }

    private static void radixPass(int[] in, int[] out, int[] s, int n, int K) {
        radixPass(in, out, s, 0, n, K);
    }

    private static void radixPass(int[] in, int[] out, int[] s, int offset, int n, int K) {
        int[] count = new int[K + 2];

        for (int i = 0; i < n; i++) {
            count[s[in[i] + offset] + 1]++;
        }

        for (int i = 1; i <= K + 1; i++) {
            count[i] += count[i - 1];
        }

        for (int i = 0; i < n; i++) {
            out[count[s[in[i] + offset]]++] = in[i];
        }
    }

    private static void computerS12(int[] s12, int[] SA12, int n12, int K12) {
        if (K12 == n12)
            for (int i = 0; i < n12; i++) {
                SA12[s12[i] - 1] = i;
            }

        else {
            makeSuffixArray(s12, SA12, n12, K12);
            for (int i = 0; i < n12; i++) {
                s12[SA12[i]] = i + 1;
            }
        }
    }

    private static void computerS0(int[] s, int[] s0, int[] SA0, int[] SA12, int n0, int n12, int K) {
        for (int i = 0, j = 0; i < n12; i++) {
            if (SA12[i] < n0)
                s0[j++] = 3 * SA12[i];
        }

        radixPass(s0, SA0, s, n0, K);
    }

    private static void merge(int[] s, int[] s12, int[] SA, int[] SA0, int[] SA12, int n, int n0, int n12, int t) {
        int p = 0, k = 0;
        while (t != n12 && p != n0) {
            int i = getIndexIntoS(SA12, t, n0); // S12 index in s
            int j = SA0[p];   // S0 index in s

            if (suffix12IsSmaller(s, s12, SA12, n0, i, j, t)) {
                SA[k++] = i;
                t++;
            } else {
                SA[k++] = j;
                p++;
            }
        }

        while (p < n0)
            SA[k++] = SA0[p++];
        while (t < n12)
            SA[k++] = getIndexIntoS(SA12, t++, n0);
    }

    private static int getIndexIntoS(int[] SA12, int t, int n0) {
        if (SA12[t] < n0)
            return SA12[t] * 3 + 1;
        else return (SA12[t] - n0) * 3 + 2;
    }

    private static boolean leq(int a1, int a2, int b1, int b2) {
        return a1 < b1 || a1 == b1 && a2 <= b2;
    }

    private static boolean leq(int a1, int a2, int a3, int b1, int b2, int b3) {
        return a1 < b1 || a1 == b1 && leq(a2, a3, b2, b3);
    }

    private static boolean suffix12IsSmaller(int[] s, int[] s12, int[] SA12, int n0, int i, int j, int t) {
        if (SA12[t] < n0)
            return leq(s[i], s12[SA12[t] + n0], s[j], s12[j / 3]);
        else
            return leq(s[i], s[i + 1], s12[SA12[t] - n0 + 1], s[j], s[j + 1], s12[j / 3 + n0]);
    }

    public static void main(String[] args) {
        String s = args[0];
        int[] sa = new int[s.length()];
        createSuffixArray(s, sa);
//        int[] index = new int[s.length()];
//        for (int i = 0; i < index.length; i++) {
//            index[sa[i]] = i;
//        }
        for (int i = 0; i < s.length(); i++) {
            StdOut.println(i + ": " + sa[i]);
        }
    }
}
