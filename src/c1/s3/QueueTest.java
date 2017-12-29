package c1.s3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QueueTest {
    public static void main(String[] args){
        Queue<String> queue=new LinkedQueue<>();
        while (!StdIn.isEmpty()){
            String s=StdIn.readString();
            if(!s.equals("-")) queue.enqueue(s);
            else if(!queue.isEmpty()) StdOut.print(queue.dequeue()+" ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
        for(String s:queue){
            StdOut.print(s+" ");
        }
    }
}
