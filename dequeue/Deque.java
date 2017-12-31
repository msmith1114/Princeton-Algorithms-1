import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node head;
    private Node tail;
    
   private class Node
   {
       private Item item;
       private Node next;
       private Node prev;
   }
   
   public Deque() // construct an empty deque
   {   
        head = null;
        tail = null;
        size = 0;
   }
                          
   public boolean isEmpty() // is the deque empty?
   {
       return head == null || tail == null;
   }
   public int size()  // return the number of items on the deque
   {
       return size;  
   }
      
   public void addFirst(Item item) // add the item to the front
   {

         Node oldhead = head;
         head = new Node();
         head.item = item;
   
       if(isEmpty())
       {
           tail = head;
       }
       else 
       {
         head.next = oldhead.next;
         head.prev = oldhead;
         oldhead.next = head;  
       }
       size++;

   }
       
   public void addLast(Item item){ // add the item to the end
       
         Node oldtail = tail;
         tail = new Node();
         tail.item = item;
         
       if(isEmpty())
       {
           head = tail;
       }
       else 
       {
         tail.prev = oldtail.prev;
         tail.next = oldtail;
         oldtail.prev = tail;  
       }
       size++;

   }
       
   public Item removeFirst() // remove and return the item from the front
   {
        if (isEmpty())
            throw new NoSuchElementException("Cannot remove from empty queue");       
        else
        {
            Item item = head.item;
            head = head.prev;
            if(isEmpty())
            {
              tail = null;  
            }
            else 
            {
              head.next = null;   
            }
            size--;
            return item;
        }

   }
       
   public Item removeLast() // remove and return the item from the end
   {
        if (isEmpty())
            throw new NoSuchElementException("Cannot remove from empty queue");
        else
        {
            Item item = tail.item;
            tail = tail.next;
            if(isEmpty())
            {
              head = null;  
            }
            else 
            {
             tail.prev = null;   
            }
            size--;
            return item;
        }

   }
   
   
       
   public Iterator<Item> iterator() // return an iterator over items in order from front to end
   {
       return new DequeIterator();
   }
       
       
   private class DequeIterator implements Iterator<Item> {
        private Node current = head;
        
        public boolean hasNext()     { return current != null; }
        public void remove()         { throw new UnsupportedOperationException("Unsupported Operation"); }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("No more nodes to traverse");
            current = current.prev;
            return current.item;
        }

    }
       
   public static void main(String[] args)
   {
       
  Deque<Integer> d = new Deque<Integer>();
  d.addFirst(1);

 d.addLast(2);

  d.addLast(3);
    d.addFirst(7);
 System.out.println(d.head.item);
 System.out.println(d.tail.item);
  System.out.println(d.removeLast());
 System.out.println(d.tail.item);
  System.out.println(d.head.item);
    System.out.println(d.removeFirst());
      System.out.println(d.head.item);
       System.out.println(d.tail.item);

 /*
  d.addFirst(2);
  System.out.println(d.head.item);
  System.out.println(d.head.next.item);
 
  System.out.println(d.removeLast());
  System.out.println(d.removeLast());
  d.addLast(4);
  System.out.println(d.removeLast());
  */
       
   }
}