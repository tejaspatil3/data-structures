import java.util.NoSuchElementException;

/*
================================================================================
|| NOTES: COMMON STACK PROBLEMS FOR TESTS & INTERVIEWS
================================================================================

This file demonstrates solutions to common problems that test your understanding of the
Stack data structure. The solutions use a custom, from-scratch `ArrayStack`
implementation (provided below) to show how to manipulate the underlying structure.

Problems covered:
1.  **Reverse a String**:
    A quintessential stack problem. The LIFO (Last-In, First-Out) nature of a stack
    is perfect for reversing a sequence. By pushing each character of a string onto
    a stack and then popping them off, the characters naturally come out in reverse order.

2.  **Balanced Brackets/Parentheses**:
    A very common and important interview question. It showcases the stack's ability
    to manage nested structures and matching pairs. When an opening bracket is seen,
    it's pushed. When a closing bracket is seen, the stack is checked for its matching
    opening bracket.

3.  **Implement a Queue using two Stacks**:
    The counterpart to "implement a stack with two queues." This problem tests your
    ability to invert the LIFO behavior of stacks to achieve the FIFO (First-In, First-Out)
    behavior of a queue. One stack is used for incoming elements (`enqueue`), and the other
    is used for outgoing elements (`dequeue`), with a transfer step in between.
*/

/**
 * Main public class to run the problem examples.
 */
public class CustomStackProblems {

    public static void main(String[] args) {

        // --- Problem 1: Reverse a String ---
        System.out.println("## Problem 1: Reverse a String");
        String text = "Hello World";
        String reversedText = reverseString(text);
        System.out.println("   Original String: " + text);
        System.out.println("   Reversed String: " + reversedText);
        System.out.println("--------------------------------------------------\n");

        // --- Problem 2: Balanced Brackets Check ---
        System.out.println("## Problem 2: Balanced Brackets Check");
        String expr1 = "([{}])";
        String expr2 = "([{]})";
        String expr3 = "{[}";
        System.out.println("   Expression '" + expr1 + "' is balanced? " + areBracketsBalanced(expr1));
        System.out.println("   Expression '" + expr2 + "' is balanced? " + areBracketsBalanced(expr2));
        System.out.println("   Expression '" + expr3 + "' is balanced? " + areBracketsBalanced(expr3));
        System.out.println("--------------------------------------------------\n");

        // --- Problem 3: Implement a Queue using two Stacks ---
        System.out.println("## Problem 3: Implement a Queue using two Stacks");
        QueueUsingStacks myQueue = new QueueUsingStacks();
        System.out.println("   Enqueuing 1, 2, 3...");
        myQueue.enqueue(1);
        myQueue.enqueue(2);
        myQueue.enqueue(3);
        System.out.println("   Front element (peek): " + myQueue.peek());
        System.out.println("   Dequeuing element: " + myQueue.dequeue()); // Should be 1 (FIFO)
        System.out.println("   Front element after dequeue: " + myQueue.peek());
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Solution for Problem 1: Reverses a string using our custom ArrayStack.
     */
    public static String reverseString(String text) {
        if (text == null) return null;

        ArrayStack<Character> stack = new ArrayStack<>(text.length());
        // 1. Push all characters of the string onto the stack.
        for (char c : text.toCharArray()) {
            stack.push(c);
        }
        // 2. Pop all characters and append them to a StringBuilder.
        StringBuilder reversed = new StringBuilder(text.length());
        while (!stack.isEmpty()) {
            reversed.append(stack.pop());
        }
        return reversed.toString();
    }

    /**
     * Solution for Problem 2: Checks if an expression has balanced brackets.
     */
    public static boolean areBracketsBalanced(String expression) {
        ArrayStack<Character> stack = new ArrayStack<>(expression.length());

        for (char c : expression.toCharArray()) {
            // If it's an opening bracket, push it onto the stack.
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            // If it's a closing bracket...
            else if (c == ')' || c == '}' || c == ']') {
                // ...but the stack is empty, it's an invalid expression.
                if (stack.isEmpty()) {
                    return false;
                }
                // ...pop the top and check for a match.
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false; // Mismatched brackets.
                }
            }
        }
        // If the stack is empty at the end, all brackets were matched.
        return stack.isEmpty();
    }
}


//==============================================================================
//== FROM-SCRATCH IMPLEMENTATION OF STACK AND SUPPORTING CLASSES
//==============================================================================

/**
 * Solution for Problem 3: A Queue implemented using two of our custom Stacks.
 */
class QueueUsingStacks {
    private ArrayStack<Integer> stack1; // For enqueue
    private ArrayStack<Integer> stack2; // For dequeue

    public QueueUsingStacks() {
        stack1 = new ArrayStack<>(); // Use default capacity
        stack2 = new ArrayStack<>();
    }

    // Enqueue operation is simple: just push to stack1.
    public void enqueue(int data) {
        stack1.push(data);
    }

    // Dequeue is the more complex operation.
    public int dequeue() {
        // If stack2 is empty, move all elements from stack1 to stack2.
        // This reverses their order, putting the oldest element on top of stack2.
        if (stack2.isEmpty()) {
            if (stack1.isEmpty()) {
                throw new NoSuchElementException("Queue is empty.");
            }
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        // Now pop from stack2 to get the oldest element.
        return stack2.pop();
    }

    public int peek() {
        if (stack2.isEmpty()) {
            if (stack1.isEmpty()) {
                throw new NoSuchElementException("Queue is empty.");
            }
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}


/**
 * A custom, generic implementation of a Stack data structure using an array.
 */
class ArrayStack<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data;
    private int top;
    private int capacity;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.data = new Object[this.capacity];
        this.top = -1;
    }



    public void push(E item) {
        if (isFull()) {
            throw new RuntimeException("Stack Overflow.");
        }
        data[++top] = item;
    }

    @SuppressWarnings("unchecked")
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }
        E item = (E) data[top];
        data[top--] = null; // Help garbage collector
        return item;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }
        return (E) data[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public int size() {
        return top + 1;
    }
}