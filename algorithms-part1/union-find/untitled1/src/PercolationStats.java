import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONST_95 = 1.96;
    private int trials;

    private double[] thresholdList;

    public PercolationStats(int n, int trials) {
        if (n < 1) {
            throw new IllegalArgumentException("Grid must have at least one row and column");
        }

        if (trials < 1) {
            throw new IllegalArgumentException("You must run percolation at least once");
        }

        this.trials = trials;
        thresholdList = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);

            while (!percolation.percolates()) {
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);

                percolation.open(row, col);
            }

            thresholdList[i] = (double) percolation.numberOfOpenSites() / (n * n);
        }
    }

    public double mean()
    {
        return StdStats.mean(thresholdList);
    }

    public double stddev()
    {
        return StdStats.stddev(thresholdList);
    }

    public double confidenceLo()
    {
        return mean() - (CONST_95 * stddev() / Math.sqrt(trials));
    }

    public double confidenceHi()
    {
        return mean() + (CONST_95 * stddev() / Math.sqrt(trials));
    }
    public static void main(String[] args)
    {
        int gridLength = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(gridLength, trials);

        StdOut.println("mean = "+ stats.mean());
        StdOut.println("stddev = "+ stats.stddev());
        StdOut.println("95% confidence interval = "+ stats.confidenceLo() + ", " + stats.confidenceHi());
    }
}