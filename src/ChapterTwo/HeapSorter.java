package ChapterTwo;

public class HeapSorter {

    public static void sort(Comparable[] a) {
        int N = a.length-1;
        for (int k = N / 2; k >= 1; k--) {
            sink(a, k, N);
        }
        //能否改为N>2
        while (N > 2) {
            exchange(a, 1, N--);
            sink(a, 1, N);
        }
    }

    private static void sink(Comparable[] a, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(a, j, j + 1)) j++;
            if (!less(a, k, j)) break;
            exchange(a, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
