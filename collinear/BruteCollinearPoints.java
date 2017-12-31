import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;


public class BruteCollinearPoints {
    
    private List<LineSegment> segments = new ArrayList<LineSegment>();
    private LineSegment[] linkedP;
    
    public BruteCollinearPoints(Point[] points) // finds all line segments containing 4 points
    { 

        //check for null constructor
        if(points == null)
            throw new IllegalArgumentException("constructor cannot be null!");

        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);
        int n = pointsCopy.length; //length of points

        
        //check for any null points
        for(int i = 0; i < pointsCopy.length; i++)
        {
         if(pointsCopy[i] == null)
             throw new java.lang.IllegalArgumentException("contains a null point!");
        }
        //check for dupes
        for (int j = 0;  j < pointsCopy.length; j++)
        {
            for (int k= j+1; k < pointsCopy.length; k++)
            {
                if (k != j && pointsCopy[k] == pointsCopy[j])
                    throw new java.lang.IllegalArgumentException("No Duplicate points allowed!");
            }
        }
        
        //check points in for loop and jumping up 4 every time. (0-3)
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        Point p = pointsCopy[i];
                        Point q = pointsCopy[j];
                        Point r = pointsCopy[k];
                        Point s = pointsCopy[l];
                        
                        if(p.slopeTo(q) == p.slopeTo(r) &&  p.slopeTo(r) == p.slopeTo(s))
                        {
                            segments.add(new LineSegment(p, s));
                        }
                        
                        
                    }
                }
            }
        }
        linkedP = segments.toArray(new LineSegment[segments.size()]);
    }
    public int numberOfSegments() // the number of line segments
    { 
        return linkedP.length;
        
    }
    public LineSegment[] segments() // the line segments
    { 
        return linkedP.clone();
    }
    
    public static void main(String[] args) {

    // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
        int x = in.readInt();
        int y = in.readInt();
        points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
        p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
    StdDraw.show();
}
    
    
}