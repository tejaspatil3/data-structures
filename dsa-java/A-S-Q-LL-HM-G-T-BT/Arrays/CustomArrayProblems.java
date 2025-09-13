/*
================================================================================
|| NOTES: COMMON ARRAY PROBLEMS FOR TESTS & INTERVIEWS (FROM SCRATCH)
================================================================================

This file demonstrates solutions to common problems that test your understanding of
array manipulations. The solutions use a custom, from-scratch `MyArray` implementation
(provided below) to showcase the logic of working with fixed-size, contiguous data.

Understanding these problems is crucial because they highlight the trade-offs of using
arrays: fast index-based access is a huge advantage, but operations that require
shifting or reordering elements can be complex.

Problems covered:
1.  **Find Maximum and Minimum Element**:
    A fundamental problem that demonstrates basic array traversal. The goal is to find
    the largest and smallest values in a single pass.

2.  **Reverse an Array (In-Place)**:
    A classic manipulation task. "In-place" means the reversal must happen without
    creating a second, separate array, thus saving memory (O(1) space complexity).
    This is efficiently solved using a two-pointer technique.

3.  **Rotate Array by 'k' Positions**:
    A more advanced manipulation problem. Given an array and a number `k`, shift every
    element `k` positions to the right. The elements that fall off the end should
    wrap around to the beginning. The solution shown here is a highly efficient,
    in-place method called the "reversal algorithm."

4.  **Remove Duplicates from a Sorted Array (In-Place)**:
    A very common interview question. Because the array is sorted, all duplicates will
    be adjacent. The challenge is to produce a version of the array with only unique
    elements at the beginning, modifying the array in-place and returning the new
    logical size.
*/

/**
 * Main public class to run the problem examples.
 */
public class CustomArrayProblems {

    public static void main(String[] args) {

        // --- Problem 1: Find Maximum and Minimum Element ---
        System.out.println("## Problem 1: Find Maximum and Minimum Element");
        MyArray arr1 = new MyArray(7);
        arr1.insert(0, 40); arr1.insert(1, 20); arr1.insert(2, 80); arr1.insert(3, 10);
        arr1.insert(4, 70); arr1.insert(5, 5); arr1.insert(6, 60);
        arr1.printArray();
        int[] minMax = findMinMax(arr1);
        System.out.println("   Minimum: " + minMax[0] + ", Maximum: " + minMax[1]);
        System.out.println("--------------------------------------------------\n");

        // --- Problem 2: Reverse the Array In-Place ---
        System.out.println("## Problem 2: Reverse the Array In-Place");
        System.out.println("   Array before reverse:");
        arr1.printArray();
        reverse(arr1);
        System.out.println("   Array after reverse:");
        arr1.printArray();
        System.out.println("--------------------------------------------------\n");

        // --- Problem 3: Rotate Array by 'k' Positions ---
        System.out.println("## Problem 3: Rotate Array by 'k' Positions");
        MyArray arr2 = new MyArray(7);
        for(int i = 0; i < 7; i++) arr2.insert(i, i + 1); // Fill with 1,2,3,4,5,6,7
        int k = 3;
        System.out.println("   Array before rotating by " + k + ":");
        arr2.printArray();
        rotate(arr2, k);
        System.out.println("   Array after rotating by " + k + ":");
        arr2.printArray();
        System.out.println("--------------------------------------------------\n");
        
        // --- Problem 4: Remove Duplicates from a Sorted Array ---
        System.out.println("## Problem 4: Remove Duplicates from a Sorted Array");
        MyArray arr3 = new MyArray(10);
        arr3.insert(0, 1); arr3.insert(1, 1); arr3.insert(2, 2); arr3.insert(3, 3);
        arr3.insert(4, 3); arr3.insert(5, 3); arr3.insert(6, 4); arr3.insert(7, 5);
        System.out.println("   Array before removing duplicates:");
        arr3.printArray();
        int newSize = removeDuplicatesFromSorted(arr3);
        System.out.println("   New logical size after removing duplicates: " + newSize);
        System.out.println("   Array after removing duplicates (only first " + newSize + " elements are valid):");
        arr3.printArray();
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Solution for Problem 1: Finds the min and max elements in one pass.
     * Time Complexity: O(n), Space Complexity: O(1).
     */
    public static int[] findMinMax(MyArray arr) {
        if (arr.size() == 0) {
            throw new IllegalStateException("Array is empty.");
        }
        int min = arr.get(0);
        int max = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            int current = arr.get(i);
            if (current < min) {
                min = current;
            }
            if (current > max) {
                max = current;
            }
        }
        return new int[]{min, max};
    }

    /**
     * Solution for Problem 2: Reverses the array in-place.
     */
    public static void reverse(MyArray arr) {
        int left = 0;
        int right = arr.size() - 1;
        while (left < right) {
            // Swap elements at left and right
            int temp = arr.get(left);
            arr.set(left, arr.get(right));
            arr.set(right, temp);
            left++;
            right--;
        }
    }

    /**
     * Solution for Problem 3: Rotates the array in-place using the reversal algorithm.
     */
    public static void rotate(MyArray arr, int k) {
        int n = arr.size();
        if (n == 0 || k <= 0) return;
        k = k % n; // Handle cases where k > n

        // 1. Reverse the first part (0 to n-k-1)
        reverseSubArray(arr, 0, n - k - 1);
        // 2. Reverse the second part (n-k to n-1)
        reverseSubArray(arr, n - k, n - 1);
        // 3. Reverse the entire array
        reverseSubArray(arr, 0, n - 1);
    }

    // Helper for the rotate function
    private static void reverseSubArray(MyArray arr, int left, int right) {
        while (left < right) {
            int temp = arr.get(left);
            arr.set(left, arr.get(right));
            arr.set(right, temp);
            left++;
            right--;
        }
    }
    
    /**
     * Solution for Problem 4: Removes duplicates from a sorted array in-place.
     * @return The new logical size of the array after removing duplicates.
     */
    public static int removeDuplicatesFromSorted(MyArray arr) {
        if (arr.size() < 2) {
            return arr.size();
        }
        // `writeIndex` points to the position where the next unique element should be written.
        int writeIndex = 1;
        for (int readIndex = 1; readIndex < arr.size(); readIndex++) {
            // If the current element is different from the previous one...
            if (arr.get(readIndex) != arr.get(readIndex - 1)) {
                // ...it's a unique element, so write it to the `writeIndex` position.
                arr.set(writeIndex, arr.get(readIndex));
                writeIndex++;
            }
        }
        // After the loop, the first `writeIndex` elements are the unique elements.
        // We update the logical size of the array.
        arr.setSize(writeIndex);
        return writeIndex;
    }
}


//==============================================================================
//== FROM-SCRATCH IMPLEMENTATION OF A FIXED-SIZE ARRAY WRAPPER
//==============================================================================
class MyArray {
    private int[] data;
    private int size;

    public MyArray(int capacity) {
        this.data = new int[capacity];
        this.size = 0;
    }

    public void insert(int index, int value) {
        if (size == data.length) throw new RuntimeException("Array is full.");
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = value;
        size++;
    }

    public int get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        return data[index];
    }

    public void set(int index, int value) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        data[index] = value;
    }

    public int size() {
        return this.size;
    }

    /**
     * A method to manually set the logical size. Useful for in-place operations.
     */
    public void setSize(int newSize) {
        if (newSize >= 0 && newSize <= data.length) {
            this.size = newSize;
        }
    }

    public void printArray() {
        System.out.print("   Array: [");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i]);
            if (i < size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("] (Size: " + size + ", Capacity: " + data.length + ")");
    }
}