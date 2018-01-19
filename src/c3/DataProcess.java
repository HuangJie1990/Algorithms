package c3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DataProcess {

    public static void main(String[] args) {
        In in = new In(args[0]);
        BST<String, Integer> bst = new BST<>();
        while (!in.isEmpty()) {
            String s = in.readString();
            int i = in.readInt();
            bst.put(s, i);
            StdOut.print(bst.size());
            for (String key : bst.keys()) {
                StdOut.print(key + " ");
            }
            StdOut.println();
        }
        for (int i = 0; i < bst.size(); i++) {
            StdOut.print(i + ":" + bst.select(i) + "-" + bst.get(bst.select(i)) + ", ");
        }
        StdOut.println();
        for (int i = 'A'; i < 'Z'; i++) {
            StdOut.print(Character.toString((char) i) + ":" + bst.rank(Character.toString((char) i)) + ", ");
        }
        StdOut.println();
        for (String key :
                bst.keys("D", "V")) {
            StdOut.print(key + " ");
        }
        StdOut.print(bst.size("D","V"));
    }
}
