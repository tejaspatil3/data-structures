import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PredefinedSearchingMethods {

    public static void main(String[] args) {
        
        System.out.println("## 1. Searching in an Array using Arrays.binarySearch()");
        demonstrateArraySearch();
        System.out.println("----------------------------------------------------------\n");
        
        System.out.println("## 2. Searching in a Collection (ArrayList)");
        demonstrateCollectionSearch();
        System.out.println("----------------------------------------------------------\n");
    }

    /*
    ======================================================================
    PART 1: SEARCHING IN ARRAYS
    ======================================================================
    * **Concept**: The `java.util.Arrays` class provides a static, highly optimized
      `binarySearch()` method to find an element in an array.
    * **Crucial Prerequisite**: The array **MUST BE SORTED** before you call
      `Arrays.binarySearch()`. If the array is not sorted, the results will be unpredictable.
    * **Return Value**:
      * **If found**: It returns the `index` of the search key (a value >= 0).
      * **If NOT found**: It returns a negative value, calculated as `(-(insertion point) - 1)`.
        The "insertion point" is the index where the key would be inserted into the array to
        maintain the sorted order. This is useful for understanding where the element *should* be.
    * **Performance**: O(log n) - It's very fast because it uses the binary search algorithm.
    */
    public static void demonstrateArraySearch() {
        // The array MUST be sorted.
        int[] sortedNumbers = {10, 20, 30, 40, 50, 60, 70, 80};
        System.out.println("   Sorted Array to search in: " + Arrays.toString(sortedNumbers));

        // --- Example 1: Target is found ---
        int targetFound = 50;
        int index1 = Arrays.binarySearch(sortedNumbers, targetFound);
        System.out.println("\n   Searching for " + targetFound + "...");
        System.out.println("   Result: Found at index " + index1);

        // --- Example 2: Target is NOT found ---
        int targetNotFound = 45;
        int index2 = Arrays.binarySearch(sortedNumbers, targetNotFound);
        System.out.println("\n   Searching for " + targetNotFound + "...");
        System.out.println("   Result: Not found. Returned value is " + index2);
        
        // Let's interpret the negative result:
        // The insertion point for 45 would be index 4 (to keep the array sorted).
        // The formula is (-(insertion point) - 1), so (-(4) - 1) = -5.
        int insertionPoint = -index2 - 1;
        System.out.println("   Interpretation: " + targetNotFound + " would be inserted at index " + insertionPoint + " to maintain order.");
    }

    /*
    ======================================================================
    PART 2: SEARCHING IN COLLECTIONS (e.g., ArrayList)
    ======================================================================
    * **Concept**: Collections like `ArrayList` provide instance methods to search for elements.
      These methods typically perform a **linear search**.
    * **Prerequisite**: The collection does **NOT** need to be sorted.
    * **Performance**: O(n) - It checks each element one by one. This is slower than a binary
      search on large, sorted datasets.
    * **Common Methods**:
      * `indexOf(Object o)`: Returns the index of the *first* occurrence of the element, or -1 if not found.
      * `contains(Object o)`: Returns `true` if the element exists in the list, otherwise `false`.
    */
    public static void demonstrateCollectionSearch() {
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Apple"); // Duplicates are allowed
        fruits.add("Mango");
        
        System.out.println("   ArrayList to search in: " + fruits);

        // --- Using indexOf() ---
        System.out.println("\n   --- Using indexOf() ---");
        String fruitToFind1 = "Orange";
        int index1 = fruits.indexOf(fruitToFind1);
        System.out.println("   The first index of '" + fruitToFind1 + "' is: " + index1);

        // Note: indexOf returns the FIRST match
        String fruitToFind2 = "Apple";
        int index2 = fruits.indexOf(fruitToFind2);
        System.out.println("   The first index of '" + fruitToFind2 + "' is: " + index2);

        String fruitToFind3 = "Grapes";
        int index3 = fruits.indexOf(fruitToFind3);
        System.out.println("   The first index of '" + fruitToFind3 + "' is: " + index3 + " (since it's not in the list)");

        // --- Using contains() ---
        System.out.println("\n   --- Using contains() ---");
        String fruitToCheck1 = "Banana";
        boolean hasBanana = fruits.contains(fruitToCheck1);
        System.out.println("   Does the list contain '" + fruitToCheck1 + "'? " + hasBanana);

        String fruitToCheck2 = "Pineapple";
        boolean hasPineapple = fruits.contains(fruitToCheck2);
        System.out.println("   Does the list contain '" + fruitToCheck2 + "'? " + hasPineapple);
    }
}