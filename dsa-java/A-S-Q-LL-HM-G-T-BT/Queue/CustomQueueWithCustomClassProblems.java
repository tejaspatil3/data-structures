import java.util.NoSuchElementException;
import java.util.Stack; // Used as a helper for the reverseQueue problem.

/*
================================================================================
|| NOTES: COMMON QUEUE PROBLEMS FOR TESTS & INTERVIEWS
================================================================================

This file demonstrates solutions to common problems that test your understanding of the
Queue data structure. The solutions use a custom, from-scratch `LinkedQueue`
implementation (provided below) to show how to manipulate the underlying structure.

Problems covered:
1.  **Implement a Stack using two Queues**:
    A classic interview question. It forces you to think about how to reverse the
    FIFO (First-In, First-Out) nature of a queue to achieve the LIFO (Last-In, First-Out)
    behavior of a stack. The key is to use the second queue as temporary storage to
    re-order elements during the `push` operation.

2.  **Reverse a Queue**:
    This problem tests your ability to use auxiliary data structures. The most
    intuitive solution is to use a Stack. By dequeuing every element and pushing it
    onto a stack, you store them in LIFO order. Then, by popping from the stack
    and enqueuing back into the queue, you effectively reverse the original order.

3.  **Generate First 'N' Binary Numbers**:
    A practical problem that showcases how queues are used for level-by-level processing,
    similar to a Breadth-First Search (BFS) in trees or graphs. A queue is perfect for
    keeping track of the "next numbers to process" in the correct sequence.
*/

/**
 * Main public class to run the problem examples.
 */
public class CustomQueueWithCustomClassProblems {

    public static void main(String[] args) {

        // --- Problem 1: Implement a Stack using two Queues ---
        System.out.println("## Problem 1: Implement a Stack using two Queues");
        StackUsingQueues myStack = new StackUsingQueues();
        System.out.println("   Pushing 10, 20, 30...");
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);
        System.out.println("   Top element (peek): " + myStack.peek());
        System.out.println("   Popping element: " + myStack.pop()); // Should be 30
        System.out.println("   Top element after pop: " + myStack.peek());
        System.out.println("--------------------------------------------------\n");

        // --- Problem 2: Reverse a Queue ---
        System.out.println("## Problem 2: Reverse a Queue");
        LinkedQueue<Integer> queueToReverse = new LinkedQueue<>();
        queueToReverse.enqueue(1);
        queueToReverse.enqueue(2);
        queueToReverse.enqueue(3);
        queueToReverse.enqueue(4);
        System.out.println("   Original Queue: ");
        queueToReverse.printQueue();
        reverseQueue(queueToReverse);
        System.out.println("   Reversed Queue: ");
        queueToReverse.printQueue();
        System.out.println("--------------------------------------------------\n");

        // --- Problem 3: Generate First 'N' Binary Numbers ---
        System.out.println("## Problem 3: Generate First 'N' Binary Numbers");
        int n = 10;
        System.out.println("   Generating the first " + n + " binary numbers:");
        generateBinaryNumbers(n);
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Solution for Problem 2: Reverses the given queue in-place.
     */
    public static <E> void reverseQueue(LinkedQueue<E> queue) {
        if (queue.isEmpty()) {
            return;
        }
        // Use a standard Stack as a helper data structure.
        Stack<E> helperStack = new Stack<>();
        // Dequeue all elements from the queue and push them onto the stack.
        while (!queue.isEmpty()) {
            helperStack.push(queue.dequeue());
        }
        // Pop all elements from the stack and enqueue them back into the queue.
        while (!helperStack.isEmpty()) {
            queue.enqueue(helperStack.pop());
        }
    }

    /**
     * Solution for Problem 3: Generates and prints the first n binary numbers.
     */
    public static void generateBinaryNumbers(int n) {
        if (n <= 0) return;

        // Use our custom queue to hold the binary number strings.
        LinkedQueue<String> queue = new LinkedQueue<>();
        // Start with the first binary number.
        queue.enqueue("1");

        for (int i = 0; i < n; i++) {
            // 1. Dequeue and print the current binary number.
            String current = queue.dequeue();
            System.out.println("   " + (i + 1) + ": " + current);

            // 2. Generate its two children and enqueue them.
            String child1 = current + "0";
            String child2 = current + "1";
            queue.enqueue(child1);
            queue.enqueue(child2);
        }
    }
}


//==============================================================================
//== FROM-SCRATCH IMPLEMENTATION OF QUEUE AND SUPPORTING CLASSES
//==============================================================================

/**
 * Solution for Problem 1: A Stack implemented using two of our custom Queues.
 */
class StackUsingQueues {
    private LinkedQueue<Integer> q1;
    private LinkedQueue<Integer> q2;

    public StackUsingQueues() {
        q1 = new LinkedQueue<>();
        q2 = new LinkedQueue<>();
    }

    // Push operation is made costly to achieve LIFO behavior.
    public void push(int data) {
        // 1. Add the new element to the empty queue (q2).
        q2.enqueue(data);
        // 2. Move all elements from the main queue (q1) to q2.
        while (!q1.isEmpty()) {
            q2.enqueue(q1.dequeue());
        }
        // 3. Swap the names of q1 and q2. Now q1 has the new element at the front.
        LinkedQueue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    public int pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }
        return q1.dequeue();
    }

    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }
        return q1.peek();
    }

    public boolean isEmpty() {
        return q1.isEmpty();
    }
}


/**
 * Generic Node class, the building block for our LinkedQueue.
 */
class Node<E> {
    E data;
    Node<E> next;

    public Node(E data) {
        this.data = data;
        this.next = null;
    }
}


/**
 * A custom, generic implementation of a Queue using a linked list.
 */
class LinkedQueue<E> {
    private Node<E> front;
    private Node<E> rear;

    public LinkedQueue() {
        this.front = null;
        this.rear = null;
    }

    public void enqueue(E data) {
        Node<E> newNode = new Node<>(data);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        E data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return data;
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void printQueue() {
        if (isEmpty()) {
            System.out.println("   Queue is empty.");
            return;
        }
        System.out.print("   (Front) -> ");
        Node<E> current = front;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null <- (Rear)");
    }
}