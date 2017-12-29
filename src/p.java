import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
/**
 * @author
 * @create 2017-12-28-17:25
 **/
public class p {

    private boolean[] matrix;
    private int row, col;
    private WeightedQuickUnionUF wquUF;
    private WeightedQuickUnionUF wquUFTop;
    private boolean alreadyPercolates;

    public p(int N) {
        if (N < 1) throw new IllegalArgumentException("Illeagal Argument");
        wquUF = new WeightedQuickUnionUF(N*N+2);
        wquUFTop = new WeightedQuickUnionUF(N*N+1);
        alreadyPercolates = false;
        row = N;
        col = N;
        matrix = new boolean[N*N+1];
    }

    private void validate(int i, int j) {
        if (i < 1 || i > row)
            throw new IndexOutOfBoundsException("row index i out of bounds");
        if (j < 1 || j > col)
            throw new IndexOutOfBoundsException("col index j out of bounds");
    }

    public void open(int i, int j) {
        validate(i, j);
        int curIdx = (i-1)*col + j;
        matrix[curIdx] = true;
        if (i == 1) {
            wquUF.union(curIdx, 0);
            wquUFTop.union(curIdx, 0);
        }
        if (i == row) {
            wquUF.union(curIdx, row*col+1);
        }

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        for (int dir = 0; dir < 4; dir++) {
            int posX = i + dx[dir];
            int posY = j + dy[dir];
            if (posX <= row && posX >= 1
                    && posY <= row && posY >= 1
                    && isOpen(posX, posY)) {
                wquUF.union(curIdx, (posX-1)*col+posY);
                wquUFTop.union(curIdx, (posX-1)*col+posY);
            }
        }
    }

    public boolean isOpen(int i, int j) {
        validate(i, j);
        return matrix[(i-1)*col + j];
    }

    public boolean isFull(int i, int j) {
        validate(i, j);
        int curIdx = (i-1)*col+j;
        if (wquUFTop.find(curIdx) == wquUFTop.find(0)) return true;
        return false;
    }

    public boolean percolates() {
        if (alreadyPercolates) return true;
        if (wquUF.find(0) == wquUF.find(row*col+1)) {
            alreadyPercolates = true;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Stopwatch stopwatch=new Stopwatch();
        p perc = new p(1000);
        for (int i = 1; i < 1001; i++) {
            perc.open(i,i);
        }
        for (int i = 1; i < 3; i++) {
            for (int j = 1; j < 3; j++) {
                StdOut.println("site[" + i + ", " + j + "] is full: " + perc.isFull(i, j));
            }
        }
        System.out.println(perc.percolates());
        System.out.println(stopwatch.elapsedTime());
    }

}
