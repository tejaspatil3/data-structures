import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.NoSuchElementException;

/*
================================================================================
|| NOTES: SOLVING TEST PROBLEMS WITH JAVA'S QUEUE
================================================================================

This file demonstrates how to solve common problems from technical tests using a **Queue**.
A `Queue` is a core part of the Java Collections Framework that models a **FIFO (First-In,
First-Out)** data structure.

Its FIFO property makes it the perfect tool for processing items in the order they were
received. In Java, the `Queue` is an interface, and the `LinkedList` class is one of the
most common concrete implementations.

Here on a Saturday evening, September 13, 2025, in Pune, the problems below are classic
examples that test a developer's understanding of the queue's fundamental applications.

Problems covered:
1.  **Level Order Traversal of a Binary Tree**: The most canonical use case for a queue.
    It perfectly demonstrates the layer-by-layer processing that queues enable, which forms
    the basis of Breadth-First Search (BFS).

2.  **Generate First 'N' Binary Numbers**: A practical problem that showcases how a queue
    can be used to generate a sequence of items in a specific, ordered way.

3.  **Implement a Stack using two Queues**: A classic interview question. It tests your
    understanding of both data structures and your ability to reverse the FIFO nature
    of a queue to achieve the LIFO behavior of a stack.
*/

// Helper class for the Level Order Traversal problem
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

/**
 * Main public class to run the Queue problem examples.
 */
public class QueueProblems {

    public static void main(String[] args) {

        // --- Problem 1: Level Order Traversal of a Binary Tree ---
        System.out.println("## Problem 1: Level Order Traversal of a Binary Tree");
        /*
         * Tree structure:
         * 3
         * / \
         * 9   20
         * /  \
         * 15   7
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<List<Integer>> levels = levelOrderTraversal(root);
        System.out.println("   Level order traversal result: " + levels);
        System.out.println("--------------------------------------------------\n");

        // --- Problem 2: Generate First 'N' Binary Numbers ---
        System.out.println("## Problem 2: Generate First 'N' Binary Numbers");
        int n = 10;
        System.out.println("   Generating the first " + n + " binary numbers:");
        List<String> binaryNumbers = generateBinaryNumbers(n);
        System.out.println("   Result: " + binaryNumbers);
        System.out.println("--------------------------------------------------\n");

        // --- Problem 3: Implement a Stack using two Queues ---
        System.out.println("## Problem 3: Implement a Stack using two Queues");
        StackUsingQueues<Integer> myStack = new StackUsingQueues<>();
        System.out.println("   Pushing 10, 20, 30...");
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);
        System.out.println("   Top element (peek): " + myStack.peek());
        System.out.println("   Popping element (LIFO): " + myStack.pop()); // Should be 30
        System.out.println("   Top element after pop: " + myStack.peek());
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Solution for Problem 1: Performs a level-order traversal on a binary tree.
     * Approach: Use a Queue to hold the nodes for the current level. In a loop,
     * process all nodes in the queue for one level, and add their children to the
     * queue for the next level. This is a Breadth-First Search (BFS).
     * Time Complexity: O(N) where N is the number of nodes.
     * Space Complexity: O(W) where W is the maximum width of the tree.
     */
    public static List<List<Integer>> levelOrderTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            result.add(currentLevel);
        }
        return result;
    }

    /**
     * Solution for Problem 2: Generates the first n binary numbers in sequence.
     * Approach: Use a queue to maintain the order. Start with "1". In a loop,
     * dequeue a number, add its two children (by appending "0" and "1") to the
     * back of the queue.
     * Time Complexity: O(n), Space Complexity: O(n).
     */
    public static List<String> generateBinaryNumbers(int n) {
        if (n <= 0) return new ArrayList<>();

        List<String> result = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer("1");

        for (int i = 0; i < n; i++) {
            String current = queue.poll();
            result.add(current);
            queue.offer(current + "0");
            queue.offer(current + "1");
        }
        return result;
    }
}


/**
 * Solution for Problem 3: A generic Stack implemented using two Queues.
 */
class StackUsingQueues<E> {
    private Queue<E> q1;
    private Queue<E> q2;

    public StackUsingQueues() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    /**
     * Push operation is made costly (O(n)) to achieve LIFO behavior.
     */
    public void push(E data) {
        // 1. Add the new element to the empty queue (q2).
        q2.offer(data);
        // 2. Move all elements from the main queue (q1) to q2.
        while (!q1.isEmpty()) {
            q2.offer(q1.poll());
        }
        // 3. Swap the names of q1 and q2. Now q1 has the new element at the front.
        Queue<E> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }
        return q1.poll();
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }
        return q1.peek();
    }

    public boolean isEmpty() {
        return q1.isEmpty();
    }
}