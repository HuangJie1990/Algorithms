package c1;

import com.sun.org.apache.bcel.internal.classfile.Constant;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class Excise {

    private Point[] points;
    private double x = 0.5;
    private double y = 0.5;
    private double r = 0.4;
    private int N;


    /*
    1.1.14
     */
    public static int lg(int value) {
        if (value < 1) throw new IllegalArgumentException("argument value must bigger than 0");
        int lg = 0;
        while (value > 1) {
            value = value / 2;
            lg++;
        }
        return lg;
    }

    public static double lnFactorial(int n) {
        if (n < 0) throw new IllegalArgumentException("argument value must bigger than 0");
        if (n == 0) return 0;
        return Math.log(n) + lnFactorial(n - 1);
    }

    private static int biTimes;
    private static double[][] bi;


    public static double binomial(int N, int k, double p) {
        /*biTimes++;
        if (N == 0 && k == 0) return 1.0;
        if (N < 0 || k < 0) return 0.0;
        return (1 - p) * binomial(N - 1, k, p) + p * binomial(N - 1, k - 1, p);*/

        bi = new double[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    bi[i][j] = Math.pow(1 - p, i);
                    continue;
                } else if (j == i && i > 0) {
                    bi[i][j] = Math.pow(p, i);
                    continue;
                } else bi[i][j] = (1 - p) * bi[i - 1][j] + p * bi[i - 1][j - 1];
            }
        }
        return bi[N][k];
    }

    public static void main(String[] args) {
        /*for (int i = 1; i < 33; i++) {
            StdOut.println("the result of lg(" + i + ") is " + lg(i));
            StdOut.println("the result of ln(" + i + "!) is " + lnFactorial(i));
        }*/

        /*
        1.1.31
         */
       /* Excise excise = new Excise();
        excise.N = Integer.parseInt(args[0]);
        excise.points = new Point[excise.N];
        double p = Double.parseDouble(args[1]);
        StdDraw.setPenRadius(0.001);
        StdDraw.circle(excise.x, excise.y, excise.r);
        StdDraw.setPenRadius(0.005);
        excise.createPoints();
        for (Point point : excise.points) {
            excise.DrawPoint(point);
        }
        StdDraw.setPenRadius(0.001);
        for (int i = 0; i < excise.N - 1; i++) {
            for (int j = i + 1; j < excise.N; j++) {
                StdOut.printf("check %d %d\n", i, j);
                Boolean b = StdRandom.bernoulli(p);
                if (b) excise.DrawLine(excise.points[i], excise.points[j]);
                else continue;
            }
        }*/

       /* double d = binomial(100, 50, 0.25);
        System.out.printf("the result is %f and loops %d times", d, biTimes);*/
    }

    private class Point {
        public double x;
        public double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }


    private void DrawPoint(Point point) {
        StdDraw.point(point.x, point.y);
    }

    private void DrawLine(Point a, Point b) {
        StdDraw.line(a.x, a.y, b.x, b.y);
    }

    private void createPoints() {
        double degree = 2 * Math.PI / N;
        for (int i = 0; i < N; i++) {
            double xi = x + r * Math.cos(i * degree);
            double yi = y + r * Math.sin(i * degree);
            points[i] = new Point(xi, yi);
        }
    }
}
