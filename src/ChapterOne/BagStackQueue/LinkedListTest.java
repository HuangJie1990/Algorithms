package ChapterOne.BagStackQueue;

import edu.princeton.cs.algs4.StdOut;

public class LinkedListTest {

    public static void main(String[] args){
        LinkedList<Integer> linkedList=new LinkedList<>();
        StdOut.println("******************************");
        for(int i:linkedList) StdOut.print(i+" ");
        StdOut.println("the size of linkedlist is "+linkedList.size());
        StdOut.println("******************************");
        for (int i = 0; i < 3; i++) {
            linkedList.add(0);
        }
        for (int i = 1; i < 10; i++) {
            linkedList.add(i);
        }
        linkedList.add(0);
        StdOut.println(linkedList.find(0));
        StdOut.println("the size of linkedlist is "+linkedList.size());
        StdOut.println("******************************");

        for(int i:linkedList) StdOut.print(i+" ");
        StdOut.println();
        StdOut.println("the size of linkedlist is "+linkedList.size());
        StdOut.println("******************************");

        linkedList.delete(1);
        for(int i:linkedList) StdOut.print(i+" ");
        StdOut.println();
        StdOut.println("the size of linkedlist is "+linkedList.size());
        StdOut.println("******************************1");

        linkedList.delete(2);
        for(int i:linkedList) StdOut.print(i+" ");
        StdOut.println();
        StdOut.println("the size of linkedlist is "+linkedList.size());
        StdOut.println("******************************2");

        linkedList.remove(linkedList.head(),0);
        for(int i:linkedList) StdOut.print(i+" ");
        StdOut.println();
        StdOut.println("the size of linkedlist is "+linkedList.size());
        StdOut.println("******************************3");
    }
}
