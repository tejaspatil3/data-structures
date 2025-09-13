import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.NoSuchElementException;

/*
================================================================================
|| NOTES: SOLVING TEST PROBLEMS WITH JAVA'S STACK (via Deque)
================================================================================

This file demonstrates how to solve common problems from technical tests using a **Stack**.
A `Stack` is a data structure that follows the **LIFO (Last-In, First-Out)** principle.

In modern Java, the `Deque` interface (and its implementation `ArrayDeque`) is the
preferred way to create a stack. The legacy `java.util.Stack` class is considered
obsolete. A `Deque` provides `push()`, `pop()`, and `peek()` methods that behave
exactly as a stack should.

Stacks are the perfect tool for problems involving:
- **Reversing sequences**: The LIFO property naturally reverses order.
- **Processing nested structures**: Such as validating matching pairs of brackets or parsing HTML/XML.
- **Backtracking algorithms**: Where you need to undo a state and return to a previous one.

Here in Pune, on a Saturday evening, September 13, 2025, mastering these stack-based
patterns is a key skill for any software development interview.

Problems covered:
1.  **Balanced Brackets/Parentheses**: The quintessential stack problem, used to validate
    properly nested structures.
2.  **Next Greater Element**: A common problem that uses a "monotonic stack" to efficiently
    find the next larger element for each element in an array.
3.  **Implement a Queue using two Stacks**: A classic interview question that tests your
    ability to invert the LIFO behavior of stacks to achieve FIFO.
4.  **Reverse a String**: A simple but effective demonstration of the LIFO principle.
*/

/**
 * Main public class to run the Stack problem examples.
 */
public class StackProblems {

    public static void main(String[] args) {

        // --- Problem 1: Balanced Brackets ---
        System.out.println("## Problem 1: Balanced Brackets");
        String expr1 = "([{}])";
        String expr2 = "([{]})";
        System.out.println("   Expression '" + expr1 + "' is balanced? " + areBracketsBalanced(expr1));
        System.out.println("   Expression '" + expr2 + "' is balanced? " + areBracketsBalanced(expr2));
        System.out.println("--------------------------------------------------\n");

        // --- Problem 2: Next Greater Element ---
        System.out.println("## Problem 2: Next Greater Element");
        int[] nums = {4, 5, 2, 10, 8};
        System.out.println("   Input array: " + Arrays.toString(nums));
        int[] result = nextGreaterElement(nums);
        System.out.println("   Next greater elements: " + Arrays.toString(result));
        System.out.println("--------------------------------------------------\n");

        // --- Problem 3: Implement a Queue using two Stacks ---
        System.out.println("## Problem 3: Implement a Queue using two Stacks");
        QueueUsingStacks<Integer> myQueue = new QueueUsingStacks<>();
        System.out.println("   Enqueuing 1, 2, 3...");
        myQueue.enqueue(1);
        myQueue.enqueue(2);
        myQueue.enqueue(3);
        System.out.println("   Front element (peek): " + myQueue.peek());
        System.out.println("   Dequeuing element (FIFO): " + myQueue.dequeue()); // Should be 1
        System.out.println("   Front element after dequeue: " + myQueue.peek());
        System.out.println("--------------------------------------------------\n");
        
        // --- Problem 4: Reverse a String ---
        System.out.println("## Problem 4: Reverse a String");
        String text = "Hello World";
        String reversedText = reverseString(text);
        System.out.println("   Original String: " + text);
        System.out.println("   Reversed String: " + reversedText);
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Solution for Problem 1: Checks if an expression has balanced brackets.
     * Time Complexity: O(n), Space Complexity: O(n).
     */
    public static boolean areBracketsBalanced(String expression) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : expression.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((c == ')' && top != '(') || (c == '}' && top != '{') || (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * Solution for Problem 2: Finds the next greater element for each element in an array.
     * Approach: Use a "monotonic stack" which stores indices of elements in decreasing order.
     * Time Complexity: O(n) because each element is pushed and popped at most once.
     * Space Complexity: O(n) in the worst case (for a sorted-descending array).
     */
    public static int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1); // Default value if no greater element exists
        Deque<Integer> stack = new ArrayDeque<>(); // Stores indices

        for (int i = 0; i < n; i++) {
            // While stack is not empty and the current element is greater than the
            // element at the index on top of the stack...
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                // ...the current element is the "next greater element" for the index on the stack.
                result[stack.pop()] = nums[i];
            }
            // Push the current index onto the stack.
            stack.push(i);
        }
        return result;
    }

    /**
     * Solution for Problem 4: Reverses a string using a Deque as a stack.
     * Time Complexity: O(n), Space Complexity: O(n).
     */
    public static String reverseString(String text) {
        if (text == null) return null;
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : text.toCharArray()) {
            stack.push(c);
        }
        StringBuilder reversed = new StringBuilder();
        while (!stack.isEmpty()) {
            reversed.append(stack.pop());
        }
        return reversed.toString();
    }
}


/**
 * Solution for Problem 3: A generic Queue implemented using two Stacks (Deques).
 */
class QueueUsingStacks<E> {
    private Deque<E> stackEnqueue;
    private Deque<E> stackDequeue;

    public QueueUsingStacks() {
        stackEnqueue = new ArrayDeque<>();
        stackDequeue = new ArrayDeque<>();
    }

    /**
     * Enqueue operation is O(1).
     */
    public void enqueue(E data) {
        stackEnqueue.push(data);
    }

    /**
     * Dequeue operation is Amortized O(1).
     */
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        // If the dequeue stack is empty, transfer elements from the enqueue stack.
        if (stackDequeue.isEmpty()) {
            while (!stackEnqueue.isEmpty()) {
                stackDequeue.push(stackEnqueue.pop());
            }
        }
        return stackDequeue.pop();
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        if (stackDequeue.isEmpty()) {
            while (!stackEnqueue.isEmpty()) {
                stackDequeue.push(stackEnqueue.pop());
            }
        }
        return stackDequeue.peek();
    }

    public boolean isEmpty() {
        return stackEnqueue.isEmpty() && stackDequeue.isEmpty();
    }
}