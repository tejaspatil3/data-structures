import java.util.Arrays;

/*
================================================================================
|| THEORY: IMPLEMENTING A DYNAMIC ARRAY (ArrayList) FROM SCRATCH
================================================================================

1.  THE PROBLEM: "ARRAY-LIKE LINKED LIST"
    - **Arrays** are fast for accessing elements by index (e.g., `arr[5]`) but have a fixed size. You can't add more elements than its initial capacity.
    - **LinkedLists** are flexible in size (you can always add more nodes) but are slow for accessing elements by index (you have to start from the beginning and count).
    - A **Dynamic Array** (which is what `ArrayList` is in Java) gives you the best of both worlds: a list that can grow on-demand while still providing fast, array-like index access.

2.  HOW IT WORKS INTERNALLY
    - **Underlying Array**: A Dynamic Array uses a standard, fixed-size array internally to store the actual data.
    - **Capacity vs. Size**: It keeps track of two important numbers:
      - `capacity`: The total length of the internal array (how much it *can* hold).
      - `size`: The number of elements currently stored in the list (how much it *does* hold).
    - **The "Dynamic" Secret: Resizing**: This is the key to its flexibility. When you try to add an element and the internal array is full (`size == capacity`), the following happens:
      1. A **new, larger array** is created in memory (often double the previous capacity).
      2. All elements from the **old array are copied** over to the new, larger array.
      3. The internal reference is updated to point to this new array, and the old one is discarded.

3.  THE ANALOGY
    - Think of a small bookshelf 📚 that can only hold 10 books. When you get your 11th book, you realize you're out of space.
    - You go out and buy a new, bigger bookshelf that can hold 20 books.
    - You then **move all 10 of your old books** onto the new shelf.
    - Now you have plenty of space to add your 11th book and more. This is exactly how a dynamic array resizes.

4.  PERFORMANCE TRADE-OFFS
    - **Get/Set Element by Index**: Very fast, **O(1)**, just like a regular array.
    - **Add Element to End**: Mostly very fast, **O(1)**. However, when a resize happens, it becomes slow, **O(n)**, because all n elements must be copied. This occasional slow operation averages out, so we call it **Amortized O(1)**.
    - **Add/Remove from Middle/Beginning**: Slow, **O(n)**. Just like a regular array, all subsequent elements must be shifted left or right.

*/

/**
 * Main public class to run examples on our custom-built MyArrayList.
 */
public class CustomArrayLinkedList {
    public static void main(String[] args) {
        System.out.println("--- Creating a new MyArrayList with default capacity ---");
        MyArrayList<String> list = new MyArrayList<>();

        System.out.println("\n--- 1. Adding Elements (to the end) ---");
        System.out.println("Is list empty? " + list.isEmpty());
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        System.out.println("List contents: " + list);
        System.out.println("Current size: " + list.size());

        System.out.println("\n--- 2. Getting Elements by Index ---");
        System.out.println("Element at index 0: " + list.get(0));
        System.out.println("Element at index 2: " + list.get(2));
        // list.get(3); // This would throw an IndexOutOfBoundsException

        System.out.println("\n--- 3. Adding an Element at a specific Index ---");
        System.out.println("List before adding: " + list);
        list.add(1, "Blueberry"); // Insert at index 1
        System.out.println("List after adding 'Blueberry' at index 1: " + list);

        System.out.println("\n--- 4. Removing an Element by Index ---");
        System.out.println("List before removing: " + list);
        String removedElement = list.remove(2); // Removes "Banana"
        System.out.println("Removed element: " + removedElement);
        System.out.println("List after removing element at index 2: " + list);
        System.out.println("Current size: " + list.size());

        System.out.println("\n--- 5. Demonstrating Automatic Resizing ---");
        MyArrayList<Integer> numberList = new MyArrayList<>(3); // Start with a small capacity
        System.out.println("Adding 1, 2, 3...");
        numberList.add(1);
        numberList.add(2);
        numberList.add(3);
        System.out.println("List is now full: " + numberList);
        System.out.println("Adding 4 (this should trigger a resize)...");
        numberList.add(4); // This triggers the resize
        System.out.println("List after resizing and adding: " + numberList);
        System.out.println("New size: " + numberList.size());
    }
}


/**
 * A custom implementation of a Dynamic Array (ArrayList).
 * It is generic, meaning it can hold any type of object.
 * @param <E> The type of element this list will hold.
 */
class MyArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data; // Use Object array to store generic type E
    private int size;      // The number of elements currently in the list
    private int capacity;  // The current length of the internal array

    // Constructor with default capacity
    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    // Constructor with a specified initial capacity
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative: " + initialCapacity);
        }
        this.capacity = initialCapacity;
        this.data = new Object[this.capacity];
        this.size = 0;
    }

    /**
     * Adds an element to the end of the list.
     */
    public void add(E element) {
        ensureCapacity();
        data[size] = element;
        size++;
    }

    /**
     * Adds an element at a specific index, shifting subsequent elements.
     */
    public void add(int index, E element) {
        if (index < 0 || index > size) { // Can add at 'size' (the end)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity();
        // Shift elements to the right
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = element;
        size++;
    }

    /**
     * Retrieves the element at a specific index.
     * @return The element at the specified index.
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) data[index]; // Cast the Object back to the generic type E
    }

    /**
     * Removes the element at a specific index, shifting subsequent elements.
     * @return The element that was removed.
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        E removedElement = get(index);
        // Shift elements to the left
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null; // Help the garbage collector
        return removedElement;
    }

    /**
     * Private helper method to handle resizing the internal array.
     */
    private void ensureCapacity() {
        if (size == capacity) {
            // If capacity is 0, start with default capacity, otherwise double it.
            int newCapacity = (capacity == 0) ? DEFAULT_CAPACITY : capacity * 2;
            System.out.println("   -> Resizing internal array from " + capacity + " to " + newCapacity);
            this.data = Arrays.copyOf(this.data, newCapacity);
            this.capacity = newCapacity;
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}