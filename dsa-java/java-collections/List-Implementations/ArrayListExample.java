import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * =======================================================================================
 * | Theory & Concepts: Understanding ArrayList in Java                                  |
 * =======================================================================================
 *
 * An ArrayList is a resizable-array implementation of the `List` interface in Java. It's one of the most
 * commonly used data structures from the Java Collections Framework.
 *
 * --- Core Concepts ---
 * 1.  **Dynamic Array**: Internally, an ArrayList uses a dynamic array to store its elements. Unlike a standard
 * Java array, which has a fixed size, an ArrayList can grow and shrink as you add or remove elements.
 *
 * 2.  **Initial Capacity & Growth**: When you create an ArrayList, it starts with an initial capacity (default is 10).
 * When the number of elements exceeds this capacity, the ArrayList automatically creates a new, larger array
 * (typically 1.5 times the old size), copies all the old elements to the new array, and then adds the new element.
 * This resizing operation can be computationally expensive.
 *
 * 3.  **Ordered Collection**: An ArrayList maintains the insertion order of elements. The first element you add is at index 0,
 * the second at index 1, and so on.
 *
 * 4.  **Allows Duplicates & Nulls**: You can store duplicate values and multiple `null` values within an ArrayList.
 *
 * 5.  **Index-Based Access**: It provides fast, constant-time random access to elements using an index, because it's backed
 * by an array. Accessing an element with `get(index)` is very efficient, with a time complexity of O(1).
 *
 * 6.  **Not Synchronized**: ArrayList is not thread-safe. If multiple threads access an ArrayList concurrently and at least
 * one of them modifies the list, it must be synchronized externally (e.g., using `Collections.synchronizedList`).
 *
 * --- Time Complexity ---
 * -   **Access (get)**: O(1) - Very fast.
 * -   **Add (to end)**: O(1) on average (amortized). It's O(n) in the worst case if the internal array needs to be resized.
 * -   **Add (at index)**: O(n) - Slower, as all subsequent elements must be shifted.
 * -   **Remove (from end)**: O(1) - Fast.
 * -   **Remove (from index)**: O(n) - Slower, as all subsequent elements must be shifted.
 * -   **Search (contains)**: O(n) - Requires scanning the list.
 *
 * ----------------------------------------------------------------------------------------------------------------------
 * ## When to Use an ArrayList?
 *
 * An `ArrayList` is an excellent choice when your primary requirement is to **store and access a collection of items by their index**.
 * It excels in scenarios where you need fast, random access to elements and where the main operations are adding or removing
 * elements from the **end** of the list. For example, if you are fetching a list of user profiles from a database and need to
 * display them in order, or if you need to iterate through a collection frequently, `ArrayList` is highly efficient. However, it's less
 * ideal if your application involves frequent insertions or deletions from the middle of the list, as these operations require
 * shifting all subsequent elements, leading to a performance penalty (O(n) complexity). In such cases, a `LinkedList` would be a better choice.
 * ----------------------------------------------------------------------------------------------------------------------
 *
 * =======================================================================================
 * | Table of Common Built-in Functions                                                  |
 * =======================================================================================
 * | Method Signature                  | Description                                                                 |
 * |-----------------------------------|-----------------------------------------------------------------------------|
 * | `boolean add(E element)`          | Appends the specified element to the end of this list.                      |
 * | `void add(int index, E element)`  | Inserts the specified element at the specified position in this list.       |
 * | `boolean addAll(Collection c)`    | Appends all of the elements in the specified collection to the end.         |
 * | `E get(int index)`                | Returns the element at the specified position in this list.                 |
 * | `E set(int index, E element)`     | Replaces the element at the specified position with the specified element.  |
 * | `E remove(int index)`             | Removes the element at the specified position in this list.                 |
 * | `boolean remove(Object o)`        | Removes the first occurrence of the specified element from this list.       |
 * | `void clear()`                    | Removes all of the elements from this list. The list will be empty.         |
 * | `int size()`                      | Returns the number of elements in this list.                                |
 * | `boolean isEmpty()`               | Returns `true` if this list contains no elements.                           |
 * | `boolean contains(Object o)`      | Returns `true` if this list contains the specified element.                 |
 * | `int indexOf(Object o)`           | Returns the index of the first occurrence of the element, or -1 if not found. |
 * | `int lastIndexOf(Object o)`       | Returns the index of the last occurrence of the element, or -1 if not found.|
 * | `Object[] toArray()`              | Returns an array containing all of the elements in this list in proper order.|
 * | `List<E> subList(int from, int to)`| Returns a view of the portion of this list between the specified indices.   |
 * | `void ensureCapacity(int minCap)` | Increases the capacity of this ArrayList instance, if necessary.            |
 * | `void trimToSize()`                | Trims the capacity of this ArrayList instance to be the list's current size.|
 * =======================================================================================
 */
