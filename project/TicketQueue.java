import java.util.LinkedList;//import the LinkedList package
import java.util.Objects;
import java.util.Queue;

public class TicketQueue 
{
    private LinkedList<CustomerInformation> queue; // Declare the object of queue using LinkedList
    int size = 0;

    // Constructor without param
    public TicketQueue() 
    {
        queue = new LinkedList<>();
    }

    // Method to insert an object to the queue
    public void enqueue(CustomerInformation item) 
    {
        queue.addLast(item);
        size++;
    }

    // Method to remove an object from the queue
    public Object dequeue() 
    {
        if (!empty()) 
        {
            size--;
            return queue.removeFirst(); // Remove the first element (front)
        } 
        else 
        {
            System.out.println("Queue is empty!");
            return null; // Return null or throw an exception to handle empty queue case
        }
    }

    // Method to test whether the queue is empty or not
    public boolean empty() 
    {
        return (queue.size() == 0);
    }

    // Return the element at the front without removing it
    public Object front() 
    {
        if (!empty())
            return queue.getFirst();
        else 
        {
            System.out.println("Queue is empty");
            return null; // Return null or throw an exception to handle empty queue case
        }
    }
    
    // Method to get the size of the queue
    public int size() 
    {
        return size;
    }
}
