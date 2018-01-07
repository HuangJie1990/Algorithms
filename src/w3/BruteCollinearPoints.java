package w3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author
 * @create 2018-01-03-14:29
 **/
public class BruteCollinearPoints {

    private ArrayList<LineSegment> lines;
    private LineSegment[] segments;
    private Point[] p;

    public BruteCollinearPoints(Point[] points) {
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
            segments = lines.toArray(new LineSegment[lines.size()]);
            return;
        }
        p = new Point[n];
        for (int i = 0; i < n; i++) {
            p[i] = points[i];
        }


        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (p[i].slopeTo(p[j]) == p[i].slopeTo(p[k])) {
                        for (int l = k + 1; l < n; l++) {
                            if (p[i].slopeTo(p[k]) == p[i].slopeTo(p[l])) {
                                Point[] line = new Point[]{p[i], p[j], p[k], p[l]};
                                lines.add(new LineSegment(line[0], line[3]));
                            }
                        }
                    }
                }
            }
        }

        segments = lines.toArray(new LineSegment[lines.size()]);
    }

    public int numberOfSegments() {
        return lines.size();
    }

    public LineSegment[] segments() {
        return segments;
    }

    private void validate(Point point) {
        if (point == null) throw new IllegalArgumentException("point is null");
    }

    private void check(Point v, Point w) {
        if (v.compareTo(w) == 0) throw new IllegalArgumentException("there are repeated points");
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
