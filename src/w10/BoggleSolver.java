package w10;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;

/**
 * An immutable data type that finds all valid wors in a given Boggle board, using a given dictionary
 *
 * @author huangjie
 * @create 2018-03-09-11:43
 **/
public class BoggleSolver {
    private OptTST<Integer> dic;

    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        dic = new OptTST<>();
        int i = 0;
        for (String word : dictionary) {
            if (word.length() > 2) dic.put(word, i);
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word :
                solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        int rows = board.rows(), cols = board.cols();
        HashMap<String, Integer> words = new HashMap<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean[][] marked = new boolean[rows][cols];
                dfs(board, marked, i, j, new StringBuilder(), words);
            }
        }
        return words.keySet();
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word in the dictionary contains only uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (word == null) throw new IllegalArgumentException("argument to scoreOf() is null");
        int length = word.length();
        if (length <= 2) return 0;
        else if (dic.contains(word)) {
            switch (length) {
                case 3:
                case 4:
                    return 1;
                case 5:
                    return 2;
                case 6:
                    return 3;
                case 7:
                    return 5;
                default:
                    return 11;
            }
        }
        return 0;
    }

    private Bag<Die> adj(BoggleBoard board, int row, int col) {
        int rows = board.rows();
        int cols = board.cols();
        Bag<Die> dies = new Bag<>();
        int left = col - 1, right = col + 1, above = row - 1, below = row + 1;
        if (above >= 0) {
            if (left >= 0) dies.add(new Die(above, left));
            dies.add(new Die(above, col));
            if (right < cols) dies.add(new Die(above, right));
        }
        if (left >= 0) dies.add(new Die(row, left));
        if (right < cols) dies.add(new Die(row, right));
        if (below < rows) {
            if (left >= 0) dies.add(new Die(below, left));
            dies.add(new Die(below, col));
            if (right < cols) dies.add(new Die(below, right));
        }
        return dies;
    }

    private void dfs(BoggleBoard board, boolean[][] marked, int row, int col, StringBuilder prefix, HashMap<String, Integer> matches) {
        char c = board.getLetter(row, col);
        boolean isQ = false;
        prefix.append(c);
        if (c == 'Q') {
            isQ = true;
            prefix.append('U');
        }
        if (prefix.length() == 1) {
            if (!dic.hasKeysWithPrefix(prefix.toString())) return;
            marked[row][col] = true;
            for (Die die : adj(board, row, col)) {
                if (!marked[die.row][die.col]) dfs(board, marked, die.row, die.col, prefix, matches);
            }
        } else if (prefix.length() == 2) {
            if (dic.hasKeysWithPrefix(prefix.toString())) {
                marked[row][col] = true;
                for (Die die : adj(board, row, col)) {
                    if (!marked[die.row][die.col]) dfs(board, marked, die.row, die.col, prefix, matches);
                }
            }
            marked[row][col] = false;
            prefix.deleteCharAt(prefix.length() - 1);
            if (isQ) prefix.deleteCharAt(prefix.length() - 1);
        } else {
            if (dic.contains(prefix.toString())) {
                marked[row][col] = true;
                matches.put(prefix.toString(), 0);
            }
            if (dic.hasKeysWithPrefix(prefix.toString())) {
                marked[row][col] = true;
                for (Die die :
                        adj(board, row, col)) {
                    if (!marked[die.row][die.col]) dfs(board, marked, die.row, die.col, prefix, matches);
                }
            }
            marked[row][col] = false;
            prefix.deleteCharAt(prefix.length() - 1);
            if (isQ) prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    private class Die {
        int row;
        int col;

        public Die(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
