// Import the HashSet and Set classes from the java.util package.
import java.util.HashSet;
import java.util.Set;

public class HashSetConcept {

    public static void main(String[] args) {

        /*
        --------------------------
        -- THEORY: WHAT IS A HASHSET? --
        --------------------------

        1.  **Collection of Unique Elements**: A HashSet is a collection that stores only unique items. 💎
            It's an implementation of the `Set` interface. Its most important characteristic is that it
            does not allow duplicate elements. If you try to add an element that already exists in the set,
            the add operation is simply ignored.

        2.  **Hashing Mechanism**: Like HashMap, a HashSet uses hashing for storage. It is internally backed by a
            HashMap. The element you add to the HashSet becomes a key in the internal HashMap. This allows for
            very fast operations (add, remove, and contains), which are typically performed in constant time, O(1),
            on average.

        3.  **Key Characteristics**:
            * **No Duplicates**: This is the defining feature. It enforces uniqueness among its elements.
            * **Unordered**: A HashSet does NOT maintain any order. The elements are not stored in the order they were inserted,
              nor are they sorted. The iteration order can seem random and may even change over time.
            * **Allows One Null Value**: You can store a single `null` value in a HashSet.
            * **Not Synchronized**: It is not thread-safe. For a thread-safe set, you can use `Collections.synchronizedSet()` or
              `CopyOnWriteArraySet`.

        4.  **Generics (`<E>`)**:
            * When creating a HashSet, you should specify the type of elements (`E`) it will hold, for example, `HashSet<Integer>`.
            * This provides compile-time type-safety, preventing you from accidentally adding an object of the wrong type.
            * You must use wrapper classes for primitive types (e.g., `Double` for `double`, `Boolean` for `boolean`).

        5.  **`hashCode()` and `equals()`**:
            * For a HashSet to work correctly, especially with custom objects, the objects must properly implement the
              `hashCode()` and `equals()` methods.
            * `hashCode()` is used to find the "bucket" where the object should be stored.
            * `equals()` is used to check if an object is truly a duplicate of another object within the same bucket.
            * Rule: If two objects are equal according to `equals()`, they MUST have the same `hashCode()`.

        6.  **Common Methods**:
            * `add(element)`: Adds the specified element to the set if it is not already present. Returns `true` if the element was added, `false` otherwise.
            * `remove(element)`: Removes the specified element from the set if it is present.
            * `contains(element)`: Returns `true` if the set contains the specified element.
            * `size()`: Returns the number of elements in the set.
            * `clear()`: Removes all of the elements from the set.
            * `isEmpty()`: Returns `true` if the set contains no elements.
        */

        // --------------------------
        // -- EXAMPLE: A PRACTICAL DEMONSTRATION --
        // --------------------------

        // 1. **Creating a HashSet**
        // This HashSet will store unique planet names (String).
        Set<String> planets = new HashSet<>();
        System.out.println("1. Created an empty HashSet.");

        // 2. **Adding elements** using the add() method.
        planets.add("Earth");
        planets.add("Mars");
        planets.add("Jupiter");
        System.out.println("2. After adding elements: " + planets);

        // 3. **Attempting to add a duplicate element**
        // The add() method returns false because "Mars" is already in the set.
        boolean wasMarsAddedAgain = planets.add("Mars");
        System.out.println("3. Was 'Mars' added again? " + wasMarsAddedAgain);
        System.out.println("   The set remains unchanged: " + planets);

        // 4. **Checking if an element exists** using contains().
        boolean hasEarth = planets.contains("Earth");
        boolean hasVenus = planets.contains("Venus");
        System.out.println("4. Does the set contain 'Earth'? " + hasEarth);
        System.out.println("   Does the set contain 'Venus'? " + hasVenus);

        // 5. **Removing an element** using the remove() method.
        planets.remove("Jupiter");
        System.out.println("5. After removing 'Jupiter': " + planets);

        // 6. **Getting the size** of the HashSet.
        System.out.println("6. The current size of the set is: " + planets.size());

        // 7. **Iterating through the HashSet**
        // A for-each loop is a simple way to iterate. Notice the order is not guaranteed.
        System.out.println("7. Iterating through the set elements:");
        for (String planet : planets) {
            System.out.println("   - " + planet);
        }

        // 8. **Clearing the HashSet**
        planets.clear();
        System.out.println("\n8. After clearing the set, is it empty? " + planets.isEmpty());
        System.out.println("   The set is now empty: " + planets);
    }
}