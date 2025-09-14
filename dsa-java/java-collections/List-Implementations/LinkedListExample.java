import java.util.LinkedList;
// import java.util.List;
import java.util.Queue;
import java.util.Deque;
// import java.util.Iterator;

/**
 * =======================================================================================
 * | Theory & Concepts: Understanding LinkedList in Java                                 |
 * =======================================================================================
 *
 * A LinkedList is a doubly-linked list implementation of the `List` and `Deque` (Double Ended Queue)
 * interfaces in Java. It is part of the Java Collections Framework.
 *
 * --- Core Concepts ---
 * 1.  **Node-Based Structure**: Unlike an ArrayList, which uses an array, a LinkedList is composed of a
 * sequence of nodes. Each node stores three pieces of information:
 * - The actual element (the data).
 * - A reference (pointer) to the next node in the sequence.
 * - A reference (pointer) to the previous node in the sequence.
 * This is why it's called a "doubly-linked" list.
 *
 * 2.  **Dynamic Size**: Like ArrayList, it can grow and shrink dynamically as elements are added or removed.
 * There is no concept of initial capacity or resizing an underlying array.
 *
 * 3.  **Ordered Collection**: A LinkedList maintains the insertion order of elements.
 *
 * 4.  **Allows Duplicates & Nulls**: You can store duplicate values and multiple `null` values.
 *
 * 5.  **Sequential Access**: Accessing an element by an index (`get(index)`) is slow. To get to the
 * element at index 'n', the list must traverse from the beginning (or end, whichever is closer)
 * node by node until it reaches the nth node.
 *
 * 6.  **Fast Insertions/Deletions**: Adding or removing elements from the beginning, end, or middle of the
 * list is very fast (O(1)), provided you have a reference to the node. This is because it only involves
 * updating the 'next' and 'previous' pointers of the adjacent nodes. There is no need to shift elements
 * like in an ArrayList.
 *
 * 7.  **Not Synchronized**: LinkedList is not thread-safe. For concurrent access, it must be synchronized
 * externally (e.g., using `Collections.synchronizedList`).
 *
 * --- Time Complexity ---
 * -   **Access (get(index))**: O(n) - Slow, requires traversal.
 * -   **Search (contains)**: O(n) - Slow, requires traversal.
 * -   **Add/Remove (at beginning/end)**: O(1) - Very fast (e.g., `addFirst`, `removeLast`).
 * -   **Add/Remove (in middle)**: O(1) if you have an iterator at the position, O(n) to find the position first.
 *
 * ----------------------------------------------------------------------------------------------------------------------
 * ## When to Use a LinkedList?
 *
 * A `LinkedList` is the ideal choice when your primary operations involve frequent **insertions and deletions**, especially
 * at the beginning and end of the collection. Because it only needs to update a few pointers to add or remove an element,
 * it significantly outperforms `ArrayList` in these scenarios. It's also perfectly suited for implementing stacks (LIFO - Last-In, First-Out)
 * and queues (FIFO - First-In, First-Out) because it provides efficient methods like `addFirst`, `removeFirst`, `addLast`,
 * and `removeLast`. You should avoid `LinkedList` when you need frequent random access to elements by a numerical index
 * (e.g., `get(i)`), as this operation is very slow and requires traversing the list from one of its ends.
 * ----------------------------------------------------------------------------------------------------------------------
 *
 * =======================================================================================
 * | Table of Common Built-in Functions                                                  |
 * =======================================================================================
 * | Method Signature                  | Description                                                                 |
 * |-----------------------------------|-----------------------------------------------------------------------------|
 * | **General List Methods** |                                                                             |
 * | `boolean add(E element)`          | Appends the element to the end of the list (same as `addLast`).             |
 * | `void add(int index, E element)`  | Inserts the element at the specified position.                              |
 * | `E get(int index)`                | Returns the element at the specified position.                              |
 * | `E set(int index, E element)`     | Replaces the element at the specified position.                             |
 * | `E remove(int index)`             | Removes the element at the specified position.                              |
 * | `int size()`                      | Returns the number of elements in this list.                                |
 * | `boolean contains(Object o)`      | Returns `true` if this list contains the specified element.                 |
 * | `void clear()`                    | Removes all of the elements from this list.                                 |
 * |-----------------------------------|-----------------------------------------------------------------------------|
 * | **Deque/Queue Specific Methods** |                                                                             |
 * | `void addFirst(E e)`              | Inserts the element at the beginning of this list.                          |
 * | `void addLast(E e)`               | Inserts the element at the end of this list.                                |
 * | `E getFirst()`                    | Returns the first element in this list.                                     |
 * | `E getLast()`                     | Returns the last element in this list.                                      |
 * | `E removeFirst()`                 | Removes and returns the first element from this list.                       |
 * | `E removeLast()`                  | Removes and returns the last element from this list.                        |
 * | `boolean offer(E e)`              | Adds the specified element as the tail (last element) of this list.         |
 * | `E poll()`                        | Retrieves and removes the head (first element) of this list, or null if empty.|
 * | `E peek()`                        | Retrieves, but does not remove, the head (first element), or null if empty. |
 * | `void push(E e)`                  | Pushes an element onto the stack represented by this list (inserts at front).|
 * | `E pop()`                         | Pops an element from the stack represented by this list (removes from front).|
 * =======================================================================================
 */
