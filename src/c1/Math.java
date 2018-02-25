package c1;

import java.util.Scanner;

/**
 * @author
 * @create 2018-02-06-10:32
 **/
public class Math {

    public static int sum(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int a = cin.nextInt();
        int b = cin.nextInt();
        System.out.println(a + b);
    }
}