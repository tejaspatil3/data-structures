import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
================================================================================
|| NOTES: COMMON GENERIC TREE PROBLEMS FOR TESTS & INTERVIEWS (FROM SCRATCH)
================================================================================

This file demonstrates solutions to common problems that test your understanding of
generic (N-ary) tree manipulations. The solutions use a custom, from-scratch `GenericTree`
implementation (provided below) to showcase the logic of working with hierarchical data.

These problems often involve recursion, as the structure of a tree (a node with a "forest"
of subtrees) lends itself naturally to recursive solutions.

Problems covered:
1.  **Find the Height of the Tree**:
    The height is the number of edges on the longest path from the root to a leaf node.
    This is a classic recursive problem where the height of a node is defined as 1 plus
    the maximum height of any of its children.

2.  **Count the Total Number of Nodes**:
    A fundamental traversal problem. The goal is to visit every node in the tree and
    keep a running count. This demonstrates a simple but complete traversal.

3.  **Find a Specific Node**:
    A common search problem. Given a value, find the node in the tree that contains
    that value. This is typically solved with a Depth-First Search (DFS).

4.  **Print All Leaf Nodes**:
    This problem requires traversing the tree and identifying which nodes are leaves
    (i.e., they have no children) and then printing their data.
*/

/**
 * Main public class to run the problem examples.
 */
public class GenericTreeProblems {

    public static void main(String[] args) {
        // --- Setup: Create a generic tree representing a file system ---
        TreeNode<String> root = new TreeNode<>("root");
        GenericTree<String> fileSystem = new GenericTree<>(root);

        TreeNode<String> folderUsers = new TreeNode<>("Users");
        TreeNode<String> folderPrograms = new TreeNode<>("Programs");
        TreeNode<String> fileConfig = new TreeNode<>("config.txt");
        root.addChild(folderUsers);
        root.addChild(folderPrograms);
        root.addChild(fileConfig);

        TreeNode<String> folderAlice = new TreeNode<>("Alice");
        folderUsers.addChild(folderAlice);
        folderAlice.addChild(new TreeNode<>("photo.jpg"));
        folderAlice.addChild(new TreeNode<>("document.docx"));

        folderPrograms.addChild(new TreeNode<>("game.exe"));
        folderPrograms.addChild(new TreeNode<>("browser.exe"));
        
        System.out.println("## Tree Structure (DFS):");
        fileSystem.traverseDFS();
        System.out.println("--------------------------------------------------\n");


        // --- Problem 1: Find the Height of the Tree ---
        System.out.println("## Problem 1: Find the Height of the Tree");
        System.out.println("   The height of the tree is: " + findTreeHeight(fileSystem));
        System.out.println("--------------------------------------------------\n");

        // --- Problem 2: Count the Total Number of Nodes ---
        System.out.println("## Problem 2: Count the Total Number of Nodes");
        System.out.println("   The total number of nodes is: " + countNodes(fileSystem));
        System.out.println("--------------------------------------------------\n");

        // --- Problem 3: Find a Specific Node ---
        System.out.println("## Problem 3: Find a Specific Node");
        String valueToFind = "Alice";
        TreeNode<String> foundNode = findNode(fileSystem, valueToFind);
        if (foundNode != null) {
            System.out.println("   Found node '" + valueToFind + "'. Its parent is '" + foundNode.parent.data + "'.");
        } else {
            System.out.println("   Node '" + valueToFind + "' not found.");
        }
        System.out.println("--------------------------------------------------\n");

        // --- Problem 4: Print All Leaf Nodes ---
        System.out.println("## Problem 4: Print All Leaf Nodes");
        System.out.print("   Leaf nodes (files in our analogy):");
        printLeafNodes(fileSystem);
        System.out.println("\n--------------------------------------------------\n");
    }

    /**
     * Solution for Problem 1: Finds the height of the tree.
     */
    public static <T> int findTreeHeight(GenericTree<T> tree) {
        if (tree == null || tree.getRoot() == null) {
            return -1; // Height of an empty tree
        }
        return findHeightRecursive(tree.getRoot());
    }

