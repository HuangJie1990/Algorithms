package w5;

import edu.princeton.cs.algs4.*;

/**
 * @author
 * @create 2018-01-20-16:38
 **/
public class PointSET {

    private final SET<Point2D> ps;

    //construct an empty set of points
    public PointSET() {
        ps = new SET<>();
    }

    //is the set empty
    public boolean isEmpty() {
        return ps.isEmpty();
    }

    //number of points in the set
    public int size() {
        return ps.size();
    }

    //add the point to the set (if it is no already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("argument to insert() is null");
        ps.add(p);
    }

    //does the set contains point p
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("argument to contains() is null");
        return ps.contains(p);
    }

    //draw all points to standard draw
    public void draw() {
        for (Point2D p :
                ps) {
            StdDraw.point(p.x(), p.y());
        }
    }

    //all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("argument to range() is null");
        Queue<Point2D> queue = new Queue<>();
        for (Point2D p :
                ps) {
            if (rect.contains(p)) queue.enqueue(p);
        }
        return queue;
    }

    //a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("argument to nearest() is null");
        double minDis = Double.MAX_VALUE;
        Point2D min = null;
        for (Point2D point :
                ps) {
            double dis = p.distanceTo(point);
            if (dis < minDis) {
                minDis = dis;
                min = point;
            }
        }
        return min;
    }

    public static void main(String[] args) {

    }
}
