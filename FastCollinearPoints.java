import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lineSegments;

    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 or more points

        // Performance requirement.
        // The order of growth of the running time of
        // your program should be n2*log n in the worst case
        // and it should use space proportional to n plus
        // the number of line segments returned.
        // FastCollinearPoints should work properly
        // even if the input has 5 or more collinear points.

        if (points == null) {
            throw new IllegalArgumentException("points is null");
        }

        lineSegments = new ArrayList<LineSegment>();

        Arrays.sort(points,  points[7].slopeOrder());

        for (int i = 0; i < points.length; i++) {
            // Arrays.sort(points, points[i].slopeOrder());
            StdOut.println(points[i].toString());
        }

    }

    public int numberOfSegments() {
        // the number of line segments
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        // the line segments
        LineSegment[] segments = new LineSegment[numberOfSegments()];
        return lineSegments.toArray(segments);
    }

    public static void main(String[] args) {
        File file = new File("collinear/Input8.txt");

        // read the n points from a file
        In in = new In(file);
        int n = in.readInt();
        StdOut.println(n);
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