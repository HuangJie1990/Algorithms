package w11;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * @author huangjie
 * @create 2018-03-19-14:52
 **/
public class BurrowsWheeler {

    // apply Burrows-Wheeler transform, reading from standard input and writing to standard output
    public static void transform() {
        String s = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        int i = 0;
        while (csa.index(i) != 0) {
            i = csa.index(i);
        }
        BinaryStdOut.write(i);
        for (int j = 0; j < csa.length(); j++) {
            int row = csa.index(j);
            if (row == 0) BinaryStdOut.write(s.charAt(s.length() - 1));
            else BinaryStdOut.write(s.charAt(row - 1));
        }
        BinaryStdOut.close();

//        StdOut.println(i);
//        for (int j = 0; j < csa.length(); j++) {
//            int row = csa.index(j);
//            if (row == 0) StdOut.print(s.charAt(s.length() - 1));
//            else StdOut.print(s.charAt(row - 1));
//        }
    }

    // apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output
    public static void inverseTransform() {
        // deduce the first column
        int first = BinaryStdIn.readInt();
//        StdOut.println("first:" + first);
        StringBuilder sb = new StringBuilder();
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
//            StdOut.print(c);
            sb.append(c);
        }
        String s = sb.toString();
//        StdOut.println("s:" + s);
        char[] t = s.toCharArray();
        char[] head = Arrays.copyOf(t, t.length);
        Arrays.sort(head);
//        StdOut.println("next array:");
//        for (char c : t) StdOut.print(c + " ");
//        StdOut.println();
//        StdOut.println("next array:");
//        for (char c : head) StdOut.print(c + " ");
//        StdOut.println();

        // construct the next[] array from t[] and first
        int[] next = new int[t.length];
        boolean[] marked = new boolean[t.length];
        for (int i = 0; i < head.length; i++) {
            for (int j = 0; j < t.length; j++) {
                if (head[i] == t[j] && !marked[j]) {
                    next[i] = j;
                    marked[j] = true;
                    break;
                }
            }
        }
//        StdOut.println("next array:");
//        for (int i : next) StdOut.print(i + " ");
//        StdOut.println();

        // reconstruct the original input string
        for (int i = 0; next[i] != 0; ) {
            int j = next[i];
            StdOut.print(head[j]);
            i = j;
        }
        StdOut.print(head[0]);
    }

    public static void main(String[] args) {
        if (args[0].contentEquals("-")) BurrowsWheeler.transform();
        else if (args[0].contentEquals("+")) BurrowsWheeler.inverseTransform();
    }
}
