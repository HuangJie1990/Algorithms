package c1.s3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BagTest {
    public static void main(String[] args) {
        Bag<Double> numbers = new ArrayBag<>();
        while (!StdIn.isEmpty()) {
            numbers.add(StdIn.readDouble());
        }
        int N = numbers.size();

        double sum = 0.0;
        for (double d : numbers) {
            sum += d;
        }
        double mean = sum / N;
        sum = 0.0;
        for (double d : numbers) {
            sum += (d - mean) * (d - mean);
        }
        double std = Math.sqrt(sum / (N - 1));
        StdOut.printf("Mean: %2f\n", mean);
        StdOut.printf("Std dev: %2f\n", std);
    }
}
