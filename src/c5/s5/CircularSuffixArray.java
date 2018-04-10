package c5.s5;

import edu.princeton.cs.algs4.MSD;
import edu.princeton.cs.algs4.Merge;

import java.lang.reflect.Array;

/**
 * @author
 * @create 2018-04-10-15:10
 **/
public class CircularSuffixArray {
    private int N;
    private int sa[];

    public CircularSuffixArray(String s) {
        N = s.length();
        Suffix suffixes[] = new Suffix[N];

        for (int i = 0; i < N; i++) {
            suffixes[i].index = i;
            suffixes[i].rank[0] = charAt(s, i);
            suffixes[i].rank[1] = charAt(s, i + 1);
        }

        Merge.sort();
    }

    public int length() {
        return N;
    }

    public int index(int i) {
        return sa[i];
    }

    private class Suffix implements Comparable<Suffix> {
        int index;
        int rank[];

        @Override
        public int compareTo(Suffix o) {

        }
    }

    private int charAt(String s, int i) {
        return i < s.length() ? s.charAt(i) : s.charAt(i - s.length());
    }
}
