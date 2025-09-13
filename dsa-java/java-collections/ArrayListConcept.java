
// The ArrayList class is a resizable array, which can be found in the java.util package.

// The difference between a built-in array and an ArrayList in Java, is that the size of an array cannot be modified (if you want to add or remove elements to/from an array, you have to create a new one). While elements can be added and removed from an ArrayList whenever you want. The syntax is also slightly

// Import the ArrayList class from the java.util package.
// This is necessary to use ArrayList in our program.

/*
        --------------------------
        -- THEORY: WHAT IS AN ARRAYLIST? --
        --------------------------

        1.  **Dynamic Array**: An ArrayList is a resizable array, which can be found in the java.util package.
            Unlike a standard Java array which has a fixed size, an ArrayList can grow and shrink dynamically
            as you add or remove elements. This is its primary advantage.

        2.  **Part of Collections Framework**: It's a part of the Java Collections Framework and implements the List interface.
            This means it has all the standard methods of a list.

        3.  **Key Characteristics**:
            * **Ordered**: It maintains the insertion order. The first element you add is at index 0, the second at index 1, and so on.
            * **Index-based**: You can access, modify, or remove elements using their integer index (position).
            * **Allows Duplicates & Nulls**: You can store duplicate values and also null values within an ArrayList.
            * **Not Synchronized**: It is not thread-safe. If multiple threads access an ArrayList concurrently, it must be synchronized externally.

        4.  **Generics (`<E>`)**:
            * When you create an ArrayList, you should specify the type of objects it will hold using angle brackets `<>`.
            * For example, `ArrayList<String>` creates a list that can only hold String objects. This is called generics.
            * Using generics provides type-safety, preventing you from adding the wrong type of object to the list, which helps catch errors at compile-time instead of runtime.
            * You must use wrapper classes for primitive types (e.g., `Integer` for `int`, `Double` for `double`).

        5.  **Common Methods**:
            * `add(element)`: Appends an element to the end of the list.
            * `add(index, element)`: Inserts an element at a specific index.
            * `get(index)`: Returns the element at a specified index.
            * `set(index, element)`: Replaces the element at a given index with a new element.
            * `remove(index)`: Removes the element at a specified index.
            * `remove(object)`: Removes the first occurrence of a specified object.
            * `size()`: Returns the number of elements in the list.
            * `clear()`: Removes all elements from the list.
            * `contains(object)`: Returns `true` if the list contains the specified element.
        */

import java.util.ArrayList;

public class ArrayListConcept {

    public static void main(String[] args) {

        ArrayList<Object> mixedList = new ArrayList<>();

        mixedList.add("Hello");        // String
        mixedList.add(123);            // Integer
        mixedList.add(3.14);           // Double
        mixedList.add(true);           // Boolean
        // mixedList.add(new CustomObject("example")); // Custom object

        for (Object item : mixedList) {
            System.out.println(item);
        }


        // --------------------------
        // -- EXAMPLE: A PRACTICAL DEMONSTRATION --
        // --------------------------

        // 1. **Creating an ArrayList**
        // This ArrayList is created to hold only String objects.
        ArrayList<String> fruits = new ArrayList<>();
        System.out.println("1. Created an empty ArrayList.");

        // 2. **Adding elements** using the add() method.
        // Elements are added to the end of the list, maintaining insertion order.
        fruits.add("Apple");    // index 0
        fruits.add("Banana");   // index 1
        fruits.add("Cherry");   // index 2
        System.out.println("2. After adding elements: " + fruits);

        // We can also add an element at a specific index.
        fruits.add(1, "Orange"); // Inserts "Orange" at index 1, shifting "Banana" and "Cherry"
        System.out.println("   After adding 'Orange' at index 1: " + fruits);

        // 3. **Getting the size** of the ArrayList using the size() method.
        System.out.println("3. The size of the list is: " + fruits.size());

        // 4. **Accessing an element** using the get() method.
        // Indices are 0-based, just like in a regular array.
        String firstFruit = fruits.get(0);
        System.out.println("4. The element at index 0 is: " + firstFruit);

        // 5. **Modifying an element** using the set() method.
        // Let's replace "Cherry" (now at index 3) with "Mango".
        fruits.set(3, "Mango");
        System.out.println("5. After replacing element at index 3: " + fruits);

        // 6. **Removing an element** using the remove() method.
        // We can remove by index or by object.
        fruits.remove(2); // Removes "Banana" which is at index 2
        System.out.println("6. After removing element at index 2: " + fruits);

        // 7. **Iterating through the ArrayList**
        // A for-each loop is a common and easy way to loop through all elements.
        System.out.println("7. Iterating through the list elements:");
        for (String fruit : fruits) {
            System.out.println("   - " + fruit);
        }

        // 8. **Checking if an element exists** using contains().
        boolean hasApple = fruits.contains("Apple");
        System.out.println("8. Does the list contain 'Apple'? " + hasApple);

        // 9. **Clearing the ArrayList**
        // The clear() method removes all elements from the list.
        fruits.clear();
        System.out.println("9. After clearing the list, its size is: " + fruits.size());
        System.out.println("   The list is now empty: " + fruits);
    }
}