package ChapterOne.BagStackQueue;

import edu.princeton.cs.algs4.StdOut;

/**
 * @Author 黄杰
 * @Date 2017-11-09 11:23
 */
public class FixedCapacityStackOfStrings {

    //用一个数组保存数据
    private String[] strings;

    //用一个整数保存栈大小
    private int n;

    public FixedCapacityStackOfStrings(int cap) {
        strings = new String[cap];
    }

    public static void main(String[] args) {
        FixedCapacityStackOfStrings fixedCapacityStackOfStrings = new FixedCapacityStackOfStrings(10);
        for (int i = 0; i < 10; i++) {
            fixedCapacityStackOfStrings.push(Integer.toString(i));
            StdOut.println(String.format("The stack push %s out, the size of stack is %d, is the stack empty: %b, is the stack full: %b", Integer.toString(i), fixedCapacityStackOfStrings.size(), fixedCapacityStackOfStrings.isEmpty(), fixedCapacityStackOfStrings.isFull()));
        }


        while (!fixedCapacityStackOfStrings.isEmpty()) {
            String s = fixedCapacityStackOfStrings.pop();
            StdOut.println(String.format("The stack pop %s out, the size of stack is %d, is the stack empty: %b", s, fixedCapacityStackOfStrings.size(), fixedCapacityStackOfStrings.isEmpty()));
        }
    }

    public void push(String string) {
        //相当于strings[n]=string;n+=1；
        strings[n++] = string;
    }

    public String pop() {
        //相当于n-=1;return strings[n];
        return strings[--n];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public boolean isFull() {
        return n == strings.length;
    }
}
