package c1;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author
 * @create 2018-01-25-10:28
 **/
public class Fibonacci {

    private static int[] f;

    private Fibonacci() {
    }

    private Fibonacci(int n) {
        f = new int[n + 1];
        f[0] = 0;
        f[1] = 1;
    }

//    public static int fibonacci(int n) {
//        if (n == 0) return 0;
//        if (n == 1) return 1;
//        return fibonacci(n - 1) + fibonacci(n - 2);
//    }

    public static int fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        Fibonacci fi = new Fibonacci(n);
        for (int i = 2; i <= n; i++) {
            fi.f[i] = fi.f[i - 1] + fi.f[i - 2];
        }
        return fi.f[n];
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        for (int i = 0; i <= n; i++) {
            StdOut.printf("f(%d)=%d\n", i, fibonacci(i));
        }
    }
}
