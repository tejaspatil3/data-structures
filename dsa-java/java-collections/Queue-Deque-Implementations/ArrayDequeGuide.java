import java.util.ArrayDeque;
import java.util.Deque;

/*
 ====================================================================================================
 # THEORY AND CONCEPTS: JAVA ARRAYDEQUE (java.util.ArrayDeque)
 ====================================================================================================

 ## 1. What is an ArrayDeque?

 An **ArrayDeque** (Array Double Ended Queue) is a special kind of queue that supports efficient
 element insertion and removal at both the **head (front)** and the **tail (end)**. It is the
 resizable-array implementation of the `Deque` interface.

 Think of it as a line where people can join and leave from both the front and the back. This
 makes it extremely versatile, as it can be used as a standard **Queue (FIFO)** or a **Stack (LIFO)**.

 ## 2. Underlying Implementation: The Circular Array 🔄

 - The `ArrayDeque` is backed by a resizable array that it treats as **circular**.
 - It uses two pointers, `head` and `tail`, to keep track of the start and end of the elements.
 - When you add an element to the front (`addFirst`), the `head` pointer moves backward. When you
   add to the back (`addLast`), the `tail` pointer moves forward.
 - If a pointer reaches the end of the physical array, it "wraps around" to the other side.
   This circular nature is the key to its efficiency, as it avoids the need to shift all
   elements when adding or removing from the front (a major performance bottleneck for `ArrayList`).
 - If the array becomes full, it is automatically resized to a larger capacity.

 

 ## 3. Key Characteristics

 - **Efficiency**: Provides amortized **O(1)** constant time performance for insertions and
   deletions at both ends.
 - **No Capacity Restrictions**: It's a "resizable-array," so it grows automatically as needed.
 - **No `null` elements**: Attempting to add a `null` element will result in a `NullPointerException`.
 - **Not Thread-Safe**: `ArrayDeque` is not synchronized. For concurrent applications, consider
   using `java.util.concurrent.LinkedBlockingDeque`.
 - **Best of Both Worlds**: It is generally more efficient than `LinkedList` for both Stack (LIFO)
   and Queue (FIFO) operations. It is also the recommended implementation for a Stack over the
   legacy `java.util.Stack` class (which is a synchronized `Vector`).

 ## 4. Time Complexity Analysis ⏱️

 - **addFirst / addLast / offerFirst / offerLast**: Amortized O(1)
 - **removeFirst / removeLast / pollFirst / pollLast**: Amortized O(1)
 - **peekFirst / peekLast / getFirst / getLast**: O(1)
 - **contains(o) / remove(o)** (element in the middle): O(n)

 *Amortized O(1)* means that while most operations are O(1), a few operations (when the array
 resizes) take O(n) time. However, over a large number of operations, the average time per
 operation is constant.

 ---

 ====================================================================================================
 # BUILT-IN METHODS (FROM DEQUE, QUEUE, AND COLLECTION INTERFACES)
 ====================================================================================================

 Methods come in two forms: one that throws an exception on failure and one that returns a
 special value (`null` or `false`).

 | Operation           | Throws Exception | Returns Special Value | Acts on | Description                             |
 |---------------------|------------------|-----------------------|---------|-----------------------------------------|
 | **Insert at Head** | `addFirst(e)`    | `offerFirst(e)`       | Head    | Adds an element to the front.           |
 | **Insert at Tail** | `addLast(e)`     | `offerLast(e)`        | Tail    | Adds an element to the end.             |
 |                     | `add(e)`         | `offer(e)`            | Tail    | *Queue method, same as addLast.* |
 |                     |                  |                       |         |                                         |
 | **Remove from Head**| `removeFirst()`  | `pollFirst()`         | Head    | Removes and returns the first element.  |
 |                     | `remove()`       | `poll()`              | Head    | *Queue method, same as removeFirst.* |
 | **Remove from Tail**| `removeLast()`   | `pollLast()`          | Tail    | Removes and returns the last element.   |
 |                     |                  |                       |         |                                         |
 | **Examine Head** | `getFirst()`     | `peekFirst()`         | Head    | Retrieves, but does not remove, head.   |
 |                     | `element()`      | `peek()`              | Head    | *Queue method, same as getFirst.* |
 | **Examine Tail** | `getLast()`      | `peekLast()`          | Tail    | Retrieves, but does not remove, tail.   |
 |                     |                  |                       |         |                                         |
 | **Stack Methods** |                  |                       |         |                                         |
 | *Push (to head)* | `push(e)`        | (same as addFirst)    | Head    | Pushes an element onto the stack.       |
 | *Pop (from head)* | `pop()`          | (same as removeFirst) | Head    | Pops an element from the stack.         |
 | *Peek (head)* | (use `peek()`)   |                       | Head    | Peeks at the top element of the stack.  |

*/

