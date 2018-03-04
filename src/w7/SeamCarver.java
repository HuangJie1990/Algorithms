package w7;

import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private int[][] colors;

    public SeamCarver(Picture picture) {
        if (picture == null) throw new IllegalArgumentException("argument is null");

    }

    public Picture picture() {

    }

    public int width() {
        return colors.length;
    }

    public int height() {
        return colors[0].length;
    }

    public double energy(int x, int y) {
        return 0.0;
    }

    public int[] findHorizontalSeam() {
        return null;
    }

    public int[] findVerticalSeam() {
        return null;
    }

    public void removeHorizontalSeam() {
    }

    public void removeVerticalSeam() {
    }

    private void validColumn(int col) {
        if (col < 0 || col >= width() - 1) throw new IllegalArgumentException("col is out of range");
    }

    private void validRow(int row) {
        if (row < 0 || row >= height() - 1) throw new IllegalArgumentException("row is out of range");
    }
}
