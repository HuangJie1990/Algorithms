package w11;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SuffixArray;

/**
 * @author huangjie
 * @create 2018-03-19-11:21
 **/
public class CircularSuffixArray {
    private SuffixArray suffixArray;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        suffixArray = new SuffixArray(s);
    }

    // length of s
    public int length() {
        return suffixArray.length();
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        validate(i);
        return suffixArray.index(i);
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
        if (i < 0 || i >= suffixArray.length()) throw new IllegalArgumentException("index out of range");
    }
}
