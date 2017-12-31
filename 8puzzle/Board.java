import java.util.ArrayList;


public class Board {
    //[ROW][COL]
    //[0,0][0,1][0,2]
    //[1,0][1,1][1,2]
    //[2,0][2,1][2,2]
    private int tiles[][];
    private int n; 
    
    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks)
    {
        n = blocks.length;
        this.tiles = new int[n][n];
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                this.tiles[i][j] = blocks[i][j];
            }
        }  
    }
    public int dimension() // board dimension n
    {
       return n; 
    } 
    
    public int hamming() // number of blocks out of place
    {
        int counter = 1;
        int hamming = 0;
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(tiles[i][j] != counter && tiles[i][j] != 0)
                {
                    hamming++;
                    counter++;
                }
                else
                    counter++;
            }
        }
        return hamming;
    }

    public int manhattan() // sum of Manhattan distances between blocks and goal
    {
        int manhattan = 0;
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
               if (tiles[i][j] != 0) 
               { 
                   int targetX = (tiles[i][j] - 1) / n; // expected x-coordinate (row)
                   int targetY = (tiles[i][j] - 1) % n; // expected y-coordinate (col)
                   int dx = i - targetX; 
                   int dy = j - targetY; 
                   manhattan += Math.abs(dx) + Math.abs(dy);
               }
            }   
        }
        return manhattan;
    }
        
    public boolean isGoal() // is this board the goal board?
    {
        return hamming() == 0;   
    }
            
    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    {
        int[][] twinTiles = copyOf(tiles);
        if(twinTiles[0][0] != 0 && twinTiles[0][1] != 0) // swap pos 0,0 and 0,1 if neither is 0
        {
            swap(twinTiles,0,0,0,1); //swap them
        }
        else
        {
           swap(twinTiles,1,0,1,1); //otherwise swap 1,0 and 1,1
        }
        Board twin = new Board(twinTiles);
        return twin;
    }
 
    public boolean equals(Object y) // does this board equal y?
    {
        if (y == this) return true;
        if (y == null || y.getClass() != this.getClass()) return false;
        if (n != ((Board) y).n) return false;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (tiles[i][j] != ((Board) y).tiles[i][j])
                    return false;
        return true;
    }
        
    public Iterable<Board> neighbors() // all neighboring boards
    {

        int blankI = 0; 
        int blankJ = 0;
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(tiles[i][j] == 0)
                {
                 blankI = i;
                 blankJ = j;
                }     
            }
        }
        
        ArrayList<Board> neighbors = new ArrayList<Board>();

        //swap left
        if(blankI > 0)
        {
            int[][] neighborTiles = copyOf(tiles);
            swap(neighborTiles, blankI, blankJ, blankI-1, blankJ);
            Board neighbor = new Board(neighborTiles);
            neighbors.add(neighbor);
        }
        //swap right   
        if(blankI < n - 1)
        {
            int[][] neighborTiles = copyOf(tiles);
            swap(neighborTiles, blankI, blankJ, blankI+1, blankJ);
            Board neighbor = new Board(neighborTiles);
            neighbors.add(neighbor);
        }
        //swap up
        if(blankJ > 0)
        {
            int[][] neighborTiles = copyOf(tiles);
            swap(neighborTiles, blankI, blankJ, blankI, blankJ-1);
            Board neighbor = new Board(neighborTiles);
            neighbors.add(neighbor);
        }
        //swap down   
        if(blankJ < n - 1)
        {
            int[][] neighborTiles = copyOf(tiles);
            swap(neighborTiles, blankI, blankJ, blankI, blankJ+1);
            Board neighbor = new Board(neighborTiles);
            neighbors.add(neighbor);
        }
        return neighbors;
        
        
        
    }
    public String toString() // string representation of this board (in the output format specified below)
    {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
    }
        return s.toString();
    } 
    
    private void swap(int [][] arr, int i, int j, int otheri, int otherj)
    {
     int temp = arr[i][j]; //store initial swap position
     arr[i][j] = arr[otheri][otherj]; //swap other position into initial
     arr[otheri][otherj] = temp; //store initial swap position (temp) into other position
    }
    
    //see: https://stackoverflow.com/questions/1686425/copy-a-2d-array-in-java
    private int[][] copyOf(int[][] matrix) 
    {
        int[][] clone = new int[matrix.length][];
        for (int row = 0; row < matrix.length; row++) {
            clone[row] = matrix[row].clone();
        }
        return clone;
    }
        

     public static void main(String[] args) {
    }
}