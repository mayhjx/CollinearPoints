import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> lineSegments;

    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        if (points == null) {
            throw new IllegalArgumentException("points is null");
        }

        lineSegments = new ArrayList<LineSegment>();
        Arrays.sort(points);

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k])
                                && points[i].slopeTo(points[k]) == points[i].slopeTo(points[l])) {
                            lineSegments.add(new LineSegment(points[i], points[l]));
                        }
                    }
                }
            }
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

    // public static void main(String[] args) {
    //     File file = new File("collinear/Input8.txt");

    //     // read the n points from a file
    //     In in = new In(file);
    //     int n = in.readInt();
    //     StdOut.println(n);

    //     Point[] points = new Point[n];
    //     for (int i = 0; i < n; i++) {
    //         int x = in.readInt();
    //         int y = in.readInt();
    //         points[i] = new Point(x, y);
    //     }

    //     // draw the points
    //     StdDraw.enableDoubleBuffering();
    //     StdDraw.setXscale(0, 32768);
    //     StdDraw.setYscale(0, 32768);
    //     for (Point p : points) {
    //         p.draw();
    //     }
    //     StdDraw.show();

    //     // print and draw the line segments
    //     BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    //     StdOut.println(collinear.numberOfSegments());

    //     for (LineSegment segment : collinear.segments()) {
    //         StdOut.println(segment);
    //         segment.draw();
    //     }
    //     StdDraw.show();
    // }
}