public class ArrayListExample {

    public static void main(String[] args) {

        // ==================================================
        // 1. Creating an ArrayList
        // ==================================================
        // We declare an ArrayList of type String. Using the interface 'List' on the left
        // is good practice as it allows for easy switching of the implementation later (e.g., to LinkedList).
        List<String> fruits = new ArrayList<>();
        System.out.println("1. Created an empty ArrayList: " + fruits);

        // ==================================================
        // 2. Adding Elements (`add`)
        // ==================================================
        // The add() method appends elements to the end of the list.
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        System.out.println("\n2. After adding elements: " + fruits);

        // Adding an element at a specific index
        fruits.add(1, "Orange"); // Inserts "Orange" at index 1
        System.out.println("   After adding 'Orange' at index 1: " + fruits);

        // ==================================================
        // 3. Accessing Elements (`get`)
        // ==================================================
        // The get() method retrieves an element by its index. Indices are 0-based.
        String firstFruit = fruits.get(0);
        String secondFruit = fruits.get(1);
        System.out.println("\n3. Accessing elements:");
        System.out.println("   Element at index 0: " + firstFruit);
        System.out.println("   Element at index 1: " + secondFruit);

        // ==================================================
        // 4. Modifying Elements (`set`)
        // ==================================================
        // The set() method replaces the element at a specified index.
        fruits.set(2, "Grape"); // Replaces "Banana" with "Grape" at index 2
        System.out.println("\n4. After modifying index 2 with 'Grape': " + fruits);

        // ==================================================
        // 5. Removing Elements (`remove`)
        // ==================================================
        // Removing by index
        fruits.remove(3); // Removes "Cherry" at index 3
        System.out.println("\n5. After removing element at index 3: " + fruits);

        // Removing by object value (removes the first occurrence)
        fruits.remove("Apple");
        System.out.println("   After removing the object 'Apple': " + fruits);

        // ==================================================
        // 6. List Size (`size` and `isEmpty`)
        // ==================================================
        System.out.println("\n6. Checking list size:");
        System.out.println("   Current size of the list: " + fruits.size());
        System.out.println("   Is the list empty? " + fruits.isEmpty());

        // ==================================================
        // 7. Searching for Elements (`contains`, `indexOf`)
        // ==================================================
        boolean hasGrape = fruits.contains("Grape");
        System.out.println("\n7. Searching for elements:");
        System.out.println("   Does the list contain 'Grape'? " + hasGrape);

        int indexOfOrange = fruits.indexOf("Orange");
        System.out.println("   The index of 'Orange' is: " + indexOfOrange);

        int indexOfMango = fruits.indexOf("Mango"); // "Mango" is not in the list
        System.out.println("   The index of 'Mango' is: " + indexOfMango); // Will print -1

        // ==================================================
        // 8. Iterating over an ArrayList
        // ==================================================
        System.out.println("\n8. Iterating over the list:");

        // Using an enhanced for-loop (most common)
        System.out.println("   Using enhanced for-loop:");
        for (String fruit : fruits) {
            System.out.println("   - " + fruit);
        }

        // Using an Iterator (allows for safe removal during iteration)
        System.out.println("   Using an Iterator:");
        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            System.out.println("   - " + iterator.next());
        }

        // ==================================================
        // 9. Clearing the ArrayList (`clear`)
        // ==================================================
        fruits.clear();
        System.out.println("\n9. After clearing the list: " + fruits);
        System.out.println("   Is the list empty now? " + fruits.isEmpty());
        System.out.println("   Size after clearing: " + fruits.size());

        // ==================================================
        // 10. Bulk Operations (`addAll`, `toArray`)
        // ==================================================
        List<String> moreFruits = new ArrayList<>();
        moreFruits.add("Pineapple");
        moreFruits.add("Mango");

        // addAll() - Add all elements from another collection
        fruits.addAll(moreFruits);
        System.out.println("\n10. After using addAll: " + fruits);

        // toArray() - Convert ArrayList to a standard array
        Object[] fruitArray = fruits.toArray();
        System.out.println("    Converted to an array: " + Arrays.toString(fruitArray));

    }
}