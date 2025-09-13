/*
================================================================================
|| THEORY: UNDERSTANDING ARRAYS FROM SCRATCH
================================================================================

1.  WHAT IS AN ARRAY?
    - An Array is a fundamental data structure that stores a collection of elements of the **same data type** in a **contiguous block of memory**.
    - **Contiguous** means that the elements are stored one after another, like houses on a street or slots in an egg carton. 🥚

2.  KEY CHARACTERISTICS
    - **Fixed Size**: The most important characteristic of a true array is that its size (capacity) is fixed when it is created. You cannot change it later. If you need more space, you must create a new, larger array and copy the old elements over.
    - **Index-Based Access**: Elements are accessed using a numerical, zero-based index (the first element is at index 0, the second at index 1, etc.).
    - **Fast Random Access**: Because elements are stored contiguously, accessing any element by its index is extremely fast. The computer can calculate the exact memory address of any element instantly (`start_address + index * element_size`). This is an **O(1)** operation and is the primary advantage of an array.
    

3.  THE ANALOGY
    - Think of a **numbered parking lot**. Each parking spot has a unique number (the index). If you want to find the car in spot #5, you can go directly there without checking spots #0 through #4.

4.  PERFORMANCE OF BASIC OPERATIONS
    - **Access (`get(index)` / `set(index, value)`)**: **O(1)** - Extremely fast.
    - **Search (`linearSearch(value)`)**: **O(n)** - In the worst case, you have to look at every single element to find what you're looking for.
    - **Insertion (`insert(index, value)`)**: **O(n)** - Slow. To insert an element at a specific index, you must shift all subsequent elements one position to the right to make space.
    - **Deletion (`delete(index)`)**: **O(n)** - Slow. To delete an element, you must shift all subsequent elements one position to the left to fill the gap.

5.  OUR IMPLEMENTATION
    - The code below simulates these behaviors by wrapping a primitive `int[]` array inside a custom class.
    - We will manage the number of actual elements using a `size` variable, which is separate from the array's total `capacity`. This allows us to demonstrate insertion and deletion within the array's capacity.

*/

/**
 * Main public class to run examples on our custom-built MyArray.
 */
public class CustomArrayExample {

    public static void main(String[] args) {

        System.out.println("--- Creating a new MyArray with a capacity of 10 ---");
        // We are using our custom MyArray class defined below.
        MyArray arr = new MyArray(10);
        System.out.println("Is array empty? " + (arr.size() == 0));

        System.out.println("\n--- 1. Inserting Elements ---");
        arr.insert(0, 10); // Insert 10 at the beginning
        arr.insert(1, 20);
        arr.insert(2, 40);
        arr.insert(3, 50);
        System.out.println("Array after initial insertions:");
        arr.printArray();
        System.out.println("Inserting 30 at index 2 (shifting elements)...");
        arr.insert(2, 30); // This will shift 40 and 50 to the right
        arr.printArray();
        System.out.println("Current size: " + arr.size());

        System.out.println("\n--- 2. Accessing and Updating Elements ---");
        System.out.println("Element at index 3 is: " + arr.get(3));
        System.out.println("Updating element at index 0 from 10 to 99...");
        arr.set(0, 99);
        arr.printArray();

        System.out.println("\n--- 3. Deleting Elements ---");
        System.out.println("Deleting element at index 2 (value 30)...");
        arr.delete(2); // This will shift 40 and 50 to the left
        arr.printArray();
        System.out.println("Current size: " + arr.size());

        System.out.println("\n--- 4. Searching for Elements ---");
        System.out.println("Searching for value 40... Found at index: " + arr.linearSearch(40));
        System.out.println("Searching for value 77... Found at index: " + arr.linearSearch(77));

        System.out.println("\n--- 5. Handling Edge Cases ---");
        try {
            System.out.println("Attempting to get element at out-of-bounds index 10...");
            arr.get(10);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Caught an error as expected: " + e.getMessage());
        }

        System.out.println("Filling the array to its capacity...");
        arr.insert(4, 60);
        arr.insert(5, 70);
        arr.insert(6, 80);
        arr.insert(7, 90);
        arr.insert(8, 100);
        arr.insert(9, 110);
        arr.printArray();

        try {
            System.out.println("Attempting to insert into a full array...");
            arr.insert(5, 999);
        } catch (RuntimeException e) {
            System.out.println("Caught an error as expected: " + e.getMessage());
        }
    }
}


/**
 * A custom implementation of a fixed-size array to demonstrate its operations.
 * This class is not public, so it can exist in the same file as the main class.
 */
class MyArray {
    private int[] data;   // The internal array to store elements
    private int size;     // The current number of elements in the array

    /**
     * Constructor to initialize the array with a fixed capacity.
     */
    public MyArray(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative.");
        }
        this.data = new int[capacity];
        this.size = 0; // Initially, the array contains no elements
    }

    /**
     * Inserts a value at a specific index. O(n) complexity.
     */
    public void insert(int index, int value) {
        // Check if the array is full
        if (size == data.length) {
            throw new RuntimeException("Array is full. Cannot insert.");
        }
        // Check if the index is valid
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size);
        }
        // Shift elements to the right to make space
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        // Insert the new value and increment the size
        data[index] = value;
        size++;
    }

    /**
     * Deletes the element at a specific index. O(n) complexity.
     */
    public void delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size);
        }
        // Shift elements to the left to fill the gap
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        // Decrement the size
        size--;
    }

    /**
     * Gets the element at a specific index. O(1) complexity.
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size);
        }
        return data[index];
    }

    /**
     * Updates the element at a specific index. O(1) complexity.
     */
    public void set(int index, int value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size);
        }
        data[index] = value;
    }

    /**
     * Searches for a value and returns its index. O(n) complexity.
     * @return The index of the value, or -1 if not found.
     */
    public int linearSearch(int value) {
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                return i;
            }
        }
        return -1; // Not found
    }

    /**
     * Returns the current number of elements in the array.
     */
    public int size() {
        return this.size;
    }

    /**
     * Utility method to print the current state of the array's contents.
     */
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