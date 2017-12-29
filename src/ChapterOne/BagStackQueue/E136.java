package ChapterOne.BagStackQueue;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class E136{
    //反转queue
    public static void main(String[] args){
        Stack<String> stack=new Stack<>();
        Queue<String> queue=new Queue<>();
        queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
        while (!queue.isEmpty()) stack.push(queue.dequeue());
        while (!stack.isEmpty()) queue.enqueue(stack.pop());
    }
}
