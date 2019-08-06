package hw2;
import static edu.princeton.cs.algs4.StdRandom.uniform;

public class PercolationStats {
    // perform T independent experiments on an N-by-N grid
    private int N;
    private int T;
    private PercolationFactory pf;
    private double thresholds[];
    private double mean;
    private double stddev;

    public PercolationStats(int N, int T, PercolationFactory pf){
        if(N <= 0 || T <= 0){
            throw new java.lang.IllegalArgumentException();
        }
        this.N = N;
        this.T = T;
        this.pf = pf;
        this.thresholds = new double[T];

        for(int i = 0; i < T; i++){
            thresholds[i] = thresholdHelper();
        }
    }
    private double thresholdHelper(){
        Percolation p = pf.make(N);
        while (!p.percolates()){
            int row = uniform(N);
            int col = uniform(N);
            if(p.isOpen(N,N)){
                continue;
            }
            p.open(row,col);
        }
        return p.numberOfOpenSites();
    }
    // sample mean of percolation threshold
    public double mean(){
        this.mean  = edu.princeton.cs.algs4.StdStats.mean(thresholds);
        return this.mean;
    }
    // sample standard deviation of percolation threshold
    public double stddev(){
        this.stddev = edu.princeton.cs.algs4.StdStats.stddev(thresholds);
        return this.stddev;
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow(){
        double confidenceLow = mean - (1.96 * stddev)/Math.sqrt(T);
        return confidenceLow;

    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh(){
        double confidenceHigh = mean + (1.96 * stddev)/Math.sqrt(T);
        return confidenceHigh;

    }
}
