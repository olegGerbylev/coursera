import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {
        if (isNull(points)) {
            throw new IllegalArgumentException("Points array can't be null or contain null values");
        }
        Point[] pointsCopy = Arrays.copyOf(points, points.length);

        Arrays.sort(pointsCopy);

        if (isDuplicatedPoints(pointsCopy)) {
            throw new IllegalArgumentException("Points array can't contains duplicated points");
        }

        int pointsLength = pointsCopy.length;
        ArrayList<LineSegment> lineSegmentsList = new ArrayList<>();

        for (int p = 0; p < (pointsLength - 3); p++) {
            for (int q = p + 1; q < (pointsLength - 2); q++) {
                for (int r = q + 1; r < (pointsLength - 1); r++) {
                    if (pointsCopy[p].slopeTo(pointsCopy[q]) != pointsCopy[p].slopeTo(pointsCopy[r])) {
                        continue;
                    }
                    for (int s = (r + 1); s < pointsLength; s++) {
                        if (pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[s])) {
                            lineSegmentsList.add(new LineSegment(pointsCopy[p], pointsCopy[s]));
                        }
                    }
                }
            }
        }
        lineSegments = lineSegmentsList.toArray(new LineSegment[lineSegmentsList.size()]);

    }    // finds all line segments containing 4 points

    public int numberOfSegments() {
        return lineSegments.length;
    }        // the number of line segments

    //
    public LineSegment[] segments() {
        return lineSegments;
    }                // the line segments

    private boolean isNull(Point[] points) {
        if (points == null) return true;
        for (Point point : points) {
            if (point == null) return true;
        }
        return false;
    }

    private boolean isDuplicatedPoints(Point[] points) {
        for (int i = 1; i < points.length; i++) {
            if (points[i - 1].compareTo(points[i]) == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        // read the n points from a file
//        In in = new In(args[0]);
//        int n = in.readInt();
        Point[] points = new Point[20];
        for (int i = 0; i < 20; i++) {
            int x = StdRandom.uniformInt(20);
            int y = StdRandom.uniformInt(20);
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 20);
        StdDraw.setYscale(0, 20);
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