/******************************************************************************
 *  Compilation:  javac SocialNetworkConnectivity.java
 *  Execution:    java SocialNetworkConnectivity < input.txt
 *  Dependencies: In.java StdOut.java SeparateChainingHashST.java WeightedQuickUnionUF.java
 ******************************************************************************/
package w1;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author
 * @create 2017-12-30-18:46
 **/
public class SocialNetworkConnectivity {

    private final In in;
    private final WeightedQuickUnionUF uf;
    private final SeparateChainingHashST<String, Integer> st;
    //the earliest time at which all members are connected
    private String time;
    private int value;

    public SocialNetworkConnectivity(In input) {
        in = input;
        int n = in.readInt();
        if (n <= 0) throw new IllegalArgumentException("input is illegal: the count of members must larger than 0");
        int m = in.readInt();
        if (m <= 0) throw new IllegalArgumentException("input is illegal: the count of timestamps must larger than 0");
        uf = new WeightedQuickUnionUF(n);
        st = new SeparateChainingHashST<>(n / 3);
        int value = 0;
        while (!in.isEmpty()) {
            String timestamp = in.readString();
            String ma = in.readString();
            add(ma);
            String mb = in.readString();
            add(mb);
            friend(ma, mb);
            if (uf.count() == 1 && st.size() == n) {
                time = timestamp;
                return;
            }
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        SocialNetworkConnectivity snc = new SocialNetworkConnectivity(in);
        StdOut.println(snc.time);
    }

    //add member to st if st does contain this member already
    private void add(String key) {
        if (!st.contains(key))
            st.put(key, value++);
    }

    //union members that formed friendship
    private void friend(String ma, String mb) {
        uf.union(st.get(ma), st.get(mb));
    }

    public String time() {
        return time;
    }
}
