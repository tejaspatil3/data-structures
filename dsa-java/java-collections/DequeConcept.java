import java.util.ArrayDeque;
import java.util.Deque;

public class DequeConcept {

    public static void main(String[] args) {
        
        /*
        ==================================================
        THEORY: WHAT IS A DEQUE?
        ==================================================
        * **Concept**: A `Deque` (pronounced "deck") stands for **Double-Ended Queue**. It is a linear data structure
          that, unlike a standard queue, allows for the insertion and removal of elements from **both ends**—the
          head (front) and the tail (end). ↔️
        * **Analogy**: Imagine a line of people where new people can join at either the front or the back, and
          people can also leave from either the front or the back. It combines the functionalities of a stack and a queue.
        * **An Interface, Not a Class**: In Java, `Deque` is an interface found in the `java.util` package. It extends
          the `Queue` interface. You must use a class that implements it to create a deque object.
        * **Common Implementations**:
          * **`ArrayDeque`**: This is a resizable-array implementation. It is highly efficient for adding/removing
            from both ends and has no capacity restrictions. **This is the recommended implementation for most cases.**
          * **`LinkedList`**: This is a doubly-linked list implementation, which also fully implements the `Deque` interface.
        * **Versatility**: Because of its dual-ended nature, a `Deque` can be used as:
          * A **Queue (FIFO - First-In, First-Out)**: By adding to the tail (`offerLast`) and removing from the head (`pollFirst`).
          * A **Stack (LIFO - Last-In, First-Out)**: By adding to the head (`push` / `offerFirst`) and removing from the head (`pop` / `pollFirst`).
        * **Key Methods**: `Deque` provides a rich set of methods. For each operation (insert, remove, examine), there is a
          method for both the head and the tail. Like `Queue`, it offers two versions of each: one that throws an
          exception on failure and one that returns a special value (`null` or `false`). Using the special-value
          methods is generally safer.

        ----Head (Front) Operations
        This table shows the methods for inserting, removing, and examining elements at the head (front) of the Deque.

    Operation	  Head Method (Throws Exception)	Head Method (Returns Special Value)
    Insert	    addFirst(e)	                    offerFirst(e) (Preferred)
    Remove	    removeFirst()	                  pollFirst() (Preferred)
    Examine	    getFirst()	                    peekFirst() (Preferred)

        ----Tail (End) Operations
        This table shows the methods for inserting, removing, and examining elements at the tail (end) of the Deque.

  Operation	  Tail Method (Throws Exception)	Tail Method (Returns Special Value)
  Insert	    addLast(e)	                    offerLast(e) (Preferred)
  Remove	    removeLast()	                  pollLast() (Preferred)
  Examine	    getLast()	                      peekLast() (Preferred)



    **Stack Methods**: For convenience, `Deque` also includes 
      `push(e)` (maps to `addFirst`), 
      `pop()` (maps to `removeFirst`),
      `peek()` (maps to `peekFirst`).
        */

        // --------------------------------------------------
        // EXAMPLE 1: General Deque Operations
        // --------------------------------------------------
        System.out.println("## 1. General Deque Operations (Adding/Removing from both ends)");
        Deque<String> taskDeque = new ArrayDeque<>();
        
        // Add tasks to both ends
        taskDeque.offerFirst("Task 2 (High Priority)");
        taskDeque.offerLast("Task 3 (Normal Priority)");
        taskDeque.offerFirst("Task 1 (Urgent)");
        taskDeque.offerLast("Task 4 (Low Priority)");
        System.out.println("   Initial Deque of tasks: " + taskDeque);
        
        // Examine elements at both ends
        System.out.println("   Peek First (head): " + taskDeque.peekFirst());
        System.out.println("   Peek Last (tail):  " + taskDeque.peekLast());
        
        // Remove tasks from both ends
        System.out.println("\n   Processing tasks...");
        String urgentTask = taskDeque.pollFirst(); // Process urgent task first
        System.out.println("   Processed from Head: " + urgentTask);
        String lowPriorityTask = taskDeque.pollLast(); // Maybe clean up low priority tasks
        System.out.println("   Processed from Tail: " + lowPriorityTask);
        System.out.println("   Remaining Deque: " + taskDeque);
        System.out.println("--------------------------------------------------\n");

        // --------------------------------------------------
        // EXAMPLE 2: Using Deque as a Queue (FIFO)
        // --------------------------------------------------
        System.out.println("## 2. Using Deque as a standard Queue (FIFO)");
        Deque<String> printerQueue = new ArrayDeque<>();
        
        // Enqueue: Add jobs to the end (tail) of the queue
        printerQueue.offerLast("Document A");
        printerQueue.offerLast("Image B");
        printerQueue.offerLast("Spreadsheet C");
        System.out.println("   Printer Queue: " + printerQueue);
        
        // Dequeue: Remove jobs from the front (head) of the queue
        System.out.println("   Printing: " + printerQueue.pollFirst()); // Prints Document A
        System.out.println("   Printing: " + printerQueue.pollFirst()); // Prints Image B
        System.out.println("   Remaining jobs in queue: " + printerQueue);
        System.out.println("--------------------------------------------------\n");
        
        // --------------------------------------------------
        // EXAMPLE 3: Using Deque as a Stack (LIFO)
        // --------------------------------------------------
        System.out.println("## 3. Using Deque as a Stack (LIFO)");
        Deque<String> browserHistory = new ArrayDeque<>();

        // Push: Add pages to the front (head) of the deque
        browserHistory.push("google.com");
        browserHistory.push("wikipedia.org");
        browserHistory.push("github.com"); // Top of the stack
        System.out.println("   Browser History (top is leftmost): " + browserHistory);
        
        // Peek: Check the current page
        System.out.println("   Current page (peek): " + browserHistory.peek());
        
        // Pop: Go "Back" by removing from the front (head)
        System.out.println("   Going back from: " + browserHistory.pop()); // Removes github.com
        System.out.println("   Current page is now: " + browserHistory.peek());
        System.out.println("--------------------------------------------------");
    }
}