package c2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;

/**
 * @author
 * @create 2018-01-05-10:03
 **/
public class SortTest {

    public static void main(String[] args) {
//        In in = new In(args[0]);
//        int[] temp = in.readAllInts();
//        int n = temp.length;
//        Stopwatch stopwatch = new Stopwatch();
//        Integer[] a = new Integer[n];
//        for (int j = 0; j < n; j++) {
//            a[j] = temp[j];
//        }
        Integer[] a = new Integer[]{
                1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 5, 5, 6, 6
        };
        ThreeWay.sort(a);
        assert Sort.isSorted(a);
        Sort.show(a);
        StdOut.println();
        for (int i = 1; i < 7; i++) {
            Comparable[] b = ThreeWay.select(a, i, 0, a.length - 1);
            StdOut.println("has "+b.length+" member");
            for (int j = 0; j < b.length; j++) {
                StdOut.print(b[j]+" ");
            }
            StdOut.println();
        }

        ArrayList<Integer> ints=new ArrayList<>();
        ints.add(6);
        ints.add(3);
        ints.add(5);
        ints.sort(null);
        for (int i = 0; i < ints.size(); i++) {
            StdOut.println(ints.get(i));
        }
//        StdOut.println(stopwatch.elapsedTime());

//        int i=0;
//        while (i++<10){
//            StdOut.println(i);
//        }
//        StdOut.println(i);


//        while (true){
//            while (i<10){
//                i++;
//                StdOut.println(i);
//            }
//            i++;
//            StdOut.println(i);
//            if(i>=1) break;
//        }
//        StdOut.println(i);
    }
}
