package c5.s3;

import edu.princeton.cs.algs4.StdOut;

/**
 * Boyer-Moore字符串匹配算法（启发式地处理不匹配的字符）
 *
 * @author huangjie
 * @create 2018-03-09-10:44
 **/
public class BoyerMoore {
    private int[] right;
    private String pat;

    public BoyerMoore(String pat) {
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        right = new int[R];
        for (int i = 0; i < R; i++) right[i] = -1;
        // 从左到右遍历模式字符串，计算各字符串出现在pat的位置，如果有多个位置，用最右边的位置覆盖
        for (int j = 0; j < M; j++) right[pat.charAt(j)] = j;
    }

    public int search(String txt) {
        // 在txt中查找模式字符串
        int N = txt.length();
        int M = pat.length();
        int skip;
        for (int i = 0; i < N - M; i += skip) {
            skip = 0;
            for (int j = M - 1; j > 0; j--)
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    skip = j - right[txt.charAt(i + j)];
                    if (skip < 1) skip = 1;
                    break;
                }
            // 找到匹配
            if (skip == 0) return i;
        }
        // 未找到匹配
        return N;
    }

    public static void main(String[] args) {
        String pat = args[0];
        String txt = args[1];
        BoyerMoore kmp = new BoyerMoore(pat);
        StdOut.println("text:    " + txt);
        int offset = kmp.search(txt);
        StdOut.print("pattern: ");
        for (int i = 0; i < offset; i++) {
            StdOut.print(" ");
        }
        StdOut.println(pat);
    }
}
