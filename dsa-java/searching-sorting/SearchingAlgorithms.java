import java.util.Arrays;

public class SearchingAlgorithms {

    public static void main(String[] args) {
        // --- Setup for the demonstrations ---
        int[] unsortedArray = {4, 42, 1, 99, 23, 17, 50, 33};
        // Binary and Jump search REQUIRE the array to be sorted first.
        int[] sortedArray = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};

        int target1 = 17; // A target that exists in the unsorted array.
        int target2 = 23; // A target that exists in the sorted array.
        int target3 = 999; // A target that does not exist in either array.

        System.out.println("Unsorted Array: " + Arrays.toString(unsortedArray));
        System.out.println("Sorted Array: " + Arrays.toString(sortedArray));
        System.out.println("--------------------------------------------------\n");


        // --- 1. Linear Search Demonstration ---
        System.out.println("## 1. Linear Search");
        int linearResult1 = linearSearch(unsortedArray, target1);
        printResult("Linear Search", target1, linearResult1);

        int linearResult2 = linearSearch(sortedArray, target3);
        printResult("Linear Search", target3, linearResult2);
        System.out.println("--------------------------------------------------\n");


        // --- 2. Binary Search (Iterative) Demonstration ---
        System.out.println("## 2. Binary Search (Iterative)");
        int binaryIterativeResult1 = binarySearchIterative(sortedArray, target2);
        printResult("Binary Search (Iterative)", target2, binaryIterativeResult1);

        int binaryIterativeResult2 = binarySearchIterative(sortedArray, target3);
        printResult("Binary Search (Iterative)", target3, binaryIterativeResult2);
        System.out.println("--------------------------------------------------\n");


        // --- 3. Binary Search (Recursive) Demonstration ---
        System.out.println("## 3. Binary Search (Recursive)");
        int binaryRecursiveResult1 = binarySearchRecursive(sortedArray, target2);
        printResult("Binary Search (Recursive)", target2, binaryRecursiveResult1);

        int binaryRecursiveResult2 = binarySearchRecursive(sortedArray, target3);
        printResult("Binary Search (Recursive)", target3, binaryRecursiveResult2);
        System.out.println("--------------------------------------------------\n");


        // --- 4. Jump Search Demonstration ---
        System.out.println("## 4. Jump Search");
        int jumpResult1 = jumpSearch(sortedArray, target2);
        printResult("Jump Search", target2, jumpResult1);

