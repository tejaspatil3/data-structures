import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 ====================================================================================================
 # THEORY AND CONCEPTS: JAVA PRIORITY QUEUE (java.util.PriorityQueue)
 ====================================================================================================

 ## 1. What is a Priority Queue?

 A Priority Queue is an abstract data structure similar to a regular queue, but with a crucial
 difference: each element has a "priority" associated with it. When you dequeue an element, the
 element with the highest priority is removed first, regardless of when it was added.

 It's an "unbounded queue," meaning it can grow as needed and doesn't have a fixed size limit
 (though you can suggest an initial capacity for performance).

 ## 2. Underlying Implementation: The Heap 🌳

 - Java's `PriorityQueue` is typically implemented using a **binary heap**.
 - A heap is a specialized tree-based data structure that satisfies the "heap property."
 - By default, Java uses a **Min-Heap**. This means the element with the *lowest* value has the
   *highest* priority. The root of the tree is always the smallest element.
 - You can change this behavior to a **Max-Heap** (where the largest value has the highest priority)
   by providing a custom `Comparator`.

 

 ## 3. Key Characteristics

 - **Ordering**: Elements are ordered based on their "natural ordering" or by a `Comparator`
   provided at queue construction time.
 - **Duplicates**: It allows duplicate elements.
 - **Nulls**: It does not permit `null` elements.
 - **Not Thread-Safe**: If you need a thread-safe version, use `java.util.concurrent.PriorityBlockingQueue`.
 - **Iterator**: The `iterator()` method does not guarantee that elements will be traversed in
   priority order. To process elements in order, you must repeatedly call `poll()`.

 ## 4. Time Complexity Analysis ⏱️

 The use of a heap data structure gives `PriorityQueue` very efficient performance for its
 main operations. For a queue with 'n' elements:

 - **add(e) / offer(e)** (Insertion): O(log n)
   - Adding an element requires placing it at the end and then "sifting up" to maintain the heap property.
 - **poll() / remove()** (Deletion of the head): O(log n)
   - Removing the head involves replacing it with the last element and then "sifting down".
 - **peek() / element()** (Retrieving the head): O(1)
   - The highest priority element is always at the root of the heap, so it's a constant-time lookup.
 - **contains(o)** (Search): O(n)
   - The heap structure does not optimize for searching arbitrary elements.

 ## 5. Natural Ordering vs. Comparator

 For a `PriorityQueue` to order its elements, it needs a way to compare them.

 - **Natural Ordering**: If you store objects of a class (e.g., `Integer`, `String`, or a custom
   class like `Task`), that class must implement the `Comparable` interface. This defines the
   "natural" way to sort these objects.
 - **Comparator**: If you don't want to use the natural ordering, or the class doesn't have one,
   you can provide a `Comparator` object to the `PriorityQueue`'s constructor. The `Comparator`
   contains the custom logic for comparing two objects. This is also how you implement a Max-Heap.

 ---

 ====================================================================================================
 # BUILT-IN CONSTRUCTORS AND METHODS
 ====================================================================================================

 | Method / Constructor                     | Description                                                                       | Throws Exception? |
 |------------------------------------------|-----------------------------------------------------------------------------------|-------------------|
 | **----- Constructors -----** |                                                                                   |                   |
 | `PriorityQueue()`                        | Creates a PQ with default capacity (11) using natural ordering.                   |                   |
 | `PriorityQueue(Comparator)`              | Creates a PQ that orders elements using the specified comparator.                 |                   |
 | `PriorityQueue(int initialCapacity)`     | Creates a PQ with a specified initial capacity using natural ordering.            |                   |
 |                                          |                                                                                   |                   |
 | **----- Insertion Methods -----** |                                                                                   |                   |
 | `boolean add(E e)`                       | Inserts the element. Returns true.                                                | Yes (on failure)  |
 | `boolean offer(E e)`                     | Inserts the element. Returns true. Preferred for bounded queues.                  | No                |
 |                                          | *For PriorityQueue, add() and offer() are effectively the same.* |                   |
 |                                          |                                                                                   |                   |
 | **----- Retrieval Methods -----** | *These retrieve but DO NOT remove the head element.* |                   |
 | `E element()`                            | Retrieves the head of the queue.                                                  | Yes (if empty)    |
 | `E peek()`                               | Retrieves the head of the queue.                                                  | No (returns null) |
 |                                          | *peek() is generally safer as it doesn't throw an exception.* |                   |
 |                                          |                                                                                   |                   |
 | **----- Deletion Methods -----** | *These retrieve AND remove the head element.* |                   |
 | `E remove()`                             | Retrieves and removes the head of the queue.                                      | Yes (if empty)    |
 | `E poll()`                               | Retrieves and removes the head of the queue.                                      | No (returns null) |
 |                                          | *poll() is generally safer as it doesn't throw an exception.* |                   |
 |                                          |                                                                                   |                   |
 | **----- Utility Methods -----** |                                                                                   |                   |
 | `int size()`                             | Returns the number of elements in the queue.                                      |                   |
 | `void clear()`                           | Removes all elements from the queue.                                              |                   |
 | `boolean contains(Object o)`             | Returns true if the queue contains the specified element. (O(n) time)             |                   |
 | `boolean isEmpty()`                      | Returns true if the queue contains no elements.                                   |                   |

*/

public class PriorityQueueGuide {

    // A custom class to demonstrate PriorityQueue with objects.
    // It implements `Comparable` to define its "natural ordering".
    // Here, a lower priority number means a higher actual priority (e.g., priority 1 is most urgent).
    static class Task implements Comparable<Task> {
        String description;
        int priority;

        public Task(String description, int priority) {
            this.description = description;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "description='" + description + '\'' +
                    ", priority=" + priority +
                    '}';
        }

