package w3;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

/**
 * @author
 * @create 2018-01-03-13:38
 **/
public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if (that == null) throw new IllegalArgumentException("argument to slopeTo is null");
        if (this.compareTo(that) == 0) return Double.NEGATIVE_INFINITY;
        if (Integer.compare(this.x, that.x) == 0) return Double.POSITIVE_INFINITY;
        if (Integer.compare(this.y, that.y) == 0) return +0.0;
        return (double) (that.y - this.y) / (that.x - this.x);
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     * point (x0 = x1 and y0 = y1);
     * a negative integer if this point is less than the argument
     * point; and a positive integer if this point is greater than the
     * argument point
     */
    public int compareTo(Point that) {
        if (that == null) throw new IllegalArgumentException("argument to compareTo is null");
        if (Integer.compare(this.y, that.y) == 0) {
            return Integer.compare(this.x, that.x);
        }
        return Integer.compare(this.y, that.y);
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new PointComparator(this);
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(5, 1);
        Point p3 = new Point(5, 5);
        Point p4 = new Point(0, 0);
        Point p5 = new Point(5, 0);
        Point p6 = new Point(0, 5);
        StdOut.println(p1.compareTo(p2));
        StdOut.println(p1.compareTo(p4));
        StdOut.println(p1.slopeTo(p2));
        StdOut.println(p1.slopeTo(p3));
        StdOut.println(p1.slopeTo(p3));
        StdOut.println(p1.slopeTo(p4));
        StdOut.println(p1.slopeTo(p5));
        StdOut.println(p1.slopeTo(p6));
    }

    private class PointComparator implements Comparator<Point> {
        private Point invokePoint;

        public PointComparator(Point invokePoint) {
            this.invokePoint = invokePoint;
        }

        @Override
        public int compare(Point o1, Point o2) {
            return Double.compare(invokePoint.slopeTo(o1), invokePoint.slopeTo(o2));
        }
    }
}

