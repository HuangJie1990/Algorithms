package c1.s3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Dijkstra {
    public static void main(String[] args) {
        Stack<Double> values = new LinkedStack<>();
        Stack<String> ops = new ArrayStack<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("(")) continue;
            else if (s.equals("+")) ops.push(s);
            else if (s.equals("-")) ops.push(s);
            else if (s.equals("*")) ops.push(s);
            else if (s.equals("/")) ops.push(s);
            else if (s.equals("sqrt")) ops.push(s);
            else if (s.equals(")")) {
                String op = ops.pop();
                double v = values.pop();
                if (op.equals("+")) v = values.pop() + v;
                else if (op.equals("-")) v = values.pop() - v;
                else if (op.equals("*")) v = values.pop() * v;
                else if (op.equals("/")) v = values.pop() / v;
                else if (op.equals("sqrt")) v = Math.sqrt(v);
                values.push(v);
            } else values.push(Double.parseDouble(s));
        }
        StdOut.println(values.pop());
    }
}
