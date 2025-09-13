/**
 * A simple Node class, the building block for our LinkedList.
 * It's a best practice to make this a private static nested class because it's an
 * implementation detail of the LinkedList and doesn't need to be accessed from outside.
 */
class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

/**
 * A custom LinkedList implementation to demonstrate common operations.
 */
class LinkedList {
    // These should be private to protect the list's internal state (encapsulation).
    private Node head;
    private Node tail;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * Adds a new element to the front (head) of the list.
     * This is an O(1) operation.
     *
     * @param data The integer data for the new node.
     */
    public void pushFront(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            // If the list is empty, the new node is both head and tail.
            head = tail = newNode;
        } else {
            // Otherwise, link the new node to the current head and update the head.
            newNode.next = head;
            head = newNode;
        }
    }

    /**
     * Reverses the linked list in-place.
     * This is an O(n) operation.
     */
    public void reverse() {
        if (head == null || head.next == null) {
            return; // No need to reverse a list with 0 or 1 elements.
        }

        Node previous = null;
        Node current = head;
        Node nextNode = null;

        // The old head will become the new tail.
        tail = head;

        while (current != null) {
            nextNode = current.next;  // Store the next node.
            current.next = previous;  // Reverse the current node's pointer.
            previous = current;       // Move pointers one position ahead.
            current = nextNode;
        }
        // After the loop, `previous` will be the new head.
        head = previous;
    }

    /**
     * Finds the k-th last element using a two-pass algorithm.
     * This approach is simple to understand but less efficient.
     * Time Complexity: O(n), as it iterates through the list twice in the worst case.
     * Space Complexity: O(1).
     *
     * @param k The position from the end (1-based, e.g., k=1 is the last element).
     * @return The data of the k-th last node.
     */
    public int getKthLastElementTwoPass(int k) {
        if (head == null || k <= 0) {
            throw new IllegalArgumentException("Invalid input: List is empty or k is non-positive.");
        }

        // First pass: Find the total length of the list.
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }

        if (k > count) {
            throw new IllegalArgumentException("k is larger than the size of the list.");
        }

        // Second pass: Traverse to the (count - k)-th element from the beginning.
        current = head;
        for (int i = 0; i < count - k; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * Finds the k-th last element using an optimal two-pointer (or "runner") technique.
     * This is the preferred solution in interviews.
     * 
     * Time Complexity: O(n), as it only requires a single pass through the list.
     * Space Complexity: O(1).
     *
     * @param k The position from the end (1-based).
     * @return The data of the k-th last node.
     */
    public int getKthLastElementOptimal(int k) {
        if (head == null || k <= 0) {
            throw new IllegalArgumentException("Invalid input: List is empty or k is non-positive.");
        }

        Node fastPointer = head;
        Node slowPointer = head;

        // 1. Move the fast pointer k steps ahead.
        for (int i = 0; i < k; i++) {
            if (fastPointer == null) {
                // This means k is larger than the list size.
                throw new IllegalArgumentException("k is larger than the size of the list.");
            }
            fastPointer = fastPointer.next;
        }

        // 2. Now, move both pointers at the same speed.
        // When the fast pointer reaches the end, the slow pointer will be at the k-th last element.
        while (fastPointer != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next;
        }

        return slowPointer.data;
    }

    /**
     * Prints the entire list in a readable format.
     */
    public void print() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}


/**
 * The main class with a driver method to demonstrate the LinkedList operations.
 */
public class KthLastElementDemo {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list.pushFront(13);
        list.pushFront(12);
        list.pushFront(11);
        list.pushFront(10);
        list.pushFront(9);

        System.out.println("Original List:");
        list.print(); // Expected: 9 -> 10 -> 11 -> 12 -> 13 -> null

        System.out.println("\n--- Finding K-th Last Element ---");

        int k = 3;
        // Using the simple two-pass method
        int elementTwoPass = list.getKthLastElementTwoPass(k);
        System.out.println("Using two-pass method, the " + k + "rd last element is: " + elementTwoPass); // Expected: 11

        // Using the optimal one-pass method
        int elementOptimal = list.getKthLastElementOptimal(k);
        System.out.println("Using optimal method, the " + k + "rd last element is: " + elementOptimal); // Expected: 11

        System.out.println("\n--- Reversing the List ---");
        list.reverse();
        System.out.println("List after reversing:");
        list.print(); // Expected: 13 -> 12 -> 11 -> 10 -> 9 -> null
    }
}
