package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import static org.junit.Assert.*;

/**
 * for grading.
 * @author wenhao
 */
public class Percolation {
    /**
     * create N-by-N grid, with all sites initially blocked.
     */
    private int n;
    private int n2;
    private int openNum;
    private boolean[] grids;
    private WeightedQuickUnionUF group;
    public Percolation(int N) {
        if (N < 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.openNum = 0;
        this.n = N;
        this.n2 = n * n;
        this.group = new WeightedQuickUnionUF(n2 + 2);
        this.grids = new boolean[n2 + 2];
    }
    // open the site (row, col) if it is not open already
    //auto connect grid with its neighbor, and if grid in
    // row 0 open,connect it with n2+1, if grid in last row
//    open, connect it with n2+2;
    public void open(int row, int col) {
        if (row >= n || row < 0 || col >= n || col < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return;
        }
        int d = row * n + col;
        grids[d] = true;
        openNum++;
        autoUnion(row - 1, col, d);
        autoUnion(row + 1, col, d);
        autoUnion(row, col - 1, d);
        autoUnion(row, col + 1, d);
        if (row == 0) {
            group.union(d, n2);
        }
        if (row == n - 1) {
            group.union(d, n2 + 1);
        }
    }
    private void autoUnion(int row, int col, int d) {
        if (row >= n || row < 0 || col >= n || col <0) {
            return;
        }
        int near = row * n + col;
        if (isOpen(row, col)) {
            group.union(d, near);
        }
    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if (row >= n || row < 0 || col >= n || col < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        int d = row * n + col;
        return grids[d];
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row >= n || row < 0 || col >= n || col < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        int d = row * n + col;
        return group.connected(d, n2);
    }
//    // number of open sites
    public int numberOfOpenSites() {
        return openNum;
    }
//    // does the system percolate?
    public boolean percolates() {
        return group.connected(n2, n2 + 1);
    }

    /**
     *use for unit testing (not required, but keep this
     * here for the autograder).
     * @param args inputfile
     */
    public static void main(String[] args) {
        Percolation p = new Percolation(2);
        p.open(1, 1);
        assertEquals(1, p.numberOfOpenSites());
        assertTrue(p.isOpen(1, 1));
        assertFalse(p.isOpen(0, 0));
        assertFalse(p.percolates());
        p.open(0, 0);
        assertEquals(2, p.numberOfOpenSites());
        assertTrue(p.isOpen(0, 0));
        assertFalse(p.isOpen(0, 1));
        assertFalse(p.percolates());
        p.open(0, 1);
        assertEquals(3, p.numberOfOpenSites());
        assertTrue(p.isOpen(0, 1));
        assertFalse(p.isOpen(1, 0));
        assertTrue(p.percolates());
    }
}
