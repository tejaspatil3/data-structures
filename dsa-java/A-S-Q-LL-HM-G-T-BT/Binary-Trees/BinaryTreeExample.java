import java.util.LinkedList;
import java.util.Queue;

/*
================================================================================
|| THEORY: UNDERSTANDING BINARY TREES
================================================================================

1.  WHAT IS A BINARY TREE?
    - A Binary Tree is a hierarchical data structure where each **node** has at most **two children**.
    - These children are typically referred to as the `left` child and the `right` child.
    - The tree starts from a single top-most node called the **Root**.
    - It's a fundamental structure used as a basis for more complex trees like Binary Search Trees, AVL Trees, etc.

2.  BINARY TREE vs. BINARY SEARCH TREE (BST)
    This is a crucial distinction:
    - **Binary Tree**: There are **NO RULES** about where nodes are placed. A node with a smaller value can be on the right, and a larger value can be on the left. It is defined purely by its structure (max two children per node).
    - **Binary Search Tree (BST)**: This is a *specialized* type of binary tree that follows a strict ordering rule: for any node, all values in its left subtree are less than its value, and all values in its right subtree are greater.

3.  KEY TERMINOLOGY
    - **Node**: Contains data and references (pointers) to its left and right children.
    - **Root**: The top-most node of the tree.
    - **Edge**: The link from a parent to a child.
    - **Parent/Child**: A node is a parent to the nodes directly below it (its children).
    - **Leaf**: A node with no children.
    

4.  TREE TRAVERSAL ALGORITHMS
    Traversal means visiting every node in the tree exactly once. The order in which you visit them defines the traversal type.

    a) Depth-First Traversals (using recursion):
       - **Pre-Order (Node, Left, Right)**: Visit the current node first, then recursively visit its entire left subtree, then its entire right subtree. Useful for creating a copy of the tree.
       - **In-Order (Left, Node, Right)**: Recursively visit the left subtree, then visit the current node, then recursively visit the right subtree. In a BST, this produces a sorted list; in a regular binary tree, it does not.
       - **Post-Order (Left, Right, Node)**: Recursively visit the left subtree, then the right subtree, and finally visit the current node. Useful for deleting nodes from a tree.

    b) Breadth-First Traversal (Level by Level):
       - **Level-Order Traversal**: This traversal visits nodes level by level, from left to right within each level. It is not recursive and is implemented using a **Queue**.
*/

/**
 * Main public class to run the Binary Tree examples.
 */
public class BinaryTreeExample {

    public static void main(String[] args) {

        System.out.println("--- 1. Building a Binary Tree Manually ---");
        // For a general binary tree, we build it by manually linking nodes,
        // as there's no ordering rule for automatic insertion.
        BinaryTree<Integer> tree = new BinaryTree<>();

        /*
         * We will construct the following tree:
         *
         * 1
         * /   \
         * 2     3
         * / \   /
         * 4   5 6
         */

        tree.root = new Node<>(1);
        tree.root.left = new Node<>(2);
        tree.root.right = new Node<>(3);
        tree.root.left.left = new Node<>(4);
        tree.root.left.right = new Node<>(5);
        tree.root.right.left = new Node<>(6);

        System.out.println("Tree has been constructed.");

        System.out.println("\n--- 2. Pre-Order Traversal (Node, Left, Right) ---");
        System.out.print("   Expected: 1 2 4 5 3 6 \n   Actual:   ");
        tree.printPreOrder();
        System.out.println();

        System.out.println("\n--- 3. In-Order Traversal (Left, Node, Right) ---");
        System.out.print("   Expected: 4 2 5 1 6 3 \n   Actual:   ");
        tree.printInOrder();
        System.out.println();

        System.out.println("\n--- 4. Post-Order Traversal (Left, Right, Node) ---");
        System.out.print("   Expected: 4 5 2 6 3 1 \n   Actual:   ");
        tree.printPostOrder();
        System.out.println();

        System.out.println("\n--- 5. Level-Order Traversal (Breadth-First Search) ---");
        System.out.print("   Expected: 1 2 3 4 5 6 \n   Actual:   ");
        tree.printLevelOrder();
        System.out.println();
    }
}


/**
 * The building block of our Tree. A Node contains data and references
 * to its left and right children.
 * @param <T> The type of data this node will hold.
 */
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


/**
 * A custom from-scratch implementation of a generic Binary Tree.
 * @param <T> The type of data this tree will hold.
 */
class BinaryTree<T> {
    Node<T> root;

    public BinaryTree() {
        this.root = null;
    }

    // --- Traversal Methods ---

    public void printInOrder() {
        inOrderRecursive(root);
    }

    private void inOrderRecursive(Node<T> node) {
        if (node == null) return;
        inOrderRecursive(node.left);
        System.out.print(node.data + " ");
        inOrderRecursive(node.right);
    }

    public void printPreOrder() {
        preOrderRecursive(root);
    }

    private void preOrderRecursive(Node<T> node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        preOrderRecursive(node.left);
        preOrderRecursive(node.right);
    }

    public void printPostOrder() {
        postOrderRecursive(root);
    }

    private void postOrderRecursive(Node<T> node) {
        if (node == null) return;
        postOrderRecursive(node.left);
        postOrderRecursive(node.right);
        System.out.print(node.data + " ");
    }

    public void printLevelOrder() {
        if (root == null) return;

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<T> currentNode = queue.poll();
            System.out.print(currentNode.data + " ");

            // Add left child to the queue if it exists
            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }
            // Add right child to the queue if it exists
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
    }
}