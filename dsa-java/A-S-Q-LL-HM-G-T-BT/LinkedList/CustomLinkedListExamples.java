/**
 * Main class to run examples on our custom-built LinkedList.
 */
public class CustomLinkedListExamples {

    public static void main(String[] args) {
        // --- 1. Setup: Create and populate our custom LinkedList ---
        MyLinkedList list = new MyLinkedList();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);
        list.addLast(50);
        System.out.println("## Original List:");
        list.printList();
        System.out.println("--------------------------------------------------\n");

        // --- 2. Find the Last Element ---
        System.out.println("## Find the Last Element");
        System.out.println("   Last element is: " + list.getLast());
        System.out.println("--------------------------------------------------\n");

        // --- 3. Find an Element by Index ---
        System.out.println("## Find Element by Index");
        System.out.println("   Element at index 2 is: " + list.get(2));
        // System.out.println(list.get(99)); // This would throw an exception
        System.out.println("--------------------------------------------------\n");

        // --- 4. Find an Element by its Data ---
        System.out.println("## Find Index by Data Value");
        System.out.println("   Index of value 40 is: " + list.indexOf(40));
        System.out.println("   Index of value 99 is: " + list.indexOf(99));
        System.out.println("--------------------------------------------------\n");

        // --- 5. Find the Middle Element ---
        System.out.println("## Find the Middle Element");
        System.out.println("   List: ");
        list.printList();
        System.out.println("   The middle element is: " + list.findMiddle());
        list.addLast(60); // Add one more to test with an even-sized list
        System.out.println("   List with even size: ");
        list.printList();
        System.out.println("   The middle element is: " + list.findMiddle()); // Should be the first of the two middle elements
        System.out.println("--------------------------------------------------\n");

        // --- 6. Reverse the LinkedList ---
        System.out.println("## Reverse the LinkedList (in-place)");
        System.out.println("   List before reverse:");
        list.printList();
        list.reverse();
        System.out.println("   List after reverse:");
        list.printList();
        System.out.println("--------------------------------------------------\n");
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
        this.next = null;
    }
}


/**
 * A custom implementation of a Singly-Linked List from scratch.
 */
class MyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // --- Basic Operations ---

    public boolean isEmpty() {
        return size == 0;
    }

    public void addLast(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void printList() {
        if (isEmpty()) {
            System.out.println("   List is empty.");
            return;
        }
        System.out.print("   HEAD -> ");
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // --- Example Methods ---

    /**
     * Gets the last element in the list.
     * Time Complexity: O(1) because we keep a tail pointer.
     */
    public int getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty.");
        }
        return tail.data;
    }

    /**
     * Gets the element at a specific index.
     * Time Complexity: O(n) because we must traverse the list.
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * Finds the index of the first occurrence of a given data value.
     * Time Complexity: O(n).
     * @return The index of the data, or -1 if not found.
     */
    public int indexOf(int data) {
        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.data == data) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    /**
     * Finds the middle element of the list using the fast and slow pointer technique.
     * Time Complexity: O(n).
     */
    public int findMiddle() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty.");
        }
        Node slow = head;
        Node fast = head;
        // The fast pointer moves two steps at a time, while slow moves one.
        // When fast reaches the end, slow will be at the middle.
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.data;
    }

    /**
     * Reverses the linked list in-place.
     * Time Complexity: O(n).
     */
    public void reverse() {
        if (isEmpty() || size == 1) {
            return; // No need to reverse
        }

        Node previous = null;
        Node current = head;
        Node next = null;
        
        // The old head becomes the new tail, and the old tail becomes the new head.
        tail = head;

        while (current != null) {
            next = current.next; // Store the next node
            current.next = previous; // Reverse the current node's pointer
            previous = current; // Move pointers one position ahead
            current = next;
        }
        // After the loop, `previous` will be the new head.
        head = previous;
    }
}