import java.util.LinkedList;
import java.util.Queue;
import java.util.NoSuchElementException;

public class QueueConcept {

    public static void main(String[] args) {
        
        /*
        ==================================================
        THEORY: WHAT IS A QUEUE?
        ==================================================
        * **Concept**: A Queue is a linear data structure that follows the **FIFO (First-In, First-Out)** principle.
          This means the first element added to the queue will be the first one to be removed.
        * **Analogy**: Think of a line of people waiting for a ticket 🚶‍♂️🚶‍♀️🚶. The first person who gets in line is the
          first person to be served and leave the line. The queue has a **head** (the front of the line) and a
          **tail** (the back of the line).
        * **Core Operations**:
          * **Enqueue**: Adding an element to the tail (end) of the queue.
          * **Dequeue**: Removing an element from the head (front) of the queue.
          * **Peek**: Looking at the element at the head of the queue without removing it.
        * **The `java.util.Queue` Interface**:
          * In Java, `Queue` is an interface, not a concrete class. You need to use a class that implements it.
          * The most common implementation is `LinkedList`. `ArrayDeque` is also an excellent choice.
        * **Key Methods**:
          The `Queue` interface provides two sets of methods for its main operations. One set throws an exception
          if the operation fails, while the other returns a special value (like `null` or `false`). It's
          generally safer and preferred to use the methods that return a special value.

          | Operation | Throws Exception  | Returns Special Value |
          |-----------|-------------------|-----------------------|
          | **Insert** | `add(e)`          | `offer(e)` (Preferred)  |
          | **Remove** | `remove()`        | `poll()` (Preferred)    |
          | **Examine** | `element()`       | `peek()` (Preferred)    |
        */
        
        // --------------------------------------------------
        // EXAMPLE: A CUSTOMER SERVICE TICKET QUEUE
        // --------------------------------------------------

        // 1. **Creating a Queue**
        // We use LinkedList as the concrete implementation.
        Queue<String> customerLine = new LinkedList<>();

        // 2. **Enqueuing (Adding) elements** using offer()
        // `offer()` is preferred over `add()` as it doesn't throw an exception.
        System.out.println("Customers are getting in line...");
        customerLine.offer("Alice");    // Alice is first
        customerLine.offer("Bob");
        customerLine.offer("Charlie");  // Charlie is last
        System.out.println("   Current line: " + customerLine);

        // 3. **Peeking at the head element**
        // `peek()` lets us see who is at the front of the line without removing them.
        String nextInLine = customerLine.peek();
        System.out.println("\n   Next customer to be served (peek): " + nextInLine);
        System.out.println("   Line remains unchanged: " + customerLine);

        // 4. **Dequeuing (Removing) elements** using poll()
        // `poll()` is preferred over `remove()` as it returns null for an empty queue.
        // Notice the FIFO (First-In, First-Out) order.
        System.out.println("\n   Serving customers...");
        String servedCustomer1 = customerLine.poll(); // Removes Alice
        System.out.println("   Served: " + servedCustomer1);
        System.out.println("   Remaining line: " + customerLine);
        
        String servedCustomer2 = customerLine.poll(); // Removes Bob
        System.out.println("   Served: " + servedCustomer2);
        System.out.println("   Remaining line: " + customerLine);
        
        // 5. **Checking the size and if it's empty**
        System.out.println("\n   Number of customers waiting: " + customerLine.size());
        System.out.println("   Is the line empty? " + customerLine.isEmpty());
        
        // 6. **Handling an empty queue**
        // Clear the rest of the queue
        customerLine.poll(); // Removes Charlie
        System.out.println("\n   The last customer has been served. Line is now empty.");
        
        // Using poll() on an empty queue returns null.
        String noOneThere = customerLine.poll();
        System.out.println("   Attempting to poll() from empty queue: " + noOneThere);
        
        // Using remove() on an empty queue throws an exception.
        try {
            System.out.println("   Attempting to remove() from empty queue...");
            customerLine.remove();
        } catch (NoSuchElementException e) {
            System.out.println("   Caught an error as expected: " + e.getClass().getSimpleName());
        }
    }
}