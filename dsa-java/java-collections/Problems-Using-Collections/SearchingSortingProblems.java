import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
================================================================================
|| NOTES: SOLVING SEARCHING & SORTING PROBLEMS WITH JAVA COLLECTIONS
================================================================================

This file demonstrates how to solve common problems from technical tests that require
a combination of searching and sorting techniques.

In a real-world or test scenario, you should almost always use the highly optimized,
built-in methods from `java.util.Arrays` and `java.util.Collections` rather than
implementing sorting algorithms like quicksort from scratch. These built-in methods are
fast, reliable, and well-tested.

The current time is Saturday, September 13, 2025 at 8:31 PM in Pune, India. Leveraging
the standard library for these tasks is a key skill for any professional Java developer.

Key methods used in this file:
- `Arrays.sort()`: Sorts an array. Uses a highly efficient algorithm (Dual-Pivot Quicksort
  for primitives, Timsort for objects).
- `Collections.sort()`: Sorts a list.
- `Collections.binarySearch()`: Performs a fast (O(log n)) search on a **sorted** list.
- `PriorityQueue`: A heap data structure that is excellent for problems involving
  finding the "top K" or "Kth largest/smallest" elements efficiently.

Problems covered:
1.  **Check if Two Strings are Anagrams**: A classic problem where sorting provides a very
    clean and simple solution.
2.  **Find the Kth Largest Element**: This problem highlights the power of a `PriorityQueue`
    (min-heap) for a more efficient solution than simple sorting.
3.  **Merge Two Sorted Arrays**: Demonstrates a straightforward approach to merging by
    collecting all elements and then sorting the combined collection.
4.  **Binary Search on a List**: Shows the correct usage of `Collections.binarySearch()`
    and emphasizes the critical prerequisite of sorting the list first.
*/

/**
 * Main public class to run the Searching and Sorting problem examples.
 */
public class SearchingSortingProblems {

    public static void main(String[] args) {

        // --- Problem 1: Check if Two Strings are Anagrams ---
        System.out.println("## Problem 1: Check if Two Strings are Anagrams");
        String s1 = "listen";
        String s2 = "silent";
        String s3 = "hello";
        String s4 = "world";
        System.out.println("   Are '" + s1 + "' and '" + s2 + "' anagrams? " + areAnagrams(s1, s2));
        System.out.println("   Are '" + s3 + "' and '" + s4 + "' anagrams? " + areAnagrams(s3, s4));
        System.out.println("--------------------------------------------------\n");

        // --- Problem 2: Find the Kth Largest Element ---
        System.out.println("## Problem 2: Find the Kth Largest Element");
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2; // We want the 2nd largest element
        System.out.println("   Array: " + Arrays.toString(nums) + ", k = " + k);
        int kthLargest = findKthLargest(nums, k);
        System.out.println("   The " + k + "nd largest element is: " + kthLargest);
        System.out.println("--------------------------------------------------\n");

        // --- Problem 3: Merge Two Sorted Arrays ---
        System.out.println("## Problem 3: Merge Two Sorted Arrays");
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 6, 8};
        System.out.println("   Array 1: " + Arrays.toString(arr1));
        System.out.println("   Array 2: " + Arrays.toString(arr2));
        int[] merged = mergeSortedArrays(arr1, arr2);
        System.out.println("   Merged and sorted array: " + Arrays.toString(merged));
        System.out.println("--------------------------------------------------\n");

        // --- Problem 4: Binary Search on a List ---
        System.out.println("## Problem 4: Binary Search on a List");
        List<Integer> numbers = new ArrayList<>(Arrays.asList(9, 2, 15, 5, 8));
        System.out.println("   Original unsorted list: " + numbers);
        // CRITICAL STEP: The list MUST be sorted before binary search.
        Collections.sort(numbers);
        System.out.println("   List after sorting: " + numbers);
        int target = 8;
        int index = Collections.binarySearch(numbers, target);
        if (index >= 0) {
            System.out.println("   Target " + target + " found at index: " + index);
        } else {
            System.out.println("   Target " + target + " not found. Return value: " + index);
        }
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Solution for Problem 1: Checks if two strings are anagrams.
     * Approach: The simplest solution is to sort the character arrays of both strings.
     * If the sorted arrays are identical, the strings are anagrams.
     * Time Complexity: O(n log n) due to sorting.
     */
    public static boolean areAnagrams(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        
        return Arrays.equals(arr1, arr2);
    }

    /**
     * Solution for Problem 2: Finds the kth largest element in an array.
     * Approach: Use a PriorityQueue (min-heap) of size k. This is more efficient
     * than sorting the entire array if k is much smaller than n.
     * Time Complexity: O(n log k), which is better than O(n log n) from sorting.
     * Space Complexity: O(k) for the PriorityQueue.
     */
    public static int findKthLargest(int[] nums, int k) {
        // A PriorityQueue in Java is a min-heap by default.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        
        for (int num : nums) {
            minHeap.add(num);
            // If the heap size exceeds k, remove the smallest element.
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        // The root of the heap is now the kth largest element.
        return minHeap.peek();
    }

    /**
     * Solution for Problem 3: Merges two sorted arrays into a single sorted array.
     * Approach: A straightforward way is to add all elements from both arrays into
     * a single list and then use `Collections.sort()`.
     * Time Complexity: O((n+m) log (n+m)), Space Complexity: O(n+m).
     */
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        List<Integer> list = new ArrayList<>();
        for (int num : arr1) {
            list.add(num);
        }
        for (int num : arr2) {
            list.add(num);
        }
        
        Collections.sort(list);
        
        // Convert the sorted list back to an array.
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}