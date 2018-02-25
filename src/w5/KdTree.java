package w5;


import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

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
        root = insert(root, p, 0, 0, 1, 1, 0);
    }

    private Node insert(Node x, Point2D p, double xmin, double ymin, double xmax, double ymax, int ori) {
        if (x == null) {
            N++;
            return new Node(p, new RectHV(xmin, ymin, xmax, ymax));
        }
        int cmp = compare(p, x.point, ori);
        if (cmp < 0) {
            if (ori == 1) {
                ymax = x.point.y();
            } else {
                xmax = x.point.x();
            }
            x.left = insert(x.left, p, xmin, ymin, xmax, ymax, 1 - ori);
        } else if (cmp > 0) {
            if (ori == 1) {
                ymin = x.point.y();
            } else {
                xmin = x.point.x();
            }
            x.right = insert(x.right, p, xmin, ymin, xmax, ymax, 1 - ori);
        }
        return x;
    }

    //does the set contains point p
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("argument to contains() is null");
        return contains(root, p, 0);
    }

    private boolean contains(Node h, Point2D p, int ori) {
        if (h == null) return false;
        int cmp = compare(p, h.point, ori);
        if (cmp < 0) return contains(h.left, p, 1 - ori);
        else if (cmp > 0) return contains(h.right, p, 1 - ori);
        return true;
    }


    //draw all points to standard draw
    public void draw() {
        StdDraw.setScale(0, 1);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius();
        new RectHV(0, 0, 1, 1).draw();
        draw(root, 0);
    }

    private void draw(Node h, int ori) {
        if (h == null) return;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        StdDraw.point(h.point.x(), h.point.y());
        if (ori == 0) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius();
            StdDraw.line(h.point.x(), h.rect.ymin(), h.point.x(), h.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius();
            StdDraw.line(h.rect.xmin(), h.point.y(), h.rect.xmax(), h.point.y());
        }
        draw(h.left, 1 - ori);
        draw(h.right, 1 - ori);
    }

    //all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("argument to range() is null");
        Queue<Point2D> queue = new Queue<>();
        Queue<Node> nodes = new Queue<>();
        if (root == null) return queue;
        nodes.enqueue(root);
        while (!nodes.isEmpty()) {
            Node x = nodes.dequeue();
            if (rect.contains(x.point)) queue.enqueue(x.point);
            if (x.left != null && rect.intersects(x.left.rect)) nodes.enqueue(x.left);
            if (x.right != null && rect.intersects(x.right.rect)) nodes.enqueue(x.right);
        }
        return queue;
    }


    //a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("argument to nearest() is null");
        Point2D near = null;
        double min = Double.POSITIVE_INFINITY;
        Queue<Node> queue = new Queue<>();
        if (root == null) return near;
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node h = queue.dequeue();
            double distance = h.point.distanceTo(p);
            if (distance < min) {
                near = h.point;
                min = distance;
            }
            while (h.left != null && h.left.rect.distanceTo(p) < min) queue.enqueue(h.left);
            while (h.right != null && h.right.rect.distanceTo(p) < min) queue.enqueue(h.right);
        }
        return near;
    }

    public static void main(String[] args) {
        KdTree tree = new KdTree();
        while (!StdIn.isEmpty()) {
            double x = StdIn.readDouble();
            double y = StdIn.readDouble();
            tree.insert(new Point2D(x, y));
            StdDraw.clear();
            tree.draw();
            StdDraw.show();
            StdOut.println(tree.size());
            StdOut.println(tree.isEmpty());
        }
    }

    private class Node {
        private Point2D point;
        private RectHV rect;
        private Node left;
        private Node right;

        public Node(Point2D p, RectHV rect) {
            this.point = p;
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
