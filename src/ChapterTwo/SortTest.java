package ChapterTwo;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.io.Console;

public class SortTest {

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void show(Comparable[] a) {
        for (Comparable comparable : a
                ) {
            StdOut.print(comparable + " ");
        }
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            if (less(a[i + 1], a[i])) return false;
        }
        return true;
    }

    public static void main(String[] args) {

        String[] a = new In(args[0]).readAllStrings();
        Stopwatch stopwatch = new Stopwatch();
        for (int i = 0; i < 100; i++) {
            Selection.sort(a);
            assert isSorted(a);
        }
        double t = stopwatch.elapsedTime();
        System.out.println(String.format("Selection spend %f to sort %d elements per loop.", t/100, a.length));

        a = new In(args[0]).readAllStrings();
        Stopwatch stopwatch1 = new Stopwatch();
        for (int i = 0; i < 100; i++) {
            Insertion.sort(a);
            assert isSorted(a);
        }
        double t1 = stopwatch1.elapsedTime();
        System.out.println(String.format("Insertion spend %f to sort %d elements per loop.", t1/100, a.length));

        a = new In(args[0]).readAllStrings();
        Stopwatch stopwatch2 = new Stopwatch();
        for (int i = 0; i < 100; i++) {
            Shell.sort(a);
            assert isSorted(a);
        }
        double t2 = stopwatch2.elapsedTime();
        System.out.println(String.format("Shell spend %f to sort %d elements per loop.", t2/100, a.length));

        a = new In(args[0]).readAllStrings();
        Stopwatch stopwatch3 = new Stopwatch();
        for (int i = 0; i < 100; i++) {
            Merge.sort(a);
            assert isSorted(a);
        }
        double t3 = stopwatch3.elapsedTime();
        System.out.println(String.format("Merge spend %f to sort %d elements per loop.", t/100, a.length));

        a = new In(args[0]).readAllStrings();
        Stopwatch stopwatch4 = new Stopwatch();
        for (int i = 0; i < 100; i++) {
            MergeBU.sort(a);
            assert isSorted(a);
        }
        double t4 = stopwatch4.elapsedTime();
        System.out.println(String.format("MergeBU spend %f to sort %d elements per loop.", t4/100, a.length));

        a = new In(args[0]).readAllStrings();
        Stopwatch stopwatch5 = new Stopwatch();
        for (int i = 0; i < 100; i++) {
            Quick.sort(a);
            assert isSorted(a);
        }
        double t5 = stopwatch5.elapsedTime();
        System.out.println(String.format("Quick spend %f to sort %d elements per loop.", t5/100, a.length));

        a = new In(args[0]).readAllStrings();
        Stopwatch stopwatch6 = new Stopwatch();
        for (int i = 0; i < 100; i++) {
            Quick3Way.sort(a);
            assert isSorted(a);
        }
        double t6 = stopwatch6.elapsedTime();
        System.out.println(String.format("Quick3Way spend %f to sort %d elements per loop.", t6/100, a.length));

        int n=a.length+1;
        a=new String[n];
        In in=new In(args[0]);
        a[0]=" ";
        for (int i = 1; i <a.length ; i++) {
            a[i]=in.readString();
        }
        Stopwatch stopwatch7=new Stopwatch();
        for (int i = 0; i < 100; i++) {
            HeapSorter.sort(a);
            assert isSorted(a);
        }
        double t7=stopwatch7.elapsedTime();
        System.out.println(String.format("HeapSorter spend %f to sort %d elements per loop.", t7/100, a.length));
        show(a);
    }
}
