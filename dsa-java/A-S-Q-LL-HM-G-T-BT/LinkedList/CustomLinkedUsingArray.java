/*
================================================================================
|| THEORY: UNDERSTANDING AND BUILDING A LINKED LIST FROM SCRATCH
================================================================================

1.  WHAT IS A LINKED LIST?
    - A LinkedList is a fundamental linear data structure. Unlike an array, its elements are not stored in contiguous
      memory locations. Instead, elements are linked together using pointers (references).

2.  THE BUILDING BLOCK: THE `Node`
    - A LinkedList is a chain of `Node` objects. Each `Node` is a simple container with two parts:
      1.  **data**: The actual value or element being stored.
      2.  **next**: A reference (or "pointer") to the next `Node` in the sequence.
    - The last node in the list has its `next` reference pointing to `null`, which signifies the end of the chain.
    - Visualizing a Node: [ data | next -> ]

3.  KEY POINTERS: `head` and `tail`
    To manage the list, we only need to keep track of a few key nodes:
    - **`head`**: A reference that always points to the **first** node in the list. If `head` is `null`, the list is empty.
    - **`tail`**: A reference that always points to the **last** node in the list. Keeping a `tail` pointer is an optimization that allows for very fast (O(1)) additions to the end of the list.

4.  THE CORE ANALOGY
    - A great analogy for a LinkedList is a **train** 🚂.
    - Each train car is a `Node`.
    - The engine is the `head` of the list.
    - The last car (caboose) is the `tail` of the list.
    - The coupling that connects one car to the next is the `next` reference.

5.  LINKED LIST vs. ARRAY/ARRAYLIST
    Understanding their differences is key to knowing when to use each one.

    | Feature            | Array / ArrayList                                  | LinkedList                                         |
    |--------------------|----------------------------------------------------|----------------------------------------------------|
    | **Memory** | Contiguous block of memory                         | Non-contiguous; nodes can be anywhere in memory    |
    | **Random Access** | **Fast (O(1))** - You can jump to any index instantly. | **Slow (O(n))** - Must traverse from the head.     |
    | **Insertion/Deletion** | **Slow (O(n))** - Elements must be shifted.      | **Fast (O(1))** - At ends. Fast in middle if you have a ref. |
    | **Size** | Fixed (Array) or dynamic with resizing (ArrayList) | Dynamic by nature                                  |

6.  HOW OPERATIONS WORK (SINGLY-LINKED LIST)
    - **Adding to End (addLast)**: Create a new node. Make the current `tail.next` point to it, then update `tail` to be the new node.
    - **Adding to Front (addFirst)**: Create a new node. Make its `next` point to the current `head`, then update `head` to be the new node.
    - **Removing from Front (removeFirst)**: Simply update `head` to point to `head.next`.
    - **Removing from End (removeLast)**: This is less efficient. You must traverse from the `head` all the way to the *second-to-last* node to update its `next` pointer to `null`.
    - **Traversal (Printing/Searching)**: Start with a temporary node pointing to the `head`. In a loop, process the node's data, then move the temporary node to its `next` reference, continuing until it becomes `null`.
*/

// Main public class to run the examples.

public class CustomLinkedUsingArray {

    public static void main(String[] args) {
        System.out.println("--- Creating a new LinkedList ---");
        MyLinkedList list = new MyLinkedList();
        list.printList();

        System.out.println("\n--- 1. Adding Elements (addLast) ---");
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.printList();
        System.out.println("Current size: " + list.size());

        System.out.println("\n--- 2. Adding to the Front (addFirst) ---");
        list.addFirst(5);
        list.printList();
        System.out.println("Current size: " + list.size());

        System.out.println("\n--- 3. Searching for Elements ---");
        System.out.println("Does the list contain 20? " + list.contains(20));
        System.out.println("Does the list contain 99? " + list.contains(99));

        System.out.println("\n--- 4. Removing from the Front (removeFirst) ---");
        list.removeFirst();
        list.printList();
        System.out.println("Current size: " + list.size());

        System.out.println("\n--- 5. Removing from the End (removeLast) ---");
        list.removeLast();
        list.printList();
        System.out.println("Current size: " + list.size());

        System.out.println("\n--- 6. Handling Edge Cases ---");
        System.out.println("Removing all remaining elements...");
        list.removeLast();
        list.removeFirst();
        list.printList();
        System.out.println("Is the list empty? " + list.isEmpty());

        try {
            System.out.println("Attempting to remove from an empty list...");
            list.removeFirst();
        } catch (IllegalStateException e) {
            System.out.println("Caught an error as expected: " + e.getMessage());
        }
    }
}


/**
 * The building block of our LinkedList. A Node contains data and a reference
 * to the next Node in the list.
 */
class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null; // Initially, the next node is null
    }
}


/**
 * A custom implementation of a Singly-Linked List.
 */
class MyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    // Constructor to initialize an empty list
    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Adds an element to the end of the list (append)
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            // If the list is empty, the new node is both the head and the tail
            head = newNode;
            tail = newNode;
        } else {
            // Otherwise, link the current tail to the new node, then update the tail
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // Adds an element to the beginning of the list (prepend)
    public void addFirst(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    // Removes the first element from the list
    public void removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot remove from an empty list.");
        }
        if (head == tail) {
            // If there's only one element
            head = tail = null;
        } else {
            head = head.next;
        }
        size--;
    }

    // Removes the last element from the list
    public void removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot remove from an empty list.");
        }
        if (head == tail) {
            head = tail = null;
        } else {
            // We must find the node BEFORE the tail
            Node current = head;
            while (current.next != tail) {
                current = current.next;
            }
            // The new tail is the node before the old tail
            tail = current;
            tail.next = null;
        }
        size--;
    }
    
    // Checks if the list contains a specific value
    public boolean contains(int data) {
        Node current = head;
        while(current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Returns the number of elements in the list
    public int size() {
        return this.size;
    }

    // Checks if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Utility method to print the contents of the list
    public void printList() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        System.out.print("HEAD -> ");
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null (TAIL at " + (tail != null ? tail.data : "null") + ")");
    }
}