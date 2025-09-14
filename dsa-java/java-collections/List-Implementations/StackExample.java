import java.util.Stack;
import java.util.EmptyStackException;

/**
 * =======================================================================================
 * | Theory & Concepts: Understanding Stack in Java                                      |
 * =======================================================================================
 *
 * A Stack is a linear data structure that follows a particular order in which operations are performed.
 * The order is LIFO (Last-In, First-Out). Think of it like a stack of plates: you can only add a new
 * plate to the top, and you can only remove the topmost plate.
 *
 * --- Core Concepts ---
 * 1.  **LIFO Principle**: The last element added (pushed) to the stack is the first element to be
 * removed (popped).
 *
 * 2.  **Extends Vector**: In Java, the `Stack` class is a subclass of `Vector`. This means it inherits
 * all the properties of a Vector, including being backed by a dynamic array and being synchronized
 * (thread-safe). Because it's built on Vector, you can technically use index-based methods like `get(i)`,
 * but doing so violates the LIFO principle and is considered bad practice.
 *
 * 3.  **Legacy Class**: Like Vector, `Stack` is a legacy class from Java 1.0. For new, non-concurrent
 * applications, the recommended modern approach is to use an implementation of the `Deque` interface,
 * such as `ArrayDeque`, which provides the same LIFO functionality (with methods like `push`, `pop`, `peek`)
 * more efficiently and without the synchronization overhead.
 *
 * 4.  **Core Operations**: A stack is defined by three primary operations:
 * - **push**: Adds an item to the top of the stack.
 * - **pop**: Removes and returns the item from the top of the stack.
 * - **peek**: Returns the item at the top of the stack without removing it.
 *
 * 5.  **EmptyStackException**: If you try to `pop()` or `peek()` an empty stack, it will throw an
 * `EmptyStackException`.
 *
 * ----------------------------------------------------------------------------------------------------------------------
 * ## When to Use a Stack?
 *
 * A `Stack` is used in problems where you need to manage data in a "Last-In, First-Out" manner. It's incredibly
 * useful for any process that requires "backtracking" or reversing order. Common use cases include:
 * - **Implementing Undo/Redo functionality**: Each action is pushed onto a stack. "Undo" pops the last action.
 * - **Parsing Expressions**: Checking for balanced parentheses, brackets, and braces in code (e.g., `{[()]}`).
 * - **Recursion and Function Calls**: The "call stack" in programming languages manages active function calls. When a function is called, it's pushed onto the stack; when it returns, it's popped off.
 * - **Navigation History**: The "back" button in a web browser can be implemented with a stack, where each visited page is pushed onto it.
 * - **Graph and Tree Traversal**: A stack is the underlying data structure for implementing the Depth-First Search (DFS) algorithm.
 * ----------------------------------------------------------------------------------------------------------------------
 *
 * =======================================================================================
 * | Table of Core Stack Functions                                                       |
 * =======================================================================================
 * | Method Signature                  | Description                                                                 |
 * |-----------------------------------|-----------------------------------------------------------------------------|
 * | `E push(E item)`                  | Pushes an item onto the top of this stack. Returns the item that was pushed.|
 * | `E pop()`                         | Removes the object at the top of this stack and returns it. Throws Exception.|
 * | `E peek()`                        | Looks at the object at the top of this stack without removing it. Throws Exc.|
 * | `boolean empty()`                 | Tests if this stack is empty. Returns `true` if the stack has no items.     |
 * | `int search(Object o)`            | Returns the 1-based position of an object on the stack (top is 1). -1 if not found.|
 * |-----------------------------------|-----------------------------------------------------------------------------|
 * | Note: Stack inherits all methods from Vector, like `size()`, `add()`, `get()`, etc., but using them directly      |
 * |       can break the LIFO contract and is generally discouraged. Use the methods above for stack operations.      |
 * =======================================================================================
 */

 
public class StackExample {

    public static void main(String[] args) {

        // ==================================================
        // 1. Creating a Stack
        // ==================================================
        Stack<String> bookStack = new Stack<>();
        System.out.println("1. Created an empty Stack: " + bookStack);

        // ==================================================
        // 2. Pushing Elements (`push`)
        // ==================================================
        // push() adds elements to the "top" of the stack.
        bookStack.push("The Hobbit");
        bookStack.push("Foundation");
        bookStack.push("Dune");
        System.out.println("\n2. After pushing 3 books: " + bookStack);
        System.out.println("   (Top of stack is 'Dune', the last item pushed)");

        // ==================================================
        // 3. Peeking at the Top Element (`peek`)
        // ==================================================
        // peek() returns the top element without removing it.
        String topBook = bookStack.peek();
        System.out.println("\n3. Peeking at the top book: " + topBook);
        System.out.println("   Stack after peek: " + bookStack + " (unchanged)");

        // ==================================================
        // 4. Popping Elements (`pop`)
        // ==================================================
        // pop() removes and returns the top element.
        String removedBook = bookStack.pop();
        System.out.println("\n4. Popped the top book: " + removedBook);
        System.out.println("   Stack after one pop: " + bookStack);

        removedBook = bookStack.pop();
        System.out.println("   Popped again: " + removedBook);
        System.out.println("   Stack after second pop: " + bookStack);

        // ==================================================
        // 5. Checking if the Stack is Empty (`empty`)
        // ==================================================
        boolean isStackEmpty = bookStack.empty();
        System.out.println("\n5. Checking if stack is empty: " + isStackEmpty);

        bookStack.pop(); // Pop the last book ("The Hobbit")
        System.out.println("   Stack after final pop: " + bookStack);
        System.out.println("   Is the stack empty now? " + bookStack.empty());

        // ==================================================
        // 6. Handling EmptyStackException
        // ==================================================
        try {
            System.out.println("\n6. Trying to pop an empty stack...");
            bookStack.pop();
        } catch (EmptyStackException e) {
            System.out.println("   Caught an exception: " + e);
        }

        // ==================================================
        // 7. Searching for an Element (`search`)
        // ==================================================
        // The search() method returns the 1-based distance from the top.
        // If not found, it returns -1.
        bookStack.push("A");
        bookStack.push("B");
        bookStack.push("C"); // Top of stack
        System.out.println("\n7. Searching in stack: " + bookStack);
        System.out.println("   Search for 'C' (top element): " + bookStack.search("C")); // Position 1
        System.out.println("   Search for 'B': " + bookStack.search("B"));               // Position 2
        System.out.println("   Search for 'A': " + bookStack.search("A"));               // Position 3
        System.out.println("   Search for 'Z' (not in stack): " + bookStack.search("Z")); // -1
    }
}