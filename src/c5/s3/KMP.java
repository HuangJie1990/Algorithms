package c5.s3;

import edu.princeton.cs.algs4.StdOut;

/**
 * Knuth-Morris-Pratt字符串查找算法
 *
 * @author huangjie
 * @create 2018-03-09-10:24
 **/
public class KMP {
    private String pat;
    private int[][] dfa;

    public KMP(String pat) {
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;
        for (int X = 0, j = 0; j < M; j++) {
            // 计算dfa[][j]
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][X];
            }
            dfa[pat.charAt(j)][j] = j + 1;
            X = dfa[pat.charAt(j)][X];
        }
    }

    public int search(String txt) {
        //在txt上模拟dfa的运行
        int i, j, N = txt.length(), M = pat.length();
        for (i = 0, j = 0; i < N && j < M; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        //找到匹配（到达模式字符串的结尾）
        if (j == M) return i - M;
            //未找到匹配（到达文本字符串的结尾）
        else return N;
    }

    public static void main(String[] args) {
        String pat = args[0];
        String txt = args[1];
        KMP kmp = new KMP(pat);
        StdOut.println("text:    " + txt);
        int offset = kmp.search(txt);
        StdOut.print("pattern: ");
        for (int i = 0; i < offset; i++) {
            StdOut.print(" ");
        }
        StdOut.println(pat);
    }
}