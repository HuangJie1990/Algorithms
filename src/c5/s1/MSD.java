package c5.s1;

/**
 * 高位优先的字符串排序
 *
 * @author huangjie
 * @create 2018-04-09-17:04
 **/
public class MSD {
    private static int R = 256;
    // 小数组的切换阈值
    private static final int M = 15;
    private static String[] aux;

    private static int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N - 1, 0);
    }

    public static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo) return;
        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i], d) + 2]++;
        }

        for (int i = 0; i < R + 1; i++) {
            count[i + 1] += count[i];
        }

        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }

        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i - lo];
        }

        for (int i = 0; i < R; i++) {
            sort(a, lo + count[i], lo + count[i + 1] - 1, d + 1);
        }
    }
}
