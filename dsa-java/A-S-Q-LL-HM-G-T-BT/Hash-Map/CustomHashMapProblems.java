import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/*
================================================================================
|| NOTES: COMMON HASHMAP PROBLEMS FOR TESTS & INTERVIEWS (FROM SCRATCH)
================================================================================

This file demonstrates solutions to common problems that test your understanding of
how and when to use a HashMap. The solutions use a custom, from-scratch `MyHashMap`
implementation (provided below) to showcase the underlying logic.

HashMaps are the go-to data structure for problems involving frequency counting,
lookup, or associating two pieces of data. Their O(1) average time complexity for
`put`, `get`, and `remove` makes them incredibly efficient.

Problems covered:
1.  **Character Frequency Count**:
    The most fundamental use case for a HashMap. Given a string, count how many
    times each character appears. This pattern is a building block for many other problems.

2.  **Two Sum Problem**:
    A world-famous interview question. Given an array of integers and a target sum,
    find two numbers in the array that add up to the target. The optimal O(n) solution
    uses a HashMap to store numbers as you see them, allowing you to check for the
    needed "complement" in O(1) time.

3.  **Anagram Check**:
    Two strings are anagrams if they contain the same characters with the same frequencies
    (e.g., "listen" and "silent"). A HashMap provides an O(n) time complexity solution by
    counting character frequencies in the first string and then decrementing the counts
    using the second string.

4.  **First Non-Repeating Character**:
    A common follow-up to the frequency count problem. After building a frequency map,
    you must iterate through the original string a second time to find the first
    character that has a count of 1.
*/

/**
 * Main public class to run the problem examples.
 */
public class CustomHashMapProblems {
    public static void main(String[] args) {

        // --- Problem 1: Character Frequency Count ---
        System.out.println("## Problem 1: Character Frequency Count");
        String text1 = "hello world";
        MyHashMap<Character, Integer> frequencyMap = findCharacterFrequency(text1);
        System.out.println("   Frequency of characters in '" + text1 + "':");
        frequencyMap.printMap();
        System.out.println("--------------------------------------------------\n");

        // --- Problem 2: Two Sum Problem ---
        System.out.println("## Problem 2: Two Sum Problem");
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = findTwoSum(nums, target);
        System.out.println("   Array: " + Arrays.toString(nums) + ", Target: " + target);
        if (result != null) {
            System.out.println("   Indices found: " + Arrays.toString(result));
        } else {
            System.out.println("   No two sum solution found.");
        }
        System.out.println("--------------------------------------------------\n");

        // --- Problem 3: Anagram Check ---
        System.out.println("## Problem 3: Anagram Check");
        String s1 = "listen";
        String s2 = "silent";
        String s3 = "hello";
        String s4 = "world";
        System.out.println("   Are '" + s1 + "' and '" + s2 + "' anagrams? " + areAnagrams(s1, s2));
        System.out.println("   Are '" + s3 + "' and '" + s4 + "' anagrams? " + areAnagrams(s3, s4));
        System.out.println("--------------------------------------------------\n");

        // --- Problem 4: First Non-Repeating Character ---
        System.out.println("## Problem 4: First Non-Repeating Character");
        String text2 = "swiss";
        char first = findFirstNonRepeatingChar(text2);
        System.out.println("   In '" + text2 + "', first non-repeating char is: '" + first + "'");
        String text3 = "aabbcdd";
        first = findFirstNonRepeatingChar(text3);
        System.out.println("   In '" + text3 + "', first non-repeating char is: '" + first + "'");
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Solution for Problem 1: Counts the frequency of each character in a string.
     */
    public static MyHashMap<Character, Integer> findCharacterFrequency(String text) {
        MyHashMap<Character, Integer> map = new MyHashMap<>();
        for (char c : text.toCharArray()) {
            Integer count = map.get(c);
            if (count == null) {
                map.put(c, 1); // First time seeing this character
            } else {
                map.put(c, count + 1); // Increment the count
            }
        }
        return map;
    }

    /**
     * Solution for Problem 2: Finds two numbers in an array that sum to a target.
     * @return An array containing the indices of the two numbers, or null if not found.
     */
    public static int[] findTwoSum(int[] nums, int target) {
        MyHashMap<Integer, Integer> map = new MyHashMap<>(); // Stores (number -> index)
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            // Check if the complement we need is already in the map
            if (map.get(complement) != null) {
                return new int[]{map.get(complement), i};
            }
            // If not found, put the current number and its index in the map
            map.put(nums[i], i);
        }
        return null; // No solution found
    }

    /**
     * Solution for Problem 3: Checks if two strings are anagrams using a HashMap.
     */
    public static boolean areAnagrams(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        
        MyHashMap<Character, Integer> freqMap = findCharacterFrequency(s1);
        
        for (char c : s2.toCharArray()) {
            Integer count = freqMap.get(c);
            // If a character in s2 doesn't exist in s1, or its count is already zero
            if (count == null || count == 0) {
                return false;
            }
            freqMap.put(c, count - 1);
        }
        // If we get through the whole loop, they are anagrams
        return true;
    }

    /**
     * Solution for Problem 4: Finds the first non-repeating character in a string.
     */
    public static char findFirstNonRepeatingChar(String text) {
        MyHashMap<Character, Integer> freqMap = findCharacterFrequency(text);
        
        for (char c : text.toCharArray()) {
            if (freqMap.get(c) == 1) {
                return c;
            }
        }
        return '\0'; // Return null character if none found
    }
}


//==============================================================================
//== FROM-SCRATCH IMPLEMENTATION OF A HASHMAP AND ITS ENTRY
//==============================================================================
class MapEntry<K, V> {
    K key;
    V value;
    MapEntry<K, V> next;
    public MapEntry(K key, V value) { this.key = key; this.value = value; this.next = null; }
}

class MyHashMap<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private MapEntry<K, V>[] buckets;
    private int capacity;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        this(INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int initialCapacity) {
        this.capacity = initialCapacity;
        this.buckets = new MapEntry[this.capacity];
    }

    private int getBucketIndex(K key) {
        if (key == null) return 0;
        return Math.abs(key.hashCode() % capacity);
    }

    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        MapEntry<K, V> head = buckets[bucketIndex];
        MapEntry<K, V> current = head;
        while (current != null) {
            if (key == null ? current.key == null : key.equals(current.key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }
        MapEntry<K, V> newEntry = new MapEntry<>(key, value);
        newEntry.next = head;
        buckets[bucketIndex] = newEntry;
        size++;
        if ((1.0 * size) / capacity >= LOAD_FACTOR) rehash();
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        MapEntry<K, V> current = buckets[bucketIndex];
        while (current != null) {
            if (key == null ? current.key == null : key.equals(current.key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        MapEntry<K, V>[] oldBuckets = buckets;
        this.capacity *= 2;
        this.buckets = new MapEntry[this.capacity];
        this.size = 0;
        for (MapEntry<K, V> headNode : oldBuckets) {
            MapEntry<K, V> current = headNode;
            while (current != null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
    }
    
    // Utility to print the contents of the map
    public void printMap() {
        for (int i = 0; i < capacity; i++) {
            MapEntry<K, V> current = buckets[i];
            if (current != null) {
                System.out.print("   Bucket " + i + ": ");
                while (current != null) {
                    System.out.print("[" + current.key + "=" + current.value + "] -> ");
                    current = current.next;
                }
                System.out.println("null");
            }
        }
    }
}