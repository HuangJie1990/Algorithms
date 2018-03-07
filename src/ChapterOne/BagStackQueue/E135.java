package ChapterOne.BagStackQueue;

import edu.princeton.cs.algs4.StdOut;


public class E135 {

    //把十进制数变为二进制数形式
    public static void main(String[] args) {
        int i = Integer.parseInt(args[0]);
        ChainTableStack<Integer> stack = new ChainTableStack<>();
        while (i > 0) {
            stack.push(i % 2);
            i = i / 2;
        }
        for (int d : stack) {
            StdOut.print(d);
        }
    }
}

