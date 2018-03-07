package w4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Board {

    private final int[][] b;
    private int hamming;
    private int manhattan;
    private int i0, j0;

    public Board(int[][] blocks) {
        int d = blocks.length;
        b = new int[d][];
        for (int i = 0; i < d; i++) {
            b[i] = new int[d];
            for (int j = 0; j < d; j++) {
                b[i][j] = blocks[i][j];
                if (b[i][j] == 0) {
                    i0 = i;
                    j0 = j;
                    continue;
                }
                if (b[i][j] != d * i + j + 1) {
                    hamming++;
                    manhattan += Math.abs((b[i][j] - 1) / d - i);
                    manhattan += Math.abs((b[i][j] - 1) % d - j);
                }
            }
        }
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        StdOut.println(initial.manhattan());
    }

    public int dimension() {
        return b.length;
    }

    public int hamming() {
        return hamming;
    }

    public int manhattan() {
        return manhattan;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public Board twin() {
        int[][] twin = new int[dimension()][dimension()];
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                twin[i][j] = b[i][j];
            }
        }
        Stack<Integer> col = new Stack<>();
        Stack<Integer> row = new Stack<>();

        if (i0 - 1 >= 0) {
            row.push(i0 - 1);
            col.push(j0);
        }
        if (i0 + 1 < dimension()) {
            row.push(i0 + 1);
            col.push(j0);
        }
        if (j0 - 1 >= 0) {
            row.push(i0);
            col.push(j0 - 1);
        }
        if (j0 + 1 < dimension()) {
            row.push(i0);
            col.push(j0 + 1);
        }
        exchange(twin, row.pop(), col.pop(), row.pop(), col.pop());
        return new Board(twin);
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;
        Board board = (Board) that;
        return Arrays.deepEquals(b, board.b);
    }

    public Iterable<Board> neighbors() {
        Queue<Board> neighbors = new Queue<>();
        int[][] up = move(b, -1, 0);
        if (up != null) neighbors.enqueue(new Board(up));
        int[][] bt = move(b, 1, 0);
        if (bt != null) neighbors.enqueue(new Board(bt));
        int[][] left = move(b, 0, -1);
        if (left != null) neighbors.enqueue(new Board(left));
        int[][] right = move(b, 0, 1);
        if (right != null) neighbors.enqueue(new Board(right));
        return neighbors;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(dimension() + "\n");
        for (int[] a : b) {
            for (int i :
                    a) {
                sb.append(String.format("%2d ", i));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private int[][] move(int[][] blocks, int di, int dj) {
        if (i0 + di < 0 || i0 + di >= dimension() || j0 + dj < 0 || j0 + dj >= dimension()) return null;
        int[][] a = new int[dimension()][dimension()];
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                a[i][j] = blocks[i][j];
            }
        }
        exchange(a, i0, j0, i0 + di, j0 + dj);
        return a;
    }

    private void exchange(int[][] blocks, int ia, int ja, int ib, int jb) {
        int temp = blocks[ia][ja];
        blocks[ia][ja] = blocks[ib][jb];
        blocks[ib][jb] = temp;
    }

}
