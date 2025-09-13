import java.util.Stack;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;

public class StackConcept {

    public static void main(String[] args) {
        
        System.out.println("## 1. Legacy Stack Class Demonstration");
        demonstrateLegacyStack();
        System.out.println("--------------------------------------------------\n");
        
        System.out.println("## 2. Modern Approach using Deque (ArrayDeque)");
        demonstrateModernStack();
        System.out.println("--------------------------------------------------\n");

    }

    /*
    ==================================================
    THEORY: WHAT IS A STACK?
    ==================================================
    * **Concept**: A Stack is a linear data structure that follows the **LIFO (Last-In, First-Out)** principle.
      This means the last element added to the stack will be the first element to be removed.
    * **Analogy**: Think of a stack of plates 🥞 or a pile of books 📚. You can only add a new plate/book to the
      **top**, and you can only remove the plate/book that is currently at the **top**.
    * **Core Operations**:
      * **Push**: Adding an element to the top of the stack.
      * **Pop**: Removing the element from the top of the stack.
      * **Peek**: Looking at the element at the top without removing it.
    * **The `java.util.Stack` Class**:
      * Java provides a built-in `Stack` class. However, it is a legacy class that `extends Vector` (a synchronized
        version of ArrayList). This is generally not ideal due to performance overhead.
      * **Modern Approach**: The recommended way to implement a stack is to use an implementation of the `Deque`
        (Double Ended Queue) interface, such as `ArrayDeque` or `LinkedList`. `ArrayDeque` is often preferred
        as it is more efficient.
    * **Key Methods of the `Stack` class**:
      * `push(E item)`: Pushes an item onto the top of the stack.
      * `pop()`: Removes and returns the object at the top of the stack. Throws `EmptyStackException` if empty.
      * `peek()`: Returns the object at the top without removing it. Throws `EmptyStackException` if empty.
      * `empty()`: Returns `true` if the stack is empty, `false` otherwise.
    */
    public static void demonstrateLegacyStack() {
        // 1. **Creating a Stack**
        Stack<String> bookStack = new Stack<>();
        System.out.println("   Stack created. Is it empty? " + bookStack.empty());

        // 2. **Pushing elements**
        // Elements are added to the top.
        System.out.println("\n   Pushing books onto the stack...");
        bookStack.push("The Hobbit");
        bookStack.push("Dune");
        bookStack.push("Foundation"); // This is now at the top
        System.out.println("   Current stack: " + bookStack);

        // 3. **Peeking at the top element**
        // `peek()` lets us see the top element without removing it.
        String topBook = bookStack.peek();
        System.out.println("\n   Peeking at the top book: " + topBook);
        System.out.println("   Stack after peek: " + bookStack);

        // 4. **Popping elements**
        // `pop()` removes and returns the top element. Notice the LIFO order.
        System.out.println("\n   Popping books from the stack...");
        String removedBook1 = bookStack.pop(); // Removes "Foundation"
        System.out.println("   Popped: " + removedBook1);
        String removedBook2 = bookStack.pop(); // Removes "Dune"
        System.out.println("   Popped: " + removedBook2);
        System.out.println("   Stack after two pops: " + bookStack);

        // 5. **Checking if empty**
        System.out.println("\n   Is the stack empty now? " + bookStack.empty());
        bookStack.pop(); // Removes "The Hobbit"
        System.out.println("   Is the stack empty after the final pop? " + bookStack.empty());
        
        // 6. **Handling EmptyStackException**
        try {
            System.out.println("\n   Attempting to pop from an empty stack...");
            bookStack.pop();
        } catch (EmptyStackException e) {
            System.out.println("   Caught an error as expected: " + e.getClass().getSimpleName());
        }
    }
    
    /*
    ==================================================
    MODERN APPROACH: Using ArrayDeque as a Stack
    ==================================================
    * **Concept**: The `Deque` interface provides methods that allow it to behave
      like a stack (LIFO). `ArrayDeque` is a resizable-array implementation of `Deque`.
    * **Why it's better**: It's not based on the legacy `Vector` class, is not
      synchronized by default, and is generally faster for single-threaded use.
    * **Key Method Mapping**:
    
      * `Stack.push(e)` -> `Deque.push(e)` or `Deque.addFirst(e)`
      * `Stack.pop()`    -> `Deque.pop()` or `Deque.removeFirst()`
      * `Stack.peek()`   -> `Deque.peek()` or `Deque.getFirst()`
      * `Stack.empty()`  -> `Deque.isEmpty()`
    */
    public static void demonstrateModernStack() {
        // 1. **Creating a Stack using Deque**
        // Use the Deque interface as the type for good practice.
        Deque<String> browserHistory = new ArrayDeque<>();
        System.out.println("   Modern stack created. Is it empty? " + browserHistory.isEmpty());

        // 2. **Pushing elements**
        System.out.println("\n   Navigating to websites (pushing)...");
        browserHistory.push("google.com");
        browserHistory.push("wikipedia.org");
        browserHistory.push("github.com"); // This is now at the top
        System.out.println("   Current browser history: " + browserHistory);
        
        // 3. **Peeking at the top element**
        String currentPage = browserHistory.peek();
        System.out.println("\n   Current page (peeking): " + currentPage);
        
        // 4. **Popping elements (going "Back")**
        System.out.println("\n   Hitting the 'Back' button (popping)...");
        String previousPage = browserHistory.pop();
        System.out.println("   Went back from: " + previousPage);
        System.out.println("   Now on page: " + browserHistory.peek());
        
        // 5. **Checking if empty**
        browserHistory.pop();
        browserHistory.pop();
        System.out.println("\n   Is the history empty now? " + browserHistory.isEmpty());
    }
}