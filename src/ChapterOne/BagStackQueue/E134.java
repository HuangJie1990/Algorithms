package ChapterOne.BagStackQueue;

import edu.princeton.cs.algs4.StdOut;

public class E134 {

    public static void main(String[] args) {
        StdOut.println(parentheses(args));
    }

    public static boolean parentheses(String[] strings) {
        ChainTableStack<String> chainTableStack = new ChainTableStack<>();
        for (int i = 0; i < strings.length; i++) {
            String s = strings[i];
            if (s.equals("{") || s.equals("[") || s.equals("(")) chainTableStack.push(s);
            else {
                switch (s) {
                    case "}":
                        if (chainTableStack.peek().equals("{")) chainTableStack.pop();
                        break;
                    case "]":
                        if (chainTableStack.peek().equals("[")) chainTableStack.pop();
                        break;
                    case ")":
                        if (chainTableStack.peek().equals("(")) chainTableStack.pop();
                        break;
                }
            }
        }
        return chainTableStack.isEmpty();
    }
}
