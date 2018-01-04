package w3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

import java.util.Arrays;

/**
 * @author
 * @create 2018-01-03-14:29
 **/
public class BruteCollinearPoints {

    private Queue<LineSegment> segments;
    private Stack<Double> sloops;
    private Point[] p;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("argument to BruteCollinearPoints is null");
        p = new Point[points.length];
        for (int i = 0; i < p.length; i++) {
            validate(points[i]);
            p[i] = points[i];
        }

        segments = new Queue<>();
        sloops = new Stack<>();

        Arrays.sort(p);
        for (int i = 0; i < p.length; i++) {
            Point[] line = new Point[4];
            double sloop = 0.0;
            line[0] = p[i];
            for (int j = i + 1; j < p.length; j++) {
                sloop = p[i].slopeTo(p[j]);
                line[1] = p[j];

                for (int k = j + 1; k < p.length; k++) {
                    double kSloop = p[i].slopeTo(p[k]);
                    if (Double.compare(sloop, kSloop) != 0) continue;
                    if (line[2] == null) {
                        line[2] = p[k];
                        continue;
                    }
                    line[3] = p[k];
                }

                if (line[3] == null) {
                    line[1] = null;
                    line[2] = null;
                    continue;
                }
                LineSegment segment = new LineSegment(line[0], line[3]);
                if (segments.isEmpty()) {
                    segments.enqueue(segment);
                    sloops.push(sloop);
                } else if (sloop != sloops.peek()) {
                    segments.enqueue(segment);
                    sloops.push(sloop);
                }
                line[1] = null;
                line[2] = null;
                line[3] = null;
            }
        }
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        LineSegment[] temp = new LineSegment[segments.size()];
        int current = 0;
        for (LineSegment segment : segments) {
            temp[current++] = segment;
        }
        return temp;
    }

    public boolean isEmpty() {
        return segments.size() == 0;
    }


    private void validate(Point point) {
        if (point == null) throw new IllegalArgumentException("point is null");
    }
}
