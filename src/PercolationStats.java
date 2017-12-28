import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author huangjie
 * @create 2017-12-28-18:01
 * @description to estimate percolation threshold by Monte Carlo simulation
 **/
public class PercolationStats {

    private int times;
    private double[] threshold;

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
            threshold[i] = (double) percolation.numberOfOpenSites() /(n * n);
        }
    }

    /**
     * @param
     * @return double,
     * @description sample mean of percolation threshold
     **/
    public double mean() {
        double sum = 0.0;
        for (double d : threshold) {
            sum += d;
        }
        return sum / times;
    }

    /**
     * @param
     * @return double
     * @description sample standard deviation of percolation threshold
     **/
    public double stddev() {
        if (times == 1) return Double.POSITIVE_INFINITY;
        double mean = mean();
        double sum = 0.0;
        for (double d : threshold) {
            sum += Math.pow(d - mean, 2);
        }
        return Math.sqrt(sum / (times - 1));
    }

    /**
     * @param
     * @return double
     * @description low endpoint of 95% confidence interval
     **/
    public double confidenceLo() {
        double mean = mean();
        double stddev = stddev();
        return mean - 1.96 * stddev / Math.sqrt(times);
    }

    /**
     * @param
     * @return double
     * @description high endpoint of 95% confidence interval
     **/
    public double confidenceHi() {
        double mean = mean();
        double stddev = stddev();
        return mean + 1.96 * stddev / Math.sqrt(times);
    }

    /**
     * @param
     * @return void
     * @description test client
     **/
    public static void main(String[] args) {
        PercolationStats percolationStats=new PercolationStats(100,1000);
        StdOut.println(percolationStats.mean());
        StdOut.println("confidenceLo is "+percolationStats.confidenceLo()+", confidenceHi is "+percolationStats.confidenceHi());
    }
}