        int jumpResult2 = jumpSearch(sortedArray, target3);
        printResult("Jump Search", target3, jumpResult2);
        System.out.println("--------------------------------------------------\n");
    }

    // Helper method to print results consistently
    public static void printResult(String algorithm, int target, int index) {
        if (index != -1) {
            System.out.println("   [" + algorithm + "]: Found target " + target + " at index " + index + ".");
        } else {
            System.out.println("   [" + algorithm + "]: Target " + target + " was not found in the array.");
        }
    }

    /*
    ==================================================
    ALGORITHM 1: LINEAR SEARCH
    ==================================================
    * **Concept**: The simplest search algorithm. It sequentially checks each element of the list
        until a match is found or the whole list has been searched.
    * **Analogy**: Finding a specific book on a shelf by checking every single book from left to right. 📚
    * **Use Case**: Works on **any array (sorted or unsorted)**. It's simple but inefficient for large datasets.
    * **Time Complexity**: O(n) - In the worst case, we have to look at every element.
    * **Space Complexity**: O(1) - It doesn't require extra memory.
    */
    public static int linearSearch(int[] arr, int target) {
        // Loop through every element in the array.
        for (int i = 0; i < arr.length; i++) {
            // If the current element matches the target, return its index.
            if (arr[i] == target) {
                return i;
            }
        }
        // If the loop finishes without finding the target, return -1.
        return -1;
    }

    /*
    ==================================================
    ALGORITHM 2: BINARY SEARCH (Iterative)
    ==================================================
    * **Concept**: A highly efficient "divide and conquer" algorithm. It works by repeatedly
        dividing the search interval in half. If the value of the search key is less than the item
        in the middle of the interval, narrow the interval to the lower half. Otherwise, narrow
        it to the upper half.
    * **Prerequisite**: The array **MUST BE SORTED**.
    * **Analogy**: Finding a word in a dictionary. You open to the middle, see if your word is
        before or after, and then repeat the process on the correct half. 📖
    * **Time Complexity**: O(log n) - Extremely fast as it halves the search space with each step.
    * **Space Complexity**: O(1) - The iterative version uses constant extra space.
    */
    public static int binarySearchIterative(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            // Calculate mid point to avoid potential integer overflow
            int mid = left + (right - left) / 2;

            // Check if target is present at mid
            if (arr[mid] == target) {
                return mid;
            }

            // If target is greater, ignore the left half
            if (arr[mid] < target) {
                left = mid + 1;
            }
            // If target is smaller, ignore the right half
            else {
                right = mid - 1;
            }
        }
        // If we reach here, then the element was not present
        return -1;
    }


    /*
    ==================================================
    ALGORITHM 3: BINARY SEARCH (Recursive)
    ==================================================
    * **Concept**: The same divide and conquer logic as the iterative version, but implemented
        using a function that calls itself.
    * **Prerequisite**: The array **MUST BE SORTED**.
    * **Time Complexity**: O(log n).
    * **Space Complexity**: O(log n) - Due to the memory used by the recursion call stack.
    */
    public static int binarySearchRecursive(int[] arr, int target) {
        return binarySearchRecursiveHelper(arr, target, 0, arr.length - 1);
    }

    private static int binarySearchRecursiveHelper(int[] arr, int target, int left, int right) {
        if (left <= right) {
            int mid = left + (right - left) / 2;

            // Base case: If the element is present at the middle itself
            if (arr[mid] == target) {
                return mid;
            }

            // Recursive step: If element is smaller than mid, then it can only be in the left subarray
            if (arr[mid] > target) {
                return binarySearchRecursiveHelper(arr, target, left, mid - 1);
            }

            // Else the element can only be in the right subarray
            return binarySearchRecursiveHelper(arr, target, mid + 1, right);
        }
        // We reach here when the element is not present in the array
        return -1;
    }


    /*
    ==================================================
    ALGORITHM 4: JUMP SEARCH
    ==================================================
    * **Concept**: An improvement on Linear Search for large arrays. The basic idea is to check
        fewer elements by "jumping" ahead by fixed steps. Once we find a block where the
        target *might* be, we perform a linear search within that smaller block.
    * **Prerequisite**: The array **MUST BE SORTED**.
    * **Analogy**: Finding a specific page in a book. You first skip through entire chapters
        (the "jumps"), and once you find the right chapter, you scan the pages one by one (the "linear search").
    * **Time Complexity**: O(√n) - Better than linear search, but not as good as binary search.
    * **Space Complexity**: O(1).
    */
    public static int jumpSearch(int[] arr, int target) {
        int n = arr.length;
        
        // Calculate the optimal jump size
        int step = (int) Math.floor(Math.sqrt(n));
        
        int prev = 0;
        // Step 1: Find the block where the element might be present by jumping.
        // We keep jumping until we find an element greater than the target.
        while (arr[Math.min(step, n) - 1] < target) {
            prev = step;
            step += (int) Math.floor(Math.sqrt(n));
            if (prev >= n) {
                // If our previous jump position is already past the array end, target can't be found.
                return -1;
            }
        }
        
        // Step 2: Perform a linear search for the target in the block beginning with prev.
        while (arr[prev] < target) {
            prev++;
            
            // If we reached the next block or end of array, element is not present.
            if (prev == Math.min(step, n)) {
                return -1;
            }
        }
        
        // If the element is found
        if (arr[prev] == target) {
            return prev;
        }
        
        return -1;
    }
}