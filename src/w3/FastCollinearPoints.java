package w3;

/**
 * @author
 * @create 2018-01-03-17:07
 **/
public class FastCollinearPoints {
    private Point[] p;
    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {

    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments;
    }
}
