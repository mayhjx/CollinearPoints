import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.print.attribute.standard.PresentationDirection;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lineSegments;
    private ArrayList<Point> InOrderPoints;

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
        InOrderPoints = new ArrayList<Point>();

        Arrays.sort(points);
        for (int i = 0; i < points.length; i++) {
            // StdOut.println(points[i].toString());
            InOrderPoints.add(points[i]);
        }

        ArrayList<Point> collinear = new ArrayList<>();

        for (int i = 0; i < InOrderPoints.size(); i++) {
            Point origin = InOrderPoints.get(i);
            // StdOut.println("origin point: " + origin.toString());
            Arrays.sort(points, origin.slopeOrder());
            int count = 0;
            double preSlope = 0.0;
            collinear.add(origin);

            for (int j = 1; j < points.length - 2; j++) {
                // StdOut.println(points[j].toString());
                double slope1 = origin.slopeTo(points[j]);
                double slope2 = origin.slopeTo(points[j + 1]);
                if (preSlope == 0.0) {
                    preSlope = slope1;
                }

                if (slope1 == slope2 && slope2 == preSlope) {
                    count++;
                    collinear.add(points[j]);
                    collinear.add(points[j + 1]);
                } else if (count >= 2) {
                    Point[] p = new Point[collinear.size()];
                    collinear.toArray(p);
                    Arrays.sort(p);
                    if (p[0] == origin) {
                        lineSegments.add(new LineSegment(origin, p[p.length - 1]));
                    }
                } else {
                    count = 0;
                    preSlope = slope2;
                }
            }
            collinear.clear();
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
        File file = new File("collinear/input6.txt");

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