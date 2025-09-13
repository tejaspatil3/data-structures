import java.util.Arrays;

public class SortingAlgorithms {

    public static void main(String[] args) {
        // --- Setup for the demonstrations ---
        int[] originalArray = {64, 34, 25, 12, 22, 11, 90, 5};

        System.out.println("Original Unsorted Array: " + Arrays.toString(originalArray));
        System.out.println("========================================================\n");

        // --- 1. Bubble Sort Demonstration ---
        int[] arrForBubble = Arrays.copyOf(originalArray, originalArray.length);
        System.out.println("## 1. Bubble Sort");
        System.out.println("   Before: " + Arrays.toString(arrForBubble));
        bubbleSort(arrForBubble);
        System.out.println("   After:  " + Arrays.toString(arrForBubble));
        System.out.println("--------------------------------------------------------\n");

        // --- 2. Selection Sort Demonstration ---
        int[] arrForSelection = Arrays.copyOf(originalArray, originalArray.length);
        System.out.println("## 2. Selection Sort");
        System.out.println("   Before: " + Arrays.toString(arrForSelection));
        selectionSort(arrForSelection);
        System.out.println("   After:  " + Arrays.toString(arrForSelection));
        System.out.println("--------------------------------------------------------\n");
        
        // --- 3. Insertion Sort Demonstration ---
        int[] arrForInsertion = Arrays.copyOf(originalArray, originalArray.length);
        System.out.println("## 3. Insertion Sort");
        System.out.println("   Before: " + Arrays.toString(arrForInsertion));
        insertionSort(arrForInsertion);
        System.out.println("   After:  " + Arrays.toString(arrForInsertion));
        System.out.println("--------------------------------------------------------\n");

        // --- 4. Merge Sort Demonstration ---
        int[] arrForMerge = Arrays.copyOf(originalArray, originalArray.length);
        System.out.println("## 4. Merge Sort");
        System.out.println("   Before: " + Arrays.toString(arrForMerge));
        mergeSort(arrForMerge, 0, arrForMerge.length - 1);
        System.out.println("   After:  " + Arrays.toString(arrForMerge));
        System.out.println("--------------------------------------------------------\n");

        // --- 5. Quick Sort Demonstration ---
        int[] arrForQuick = Arrays.copyOf(originalArray, originalArray.length);
        System.out.println("## 5. Quick Sort");
        System.out.println("   Before: " + Arrays.toString(arrForQuick));
        quickSort(arrForQuick, 0, arrForQuick.length - 1);
        System.out.println("   After:  " + Arrays.toString(arrForQuick));
        System.out.println("--------------------------------------------------------\n");
    }

    // Helper method for swapping two elements in an array
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
    ==================================================
    ALGORITHM 1: BUBBLE SORT
    ==================================================
    * **Concept**: Repeatedly steps through the list, compares adjacent elements, and swaps
      them if they are in the wrong order. This process is repeated until the list is sorted,
      causing the largest elements to "bubble" to the end.
    * **Analogy**: Bubbles in a drink rising to the surface. 🫧
    * **Time Complexity**: O(n²) - Inefficient for large lists.
    * **Space Complexity**: O(1) - It's an "in-place" sort.
    */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            // Optimization: If no two elements were swapped in the inner loop, the array is sorted.
            if (!swapped) break;
        }
    }

    /*
    ==================================================
    ALGORITHM 2: SELECTION SORT
    ==================================================
    * **Concept**: Divides the list into a sorted and an unsorted part. It repeatedly finds
      the minimum element from the unsorted part and moves it to the end of the sorted part.
    * **Analogy**: Picking the shortest person from a group, then the next shortest from the
      rest, and so on, lining them up one by one.
    * **Time Complexity**: O(n²) - Performance is not affected by the initial order.
    * **Space Complexity**: O(1) - In-place sort.
    */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            // Find the index of the minimum element in the unsorted array
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            // Swap the found minimum element with the first element of the unsorted part
            swap(arr, i, minIdx);
        }
    }
    
    /*
    ==================================================
    ALGORITHM 3: INSERTION SORT
    ==================================================
    * **Concept**: Builds the final sorted array one item at a time. It iterates through the
      input elements and inserts each element into its correct position in the already sorted part.
    * **Analogy**: Sorting a hand of playing cards. You pick up cards one by one and insert
      them into their correct position in your hand. 🃏
    * **Time Complexity**: O(n²) worst case, but O(n) best case (for nearly sorted data).
    * **Space Complexity**: O(1) - In-place sort.
    */
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            // Move elements of arr[0..i-1] that are greater than key,
            // to one position ahead of their current position.
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    /*
    ==================================================
    ALGORITHM 4: MERGE SORT
    ==================================================
    * **Concept**: A "divide and conquer" algorithm. It divides the array into two halves,
      recursively sorts them, and then merges the two sorted halves into one.
    * **Analogy**: A librarian splitting a huge pile of books in half, giving each half to an
      assistant to sort, and then merging the two sorted piles back together.
    * **Time Complexity**: O(n log n) - Very efficient and consistent.
    * **Space Complexity**: O(n) - Requires extra memory for merging, not in-place.
    */
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            // Find the middle point
            int mid = left + (right - left) / 2;

            // Sort first and second halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    // Helper method to merge two subarrays
    private static void merge(int[] arr, int left, int mid, int right) {
        // Find sizes of two subarrays to be merged
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temp arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i) L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[mid + 1 + j];

        // Merge the temp arrays
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    /*
    ==================================================
    ALGORITHM 5: QUICK SORT
    ==================================================
    * **Concept**: Another "divide and conquer" algorithm. It picks an element as a 'pivot'
      and partitions the array around it. All elements smaller than the pivot are moved before it,
      and all elements greater are moved after it. This is then done recursively.
    * **Analogy**: A teacher lining up students by height. They pick one student as a pivot height.
      Shorter students go left, taller students go right. This is repeated for the two smaller groups.
    * **Time Complexity**: O(n log n) on average. O(n²) in the worst case.
    * **Space Complexity**: O(log n) - Due to the recursion stack.
    */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // pi is the partitioning index, arr[pi] is now at the right place
            int pi = partition(arr, low, high);

            // Recursively sort elements before partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Helper method to partition the array and return the pivot index
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Choosing the last element as the pivot
        int i = (low - 1); // Index of smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than the pivot
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        // Swap the pivot element to its correct position
        swap(arr, i + 1, high);
        return (i + 1);
    }
}