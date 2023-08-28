import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
/**
model a percolation system using an n-by-n grid of sites.
Each site is either open or blocked. A full site is an open
site that can be connected to an open site in the top row
via a chain of neighboring (left, right, up, down) open sites.
We say the system percolates if there is a full site in the
bottom row. In other words, a system percolates if we fill
all open sites connected to the top row and that process
fills some open site on the bottom row.
**/

public class Percolation {
    private WeightedQuickUnionUF percolation;
    private boolean[][] grid;
    private int number_of_open_sites=0;
    private int size;
    private final int top = 0;
    private static int bottom ;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        percolation = new WeightedQuickUnionUF(n*n+2);
        size = n;
        bottom = n*n+1;
        grid = new boolean[n][n];
    }
    
    private int getValue(int row,int col){
        return row*size+col;
    }
    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        this.grid[row][col] = true;
        this.number_of_open_sites++;

        if (row>size || row<0){
            throw new IndexOutOfBoundsException("row is out of bound.");
        }
        if ( col>size || col<0){
            throw new IndexOutOfBoundsException("col is out of bound.");
        }
        if (col ==0){
            percolation.union(getValue(row, 0), 0);

        }
        if (row+1<size && isOpen(row+1, col)){
            this.percolation.union(getValue(row+1, col), getValue(row, col));

        }
        if (row-1>=0 && isOpen(row-1, col)){
            this.percolation.union(getValue(row-1, col), getValue(row, col));

        }

        if (col+1<size && isOpen(row, col+1)){
            this.percolation.union(getValue(row,col+1), getValue(row, col));
        }
        if (col-1>=0 && isOpen(row, col-1)){
            this.percolation.union(getValue(row, col-1), getValue(row,col));
        }
        if (col ==size-1){
            percolation.union(getValue(row, col), bottom);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        return  this.grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){

        return this.percolation.find(getValue(row, col))==percolation.find(top);
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return this.number_of_open_sites;
    }

    // does the system percolate?
    public boolean percolates(){
        return percolation.find(bottom)== percolation.find(top);
    }

    // test client (optional)
    public static void main(String[] args){
        Percolation percolation1 = new Percolation(5);
        percolation1.open(3, 3);
        System.out.println(percolation1.isOpen(4, 3));
        percolation1.open(3, 2);
        percolation1.open(2, 2);
        percolation1.open(2, 1);
        percolation1.open(2, 0);
        percolation1.open(3,  4);
        percolation1.open(4,  3);
        percolation1.open(2,  3);

        System.out.println(percolation1.percolates());
        System.out.println(percolation1.isFull(3,3));
        System.out.println(percolation1.isFull(4,3));
    }
}
