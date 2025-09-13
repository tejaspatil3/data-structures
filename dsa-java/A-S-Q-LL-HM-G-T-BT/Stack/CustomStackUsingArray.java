/*
================================================================================
|| THEORY: UNDERSTANDING AND BUILDING A STACK IN JAVA
================================================================================

1.  WHAT IS A STACK?
    - A Stack is a fundamental linear data structure that follows the **LIFO (Last-In, First-Out)** principle.
    - This means the last element that is added ("pushed") onto the stack will be the very first element to be removed ("popped").

2.  THE CORE ANALOGY
    - The most common and effective analogy for a stack is a **stack of plates** or a pile of books.
    - You can only add a new plate to the **top** of the stack.
    - You can only remove a plate from the **top** of the stack. You can't pull a plate from the middle or bottom without the whole stack toppling over.
    - 

3.  CORE OPERATIONS
    A stack is defined by three primary operations:
    - **push(item)**: Adds a new item to the top of the stack.
    - **pop()**: Removes the item from the top of the stack and returns it.
    - **peek()**: Looks at the item at the top of the stack without removing it.

4.  IMPLEMENTING A STACK FROM SCRATCH WITH AN ARRAY
    While Java has a built-in `Stack` class and a modern `Deque` interface (`ArrayDeque`), building one yourself is the best way to understand its mechanics.

    How it works:
    - **The Array**: We use a fixed-size array to store the stack's elements.
    - **The `top` Pointer**: The most crucial part is an integer variable, often called `top` or `tos` (top of stack). This variable keeps track of the **index** of the last element added.
      - An empty stack is typically represented by `top = -1`.
    - **Push Operation**:
      1. Check if the stack is full (i.e., if `top` is at the last index of the array). This is called **Stack Overflow**.
      2. If not full, increment `top` by 1.
      3. Add the new element at the array index indicated by the new `top`.
    - **Pop Operation**:
      1. Check if the stack is empty (i.e., if `top` is -1). Trying to pop from an empty stack is called **Stack Underflow**.
      2. If not empty, get the element at the index `top`.
      3. Decrement `top` by 1.
      4. Return the element you retrieved.
    - **Peek Operation**:
      1. Check if the stack is empty.
      2. If not empty, return the element at the index `top` **without** changing the value of `top`.

5.  AUXILIARY OPERATIONS
    - **isEmpty()**: A helper method that checks if `top` is -1.
    - **isFull()**: A helper method that checks if `top` has reached the array's maximum capacity.
    - **size()**: Returns the number of elements currently in the stack (`top + 1`).
*/

// Main public class to run the examples.
public class CustomStackUsingArray {

    public static void main(String[] args) {

        System.out.println("--- Creating a new stack with a capacity of 5 ---");
        // We are using our custom ArrayStack class defined below.
        ArrayStack myStack = new ArrayStack(5);

        System.out.println("\n--- 1. Pushing Elements ---");
        System.out.println("Is stack empty? " + myStack.isEmpty());
        System.out.println("Pushing 10...");
        myStack.push(10);
        myStack.printStack();
        System.out.println("Pushing 20...");
        myStack.push(20);
        myStack.printStack();
        System.out.println("Pushing 30...");
        myStack.push(30);
        myStack.printStack();

        System.out.println("\n--- 2. Peeking and Checking Size ---");
        System.out.println("Is stack full? " + myStack.isFull());
        System.out.println("Current size of stack: " + myStack.size());
        System.out.println("Top element (peek): " + myStack.peek());
        System.out.println("Stack state after peek (unchanged):");
        myStack.printStack();

        System.out.println("\n--- 3. Popping Elements (LIFO) ---");
        System.out.println("Popped element: " + myStack.pop());
        myStack.printStack();
        System.out.println("Popped element: " + myStack.pop());
        myStack.printStack();

        System.out.println("\n--- 4. Handling Edge Case: Stack Overflow ---");
        System.out.println("Pushing 40, 50, 60 to fill the stack...");
        myStack.push(40);
        myStack.push(50);
        myStack.push(60);
        myStack.printStack();
        System.out.println("Is stack full? " + myStack.isFull());

        try {
            System.out.println("Attempting to push 70 onto a full stack...");
            myStack.push(70);
        } catch (RuntimeException e) {
            System.out.println("Caught an error as expected: " + e.getMessage());
        }

        System.out.println("\n--- 5. Handling Edge Case: Stack Underflow ---");
        System.out.println("Popping all remaining elements...");
        System.out.println("Popped: " + myStack.pop());
        System.out.println("Popped: " + myStack.pop());
        System.out.println("Popped: " + myStack.pop());
        myStack.printStack();
        System.out.println("Is stack empty? " + myStack.isEmpty());

        try {
            System.out.println("Attempting to pop from an empty stack...");
            myStack.pop();
        } catch (RuntimeException e) {
            System.out.println("Caught an error as expected: " + e.getMessage());
        }
    }
}


/**
 * A custom implementation of a Stack data structure using a primitive integer array.
 * This class is not public, so it can exist in the same file as the public main class.
 */
class ArrayStack {
    private int[] arr;
    private int top; // Index of the top-most element
    private int capacity; // Maximum size of the stack

    // Constructor to initialize the stack
    public ArrayStack(int size) {
        this.capacity = size;
        this.arr = new int[capacity];
        this.top = -1; // Initialize stack as empty
    }

    // Method to add an element to the top of the stack
    public void push(int item) {
        if (isFull()) {
            throw new RuntimeException("Stack Overflow: Cannot push element, stack is full.");
        }
        // Increment top, then add the item
        top++;
        arr[top] = item;
    }

    // Method to remove and return the top element of the stack
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack Underflow: Cannot pop element, stack is empty.");
        }
        // Get the top element, then decrement top
        int itemToPop = arr[top];
        top--;
        return itemToPop;
    }

    // Method to return the top element without removing it
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty.");
        }
        return arr[top];
    }

    // Helper method to check if the stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Helper method to check if the stack is full
    public boolean isFull() {
        return top == capacity - 1;
    }

    // Method to return the current number of elements in the stack
    public int size() {
        return top + 1;
    }

    // Utility method to print the current state of the stack
    public void printStack() {
        System.out.print("   Stack State: (bottom) -> ");
        for (int i = 0; i <= top; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("<- (top)");
    }
}