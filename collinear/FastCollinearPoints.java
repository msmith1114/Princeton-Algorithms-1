import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FastCollinearPoints {
    
    private List<LineSegment> segments = new ArrayList<LineSegment>();

    
    public FastCollinearPoints(Point[] points) // finds all line segments containing 4 points
    { 

        //check for null constructor
        if(points == null)
            throw new IllegalArgumentException("constructor cannot be null!");

        //copy points
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);
        
        //check for any null points
        for(int i = 0; i < pointsCopy.length; i++)
        {
         if(pointsCopy[i] == null)
             throw new IllegalArgumentException("contains a null point!");
        }
        //check for dupes
        for (int j = 0; j<pointsCopy.length; j++)
        {
            for (int k= j+1; k<pointsCopy.length; k++)
            {
                if (k != j && pointsCopy[k] == pointsCopy[j])
                    throw new IllegalArgumentException("No Duplicate points allowed!");
            }
        }
        
        
         for (int i = 0; i < pointsCopy.length - 3; i++) {
            Arrays.sort(pointsCopy);
            Arrays.sort(pointsCopy, pointsCopy[i].slopeOrder());

            for (int p = 0, first = 1, last = 2; last < pointsCopy.length; last++) {
                while (last < pointsCopy.length
                        && Double.compare(pointsCopy[p].slopeTo(pointsCopy[first]), pointsCopy[p].slopeTo(pointsCopy[last])) == 0) {
                    last++;
                }
                if (last - first >= 3 && pointsCopy[p].compareTo(pointsCopy[first]) < 0) {
                    segments.add(new LineSegment(pointsCopy[p], pointsCopy[last - 1]));
                }
                first = last;
            }
        }
    }
    public int numberOfSegments() // the number of line segments
    { 
        return segments.size();
        
    }
    public LineSegment[] segments() // the line segments
    { 
         return segments.toArray(new LineSegment[segments.size()]);
    }
    
}