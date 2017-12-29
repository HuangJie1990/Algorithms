package w1;

import edu.princeton.cs.algs4.*;

/**
 * @author huangjie
 * @create 2017-12-28-13:15
 **/
public class Percolation {

    private boolean[] opened;
    private boolean[] connectedTop;
    private boolean[] connectedBottom;
    private int numberOfOpenSites;
    private final int order;
    private final int size;
    private WeightedQuickUnionUF uf;
    private boolean percolate;

    /**
     * @param
     * @return
     * @description create n-by-n grid, with all opened blocked
     **/
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("argument to Percolation() must bigger than 0");
        order = n;
        size = n * n;
        uf = new WeightedQuickUnionUF(size);
        opened = new boolean[size];
        connectedTop = new boolean[size];
        connectedBottom = new boolean[size];

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
        opened[current] = true;
        boolean top = false;
        boolean bottom = false;

        if (row == 1) {
            top = true;
        }
        if (row == order) {
            bottom = true;
        }

        if (row < order && isOpen(current + order)) {
            if (connectedTop[uf.find(current + order)]) top = true;
            if (connectedBottom[uf.find(current + order)]) bottom = true;
            uf.union(current, current + order);
        }

        if (row > 1 && isOpen(current - order)) {
            if (connectedTop[uf.find(current - order)]) top = true;
            if (connectedBottom[uf.find(current - order)]) bottom = true;
            uf.union(current, current - order);
        }

        if (col < order && isOpen(current + 1)) {
            if (connectedTop[uf.find(current + 1)]) top = true;
            if (connectedBottom[uf.find(current + 1)]) bottom = true;
            uf.union(current, current + 1);
        }

        if (col > 1 && isOpen(current - 1)) {
            if (connectedTop[uf.find(current - 1)]) top = true;
            if (connectedBottom[uf.find(current - 1)]) bottom = true;
            uf.union(current, current - 1);
        }

        connectedTop[uf.find(current)] = top;
        connectedBottom[uf.find(current)] = bottom;
        if (connectedTop[uf.find(current)] && connectedBottom[uf.find(current)]) percolate = true;

        numberOfOpenSites++;
    }

    /**
     * @param {@code row} of site, {@code col} of site
     * @return boolean
     * @description is site(row, col) open?
     **/
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return opened[index(row, col)];
    }

    private boolean isOpen(int i) {
        if (i < 0 || i >= opened.length)
            throw new IllegalArgumentException(String.format("argument is an integer varies in the interval [0, %d]", opened.length-1));
        return opened[i];
    }

    /**
     * {@code row} of site, {@code col} of site
     *
     * @param
     * @return int, the index of site(i, j) in opened array, let opened[0] be the virtual top site
     * @description
     **/
    private int index(int row, int j) {
        return order * (row - 1) + j - 1;
    }

    /**
     * @param {@code row} of site, {@code col} of site
     * @return boolean
     * @description is site(row, col) full?
     **/
    public boolean isFull(int row, int col) {
        validate(row, col);
        return connectedTop[uf.find(index(row, col))];
    }

    /**
     * @param
     * @return int
     * @description number of open opened
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
        return percolate;
    }

    private void validate(int i, int j) {
        if (i < 1 || i > order)
            throw new IllegalArgumentException(String.format("row index must be an integer varies in the interval [1, %d]", order));
        if (j < 1 || j > order)
            throw new IllegalArgumentException(String.format("col index must be an integer varies in the interval [1, %d]", order));
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        In in = new In(args[0]);
        int n = in.readInt();
        Percolation percolation = new Percolation(n);
        while (!in.isEmpty()) {
            int row = in.readInt();
            int col = in.readInt();
            percolation.open(row, col);
        }
        StdOut.println(percolation.percolates());
        System.out.println(stopwatch.elapsedTime());
    }
}