public class ArrayDequeGuide {

    public static void main(String[] args) {

        System.out.println("--- EXAMPLE 1: Using ArrayDeque as a Queue (FIFO) ---");
        // FIFO: First-In, First-Out. Use offer() to add to the tail and poll() to remove from the head.
        Deque<String> ticketQueue = new ArrayDeque<>();
        ticketQueue.offer("Person 1"); // Adds to the tail
        ticketQueue.offer("Person 2");
        ticketQueue.offer("Person 3");

        System.out.println("Current Queue: " + ticketQueue);
        System.out.println("Next in line (peek()): " + ticketQueue.peek()); // Looks at the head: Person 1

        System.out.println("Serving: " + ticketQueue.poll()); // Removes from the head: Person 1
        System.out.println("Serving: " + ticketQueue.poll()); // Removes from the head: Person 2

        System.out.println("Remaining Queue: " + ticketQueue);

        System.out.println("\n--- EXAMPLE 2: Using ArrayDeque as a Stack (LIFO) ---");
        // LIFO: Last-In, First-Out. Use push() to add to the head and pop() to remove from the head.
        Deque<String> browserHistory = new ArrayDeque<>();
        browserHistory.push("google.com");   // Pushes to the head
        browserHistory.push("wikipedia.org");
        browserHistory.push("github.com");    // This is now the head

        System.out.println("Browser History Stack: " + browserHistory);
        System.out.println("Current Page (peek()): " + browserHistory.peek()); // Looks at the head: github.com

        System.out.println("Going back to: " + browserHistory.pop()); // Removes from the head: github.com
        System.out.println("Going back to: " + browserHistory.pop()); // Removes from the head: wikipedia.org

        System.out.println("Current Page: " + browserHistory.peek()); // google.com

        System.out.println("\n--- EXAMPLE 3: Full Deque Functionality (Adding/Removing from both ends) ---");
        Deque<Integer> numbers = new ArrayDeque<>();
        
        // Add elements to both ends
        numbers.addFirst(10); // Deque: [10]
        numbers.addLast(100);  // Deque: [10, 100]
        numbers.addFirst(5);   // Deque: [5, 10, 100]
        numbers.addLast(200);  // Deque: [5, 10, 100, 200]
        
        System.out.println("Initial Deque: " + numbers);
        
        // Peek at both ends
        System.out.println("First element (peekFirst): " + numbers.peekFirst()); // 5
        System.out.println("Last element (peekLast): " + numbers.peekLast());   // 200
        
        // Remove from both ends
        int removedFromFront = numbers.pollFirst();
        System.out.println("Removed from front: " + removedFromFront + ". Deque is now: " + numbers);

        int removedFromBack = numbers.pollLast();
        System.out.println("Removed from back: " + removedFromBack + ". Deque is now: " + numbers);
    }
}

/*
 ====================================================================================================
 # WHEN TO USE AN ARRAYDEQUE? (PRACTICAL APPLICATIONS)
 ====================================================================================================

 An `ArrayDeque` is the ideal choice when you need a highly efficient **Queue** or **Stack**. Its
 performance for adding and removing elements from the ends is superior to `LinkedList` (due to
 better cache locality) and the legacy `Stack` class. Beyond these basic uses, its true power
 shines in algorithms that require a "double-ended" queue structure. This makes it a go-to tool for
 problems involving processing sequences, maintaining dynamic windows of data, or exploring data
 structures like graphs.

 Common use cases include:

 - **Sliding Window Algorithms**: For problems like "find the maximum element in all contiguous
   subarrays of size k," an `ArrayDeque` is used to maintain a window of candidate indices. As the
   window slides, elements are efficiently added and removed from both ends of the deque to keep
   track of the current maximum in O(n) total time.

 - **Breadth-First Search (BFS)**: BFS is a graph traversal algorithm that explores neighbor nodes
   level by level. An `ArrayDeque` serves as the perfect queue to store the nodes to be visited
   next, implementing the FIFO logic of the algorithm.

 - **Parsing and Expression Evaluation**: It can be used as a stack to handle operators and
   operands when parsing mathematical expressions, such as in the shunting-yard algorithm.

 - **Undo/Redo Functionality**: In applications like text editors, a deque can store a sequence of
   commands. `addLast` adds a new action, `pollLast` performs an "undo," and an "undone" command can
   be placed in a separate "redo" deque.

 - **Job Schedulers**: A deque can manage a queue of tasks. A worker thread can `pollFirst` for a
   new task, while a special high-priority task can be added to the front using `addFirst` to be
   processed immediately.

*/