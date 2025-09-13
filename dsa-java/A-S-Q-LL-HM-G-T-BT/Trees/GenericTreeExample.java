import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
================================================================================
|| THEORY: UNDERSTANDING GENERIC (N-ARY) TREES
================================================================================

1.  WHAT IS A GENERIC TREE?
    - A Generic Tree, often called an N-ary Tree, is a hierarchical data structure where each
      node can have **zero or more** children.
    - This is the primary difference from a Binary Tree, which is restricted to a maximum of two children.
    - Generic trees are highly flexible and can represent many real-world hierarchical relationships.

2.  THE CORE ANALOGY
    - A **file system directory structure** is a perfect analogy. A folder (a `Node`) can contain
      any number of other folders or files (its `children`). 📁
    - Other examples include a company's organizational chart or the table of contents of a book.

3.  REPRESENTING A NODE
    - Since a node can have a variable number of children, we can't use simple `left` and `right`
      pointers like in a binary tree.
    - Instead, the `Node` in a generic tree contains:
      1.  **data**: The value or element being stored.
      2.  **A List of children**: A `List` (like an `ArrayList`) is used to hold references to all of its child nodes.

4.  TREE TRAVERSAL ALGORITHMS
    Traversal means visiting every node in the tree exactly once. The two main traversal
    methods are the same as for graphs.

    a) Depth-First Search (DFS):
    - **Concept**: Explores as far as possible down one branch before backtracking. It visits a
      node, then recursively visits the entire subtree of its first child, then the entire
      subtree of its second child, and so on.
    - **Analogy**: Navigating a file system by going as deep into one folder as possible before
      returning to explore the next folder at the same level.
    - **Implementation**: Most naturally implemented using **recursion**.

    b) Breadth-First Search (BFS) / Level-Order Traversal:
    - **Concept**: Explores the tree layer by layer. It visits the root, then all of the root's
      children, then all of their children (the "grandchildren"), and so on.
    - **Analogy**: An announcement from a CEO that is first told to all VPs, who then all tell
      their Directors, who then all tell their Managers.
    - **Implementation**: Implemented using a **Queue**.
*/

/**
 * Main public class to run the Generic Tree examples.
 */
public class GenericTreeExample {

    public static void main(String[] args) {
        System.out.println("--- Building a Company Organizational Chart (Generic Tree) ---");

        // The tree's root is the CEO.
        TreeNode<String> ceo = new TreeNode<>("CEO");
        GenericTree<String> companyTree = new GenericTree<>(ceo);

        // Create and add VPs under the CEO
        TreeNode<String> vpMarketing = new TreeNode<>("VP of Marketing");
        TreeNode<String> vpEngineering = new TreeNode<>("VP of Engineering");
        ceo.addChild(vpMarketing);
        ceo.addChild(vpEngineering);

        // Add employees under Marketing
        TreeNode<String> dirSales = new TreeNode<>("Director of Sales");
        TreeNode<String> mgrContent = new TreeNode<>("Content Manager");
        vpMarketing.addChild(dirSales);
        vpMarketing.addChild(mgrContent);
        dirSales.addChild(new TreeNode<>("Sales Rep 1"));
        dirSales.addChild(new TreeNode<>("Sales Rep 2"));
        mgrContent.addChild(new TreeNode<>("Writer"));
        
        // Add employees under Engineering
        TreeNode<String> leadDev = new TreeNode<>("Lead Developer");
        TreeNode<String> leadQa = new TreeNode<>("QA Lead");
        vpEngineering.addChild(leadDev);
        vpEngineering.addChild(leadQa);
        leadDev.addChild(new TreeNode<>("Software Engineer 1"));
        leadDev.addChild(new TreeNode<>("Software Engineer 2"));
        leadQa.addChild(new TreeNode<>("QA Engineer"));


        System.out.println("\n--- 1. Depth-First Search (DFS) Traversal ---");
        System.out.println("   (Shows the hierarchy, like an indented list)");
        companyTree.traverseDFS();


        System.out.println("\n\n--- 2. Breadth-First Search (BFS) / Level-Order Traversal ---");
        System.out.println("   (Shows the roles level by level)");
        companyTree.traverseBFS();
    }
}


/**
 * The building block of our Generic Tree. A TreeNode contains data, a list of children,
 * and a reference to its parent.
 * @param <T> The type of data this node will hold.
 */
class TreeNode<T> {
    T data;
    TreeNode<T> parent;
    List<TreeNode<T>> children;

    public TreeNode(T data) {
        this.data = data;
        this.parent = null;
        this.children = new ArrayList<>();
    }

    // Helper method to add a child and set its parent reference.
    public void addChild(TreeNode<T> child) {
        child.parent = this;
        this.children.add(child);
    }
}


/**
 * A custom from-scratch implementation of a Generic (N-ary) Tree.
 * @param <T> The type of data this tree will hold.
 */
class GenericTree<T> {
    private TreeNode<T> root;

    public GenericTree(TreeNode<T> root) {
        this.root = root;
    }

    /**
     * Public method to start the Depth-First Search (DFS) traversal.
     */
    public void traverseDFS() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        dfsRecursive(root, 0); // Start recursion from the root at depth 0
    }

    /**
     * Private recursive helper for DFS.
     * @param node The current node to visit.
     * @param depth The current depth for indentation.
     */
    private void dfsRecursive(TreeNode<T> node, int depth) {
        // Print the current node with indentation based on its depth
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("- " + node.data);

        // Recursively call DFS for each child
        for (TreeNode<T> child : node.children) {
            dfsRecursive(child, depth + 1);
        }
    }

    /**
     * Public method to perform a Breadth-First Search (BFS) or Level-Order traversal.
     */
    public void traverseBFS() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root); // Start with the root

        while (!queue.isEmpty()) {
            TreeNode<T> currentNode = queue.poll();
            System.out.print(" " + currentNode.data + " |");

            // Add all children of the current node to the queue
            for (TreeNode<T> child : currentNode.children) {
                queue.offer(child);
            }
        }
        System.out.println();
    }
}