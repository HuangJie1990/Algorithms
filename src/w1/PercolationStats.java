package w1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * @author huangjie
 * @create 2017-12-28-18:01
 * @description to estimate percolation threshold by Monte Carlo simulation
 **/
public class PercolationStats {

    private int times;
    private double[] threshold;
    private double mean;
    private double stddev;

    /**
     * @param n      the order of grid
     * @param trials the times of trails
     * @return
     * @description perform trails independent experiment on an n-by-n grid
     **/
    public PercolationStats(int n, int trials) {
        times = trials;
        threshold = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                percolation.open(row, col);
            }
            threshold[i] = (double) percolation.numberOfOpenSites() / (n * n);
        }
        mean = StdStats.mean(threshold);
        stddev = StdStats.stddev(threshold);
    }

    /**
     * @param
     * @return double,
     * @description sample mean of percolation threshold
     **/
    public double mean() {
        return mean;
    }

    /**
     * @param
     * @return double
     * @description sample standard deviation of percolation threshold
     **/
    public double stddev() {
        return stddev;
    }

    /**
     * @param
     * @return double
     * @description low endpoint of 95% confidence interval
     **/
    public double confidenceLo() {
        return mean - 1.96 * stddev / Math.sqrt(times);
    }

    /**
     * @param
     * @return double
     * @description high endpoint of 95% confidence interval
     **/
    public double confidenceHi() {
        return mean + 1.96 * stddev / Math.sqrt(times);
    }

    /**
     * @param
     * @return void
     * @description test client
     **/
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, trails);
        StdOut.printf("%-25s= %f\n", "mean", percolationStats.mean());
        StdOut.printf("%-25s= %f\n", "stddev", percolationStats.stddev());
        StdOut.printf("%-25s= [%f, %f]\n", "95% confidence interval", percolationStats.confidenceLo(), percolationStats.confidenceHi());
    }
}
