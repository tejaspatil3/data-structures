import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Arrays;

/*
================================================================================
|| NOTES: SOLVING TEST PROBLEMS WITH DEQUE
================================================================================

This file demonstrates how to solve common problems from technical tests using a **Deque**
(Double-Ended Queue). In modern Java, the `ArrayDeque` class is the go-to implementation
for these tasks.

A Deque is particularly powerful for problems involving:
- **Window-based calculations**: Its ability to efficiently add to the tail and remove from
  the head makes it perfect for managing a "sliding window" of elements.
- **Palindrome checks**: Its double-ended nature provides an intuitive way to compare
  elements from both ends of a sequence.
- **Stack-based logic**: It is the recommended replacement for the legacy `Stack` class for
  any problem requiring LIFO (Last-In, First-Out) behavior, like validating nested structures.

Here on a Saturday evening, September 13, 2025, in Pune, these patterns are essential for any
Java developer preparing for technical interviews.

Problems covered:
1.  **Sliding Window Maximum**: A classic and challenging problem where the Deque's properties
    provide an optimal O(n) solution.
2.  **Balanced Brackets/Parentheses**: A fundamental problem that is perfectly solved using the
    Deque as a stack.
3.  **Palindrome Check**: A simple but elegant demonstration of the Deque's two-ended nature.
*/

/**
 * Main public class to run the Deque problem examples.
 */
public class DequeProblems {

    public static void main(String[] args) {

        // --- Problem 1: Sliding Window Maximum ---
        System.out.println("## Problem 1: Sliding Window Maximum");
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println("   Input array: " + Arrays.toString(nums) + ", Window size k=" + k);
        int[] maxes = findSlidingWindowMax(nums, k);
        System.out.println("   Maximums in each window: " + Arrays.toString(maxes));
        System.out.println("--------------------------------------------------\n");

        // --- Problem 2: Balanced Brackets ---
        System.out.println("## Problem 2: Balanced Brackets");
        String expr1 = "([{}])";
        String expr2 = "([{]})";
        System.out.println("   Expression '" + expr1 + "' is balanced? " + areBracketsBalanced(expr1));
        System.out.println("   Expression '" + expr2 + "' is balanced? " + areBracketsBalanced(expr2));
        System.out.println("--------------------------------------------------\n");

        // --- Problem 3: Palindrome Check ---
        System.out.println("## Problem 3: Palindrome Check");
        String p1 = "madam";
        String p2 = "hello";
        System.out.println("   Is '" + p1 + "' a palindrome? " + isPalindrome(p1));
        System.out.println("   Is '" + p2 + "' a palindrome? " + isPalindrome(p2));
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Solution for Problem 1: Finds the maximum element in every contiguous subarray of size k.
     * Approach: Use a Deque to store the *indices* of elements in the current window. The
     * Deque is maintained in such a way that it's always sorted in decreasing order of
     * the elements' values, with the index of the largest element always at the front.
     * Time Complexity: O(n) because each element is added and removed from the deque once.
     * Space Complexity: O(k) for the deque.
     */
    public static int[] findSlidingWindowMax(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>(); // Stores indices

        for (int i = 0; i < n; i++) {
            // 1. Remove indices from the front that are out of the current window.
            if (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            // 2. Maintain decreasing order: remove smaller elements from the back.
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }

            // 3. Add the current index to the back.
            dq.offerLast(i);

            // 4. The front of the deque is the max for the current window.
            // Start recording results once the first full window is formed.
            if (i >= k - 1) {
                result[i - k + 1] = nums[dq.peekFirst()];
            }
        }
        return result;
    }

    /**
     * Solution for Problem 2: Checks if an expression has balanced brackets using a Deque as a stack.
     * Time Complexity: O(n), Space Complexity: O(n).
     */
    public static boolean areBracketsBalanced(String expression) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : expression.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c); // Add opening brackets to the front
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    return false; // Closing bracket with no matching opening one
                }
                char top = stack.pop(); // Remove from the front
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false; // Mismatched brackets
                }
            }
        }
        // If the stack is empty at the end, all brackets were balanced.
        return stack.isEmpty();
    }

    /**
     * Solution for Problem 3: Checks if a string is a palindrome using a Deque.
     * Approach: Add all characters to the Deque, then repeatedly remove from both
     * ends and compare.
     * Time Complexity: O(n), Space Complexity: O(n).
     */
    public static boolean isPalindrome(String str) {
        if (str == null) return false;

        Deque<Character> deque = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            deque.addLast(c);
        }

        while (deque.size() > 1) {
            // Remove from both ends and compare
            if (deque.removeFirst() != deque.removeLast()) {
                return false;
            }
        }
        // If the loop completes, it's a palindrome.
        return true;
    }
}