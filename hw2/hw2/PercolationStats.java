package hw2;
import static edu.princeton.cs.algs4.StdRandom.uniform;

public class PercolationStats {
    // perform t independent experiments on an n-by-n grid
    private int n;
    private int t;
    private PercolationFactory pf;
    private double thresholds[];
    private double mean;
    private double stddev;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.n = N;
        this.t = T;
        this.pf = pf;
        this.thresholds = new double[T];

        for (int i = 0; i < T; i++) {
            thresholds[i] = thresholdHelper();
        }
    }
    private double thresholdHelper() {
        Percolation p = pf.make(n);
        while (!p.percolates()) {
            int row = uniform(n);
            int col = uniform(n);
            if (p.isOpen(row, col)) {
                continue;
            }
            p.open(row, col);
        }
        return p.numberOfOpenSites() / (double) (n * n);
    }
    // sample mean of percolation threshold
    public double mean() {
        this.mean  = edu.princeton.cs.algs4.StdStats.mean(thresholds);
        return this.mean;
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        this.stddev = edu.princeton.cs.algs4.StdStats.stddev(thresholds);
        return this.stddev;
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        mean();
        stddev();
        double confidenceLow = mean - (1.96 * stddev) / Math.sqrt(t);
        return confidenceLow;

    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        mean();
        stddev();
        double confidenceHigh = mean + (1.96 * stddev) / Math.sqrt(t);
        return confidenceHigh;
    }

    public static void main(String[] args) {
        PercolationStats test = new PercolationStats(20,10,new PercolationFactory());
        System.out.println(test.stddev());
    }
}
