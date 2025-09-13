import java.util.LinkedList;
import java.util.Queue;

/*
================================================================================
|| NOTES: COMMON BINARY TREE PROBLEMS FOR TESTS & INTERVIEWS (FROM SCRATCH)
================================================================================

This file demonstrates solutions to common problems that test your understanding of
binary tree structures and traversals. The solutions use a custom, from-scratch `BinaryTree`
implementation (provided below).

These problems almost always have elegant recursive solutions, as the definition of a
tree is itself recursive (a tree is a node with left and right subtrees).

Problems covered:
1.  **Find the Height of a Binary Tree**:
    The height of a tree is the number of edges on the longest path from the root to a
    leaf node. A tree with a single node has a height of 0. This is a classic recursive
    problem where the height of a node is 1 + the maximum height of its children.

2.  **Find the Maximum Value in a Tree**:
    This problem requires traversing the entire tree to find the largest value. It's
    a good example of combining a traversal with a calculation. This assumes the
    data in the tree is comparable (e.g., numbers).

3.  **Check if Two Trees are Identical**:
    A classic problem to test recursive thinking. Two trees are identical if they
    have the same structure, and the nodes at corresponding positions have the same
    values.

4.  **Invert a Binary Tree (Mirror Image)**:
    A famous interview problem. To invert a tree, you must swap the left and right
    children of every node in the tree. This is most easily accomplished with a
    recursive, post-order traversal pattern.
*/

/**
 * Main public class to run the problem examples.
 */
public class BinaryTreeProblems {

    public static void main(String[] args) {

        // --- Setup: Create a sample binary tree ---
        BinaryTree<Integer> tree = new BinaryTree<>();
        /*
         * We will construct the following tree:
         * 10
         * /  \
         * 5    15
         * / \     \
         * 2   7     20
         */
        tree.root = new Node<>(10);
        tree.root.left = new Node<>(5);
        tree.root.right = new Node<>(15);
        tree.root.left.left = new Node<>(2);
        tree.root.left.right = new Node<>(7);
        tree.root.right.right = new Node<>(20);
        
        System.out.println("## Operating on the following tree:");
        tree.printLevelOrder();
        System.out.println("\n--------------------------------------------------\n");


        // --- Problem 1: Find the Height of the Tree ---
        System.out.println("## Problem 1: Find the Height of the Tree");
        int height = findHeight(tree.root);
        System.out.println("   The height of the tree is: " + height);
        System.out.println("--------------------------------------------------\n");

        // --- Problem 2: Find the Maximum Value ---
        System.out.println("## Problem 2: Find the Maximum Value");
        int max = findMax(tree.root);
        System.out.println("   The maximum value in the tree is: " + max);
        System.out.println("--------------------------------------------------\n");

        // --- Problem 3: Check if Two Trees are Identical ---
        System.out.println("## Problem 3: Check if Two Trees are Identical");
        BinaryTree<Integer> identicalTree = new BinaryTree<>();
        identicalTree.root = new Node<>(10);
        identicalTree.root.left = new Node<>(5);
        identicalTree.root.right = new Node<>(15);
        identicalTree.root.left.left = new Node<>(2);
        identicalTree.root.left.right = new Node<>(7);
        identicalTree.root.right.right = new Node<>(20);

        BinaryTree<Integer> differentTree = new BinaryTree<>();
        differentTree.root = new Node<>(10);
        differentTree.root.left = new Node<>(5);
        differentTree.root.right = new Node<>(99); // Different value

        System.out.println("   Are the first two trees identical? " + areIdentical(tree.root, identicalTree.root));
        System.out.println("   Are the first and third trees identical? " + areIdentical(tree.root, differentTree.root));
        System.out.println("--------------------------------------------------\n");
        
        // --- Problem 4: Invert a Binary Tree ---
        System.out.println("## Problem 4: Invert a Binary Tree");
        System.out.println("   Tree before inversion (Level Order):");
        tree.printLevelOrder();
        invertTree(tree.root);
        System.out.println("\n   Tree after inversion (Level Order):");
        tree.printLevelOrder();
        System.out.println("\n--------------------------------------------------\n");
    }

    /**
     * Solution for Problem 1: Finds the height of a binary tree.
     * Height is the number of edges on the longest path from root to leaf.
     * The height of a tree with a single node is 0. Height of an empty tree is -1.
     */
    public static int findHeight(Node<Integer> node) {
        // Base case: An empty subtree has a height of -1.
        if (node == null) {
            return -1;
        }
        // Recursively find the height of the left and right subtrees.
        int leftHeight = findHeight(node.left);
        int rightHeight = findHeight(node.right);
        // The height of the current node is 1 + the max height of its children.
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Solution for Problem 2: Finds the maximum value in the tree.
     * Assumes the tree contains integers.
     */
    public static int findMax(Node<Integer> node) {
        // Base case: If the node is null, return the smallest possible integer value.
        if (node == null) {
            return Integer.MIN_VALUE;
        }
        int rootVal = node.data;
        int leftMax = findMax(node.left);
        int rightMax = findMax(node.right);
        
        // The max value is the largest of the current node's value, the max of the
        // left subtree, and the max of the right subtree.
        return Math.max(rootVal, Math.max(leftMax, rightMax));
    }
    
    /**
     * Solution for Problem 3: Checks if two binary trees are structurally and
     * value-wise identical.
     */
    public static <T> boolean areIdentical(Node<T> node1, Node<T> node2) {
        // If both nodes are null, they are identical.
        if (node1 == null && node2 == null) {
            return true;
        }
        // If one is null but the other isn't, they are not identical.
        if (node1 == null || node2 == null) {
            return false;
        }
        // Check if the current nodes' data are equal, and then recursively
        // check if their left and right subtrees are also identical.
        return node1.data.equals(node2.data) &&
               areIdentical(node1.left, node2.left) &&
               areIdentical(node1.right, node2.right);
    }
    
    /**
     * Solution for Problem 4: Inverts a binary tree in-place.
     */
    public static <T> void invertTree(Node<T> node) {
        // Base case: If the node is null, there's nothing to do.
        if (node == null) {
            return;
        }
        // Recursively invert the left and right subtrees first (post-order pattern).
        invertTree(node.left);
        invertTree(node.right);
        
        // Swap the left and right children of the current node.
        Node<T> temp = node.left;
        node.left = node.right;
        node.right = temp;
    }
}


//==============================================================================
//== FROM-SCRATCH IMPLEMENTATION OF A BINARY TREE AND ITS NODE
//==============================================================================
class Node<T> {
    T data;
    Node<T> left;
    Node<T> right;

    public Node(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class BinaryTree<T> {
    Node<T> root;

    public BinaryTree() {
        this.root = null;
    }

    // Utility function for printing (used for verifying results)
    public void printLevelOrder() {
        if (root == null) {
            System.out.println("   Tree is empty.");
            return;
        }
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        System.out.print("   ");
        while (!queue.isEmpty()) {
            Node<T> currentNode = queue.poll();
            System.out.print(currentNode.data + " ");
            if (currentNode.left != null) queue.add(currentNode.left);
            if (currentNode.right != null) queue.add(currentNode.right);
        }
    }
}