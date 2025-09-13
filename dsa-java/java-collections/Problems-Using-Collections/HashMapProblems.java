import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
================================================================================
|| NOTES: SOLVING TEST PROBLEMS WITH JAVA'S HASHMAP
================================================================================

This file demonstrates how to solve common problems from technical tests using a **HashMap**.
A `HashMap` is one of the most powerful and frequently used tools in the Java Collections
Framework.

Its ability to store key-value pairs and provide **O(1) average time complexity** for
insertions, deletions, and lookups makes it the ideal choice for a wide range of problems.
In a coding test, recognizing when to use a HashMap is a critical skill.

Here in Pune, on a Saturday evening, September 13, 2025, proficiency with these patterns
is a standard expectation for any Java developer role.

Problems covered:
1.  **Two Sum Problem**: A classic interview question where a HashMap provides the optimal
    O(n) solution by storing seen numbers and their indices.
2.  **Group Anagrams**: A perfect example of grouping items. A HashMap is used to map a
    "canonical key" (the sorted word) to a list of its anagrams.
3.  **Find the First Non-Repeating Character**: Demonstrates the fundamental "frequency
    counting" pattern, a primary use case for HashMaps.
4.  **Reconstruct Itinerary**: A path-finding problem where a HashMap is used to represent
    the connections (tickets) for easy traversal from source to destination.
*/

/**
 * Main public class to run the HashMap problem examples.
 */
public class HashMapProblems {

    public static void main(String[] args) {

        // --- Problem 1: Two Sum Problem ---
        System.out.println("## Problem 1: Two Sum Problem");
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

        // --- Problem 2: Group Anagrams ---
        System.out.println("## Problem 2: Group Anagrams");
        String[] anagrams = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("   Input strings: " + Arrays.toString(anagrams));
        List<List<String>> grouped = groupAnagrams(anagrams);
        System.out.println("   Grouped Anagrams: " + grouped);
        System.out.println("--------------------------------------------------\n");

        // --- Problem 3: Find the First Non-Repeating Character ---
        System.out.println("## Problem 3: Find the First Non-Repeating Character");
        String text = "leetcode";
        char first = findFirstNonRepeatingChar(text);
        System.out.println("   In '" + text + "', first non-repeating char is: '" + first + "'");
        System.out.println("--------------------------------------------------\n");

        // --- Problem 4: Reconstruct Itinerary ---
        System.out.println("## Problem 4: Reconstruct Itinerary");
        Map<String, String> tickets = new HashMap<>();
        tickets.put("Chennai", "Bengaluru");
        tickets.put("Mumbai", "Delhi");
        tickets.put("Goa", "Chennai");
        tickets.put("Delhi", "Goa");
        System.out.println("   Tickets: " + tickets);
        List<String> itinerary = findItinerary(tickets);
        System.out.println("   Reconstructed Itinerary: " + itinerary);
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Solution for Problem 1: Finds two numbers that sum to a target.
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
     * Solution for Problem 2: Groups anagrams together.
     * Approach: Use a HashMap where the key is the sorted version of a string (a "canonical"
     * representation of an anagram group) and the value is a list of original strings.
     * Time Complexity: O(N * K log K) where N is # of strings, K is max length.
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

    /**
     * Solution for Problem 3: Finds the first character that does not repeat in a string.
     * Approach: Use a HashMap to count character frequencies in the first pass. In the
     * second pass, iterate through the string again and return the first char with a count of 1.
     * Time Complexity: O(n), Space Complexity: O(k) where k is # of unique chars.
     */
    public static char findFirstNonRepeatingChar(String text) {
        Map<Character, Integer> freqMap = new HashMap<>();
        // First pass: build frequency map.
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        // Second pass: find the first character with a count of 1.
        for (char c : text.toCharArray()) {
            if (freqMap.get(c) == 1) {
                return c;
            }
        }
        return '\0'; // Return null character if no unique character is found.
    }
    
    /**
     * Solution for Problem 4: Reconstructs a travel itinerary from a list of tickets.
     * Approach: The starting city is the one that appears as a source but never as a
     * destination. We find it by creating a set of all destinations. Then, we can
     * traverse the path using the map.
     * Time Complexity: O(n), Space Complexity: O(n).
     */
    public static List<String> findItinerary(Map<String, String> tickets) {
        Set<String> destinations = new HashSet<>(tickets.values());
        
        // Find the starting city.
        String startCity = null;
        for (String city : tickets.keySet()) {
            if (!destinations.contains(city)) {
                startCity = city;
                break;
            }
        }
        
        if (startCity == null) {
            // This could happen if the itinerary is circular or the map is empty.
            return new ArrayList<>();
        }
        
        // Reconstruct the path.
        List<String> itinerary = new ArrayList<>();
        String currentCity = startCity;
        while (currentCity != null) {
            itinerary.add(currentCity);
            currentCity = tickets.get(currentCity);
        }
        return itinerary;
    }
}