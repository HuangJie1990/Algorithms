package c2;

import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Transaction;

public class TopM {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int m = in.readInt();
        PriorityQueue<Transaction> pq = new MinPQ();
        while (!in.isEmpty()) {
            String t = in.readLine();
            Transaction transaction = new Transaction(t);

        }
    }
}
