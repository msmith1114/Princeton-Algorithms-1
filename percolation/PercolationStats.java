import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;


public class PercolationStats {
    private double[] scores;

    
    public PercolationStats(int n, int trials) {
        if (trials <= 0 || n <= 0) throw new IllegalArgumentException();
        scores = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            int opensite = 0;
            while (!perc.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if(!perc.isOpen(row,col))
                {
                    opensite++;
                    perc.open(row, col);
                    
                }
            }
            scores[i] = opensite * 1.0 / (n * n);
        }
    }
    
    public double stddev() {
        return StdStats.stddev(scores);
    }
    
    public double mean() {
        return StdStats.mean(scores);
    }
    
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(scores.length));
    }

    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(scores.length));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats percStats = new PercolationStats(n, t);
        StdOut.printf("mean      = %f\n", percStats.mean());
        StdOut.printf("stddev    = %f\n", percStats.stddev());
        StdOut.println("95% confidence interval = " + percStats.confidenceLo() + " , " + percStats.confidenceHi());
    }
}