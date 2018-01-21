package w5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

/**
 * @author
 * @create 2018-01-20-16:55
 **/
public class KdTree {

    private Node root;
    private int N;

    public KdTree() {
    }

    //is the set empty
    public boolean isEmpty() {
        return N == 0;
    }

    //number of points in the set
    public int size() {
        return N;
    }

    //add the point to the set (if it is no already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("argument to insert() is null");
    }

    private Node insert(Node x,Point2D p,double xmin,double ymin,double xmax,double ymax,int ori){
        if(x==null){
            N++;
            return new Node(p,new RectHV(xmin,ymin,xmax,ymax));
        }
        int cmp=p.compareTo(x.p);
        if(cmp<0){
            if(ori==0)
        }
    }

    //does the set contains point p
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("argument to contains() is null");
    }

    //draw all points to standard draw
    public void draw() {
    }

    //all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("argument to range() is null");
    }

    //a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("argument to nearest() is null");
    }

    public static void main(String[] args) {

    }

    private class Node {
        private Point2D p;
        private RectHV rect;
        private Node left;
        private Node right;

        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
        }
    }

    private int compare(Point2D p, Point2D q, int ori) {
        validate(p);
        validate(q);
        if (p.equals(q)) return 0;
        else {
            if (ori == 0) {
                if (p.x() < q.x()) return -1;
                else return 1;
            } else {
                if (p.y() < q.y()) return -1;
                else return 1;
            }
        }
    }

    private void validate(Point2D p) {
        if (p == null) throw new IllegalArgumentException("argument is null");
    }
}
