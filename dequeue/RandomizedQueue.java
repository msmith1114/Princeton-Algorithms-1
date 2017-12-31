
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {
   private Item[] array; //the array that holds the items
   private int N; //count of items
   
   
   public RandomizedQueue() // construct an empty randomized queue
   {
        array = (Item[])new Object[2];
        N = 0;
   }
   public boolean isEmpty() // is the randomized queue empty?
   {
       return N == 0;
   }
   public int size() // return the number of items on the randomized queue
   {
       return N;
   }
   public void enqueue(Item item) // add the item
   {
       if(item == null)
           throw new IllegalArgumentException("Cannot enqueue a null value");
                                                  
       if(N == array.length)
       {
           resize(2 * array.length);
       }
       array[N++] = item;                      
       
   }
   
   private void resize(int capacity) // resize
   {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) 
        {
            copy[i] = array[i];
        }
        array = copy;
    }
   
   public Item dequeue() // remove and return a random item
   {
       if (isEmpty())
            throw new NoSuchElementException("Cannot remove from an empty array"); 
       
        int randI = StdRandom.uniform(N);
        Item item = array[randI];
        array[randI] = array[N-1];
        array[N-1] = null;
        N--;
        if ( N > 0 && N == array.length/4 ) {
            resize(array.length / 2);
        }
        return item;
       
       

   }
   public Item sample() // return a random item (but do not remove it)
   {
       if (isEmpty())
            throw new NoSuchElementException("Cannot sample from an empty aray"); 
       
        int randI2 = StdRandom.uniform(N);
        Item item = array[randI2];
        return item;

   }
   
   public Iterator<Item> iterator() 
    {
        return new RandomizedIterator();
    }

   
    private class RandomizedIterator implements Iterator<Item> 
    {
        private Item[] copy;
        private int current;

        public RandomizedIterator() 
        {
            copy = (Item[]) new Object[N];
            for (int i = 0; i < N; i++) 
            {
                copy[i] = array[i];
            }
            current = N;
        }

        public boolean hasNext() 
        {
            return current > 0;
        }

        public Item next() 
        {
            if (!hasNext()) 
                throw new NoSuchElementException("Out of items!");
            int randI = StdRandom.uniform(current);
            Item item = copy[randI];
            copy[randI] = copy[current-1];
            copy[current-1] = null;
            current--;
            return item;
        }

        public void remove() 
        {
            throw new UnsupportedOperationException("Remove is not supported");
        }
    }

}