import java.util.Arrays;
import java.util.Map;
import java.util.LinkedHashMap; // Preserves insertion order, crucial for one problem

public class StringTestProblems {

    public static void main(String[] args) {
        System.out.println("--- Problem 1: Palindrome Check ---");
        String p1 = "madam";
        String p2 = "A man a plan a canal Panama";
        String p3 = "hello";
        System.out.println("Is \"" + p1 + "\" a palindrome? " + isPalindrome(p1));
        System.out.println("Is \"" + p2 + "\" a palindrome (ignoring case/spaces)? " + isPalindromeComplex(p2));
        System.out.println("Is \"" + p3 + "\" a palindrome? " + isPalindrome(p3));

        System.out.println("\n--- Problem 2: Reverse Words in a String ---");
        String sentence = "Java is a powerful language";
        System.out.println("Original: \"" + sentence + "\"");
        System.out.println("Reversed: \"" + reverseWords(sentence) + "\"");
        
        String sentenceWithExtraSpaces = "  hello   world  ";
        System.out.println("Original: \"" + sentenceWithExtraSpaces + "\"");
        System.out.println("Reversed: \"" + reverseWords(sentenceWithExtraSpaces) + "\"");

        System.out.println("\n--- Problem 3: Anagram Check ---");
        String a1 = "listen";
        String a2 = "silent";
        String a3 = "hello";
        String a4 = "world";
        System.out.println("Are \"" + a1 + "\" and \"" + a2 + "\" anagrams? " + areAnagrams(a1, a2));
        System.out.println("Are \"" + a3 + "\" and \"" + a4 + "\" anagrams? " + areAnagrams(a3, a4));
        
        System.out.println("\n--- Problem 4: Find First Non-Repeated Character ---");
        String nonRepeatStr = "swiss";
        System.out.println("In \"" + nonRepeatStr + "\", first non-repeated char is: '" + findFirstNonRepeatedChar(nonRepeatStr) + "'");
        String nonRepeatStr2 = "programming";
        System.out.println("In \"" + nonRepeatStr2 + "\", first non-repeated char is: '" + findFirstNonRepeatedChar(nonRepeatStr2) + "'");
        
        System.out.println("\n--- Problem 5: Count Vowels and Consonants ---");
        String countStr = "Hello World";
        int[] counts = countVowelsAndConsonants(countStr);
        System.out.println("In \"" + countStr + "\", Vowels: " + counts[0] + ", Consonants: " + counts[1]);
    }

    /**
     * Problem 1: Palindrome Check (Simple)
     * A palindrome is a word that reads the same forwards and backward.
     * Approach: Use a two-pointer technique. One pointer starts at the beginning,
     * and one starts at the end. Compare characters as the pointers move inward.
     * Time Complexity: O(n), Space Complexity: O(1).
     */
    public static boolean isPalindrome(String str) {
        if (str == null || str.isEmpty()) {
            return true; // Or false, depending on requirements for empty strings.
        }
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    /**
     * Problem 1 (Advanced): Palindrome Check ignoring case and non-alphanumeric chars.
     */
    public static boolean isPalindromeComplex(String str) {
        if (str == null) return true;
        String cleaned = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return isPalindrome(cleaned);
    }
    

    /**
     * Problem 2: Reverse Words in a String
     * Given an input string, reverse the string word by word.
     * Approach: Split the string by spaces, then iterate through the resulting
     * array in reverse and build a new string.
     * Time Complexity: O(n), Space Complexity: O(n).
     */
    public static String reverseWords(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) {
            return "";
        }
        // Trim leading/trailing spaces and split by one or more spaces
        String[] words = sentence.trim().split("\\s+");
        StringBuilder reversed = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]);
            if (i > 0) {
                reversed.append(" ");
            }
        }
        return reversed.toString();
    }

    /**
     * Problem 3: Anagram Check
     * Two strings are anagrams if they contain the same characters in a different order.
     * Approach: The simplest method is to convert both strings to char arrays,
     * sort them, and then check if the sorted arrays are equal.
     * Time Complexity: O(n log n) due to sorting, Space Complexity: O(n).
     */
    public static boolean areAnagrams(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        char[] arr1 = str1.toLowerCase().toCharArray();
        char[] arr2 = str2.toLowerCase().toCharArray();

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        return Arrays.equals(arr1, arr2);
    }

    /**
     * Problem 4: Find the First Non-Repeated Character
     * Find the first character in a string that does not repeat.
     * Approach: Use a frequency map (like a HashMap) to store character counts.
     * The first pass populates the map. The second pass iterates through the string
     * again to find the first character with a count of 1.
     * Time Complexity: O(n), Space Complexity: O(k) where k is number of unique chars.
     */
    public static char findFirstNonRepeatedChar(String str) {
        if (str == null || str.isEmpty()) {
            return '\0'; // Null character represents "not found"
        }
        // LinkedHashMap preserves insertion order of keys
        Map<Character, Integer> counts = new LinkedHashMap<>();
        for (char c : str.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return '\0'; // No non-repeated character found
    }

    /**
     * Problem 5: Count Vowels and Consonants
     * Count the total number of vowels and consonants in a string.
     * Approach: Iterate through the string character by character. For each character,
     * check if it's a letter. If it is, check if it's a vowel or a consonant.
     * Time Complexity: O(n), Space Complexity: O(1).
     */
    public static int[] countVowelsAndConsonants(String str) {
        if (str == null) {
            return new int[]{0, 0};
        }
        int vowelCount = 0;
        int consonantCount = 0;
        String vowels = "aeiou";
        
        String cleanStr = str.toLowerCase();

        for (char c : cleanStr.toCharArray()) {
            if (c >= 'a' && c <= 'z') { // Check if it's a letter
                if (vowels.indexOf(c) != -1) { // Check if the letter is a vowel
                    vowelCount++;
                } else {
                    consonantCount++;
                }
            }
        }
        return new int[]{vowelCount, consonantCount};
    }
}