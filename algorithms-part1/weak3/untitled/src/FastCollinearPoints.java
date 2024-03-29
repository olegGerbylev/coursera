import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    private int segmentsCount = 0;
    private LineSegment[] segments = new LineSegment[0];
    private int[] vertex;
    private int vertexCount = 0;

    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();

        segments = new LineSegment[points.length];
        vertex = new int[points.length];

        class Tuple implements Comparable<Tuple> {
            public int idx;
            public double slope;

            public Tuple(int idx, double slope) {
                this.idx = idx;
                this.slope = slope;
            }

            public int compareTo(Tuple other) {
                if (Double.compare(this.slope, other.slope) == 0)
                    return this.p().compareTo(other.p());
                else
                    return Double.compare(this.slope, other.slope);
            }

            public Point p() {
                return points[idx];
            }

            public String toString() {
                return p().toString() + slope;
            }
        }

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException();

            Tuple[] tuples = new Tuple[points.length - i - 1];
            for (int j = i + 1; j < points.length; j++) {
                if (points[j] == null || points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException();

                tuples[j - i - 1] = new Tuple(j, points[i].slopeTo(points[j]));
            }
            Arrays.sort(tuples);

            int s = 0;
            int e = 0;
            while (e < tuples.length) {
                e++;
                if (e >= tuples.length || tuples[s].slope != tuples[e].slope) {
                    if (e - s > 2) {
                        int idx1 = min(points, i, tuples[s].idx);
                        int idx2 = max(points, i, tuples[e - 1].idx);
                        boolean isAlreadyUsed = useVertex(points, idx1, 0, vertexCount);
                        isAlreadyUsed = useVertex(points, idx2, 0, vertexCount) || isAlreadyUsed;
                        if (isAlreadyUsed)
                            segments[segmentsCount++] = new LineSegment(points[idx1], points[idx2]);
                    }
                    s = e;
                }
            }
        }
    }

    private int min(Point[] points, int idx1, int idx2) {
        Point x = points[idx1];
        Point y = points[idx2];
        return (x.compareTo(y) <= 0) ? idx1 : idx2;
    }

    private int max(Point[] points, int idx1, int idx2) {
        Point x = points[idx1];
        Point y = points[idx2];
        return (x.compareTo(y) >= 0) ? idx1 : idx2;
    }

    private boolean useVertex(Point[] points, int idx, int lo, int length) {
        if (lo + length > vertex.length)
            return false;

        if (length == 0) {
            vertex[lo] = idx;
            vertexCount++;
            return true;
        }
        if (length == 1) {
            if (lo < (points.length - 1) && points[vertex[lo]].compareTo(points[idx]) != 0) {
                vertex[lo + 1] = idx;
                vertexCount++;
                return true;
            } else
                return false;
        }
        int middle = lo + length / 2;
        Point p = points[idx];
        int ratio = p.compareTo(points[vertex[middle]]);
        if (ratio == 0)
            return false;
        if (ratio < 0) {
            return useVertex(points, idx, 0, middle);
        } else {
            return useVertex(points, idx, middle, (lo + length) - middle);
        }
    }


    public int numberOfSegments() {
        return segmentsCount;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(this.segments, segmentsCount);
    }

    public static void main(String[] args) {

        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        StdDraw.setPenRadius(0.01);

        StdDraw.enableDoubleBuffering();
        int i = 0;
        StdDraw.show();

        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
        }
        StdDraw.show();
    }
}