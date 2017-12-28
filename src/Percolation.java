import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author huangjie
 * @create 2017-12-28-13:15
 **/
public class Percolation {

    private boolean[] sites;
    private int numberOfOpenSites;
    private int row, col, top, bottom;
    private WeightedQuickUnionUF topUF;
    private WeightedQuickUnionUF topBottomUF;

    /**
     * @param
     * @return
     * @description create n-by-n grid, with all sites blocked
     **/
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("argument to Percolation() must bigger than 0");
        topUF = new WeightedQuickUnionUF(n * n + 1);
        topBottomUF = new WeightedQuickUnionUF(n * n + 2);
        row = n;
        col = n;
        sites = new boolean[n * n + 1];

        //the index of virtual top site
        top = 0;

        //the index of virtual bottom site
        bottom = n * n + 1;
    }

    /**
     * @param @row of site, @col of site
     * @return void
     * @description open site(row, col) if it is not open already
     **/
    public void open(int row, int col) {
        validate(row, col);
        int current = index(row, col);

        //do nothing if this site is already open
        if (isOpen(current)) return;

        sites[current] = true;
        //if this site is in the top row, union it with the virtual top site
        if (row == 1) {
            topUF.union(current, top);
            topBottomUF.union(current, top);
        } else if (row == this.row) {
            topBottomUF.union(current, bottom);
        }

        int u = row - 1;
        if (u >= 1) {
            int upper = index(u, col);
            if (isOpen(upper)) {
                topUF.union(current, upper);
                topBottomUF.union(current, upper);
            }
        }

        int b = row + 1;
        if (b <= this.row) {
            int bottom = index(b, col);
            if (isOpen(bottom)) {
                topUF.union(current, bottom);
                topBottomUF.union(current, bottom);
            }
        }

        int l = col - 1;
        if (l >= 1) {
            int left = index(row, l);
            if (isOpen(left)) {
                topUF.union(current, left);
                topBottomUF.union(current, left);
            }
        }

        int r = col + 1;
        if (r <= this.col) {
            int right = index(row, r);
            if (isOpen(right)) {
                topUF.union(current, right);
                topBottomUF.union(current, right);
            }
        }
        numberOfOpenSites++;
    }

    /**
     * @param {@code row} of site, {@code col} of site
     * @return boolean
     * @description is site(row, col) open?
     **/
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return sites[index(row, col)];
    }

    private boolean isOpen(int i) {
        if (i < 1 || i > sites.length)
            throw new IllegalArgumentException(String.format("argument is an integer varies in the interval [1, %d]", sites.length));
        return sites[i];
    }

    /**
     * {@code row} of site, {@code col} of site
     *
     * @param
     * @return int, the index of site(i, j) in sites array, let sites[0] be the virtual top site
     * @description
     **/
    private int index(int row, int j) {
        return this.row * (row - 1) + j;
    }

    /**
     * @param {@code row} of site, {@code col} of site
     * @return boolean
     * @description is site(row, col) full?
     **/
    public boolean isFull(int row, int col) {
        validate(row, col);
        if (topUF.find(index(row, col)) == topUF.find(0)) return true;
        return false;
    }

    /**
     * @param
     * @return int
     * @description number of open sites
     **/
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    /**
     * @param
     * @return boolean
     * @description does the system percolate?
     **/
    public boolean percolates() {
        return topBottomUF.find(top) == topBottomUF.find(bottom);
    }

    private void validate(int i, int j) {
        if (i < 1 || i > row)
            throw new IllegalArgumentException(String.format("row index must be an integer varies in the interval [1, %d]", row));
        if (j < 1 || j > col)
            throw new IllegalArgumentException(String.format("col index must be an integer varies in the interval [1, %d]", col));
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        Percolation percolation = new Percolation(5);
        while (!percolation.percolates()) {
            int row = StdRandom.uniform(5) + 1;
            int col = StdRandom.uniform(5) + 1;
            percolation.open(row, col);
        }
        StdOut.println(percolation.numberOfOpenSites());
        System.out.println(stopwatch.elapsedTime());
    }
}
