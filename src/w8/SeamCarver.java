package w8;

import edu.princeton.cs.algs4.Picture;

/**
 * @author huangjie
 * @create 2018-02-26-18:19
 **/
public class SeamCarver {
    private Picture picture;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        this.picture = new Picture(picture);
    }

    // current picture
    public Picture picture() {
        return picture;
    }

    // width of current picture
    public int width() {
        return picture.width();
    }

    // height of current picture
    public int height() {
        return picture.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x == 0 || x == width() || y == 0 || y == height()) return 1000;
        int left = picture.getRGB(x - 1, y);
        int right = picture.getRGB(x + 1, y);
        int rr = (right >> 16) & 0xFF, rg = (right >> 8) & 0xFF, rb = (right) & 0xFF;
        int lr = (left >> 16) & 0xFF, lg = (left >> 8) & 0xFF, lb = (left) & 0xFF;
        int upper = picture.getRGB(x, y - 1);
        int bottom = picture.getRGB(x, y + 1);
        int br = (bottom >> 16) & 0xFF, bg = (bottom >> 8) & 0xFF, bb = (bottom) & 0xFF;
        int ur = (upper >> 16) & 0xFF, ug = (upper >> 8) & 0xFF, ub = (upper) & 0xFF;
        return Math.sqrt(Math.pow(rr - lr, 2) + Math.pow(rg - lg, 2) + Math.pow(rb - lb, 2) + Math.pow(br - ur, 2) + Math.pow(bg - ug, 2) + Math.pow(bb - ub, 2));
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        return null;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        return null;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {

    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {

    }

}
