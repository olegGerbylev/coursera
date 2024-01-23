import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    private class PointComparator implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            double p1Slope = slopeTo(p1);
            double p2Slope = slopeTo(p2);

            if (p1Slope > p2Slope) return 1;
            if (p1Slope < p2Slope) return -1;
            return 0;
        }
    }

    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        int d = that.y - this.y;
        int p = that.x - this.x;
        if (d == 0 && p == 0) return Double.NEGATIVE_INFINITY;
        if (d == 0) return +0.0;
        if (p == 0) return Double.POSITIVE_INFINITY;
        return (double) d / p;
    }

    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (that.y < this.y) return 1;
        if (that.y == this.y && that.x < this.x) return 1;
        if (that.x == this.x && that.y == this.y) return 0;
        return -1;
    }

    public Comparator<Point> slopeOrder() {
        return new PointComparator();
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}