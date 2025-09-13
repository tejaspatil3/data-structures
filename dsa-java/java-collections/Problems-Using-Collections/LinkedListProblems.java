import java.util.LinkedList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
================================================================================
|| NOTES: SOLVING TEST PROBLEMS WITH JAVA'S LINKEDLIST
================================================================================

This file demonstrates how to solve common problems from technical tests using Java's
built-in **`LinkedList`** class.

Java's `LinkedList` is a doubly-linked list, meaning it implements both the `List`
and `Deque` interfaces. This gives it unique characteristics:
- **Fast Add/Remove at Ends**: Adding or removing from the beginning (`addFirst`, `removeFirst`)
  or end (`addLast`, `removeLast`) is very efficient (O(1)).
- **Slow Index-Based Access**: Getting an element by index (`get(index)`) is slow (O(n))
  because the list must be traversed from the start or end.

The best solutions to `LinkedList` problems often use iterators or leverage its `Deque`
properties rather than relying on index-based loops.

Here in Pune, on a Saturday evening, September 13, 2025, these classic problems are
still staples in technical interviews for assessing a candidate's grasp of data
structures.

Problems covered:
1.  **Find the Middle of a LinkedList**: A classic problem optimally solved with the
    "fast and slow pointer" technique.
2.  **Reverse a LinkedList**: A fundamental manipulation task. The `descendingIterator()`
    method in Java's `LinkedList` provides a very clean solution.
3.  **Merge Two Sorted LinkedLists**: A very common interview problem that tests
    pointer-like logic and handling of multiple lists simultaneously.
4.  **Detect a Cycle in a LinkedList**: Another famous problem that has an elegant
    solution using the "fast and slow pointer" (Floyd's Tortoise and Hare) algorithm.
*/

// Helper class for the cycle detection problem
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

/**
 * Main public class to run the LinkedList problem examples.
 */
public class LinkedListProblems {

    public static void main(String[] args) {

        // --- Problem 1: Find the Middle of a LinkedList ---
        System.out.println("## Problem 1: Find the Middle of a LinkedList");
        LinkedList<String> list1 = new LinkedList<>(Arrays.asList("A", "B", "C", "D", "E"));
        System.out.println("   List with odd size: " + list1);
        System.out.println("   The middle element is: " + findMiddle(list1));
        list1.addLast("F");
        System.out.println("   List with even size: " + list1);
        System.out.println("   The middle element is: " + findMiddle(list1));
        System.out.println("--------------------------------------------------\n");

        // --- Problem 2: Reverse a LinkedList ---
        System.out.println("## Problem 2: Reverse a LinkedList");
        LinkedList<Integer> list2 = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("   Original list: " + list2);
        LinkedList<Integer> reversed = reverseList(list2);
        System.out.println("   Reversed list: " + reversed);
        System.out.println("--------------------------------------------------\n");
        
        // --- Problem 3: Merge Two Sorted LinkedLists ---
        System.out.println("## Problem 3: Merge Two Sorted LinkedLists");
        LinkedList<Integer> sorted1 = new LinkedList<>(Arrays.asList(1, 3, 5, 8));
        LinkedList<Integer> sorted2 = new LinkedList<>(Arrays.asList(2, 4, 6, 7, 9));
        System.out.println("   List 1: " + sorted1);
        System.out.println("   List 2: " + sorted2);
        LinkedList<Integer> merged = mergeTwoSortedLists(sorted1, sorted2);
        System.out.println("   Merged list: " + merged);
        System.out.println("--------------------------------------------------\n");

        // --- Problem 4: Detect a Cycle ---
        System.out.println("## Problem 4: Detect a Cycle");
        // Create a list with a cycle manually for this problem
        ListNode headWithCycle = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        headWithCycle.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = second; // Creates a cycle back to the 2nd node
        System.out.println("   Does the list (1->2->3->4->2...) have a cycle? " + hasCycle(headWithCycle));
        
        ListNode headWithoutCycle = new ListNode(1);
        headWithoutCycle.next = new ListNode(2);
        System.out.println("   Does the list (1->2) have a cycle? " + hasCycle(headWithoutCycle));
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Solution for Problem 1: Finds the middle element of the list.
     * Approach: For Java's LinkedList, the simplest approach is to get the element
     * at index `size / 2`. The classic "fast and slow pointer" is the optimal
     * algorithm for a raw linked list where you manipulate nodes directly.
     * Time Complexity: O(n) because get() on a LinkedList is O(n).
     */
    public static <E> E findMiddle(LinkedList<E> list) {
        if (list == null || list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.get(list.size() / 2);
    }

    /**
     * Solution for Problem 2: Reverses a LinkedList.
     * Approach: An efficient and idiomatic way for Java's LinkedList is to use its
     * built-in `descendingIterator()`, which iterates from tail to head.
     * Time Complexity: O(n), Space Complexity: O(n) for the new list.
     */
    public static <E> LinkedList<E> reverseList(LinkedList<E> list) {
        LinkedList<E> reversedList = new LinkedList<>();
        Iterator<E> it = list.descendingIterator();
        while (it.hasNext()) {
            reversedList.add(it.next());
        }
        return reversedList;
    }
    
    /**
     * Solution for Problem 3: Merges two sorted lists into a single sorted list.
     * Approach: Use two iterators to traverse both lists, comparing elements
     * and adding the smaller one to the new list.
     * Time Complexity: O(n + m), Space Complexity: O(n + m).
     */
    public static LinkedList<Integer> mergeTwoSortedLists(LinkedList<Integer> list1, LinkedList<Integer> list2) {
        LinkedList<Integer> mergedList = new LinkedList<>();
        Iterator<Integer> it1 = list1.iterator();
        Iterator<Integer> it2 = list2.iterator();
        
        Integer e1 = it1.hasNext() ? it1.next() : null;
        Integer e2 = it2.hasNext() ? it2.next() : null;
        
        while (e1 != null && e2 != null) {
            if (e1 <= e2) {
                mergedList.add(e1);
                e1 = it1.hasNext() ? it1.next() : null;
            } else {
                mergedList.add(e2);
                e2 = it2.hasNext() ? it2.next() : null;
            }
        }
        
        // Add any remaining elements from list1
        while (e1 != null) {
            mergedList.add(e1);
            e1 = it1.hasNext() ? it1.next() : null;
        }
        
        // Add any remaining elements from list2
        while (e2 != null) {
            mergedList.add(e2);
            e2 = it2.hasNext() ? it2.next() : null;
        }
        
        return mergedList;
    }

    /**
     * Solution for Problem 4: Detects if a linked list has a cycle.
     * Approach: Floyd's Tortoise and Hare algorithm. Use two pointers, one "slow"
     * that moves one step at a time, and one "fast" that moves two steps. If there
     * is a cycle, the fast pointer will eventually catch up to the slow pointer.
     * Time Complexity: O(n), Space Complexity: O(1).
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null) return false;
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                return true; // Cycle detected
            }
        }
        
        return false; // No cycle found
    }
}