    // Recursive helper for finding height. Height of a leaf is 0.
    private static <T> int findHeightRecursive(TreeNode<T> node) {
        // Base case: a leaf node has a height of 0.
        if (node.children.isEmpty()) {
            return 0;
        }
        // Find the maximum height among all children.
        int maxChildHeight = -1;
        for (TreeNode<T> child : node.children) {
            int childHeight = findHeightRecursive(child);
            if (childHeight > maxChildHeight) {
                maxChildHeight = childHeight;
            }
        }
        // The height of the current node is 1 + the height of its tallest child.
        return 1 + maxChildHeight;
    }

    /**
     * Solution for Problem 2: Counts the total number of nodes in the tree.
     */
    public static <T> int countNodes(GenericTree<T> tree) {
        if (tree == null || tree.getRoot() == null) {
            return 0;
        }
        return countNodesRecursive(tree.getRoot());
    }

    // Recursive helper for counting nodes.
    private static <T> int countNodesRecursive(TreeNode<T> node) {
        // The total count is 1 (for the current node)...
        int count = 1;
        // ...plus the count of all nodes in its children's subtrees.
        for (TreeNode<T> child : node.children) {
            count += countNodesRecursive(child);
        }
        return count;
    }

    /**
     * Solution for Problem 3: Finds a node with a specific value.
     */
    public static <T> TreeNode<T> findNode(GenericTree<T> tree, T value) {
        if (tree == null || tree.getRoot() == null) {
            return null;
        }
        return findNodeRecursive(tree.getRoot(), value);
    }

    // Recursive helper for finding a node.
    private static <T> TreeNode<T> findNodeRecursive(TreeNode<T> currentNode, T value) {
        // Base case: We found the node.
        if (currentNode.data.equals(value)) {
            return currentNode;
        }
        // Recurse on children.
        for (TreeNode<T> child : currentNode.children) {
            TreeNode<T> foundInChild = findNodeRecursive(child, value);
            // If the node was found in a child's subtree, propagate the result up.
            if (foundInChild != null) {
                return foundInChild;
            }
        }
        // If we've checked all children and not found it, return null.
        return null;
    }

    /**
     * Solution for Problem 4: Prints all leaf nodes.
     */
    public static <T> void printLeafNodes(GenericTree<T> tree) {
        if (tree == null || tree.getRoot() == null) return;
        printLeafNodesRecursive(tree.getRoot());
    }

    // Recursive helper for printing leaf nodes.
    private static <T> void printLeafNodesRecursive(TreeNode<T> node) {
        // Base case: If a node has no children, it's a leaf.
        if (node.children.isEmpty()) {
            System.out.print(" " + node.data);
        }
        // Recurse on all children.
        for (TreeNode<T> child : node.children) {
            printLeafNodesRecursive(child);
        }
    }
}


//==============================================================================
//== FROM-SCRATCH IMPLEMENTATION OF A GENERIC TREE AND ITS NODE
//==============================================================================
class TreeNode<T> {
    T data;
    TreeNode<T> parent;
    List<TreeNode<T>> children;

    public TreeNode(T data) {
        this.data = data;
        this.parent = null;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode<T> child) {
        child.parent = this;
        this.children.add(child);
    }
}

class GenericTree<T> {
    private TreeNode<T> root;

    public GenericTree(TreeNode<T> root) {
        this.root = root;
    }

    public TreeNode<T> getRoot() {
        return this.root;
    }

    public void traverseDFS() {
        if (root == null) return;
        dfsRecursive(root, 0);
    }

    private void dfsRecursive(TreeNode<T> node, int depth) {
        for (int i = 0; i < depth; i++) System.out.print("  ");
        System.out.println("- " + node.data);
        for (TreeNode<T> child : node.children) {
            dfsRecursive(child, depth + 1);
        }
    }
}