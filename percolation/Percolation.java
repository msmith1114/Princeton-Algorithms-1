import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // Open = 1
    // Closed = 0 
    // For site
    
    /*
     * (1,1) = Top-Left Row
     * (n,n) = Bottom-Right Row
     */
    
    private int gridsize;
    private int opensites;
    private boolean[][] grid; //Grid to Hold items
    private WeightedQuickUnionUF wuF; //empty data structure
    private int top; // virtual top
    private int bottom; // virtual bottom
    
    public Percolation(int n){
        if (n <= 0) throw new IllegalArgumentException();
        gridsize = n;
        bottom = 0; //SITE # for top is 0
        bottom = n*n+1; //SITE # for bottom is N*N+1
        wuF = new WeightedQuickUnionUF((n * n) + 2);
        grid = new boolean[n][n]; //Initalize new 2-D Array (n*n)
        
 
    }
   public void open(int row, int col)    // open site (row, col) if it is not open already
   {
      if (row < 1 || row > gridsize || col < 1 || col > gridsize) throw new IllegalArgumentException();
      if(isOpen(row,col)){return;}
         
      grid[row-1][col-1] = true; //Open Site
      opensites++;

         
         if(row == 1){
          wuF.union(xyTo1D(row,col),top); 
         }
         if(row == gridsize){
           wuF.union(xyTo1D(row,col),bottom); 
         }
         
         // Look Up  (assumes row > 1)
         if(row > 1 && isOpen(row-1,col) == true){
          wuF.union(xyTo1D(row-1,col),xyTo1D(row,col));  
         }
         // Look down (assumes row < gridsize)
         if(row < gridsize && isOpen(row+1,col) == true){
          wuF.union(xyTo1D(row+1,col),xyTo1D(row,col)); 
         }
         // Look left (assumes col > 1)
         if(col > 1 && isOpen(row,col-1) == true){
          wuF.union(xyTo1D(row,col-1),xyTo1D(row,col)); 
         }
         // Look right
         if(col < gridsize && isOpen(row,col+1) == true){
          wuF.union(xyTo1D(row,col+1),xyTo1D(row,col));  
         }
         
         
         
   }
   public boolean isOpen(int row, int col)  // is site (row, col) open?
   {
    if (row < 1 || row > gridsize || col < 1 || col > gridsize) throw new IllegalArgumentException();
    if(grid[row-1][col-1] == true){return true;}
    else{return false;}
   }
   
   public boolean isFull(int row, int col)  // is site (row, col) full?
   {
       if (row < 1 || row > gridsize || col < 1 || col > gridsize) throw new IllegalArgumentException();
       if(wuF.connected(xyTo1D(row,col),top)){return true;}
       else{return false;}
   }
   
   
   public boolean percolates()              // does the system percolate?
   {
       if(wuF.connected(top,bottom)){return true;}
       else{return false;}
   }
   
   public int numberOfOpenSites() {
        return opensites;
    }
   
   private int xyTo1D(int row, int col){
       if (row < 1 || row > gridsize || col < 1 || col > gridsize) throw new IllegalArgumentException();
       return (row - 1) * gridsize + col;
       
       
   }
         
   }
   