        // This method defines the natural ordering.
        // It will be used by the PriorityQueue to decide which task has higher priority.
        @Override
        public int compareTo(Task other) {
            // A positive return value means `this` object is "greater than" `other`.
            // A negative return value means `this` object is "less than" `other`.
            // For a min-heap, "less than" means higher priority.
            return Integer.compare(this.priority, other.priority);
            // Equivalent to: return this.priority - other.priority;
        }
    }


    public static void main(String[] args) {

        System.out.println("--- EXAMPLE 1: Min-Heap (Default Behavior with Integers) ---");
        // By default, PriorityQueue is a Min-Heap. The smallest element has the highest priority.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(30);
        minHeap.add(10);
        minHeap.add(50);
        minHeap.add(20);

        System.out.println("Top element (highest priority) using peek(): " + minHeap.peek()); // Should be 10

        System.out.println("Polling elements from the min-heap:");
        while (!minHeap.isEmpty()) {
            System.out.println("Polled: " + minHeap.poll()); // Will print 10, 20, 30, 50
        }

        System.out.println("\n--- EXAMPLE 2: Max-Heap (Using a Comparator) ---");
        // To create a Max-Heap, we provide a reverse order comparator.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.offer(30); // offer() works just like add()
        maxHeap.offer(10);
        maxHeap.offer(50);
        maxHeap.offer(20);

        System.out.println("Top element (highest priority) using peek(): " + maxHeap.peek()); // Should be 50

        System.out.println("Polling elements from the max-heap:");
        while (!maxHeap.isEmpty()) {
            System.out.println("Polled: " + maxHeap.poll()); // Will print 50, 30, 20, 10
        }

        System.out.println("\n--- EXAMPLE 3: Custom Objects with Natural Ordering (Comparable) ---");
        PriorityQueue<Task> taskQueue = new PriorityQueue<>();
        taskQueue.add(new Task("Fix critical bug", 1)); // Priority 1 (highest)
        taskQueue.add(new Task("Update documentation", 5)); // Priority 5 (lowest)
        taskQueue.add(new Task("Add new feature", 3));
        taskQueue.add(new Task("Refactor legacy code", 3));

        System.out.println("Processing tasks based on their natural priority:");
        while (!taskQueue.isEmpty()) {
            System.out.println("Processing -> " + taskQueue.poll());
        }
        // Output order will be based on priority number (1, 3, 3, 5).
        // The order for the two tasks with priority 3 is not guaranteed.

        System.out.println("\n--- EXAMPLE 4: Custom Objects with a Custom Comparator ---");
        // Let's ignore the `priority` field and order tasks by the length of their description.
        Comparator<Task> descriptionLengthComparator = Comparator.comparingInt(task -> task.description.length());
        PriorityQueue<Task> taskQueueByLength = new PriorityQueue<>(descriptionLengthComparator);

        taskQueueByLength.add(new Task("Fix bug", 1)); // Shortest description
        taskQueueByLength.add(new Task("Update documentation", 5)); // Longest
        taskQueueByLength.add(new Task("Add a new feature", 3));

        System.out.println("Processing tasks based on description length:");
        while (!taskQueueByLength.isEmpty()) {
            System.out.println("Processing -> " + taskQueueByLength.poll());
        }

        System.out.println("\n--- EXAMPLE 5: Other Utility Methods ---");
        PriorityQueue<String> nameQueue = new PriorityQueue<>();
        nameQueue.add("Charlie");
        nameQueue.add("Alice");
        nameQueue.add("Bob");

        System.out.println("Queue size: " + nameQueue.size()); // 3
        System.out.println("Does it contain 'Bob'? " + nameQueue.contains("Bob")); // true
        System.out.println("Does it contain 'David'? " + nameQueue.contains("David")); // false

        nameQueue.clear();
        System.out.println("Is the queue empty after clearing? " + nameQueue.isEmpty()); // true

    }
}

/*
 ====================================================================================================
 # WHEN TO USE A PRIORITY QUEUE? (PRACTICAL APPLICATIONS)
 ====================================================================================================

 A Priority Queue is the perfect data structure for any problem where you need to efficiently and
 repeatedly find the "best" or "highest-priority" item from a dynamic collection. Instead of
 sorting the entire collection every time an item is added, the Priority Queue's heap structure
 maintains the order with logarithmic time complexity. This makes it ideal for algorithms and
 systems that involve scheduling, optimization, and processing items in a specific, non-sequential order.

 Common use cases include:

 - **Graph Algorithms**: In **Dijkstra's shortest path algorithm**, a priority queue is used to
   efficiently select the unvisited vertex with the smallest distance from the source. Similarly,
   in **Prim's algorithm** for finding a Minimum Spanning Tree, it's used to select the
   minimum-weight edge to add to the tree.

 - **Task Scheduling**: Operating systems can use a priority queue to manage processes. The process
   with the highest priority (e.g., a system-critical task) is selected to run next from the queue
   of ready processes.

 - **Event-Driven Simulation**: In simulations, events are scheduled to occur at certain times. A
   priority queue can store these events, using the event time as the priority. The simulation
   engine simply needs to pull the next event from the queue to process events in chronological order.

 - **Data Compression**: The **Huffman coding algorithm**, which is used for lossless data
   compression, uses a priority queue to build its optimal prefix code tree by repeatedly selecting
   the two least frequent characters.

 - **Finding Kth Largest/Smallest Element**: You can find the Kth largest element in a large stream
   of data by maintaining a min-heap of size K. For each new element, if it's larger than the
   smallest item in the heap (the root), you remove the root and add the new element. After
   processing all data, the root of the heap is the Kth largest element.

*/