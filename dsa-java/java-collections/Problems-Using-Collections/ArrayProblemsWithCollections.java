import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
================================================================================
|| NOTES: SOLVING ARRAY PROBLEMS WITH THE JAVA COLLECTIONS FRAMEWORK
================================================================================

This file demonstrates how to solve common array-based problems that are frequently
asked in technical tests and interviews.

While implementing data structures from scratch is excellent for learning, in a real-world
or test scenario, you are expected to use the optimized, built-in tools provided by the
language. The Java Collections Framework offers powerful classes like `HashSet`, `HashMap`,
and `ArrayList` that make solving these problems cleaner and more efficient.

The current time is Saturday, September 13, 2025, in Pune, India, and these techniques
are standard practice in modern Java development.

Problems covered:
1.  **Remove Duplicates from an Array**:
    A perfect use case for a `Set`. A `Set` is a collection that cannot contain
    duplicate elements. By adding all array elements to a `Set`, we automatically
    get a collection of unique items.

2.  **Find the Intersection of Two Arrays**:
    This problem asks for the elements that are common to two arrays. A `HashSet` is
    ideal here for its extremely fast `contains()` check, which has an average time
    complexity of O(1).

3.  **Two Sum Problem**:
    A classic interview question. The optimal O(n) solution uses a `HashMap` to store
    numbers as you iterate, allowing you to check if the required complement
    (`target - current_number`) has been seen before in constant time.

4.  **Group Anagrams**:
    A more advanced problem that beautifully showcases the power of a `HashMap`. We can
    group words by creating a "canonical key" for each anagram group (the sorted version
    of the word) and use this key to group the original words in a map.
*/

/**
 * Main public class to run the problem examples.
 */
public class ArrayProblemsWithCollections {

    public static void main(String[] args) {

        // --- Problem 1: Remove Duplicates from an Array ---
        System.out.println("## Problem 1: Remove Duplicates from an Array");
        int[] arrWithDuplicates = {1, 4, 2, 5, 4, 1, 6, 2, 7};
        System.out.println("   Original array: " + Arrays.toString(arrWithDuplicates));
        int[] uniqueArray = removeDuplicates(arrWithDuplicates);
        System.out.println("   Array with duplicates removed: " + Arrays.toString(uniqueArray));
        System.out.println("--------------------------------------------------\n");

        // --- Problem 2: Find the Intersection of Two Arrays ---
        System.out.println("## Problem 2: Find the Intersection of Two Arrays");
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {4, 5, 6, 7, 1};
        System.out.println("   Array 1: " + Arrays.toString(arr1));
        System.out.println("   Array 2: " + Arrays.toString(arr2));
        int[] intersection = findIntersection(arr1, arr2);
        System.out.println("   Intersection: " + Arrays.toString(intersection));
        System.out.println("--------------------------------------------------\n");

        // --- Problem 3: Two Sum Problem ---
        System.out.println("## Problem 3: Two Sum Problem");
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        System.out.println("   Array: " + Arrays.toString(nums) + ", Target: " + target);
        int[] result = findTwoSum(nums, target);
        if (result != null) {
            System.out.println("   Indices found: " + Arrays.toString(result));
        } else {
            System.out.println("   No solution found.");
        }
        System.out.println("--------------------------------------------------\n");
        
        // --- Problem 4: Group Anagrams ---
        System.out.println("## Problem 4: Group Anagrams");
        String[] anagrams = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("   Input strings: " + Arrays.toString(anagrams));
        List<List<String>> grouped = groupAnagrams(anagrams);
        System.out.println("   Grouped Anagrams: " + grouped);
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Solution for Problem 1: Removes duplicate elements from an array.
     * Approach: Use a LinkedHashSet to preserve the original order while ensuring uniqueness.
     * Time Complexity: O(n), Space Complexity: O(n).
     */
    public static int[] removeDuplicates(int[] nums) {
        // A Set automatically handles uniqueness. LinkedHashSet preserves insertion order.
        Set<Integer> uniqueSet = new LinkedHashSet<>();
        for (int num : nums) {
            uniqueSet.add(num);
        }

        // Convert the Set back to an array.
        int[] result = new int[uniqueSet.size()];
        int i = 0;
        for (int num : uniqueSet) {
            result[i++] = num;
        }
        return result;
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
     * Solution for Problem 3: Finds two numbers that sum to a target.
     * Approach: Use a HashMap to store numbers we've seen and their indices.
     * For each number, check if its complement (target - number) is already in the map.
     * Time Complexity: O(n), Space Complexity: O(n).
     */
    public static int[] findTwoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>(); // Stores (number -> index)
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (numMap.containsKey(complement)) {
                return new int[]{numMap.get(complement), i};
            }
            numMap.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    
    /**
     * Solution for Problem 4: Groups anagrams together.
     * Approach: Use a HashMap where the key is the sorted version of a string (a "canonical"
     * representation of an anagram group) and the value is a list of original strings.
     * Time Complexity: O(N * K log K) where N is the number of strings and K is the max length.
     * Space Complexity: O(N * K).
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String key = String.valueOf(charArray);
            
            // Get the list for this key, create it if it doesn't exist, then add the original string.
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }
}