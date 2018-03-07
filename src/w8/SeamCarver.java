package w8;

import edu.princeton.cs.algs4.Picture;

/**
 * @author huangjie
 * @create 2018-03-05-9:02
 **/
public class SeamCarver {

    //不直接存储Picture对象，存储色值矩阵
    private int[][] colors;
    private int[][] e;

    public SeamCarver(Picture picture) {
        if (picture == null) throw new IllegalArgumentException("argument is null");
        colors = new int[picture.height()][picture.width()];
        e = new int[picture.height()][picture.width()];
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                colors[i][j] = picture.getRGB(j, i);
            }
        }
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                e[i][j] = calculateEnergy(j, i);
            }
        }
    }

    public Picture picture() {
        Picture picture = new Picture(width(), height());
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                picture.setRGB(j, i, colors[i][j]);
            }
        }
        return picture;
    }

    public int height() {
        return colors.length;
    }

    public int width() {
        return colors[0].length;
    }

    //energy of pixel at column x and row y
    public double energy(int x, int y) {
        validateColumn(x);
        validateRow(y);
        return Math.sqrt(e[y][x]);
    }


    public int[] findHorizontalSeam() {
        int[][] distTo = new int[height()][width()];
        int[][] edgeTo = new int[height()][width() - 1];
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                distTo[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                horizontalRelax(distTo, edgeTo, i, j);
            }
        }
        int min = 0;
        for (int i = 0; i < height(); i++) {
            if (distTo[i][width() - 1] < distTo[min][width() - 1]) min = i;
        }
        int[] path = new int[width()];
        path[width() - 1] = min;
        for (int i = width() - 2; i >= 0; i--) {
            path[i] = edgeTo[path[i + 1]][i];
        }
        return path;
    }

    public int[] findVerticalSeam() {
        int[][] distTo = new int[height()][width()];
        int[][] edgeTo = new int[height() - 1][width()];
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                distTo[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                verticalRelax(distTo, edgeTo, j, i);
            }
        }
        int min = 0;
        for (int i = 0; i < width(); i++) {
            if (distTo[height() - 1][i] < distTo[height() - 1][min]) min = i;
        }
        int[] path = new int[height()];
        path[height() - 1] = min;
        for (int i = height() - 2; i >= 0; i--) {
            path[i] = edgeTo[i][path[i + 1]];
        }
        return path;
    }

    public void removeHorizontalSeam(int[] seam) {
        if (seam == null) throw new IllegalArgumentException("argument is null");
        if (height() <= 1) throw new IllegalArgumentException("picture's height is not enough");
        int[][] temp = new int[height() - 1][width()];
    }

    public void removeVerticalSeam(int[] seam) {
        if (seam == null) throw new IllegalArgumentException("argument is null");
        if (width() <= 1) throw new IllegalArgumentException("picture's width is not enough");
        int[][] temp = new int[height()][width() - 1];
        int min = width(), max = 0;
        for (int i = 0; i < seam.length; i++) {
            validateColumn(seam[i]);
            if (seam[i] < min) min = seam[i];
            if (seam[i] > max) max = seam[i];
        }
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width() - 1; j++) {
                if (j < seam[i]) temp[i][j] = colors[i][j];
                temp[i][j] = colors[i][j + 1];
            }
        }
        colors = temp;
        for (int i = 0; i < height(); i++) {
            for (int j = min - 1; j <= Math.min(max, width() - 1); j++) {
                calculateEnergy(j, i);
            }
        }
    }

    //calculate energy of pixel at column x and row y
    private int calculateEnergy(int x, int y) {
        if (x == 0 || x == (width() - 1) || y == 0 || y == (height() - 1)) return 1000000;
        int right = colors[y][x + 1], left = colors[y][x - 1], above = colors[y - 1][x], below = colors[y + 1][x];
        int rr = (right >> 16) & 0xFF, rg = (right >> 8) & 0xFF, rb = right & 0xFF;
        int lr = (left >> 16) & 0xFF, lg = (left >> 8) & 0xFF, lb = left & 0xFF;
        int br = (below >> 16) & 0xFF, bg = (below >> 8) & 0xFF, bb = below & 0xFF;
        int ar = (above >> 16) & 0xFF, ag = (above >> 8) & 0xFF, ab = above & 0xFF;
        return (rr - lr) * (rr - lr) + (rg - lg) * (rg - lg) + (rb - lb) * (rb - lb) + (br - ar) * (br - ar) + (bg - ag) * (bg - ag) + (bb - ab) * (bb - ab);
    }

    private void validateColumn(int col) {
        if (col < 0 || col > width() - 1) throw new IllegalArgumentException("column is out of range");
    }

    private void validateRow(int row) {
        if (row < 0 || row > height() - 1) throw new IllegalArgumentException("row is out of range");
    }

    private void verticalRelax(int[][] distTo, int[][] edgeTo, int col, int row) {
        if (row == 0) {
            distTo[row][col] = e[row][col];
            return;
        }
        int above = row - 1;
        int left = col - 1;
        if (left >= 0) {
            if (distTo[above][left] + e[row][col] < distTo[row][col]) {
                distTo[row][col] = distTo[above][left] + e[row][col];
                edgeTo[row - 1][col] = left;
            }
        }
        if (distTo[above][col] + e[row][col] < distTo[row][col]) {
            distTo[row][col] = distTo[above][col] + e[row][col];
            edgeTo[row - 1][col] = col;
        }
        int right = col + 1;
        if (right < width()) {
            if (distTo[above][right] + e[row][col] < distTo[row][col]) {
                distTo[row][col] = distTo[above][right] + e[row][col];
                edgeTo[row - 1][col] = right;
            }
        }
    }

    private void horizontalRelax(int[][] distTo, int[][] edgeTo, int col, int row) {
        if (col == 0) {
            distTo[row][col] = e[row][col];
            return;
        }
        int left = col - 1;
        int above = row - 1;
        if (above >= 0) {
            if (distTo[above][left] + e[row][col] < distTo[row][col]) {
                distTo[row][col] = distTo[above][left] + e[row][col];
                edgeTo[row][col - 1] = above;
            }
        }
        if (distTo[row][left] + e[row][col] < distTo[row][col]) {
            distTo[row][col] = distTo[row][left] + e[row][col];
            edgeTo[row][col - 1] = row;
        }
        int below = row + 1;
        if (below < height()) {
            if (distTo[below][left] + e[row][col] < distTo[row][col]) {
                distTo[row][col] = distTo[below][left] + e[row][col];
                edgeTo[row][col - 1] = below;
            }
        }
    }
}
