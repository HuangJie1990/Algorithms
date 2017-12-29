package c1;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class Draw{

    public static void main(String[] args){
        StdDraw.setPenRadius(0.001);
        StdDraw.point(0.8,0.5);
        StdDraw.point(0,0);
        StdDraw.line(0,0,1,1);
        StdDraw.circle(0.1,0.1,0.1);
        StdDraw.square(0.5,0.5,0.5);
        StdDraw.text(0.05,0.95,"12345");
        StdDraw.text(0.05,0.9,"12345");
        StdDraw.setPenColor(Color.RED);
        StdDraw.filledRectangle(0.3,0.4,0.1,0.4);
        StdDraw.setPenColor();
        StdDraw.filledRectangle(0.6,0.4,0.1,0.4);
    }
}
