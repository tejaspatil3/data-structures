import java.util.NoSuchElementException;

/*
================================================================================
|| THEORY: UNDERSTANDING AND BUILDING A QUEUE FROM SCRATCH
================================================================================

1.  WHAT IS A QUEUE?
    - A Queue is a fundamental linear data structure that follows the **FIFO (First-In, First-Out)** principle.
    - This means the first element that is added ("enqueued") will be the first element to be removed ("dequeued").

2.  THE CORE ANALOGY
    - The perfect analogy for a queue is a **checkout line at a grocery store** 🚶‍♂️🚶‍♀️🚶.
    - The first person to get in line is the first person to be served.
    - New people join at the **end** of the line (the "rear" or "tail").
    - People are served from the **front** of the line (the "front" or "head").
    - 

3.  CORE OPERATIONS
    A queue is defined by three primary operations:
    - **enqueue(item)**: Adds a new item to the rear (end) of the queue.
    - **dequeue()**: Removes the item from the front (beginning) of the queue and returns it.
    - **peek()**: Looks at the item at the front of the queue without removing it.

4.  IMPLEMENTING A QUEUE WITH A LINKED LIST
    A linked list is a natural fit for implementing a queue because adding to the end and removing from the beginning are very efficient operations.

    How it works:
    - **The `Node`**: Just like a standard linked list, a queue is built from `Node` objects, where each node contains data and a reference to the next node.
    - **Key Pointers**: We use two pointers to manage the queue efficiently:
      - `front` (or `head`): This pointer always points to the first node in the list (the one to be dequeued next).
      - `rear` (or `tail`): This pointer always points to the last node in the list (where new nodes are enqueued).
    - **Enqueue Operation (Adding to Rear)**:
      1. Create a new node with the given data.
      2. If the queue is empty, make both `front` and `rear` point to this new node.
      3. If the queue is not empty, make the current `rear` node's `next` reference point to the new node.
      4. Finally, update the `rear` pointer to be this new node.
      This is an O(1) operation.
    - **Dequeue Operation (Removing from Front)**:
      1. Check if the queue is empty. Trying to dequeue from an empty queue is an error (an "underflow").
      2. If not empty, get the data from the `front` node.
      3. Move the `front` pointer to the next node in the list (`front = front.next`).
      4. **Crucial edge case**: If moving the `front` pointer makes it `null` (meaning the list is now empty), we must also set the `rear` pointer to `null`.
      5. Return the data you retrieved.
      This is an O(1) operation.

5.  AUXILIARY OPERATIONS
    - **isEmpty()**: A helper method that checks if the `front` pointer is `null`.
    - **size()**: Returns the number of elements currently in the queue.
*/

/**
 * Main public class to run the examples on our custom-built LinkedQueue.
 */
class CustomQueueWithCustomClass {

    public static void main(String[] args) {

        System.out.println("--- Creating a new Queue ---");
        // We are using our custom LinkedQueue class defined below.
        LinkedQueue customerQueue = new LinkedQueue();

        System.out.println("\n--- 1. Enqueuing Elements (FIFO) ---");
        System.out.println("Is queue empty? " + customerQueue.isEmpty());
        System.out.println("Enqueuing customer 'Alice'...");
        customerQueue.enqueue(101); // Representing Alice with an ID
        customerQueue.printQueue();
        System.out.println("Enqueuing customer 'Bob'...");
        customerQueue.enqueue(102); // Representing Bob
        customerQueue.printQueue();
        System.out.println("Enqueuing customer 'Charlie'...");
        customerQueue.enqueue(103); // Representing Charlie
        customerQueue.printQueue();

        System.out.println("\n--- 2. Peeking and Checking Size ---");
        System.out.println("Current size of queue: " + customerQueue.size());
        System.out.println("Front element (peek): Customer ID " + customerQueue.peek());
        System.out.println("Queue state after peek (unchanged):");
        customerQueue.printQueue();

        System.out.println("\n--- 3. Dequeuing Elements (FIFO) ---");
        System.out.println("Serving customer with ID: " + customerQueue.dequeue());
        customerQueue.printQueue();
        System.out.println("Serving customer with ID: " + customerQueue.dequeue());
        customerQueue.printQueue();

        System.out.println("\n--- 4. Handling Edge Cases ---");
        System.out.println("Serving the last customer...");
        System.out.println("Served: " + customerQueue.dequeue());
        customerQueue.printQueue();
        System.out.println("Is queue empty? " + customerQueue.isEmpty());

        try {
            System.out.println("Attempting to peek from an empty queue...");
            customerQueue.peek();
        } catch (NoSuchElementException e) {
            System.out.println("Caught an error as expected: " + e.getMessage());
        }

        try {
            System.out.println("Attempting to dequeue from an empty queue...");
            customerQueue.dequeue();
        } catch (NoSuchElementException e) {
            System.out.println("Caught an error as expected: " + e.getMessage());
        }
    }
}

/**
 * The building block of our queue. A Node contains data and a reference
 * to the next Node in the list.
 */
class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

/**
 * A custom implementation of a Queue data structure using a linked list.
 * This class is not public, so it can exist in the same file as the main class.
 */
class LinkedQueue {
    private Node front; // The head of the list, where we remove items
    private Node rear;  // The tail of the list, where we add items
    private int size;

    // Constructor to initialize an empty queue
    public LinkedQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    /**
     * Adds an item to the end of the queue.
     * @param data The data to add.
     */
    public void enqueue(int data) {
        Node newNode = new Node(data);
        // If the queue is empty, the new node is both the front and the rear.
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            // Otherwise, add the new node to the end and update the rear pointer.
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    /**
     * Removes and returns the item from the front of the queue.
     * @return The data from the front of the queue.
     */
    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty. Cannot dequeue.");
        }
        // Get the data from the front node.
        int data = front.data;
        // Move the front pointer to the next node.
        front = front.next;

        // If front becomes null, the queue is now empty, so we must also update rear.
        if (front == null) {
            rear = null;
        }
        size--;
        return data;
    }

    /**
     * Returns the item at the front of the queue without removing it.
     * @return The data from the front of the queue.
     */
    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty. Cannot peek.");
        }
        return front.data;
    }

    /**
     * Returns the number of items in the queue.
     */
    public int size() {
        return this.size;
    }

    /**
     * Checks if the queue is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Utility method to print the current state of the queue.
     */
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("   Queue is empty. (Front: null, Rear: null)");
            return;
        }
        System.out.print("   (Front) -> ");
        Node current = front;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null <- (Rear)");
    }
}