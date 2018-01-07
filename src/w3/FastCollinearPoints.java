package w3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author
 * @create 2018-01-03-17:07
 **/
public class FastCollinearPoints {

    private Point[] p;
    private LineSegment[] segments;
    private ArrayList<Point[]> lines;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        int n = points.length;
        for (int i = 0; i < n; i++) {
            validate(points[i]);
        }
        Arrays.sort(points);
        for (int i = 1; i < n; i++) {
            check(points[i], points[i - 1]);
        }

        lines = new ArrayList<>();
        if (n < 4) {
            segments = new LineSegment[0];
            return;
        }
        p = new Point[n];
        //n
        for (int i = 0; i < n; i++) {
            p[i] = points[i];
        }

        for (int i = 0; i < n; i++) {
            Arrays.sort(p, points[i].slopeOrder());
            int count = 1;
            for (int j = 1; j < n - 1; j += count) {
                int lo = j, hi = j + 1;
                while (hi < n) {
                    if (p[0].slopeTo(p[lo]) == p[0].slopeTo(p[hi])) hi++;
                    else break;
                }
                count = hi - lo;
                if (count < 3) continue;
                Point[] seg = new Point[count + 1];
                seg[0] = points[i];
                System.arraycopy(p, lo, seg, 1, count);
                Arrays.sort(seg);
                lines.add(seg);
            }
        }

        for (int i = 0; i < lines.size(); i++) {
            int j = i + 1;
            while (j < lines.size()) {
                if (permutation(lines.get(i), lines.get(j))) lines.remove(j);
                else j++;
            }
        }

        segments = new LineSegment[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            Point[] line = lines.get(i);
            segments[i] = new LineSegment(line[0], line[line.length - 1]);
        }
    }

    public int numberOfSegments() {
        return lines.size();
    }

    public LineSegment[] segments() {

        return segments;
    }

    private void validate(Point p) {
        if (p == null) throw new IllegalArgumentException("point is null");
    }

    private void check(Point v, Point w) {
        if (v.compareTo(w) == 0) throw new IllegalArgumentException("there are repeated points");
    }

    private boolean permutation(Point[] a, Point[] b) {
        int sizea = a.length;
        int sizeb = b.length;
        if (sizea != sizeb) return false;
        else if (a[0].compareTo(b[0]) != 0) return false;
        else if (a[sizea - 1].compareTo(b[sizeb - 1]) != 0) return false;
        else return true;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
