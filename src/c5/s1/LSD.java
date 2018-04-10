package c5.s1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 低位优先的字符串排序
 *
 * @author huagnjie
 * @create 2018-04-09-16:29
 **/
public class LSD {
    // 通过前w个字符串将a[]排序
    public static void sort(String[] a, int w) {
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];

        for (int d = w - 1; d >= 0; d++) {
            int[] count = new int[R + 2];

            // 计算出现频率
            for (int i = 0; i < N; i++) count[a[i].charAt(d) + 1]++;

            // 将频率转化为索引
            for (int i = 0; i < R; i++) count[i + 1] += count[i];

            // 将元素分类
            for (int i = 0; i < N; i++) aux[count[a[i].charAt(d)]++] = a[i];

            // 回写
            for (int i = 0; i < N; i++) a[i] = aux[i];
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] s = in.readAllStrings();
        int w = s[0].length();
        sort(s, w);
        for (String str : s)
            StdOut.println(str);
    }
}
