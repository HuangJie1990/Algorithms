package w11;

import c3.BST;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author huangjie
 * @create 2018-03-19-11:21
 **/
public class CircularSuffixArray {
    private final int n;
    private String[] suffixes;
    private int[] index;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null) throw new IllegalArgumentException("argument is null");
        n = s.length();
        index = new int[n];
        suffixes = new String[n];
        for (int i = 0; i < n; i++) {
            suffixes[i] = s.substring(i, n) + s.substring(0, i);
        }
        BST<String, Integer> bst = new BST<>();
        for (int i = 0; i < n; i++) {
            bst.put(suffixes[i], i);
        }
        for (int i = 0; i < n; i++) {
            index[bst.rank(suffixes[i])] = i;
        }
    }

    // length of s
    public int length() {
        return n;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        validate(i);
        return index[i];
    }

    //unit testing (required)
    public static void main(String[] args) {
        String s = args[0];
        CircularSuffixArray csa = new CircularSuffixArray(s);
        StdOut.println(csa.length());
        for (int i = 0; i < csa.length(); i++) {
            StdOut.println(i + ": " + csa.index(i));
        }
    }

    private void validate(int i) {
        if (i < 0 || i >= n) throw new IllegalArgumentException("index out of range");
    }
}
