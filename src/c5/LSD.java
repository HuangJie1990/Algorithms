package c5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 低位优先的字符串排序
 */
public class LSD {

    /**
     * @param strings 将要被排序的字符串数组
     * @param W       字符串长度
     */
    public static void sort(String[] strings, int W) {

        int N = strings.length;
        int R = 256;
        //辅助数组
        String[] aux = new String[N];
        //根据第w个字符用建索引计数法排序
        for (int w = W - 1; w >= 0; w--) {
            int[] count = new int[R + 1];
            //计算频率
            for (int i = 0; i < N; i++) {
                count[strings[i].charAt(w) + 1]++;
            }
            //将频率转换为索引
            for (int i = 0; i < R; i++) {
                count[i + 1] += count[i];
            }
            //将元素分类
            for (int i = 0; i < N; i++) {
                aux[count[strings[i].charAt(w)]++] = strings[i];
            }
            //回写
            for (int i = 0; i < N; i++) {
                strings[i] = aux[i];
            }
        }
    }

    public static void main(String[] args) {
        In in = new In("src\\c5\\lsd.txt");
        String[] strings = in.readAllStrings();
        sort(strings, strings[0].length());
        for (String s : strings) {
            StdOut.println(s);
        }
    }
}
