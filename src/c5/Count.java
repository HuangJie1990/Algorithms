package c5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Count {
    public static void main(String[] args){
        Alphabet alphabet=new Alphabet(args[0]);
        int R=alphabet.R();
        int[] count=new int[R];

        In in=new In(args[1]);
        String s=in.readString();
        int N=s.length();
        for (int i = 0; i < N; i++) {
            if(alphabet.contains(s.charAt(i))) count[alphabet.toIndex(s.charAt(i))]++;
        }
        for (int i = 0; i < R; i++) {
            StdOut.println(alphabet.toChar(i)+" "+count[i]);
        }
        String string="asd";
        char c=string.charAt(1);
        StdOut.println(string.charAt(2));
    }
}
