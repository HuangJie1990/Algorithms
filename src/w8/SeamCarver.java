package w8;

import edu.princeton.cs.algs4.AcyclicSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Picture;

/**
 * @author huangjie
 * @create 2018-02-26-18:19
 **/
public class SeamCarver {
    private Picture picture;
    private double e[][];
    private EdgeWeightedDigraph verticalEWD;
    private EdgeWeightedDigraph horizontalEWD;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) throw new IllegalArgumentException("argument is null");
        this.picture = new Picture(picture);
        calculateEnergy();
        verticalEWD();
        horizontalEWD();
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
        validColumn(x);
        validaRow(y);
        if (x == 0 || x == (width() - 1) || y == 0 || y == (height() - 1)) return 1000;
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
        AcyclicSP sp = new AcyclicSP(horizontalEWD, height() * width());
        int[] path = new int[width()];
        int i = 0;
        for (DirectedEdge e :
                sp.pathTo(height() * width() + 1)) {
            if (e.from() >= height() * width()) continue;
            path[i++] = e.from() - (i - 1) * height();
        }
        return path;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        AcyclicSP sp = new AcyclicSP(verticalEWD, height() * width());
        int[] path = new int[height()];
        int i = 0;
        for (DirectedEdge e :
                sp.pathTo(height() * width() + 1)) {
            if (e.from() >= height() * width()) continue;
            path[i++] = e.from() - (i - 1) * width();
        }
        return path;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null) throw new IllegalArgumentException("argument is null");
        if (seam.length != width()) throw new IllegalArgumentException("argument has wrong length");
        if (height() <= 1) throw new IllegalArgumentException("the picture's height is not enough");
        Picture temp = new Picture(width(), height() - 1);
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height() - 1; j++) {
                validaRow(seam[i]);
                if (j < seam[i]) temp.set(i, j, picture.get(i, j));
                else temp.set(i, j, picture.get(i, j + 1));
            }
        }
        picture = temp;
        calculateEnergy();
        verticalEWD();
        horizontalEWD();
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null) throw new IllegalArgumentException("argument is null");
        if (seam.length != height()) throw new IllegalArgumentException("argument has wrong length");
        if (width() <= 1) throw new IllegalArgumentException("the picture's width is not enough");
        Picture temp = new Picture(width() - 1, height());
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width() - 1; j++) {
                validColumn(seam[i]);
                if (j < seam[i]) temp.set(j, i, picture.get(j, i));
                else temp.set(j, i, picture.get(j + 1, i));
            }
        }
        picture = temp;
        calculateEnergy();
        verticalEWD();
        horizontalEWD();
    }

    private void calculateEnergy() {
        e = new double[picture.height()][picture.width()];
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                e[j][i] = energy(i, j);
            }
        }
    }

    private void verticalEWD() {
        //V个真实节点，序号是0到V-1；
        int V = height() * width();
        //添加两个虚拟节点
        verticalEWD = new EdgeWeightedDigraph(V + 2);

        //顶部虚拟节点：V，指向第0行每个节点
        for (int i = 0; i < width(); i++) {
            verticalEWD.addEdge(new DirectedEdge(V, i, e[0][i]));
        }
        for (int i = 0; i < height() - 1; i++) {
            for (int j = 0; j < width(); j++) {
                int v = i * width() + j;
                if (j - 1 >= 0) {
                    int w = (i + 1) * width() + j - 1;
                    double weight = e[i + 1][j - 1];
                    verticalEWD.addEdge(new DirectedEdge(v, w, weight));
                }
                if (j + 1 < width()) {
                    int w = (i + 1) * width() + j + 1;
                    double weight = e[i + 1][j + 1];
                    verticalEWD.addEdge(new DirectedEdge(v, w, weight));
                }
                verticalEWD.addEdge(new DirectedEdge(v, v + width(), e[i + 1][j]));
            }
        }
        //第heith()-1行所有节点指向底部虚拟节点：V+1
        for (int i = V - width(); i < V; i++) {
            verticalEWD.addEdge(new DirectedEdge(i, V + 1, 0));
        }
    }

    private void horizontalEWD() {
        int V = height() * width();
        horizontalEWD = new EdgeWeightedDigraph(V + 2);

        for (int i = 0; i < height(); i++) {
            horizontalEWD.addEdge(new DirectedEdge(V, i, e[i][0]));
        }
        for (int i = 0; i < width() - 1; i++) {
            for (int j = 0; j < height(); j++) {
                int v = i * height() + j;
                if (j - 1 >= 0) {
                    int w = (i + 1) * height() + j - 1;
                    double weight = e[j - 1][i + 1];
                    horizontalEWD.addEdge(new DirectedEdge(v, w, weight));
                }
                if (j + 1 < height()) {
                    int w = (i + 1) * height() + j + 1;
                    double weight = e[j + 1][i + 1];
                    horizontalEWD.addEdge(new DirectedEdge(v, w, weight));
                }
                horizontalEWD.addEdge(new DirectedEdge(v, v + height(), e[j][i + 1]));
            }
        }
        for (int i = V - height(); i < V; i++) {
            horizontalEWD.addEdge(new DirectedEdge(i, V + 1, 0));
        }
    }

    private void validColumn(int col) {
        if (col < 0 || col >= width()) throw new IllegalArgumentException("col out of range");
    }

    private void validaRow(int row) {
        if (row < 0 || row >= height()) throw new IllegalArgumentException("row out of range");
    }
}
