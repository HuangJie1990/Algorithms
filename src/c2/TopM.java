package c2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

public class TopM {
    public static void main(String[] args) {
        int M=Integer.parseInt(args[0]);
        In in = new In(args[1]);
        PriorityQueue<Transaction> pq = new MaxPQ<>(0);
        StdOut.println(pq.max());
        StdOut.println(pq.delMax());
        while (!in.isEmpty()) {
            String t = in.readLine();
            Transaction transaction = new Transaction(t);
            pq.insert(transaction);
//            if (pq.size() > M) pq.delMin();
        }
        for (Transaction t :
                pq) {
            StdOut.println(t);
        }
        StdOut.println("------------------------------------------");
        Stack<Transaction> stack = new Stack<>();
        while (!pq.isEmpty()) stack.push(pq.delMax());
        for (Transaction t :
                stack) {
            StdOut.println(t);
        }
    }
}
