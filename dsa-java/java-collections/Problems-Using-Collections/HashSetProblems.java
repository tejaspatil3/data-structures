import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
================================================================================
|| NOTES: SOLVING TEST PROBLEMS WITH JAVA'S HASHSET
================================================================================

This file demonstrates how to solve common problems from technical tests using a **HashSet**.
A `HashSet` is a core part of the Java Collections Framework and is the ideal tool
when you need to handle collections of unique items or perform very fast existence checks.

Its two main strengths are:
1.  **Ensuring Uniqueness**: A `Set` cannot contain duplicate elements. This property can be
    used to easily find or remove duplicates from other collections.
2.  **Fast Lookups**: The `contains()` and `add()` methods have an **O(1) average time complexity**.
    This makes a `HashSet` incredibly efficient for problems where you need to quickly
    check if you've "seen" an element before.

Here in Pune, on a Saturday evening, September 13, 2025, using a `HashSet` for these types
of problems is a standard and expected technique in Java interviews.

Problems covered:
1.  **Contains Duplicate**: The most fundamental `HashSet` problem. Given an array,
    determine if it contains any duplicate elements.
2.  **Find the Intersection of Two Arrays**: A classic problem where a `HashSet` is used for
    its efficient `contains()` check to find common elements.
3.  **Find the Missing Number**: A `HashSet` provides a clear and readable solution for finding
    a missing number in a sequence.
4.  **Pair with a Given Sum Exists**: A variation of the "Two Sum" problem. A `HashSet` is
    perfect for tracking seen numbers and checking for the existence of the required complement.
*/

/**
 * Main public class to run the HashSet problem examples.
 */
public class HashSetProblems {

    public static void main(String[] args) {

        // --- Problem 1: Contains Duplicate ---
        System.out.println("## Problem 1: Contains Duplicate");
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {1, 2, 3, 4, 1}; // Contains a duplicate '1'
        System.out.println("   Array " + Arrays.toString(arr1) + " contains duplicate? " + containsDuplicate(arr1));
        System.out.println("   Array " + Arrays.toString(arr2) + " contains duplicate? " + containsDuplicate(arr2));
        System.out.println("--------------------------------------------------\n");

        // --- Problem 2: Find the Intersection of Two Arrays ---
        System.out.println("## Problem 2: Find the Intersection of Two Arrays");
        int[] setA = {1, 2, 3, 4, 5};
        int[] setB = {4, 5, 6, 7, 1};
        System.out.println("   Array 1: " + Arrays.toString(setA));
        System.out.println("   Array 2: " + Arrays.toString(setB));
        int[] intersection = findIntersection(setA, setB);
        System.out.println("   Intersection: " + Arrays.toString(intersection));
        System.out.println("--------------------------------------------------\n");
        
        // --- Problem 3: Find the Missing Number ---
        System.out.println("## Problem 3: Find the Missing Number");
        int[] numsWithMissing = {3, 0, 1}; // n=3, so range is [0, 3]. Missing number is 2.
        System.out.println("   Array: " + Arrays.toString(numsWithMissing));
        System.out.println("   The missing number is: " + findMissingNumber(numsWithMissing));
        System.out.println("--------------------------------------------------\n");

        // --- Problem 4: Pair with a Given Sum Exists ---
        System.out.println("## Problem 4: Pair with a Given Sum Exists");
        int[] sumArr = {10, 15, 3, 7};
        int target1 = 17;
        int target2 = 20;
        System.out.println("   Array: " + Arrays.toString(sumArr));
        System.out.println("   Pair with sum " + target1 + " exists? " + pairWithSumExists(sumArr, target1));
        System.out.println("   Pair with sum " + target2 + " exists? " + pairWithSumExists(sumArr, target2));
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Solution for Problem 1: Checks if an array contains any duplicate elements.
     * Approach: Iterate through the array and use a HashSet to keep track of the
     * numbers encountered. The `add` method of a Set returns false if the element
     * is already present.
     * Time Complexity: O(n), Space Complexity: O(n).
     */
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> seenNumbers = new HashSet<>();
        for (int num : nums) {
            // If the number is already in the set, add() will return false.
            if (!seenNumbers.add(num)) {
                return true; // Found a duplicate
            }
        }
        return false; // No duplicates found
    }

    /**
     * Solution for Problem 2: Finds the intersection of two arrays.
     * Approach: Add elements of the first array to a HashSet for O(1) lookup.
     * Then, iterate through the second array and check for existence in the set.
     * Time Complexity: O(n + m), Space Complexity: O(n).
     */
    public static int[] findIntersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }

        Set<Integer> intersectionSet = new HashSet<>();
        for (int num : nums2) {
            if (set1.contains(num)) {
                intersectionSet.add(num);
            }
        }

        // Convert the result set to an array.
        int[] result = new int[intersectionSet.size()];
        int i = 0;
        for (int num : intersectionSet) {
            result[i++] = num;
        }
        return result;
    }

    /**
     * Solution for Problem 3: Finds the missing number in an array containing n
     * distinct numbers from the range [0, n].
     * Approach: Add all numbers from the array to a HashSet. Then, iterate from 0 to n
     * and check which number is not in the set.
     * Time Complexity: O(n), Space Complexity: O(n).
     */
    public static int findMissingNumber(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            if (!numSet.contains(i)) {
                return i;
            }
        }
        return -1; // Should not happen given the problem constraints
    }

    /**
     * Solution for Problem 4: Checks if a pair of numbers in an array sums up to a target.
     * Approach: Iterate through the array. For each number, check if its complement
     * (target - number) has been seen before by looking it up in a HashSet.
     * Time Complexity: O(n), Space Complexity: O(n).
     */
    public static boolean pairWithSumExists(int[] nums, int target) {
        Set<Integer> seenNumbers = new HashSet<>();
        for (int num : nums) {
            int complement = target - num;
            if (seenNumbers.contains(complement)) {
                return true; // Found a pair
            }
            seenNumbers.add(num);
        }
        return false; // No such pair found
    }
}