public class LinkedListExample {

    public static void main(String[] args) {

        // ==================================================
        // 1. Creating a LinkedList
        // ==================================================
        // It's common to declare with List, but to use specific methods like addFirst(),
        // we must declare it as a LinkedList or Deque.
        LinkedList<String> animals = new LinkedList<>();
        System.out.println("1. Created an empty LinkedList: " + animals);

        // ==================================================
        // 2. Adding Elements (add, addFirst, addLast)
        // ==================================================
        // add() appends to the end
        animals.add("Dog");
        System.out.println("\n2. After adding elements:");
        
        // addLast() is explicit about adding to the end
        animals.addLast("Cat");

        // addFirst() adds to the beginning
        animals.addFirst("Horse");
        System.out.println("   After addFirst, add, addLast: " + animals);

        // Adding at a specific index
        animals.add(2, "Lion"); // Inserts "Lion" at index 2
        System.out.println("   After adding 'Lion' at index 2: " + animals);

        // ==================================================
        // 3. Accessing Elements (get, getFirst, getLast)
        // ==================================================
        // Note: Accessing by index is inefficient in a LinkedList.
        String elementAtIndex2 = animals.get(2);
        System.out.println("\n3. Accessing elements:");
        System.out.println("   Element at index 2: " + elementAtIndex2);
        System.out.println("   First element (getFirst): " + animals.getFirst());
        System.out.println("   Last element (getLast): " + animals.getLast());

        // ==================================================
        // 4. Modifying Elements (set)
        // ==================================================
        animals.set(3, "Tiger"); // Replaces "Cat" with "Tiger" at index 3
        System.out.println("\n4. After modifying index 3 with 'Tiger': " + animals);

        // ==================================================
        // 5. Removing Elements (remove, removeFirst, removeLast)
        // ==================================================
        // Removing the first element
        animals.removeFirst();
        System.out.println("\n5. After removing the first element: " + animals);

        // Removing the last element
        animals.removeLast();
        System.out.println("   After removing the last element: " + animals);

        // Removing by object value
        animals.remove("Dog");
        System.out.println("   After removing the object 'Dog': " + animals);

        // ==================================================
        // 6. Iterating over a LinkedList
        // ==================================================
        animals.add("Zebra");
        animals.add("Elephant");
        System.out.println("\n6. Iterating over the list: " + animals);
        // Using an enhanced for-loop
        System.out.println("   Using enhanced for-loop:");
        for (String animal : animals) {
            System.out.println("   - " + animal);
        }

        // ==================================================
        // 7. Using LinkedList as a Queue (FIFO)
        // ==================================================
        // Queue: First-In, First-Out
        Queue<String> customerQueue = new LinkedList<>();
        customerQueue.offer("Customer 1"); // offer() adds to the end
        customerQueue.offer("Customer 2");
        customerQueue.offer("Customer 3");
        System.out.println("\n7. Using as a Queue: " + customerQueue);

        // peek() looks at the head of the queue without removing it
        System.out.println("   Next to be served (peek): " + customerQueue.peek());

        // poll() removes and returns the head of the queue
        String servedCustomer = customerQueue.poll();
        System.out.println("   Serving now (poll): " + servedCustomer);
        System.out.println("   Queue after serving one: " + customerQueue);

        // ==================================================
        // 8. Using LinkedList as a Stack (LIFO)
        // ==================================================
        // Stack: Last-In, First-Out
        Deque<String> browserHistory = new LinkedList<>();
        browserHistory.push("google.com"); // push() adds to the front
        browserHistory.push("youtube.com");
        browserHistory.push("wikipedia.org");
        System.out.println("\n8. Using as a Stack (Browser History): " + browserHistory);

        // pop() removes and returns the element from the front
        String lastVisited = browserHistory.pop();
        System.out.println("   Clicked 'Back' button (pop): " + lastVisited);
        System.out.println("   History after going back: " + browserHistory);
    }
}