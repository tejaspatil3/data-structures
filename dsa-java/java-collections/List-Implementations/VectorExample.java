import java.util.Vector;
import java.util.List;
import java.util.Enumeration;
// import java.util.Arrays;

/**
 * =======================================================================================
 * | Theory & Concepts: Understanding Vector in Java                                     |
 * =======================================================================================
 *
 * A Vector is a resizable-array implementation of the `List` interface. It is one of the original
 * data structures from Java 1.0 and is essentially a synchronized version of `ArrayList`.
 *
 * --- Core Concepts ---
 * 1.  **Dynamic Array**: Just like an ArrayList, a Vector is backed by a dynamic array that can grow
 * or shrink as needed.
 *
 * 2.  **Synchronization (Thread-Safety) 🔐**: This is the most important characteristic of a Vector.
 * Almost every public method in the Vector class is declared as `synchronized`. This means that only
 * one thread can access and modify the Vector's contents at a time. This prevents data corruption in
 * multi-threaded applications but comes with a performance cost due to the overhead of acquiring and
 * releasing locks for every operation.
 *
 * 3.  **Legacy Class**: Vector is considered a legacy class. It was part of Java's first release,
 * before the main Collections Framework was introduced in Java 1.2. For new single-threaded applications,
 * `ArrayList` is almost always preferred as it is unsynchronized and therefore faster. For new multi-threaded
 * applications, `Collections.synchronizedList(new ArrayList<>())` or more advanced classes like
 * `CopyOnWriteArrayList` are often better alternatives.
 *
 * 4.  **Capacity Growth**: By default, a Vector doubles its internal array's size when it runs out of
 * capacity. This is different from an ArrayList, which typically increases its size by 50% (1.5x).
 * You can also specify a custom capacity increment when creating a Vector.
 *
 * 5.  **Enumeration**: Besides the standard `Iterator`, Vector also supports a legacy interface called
 * `Enumeration` for traversing elements.
 *
 * --- Time Complexity ---
 * The time complexities are the same as ArrayList, but with a constant-time overhead for synchronization
 * on every operation.
 * -   **Access (get)**: O(1)
 * -   **Add (to end)**: O(1) amortized
 * -   **Add (at index)**: O(n)
 * -   **Remove (at index)**: O(n)
 * -   **Search (contains)**: O(n)
 *
 * ----------------------------------------------------------------------------------------------------------------------
 * ## When to Use a Vector?
 *
 * A `Vector` should be used in scenarios where you need a **thread-safe, list-like data structure** and do not want
 * to handle synchronization manually. Its most common application is in multi-threaded environments where different
 * threads need to add or remove elements from a shared list without causing concurrency issues like `ConcurrentModificationException`.
 * However, because it is a legacy class and its approach to synchronization (locking the entire collection for every
 * operation) can cause performance bottlenecks, its use is often limited to **maintaining old codebases**. For new development,
 * modern alternatives are generally superior. For instance, if you have many more read operations than write operations in a
 * concurrent environment, `CopyOnWriteArrayList` is a much more efficient choice. If you need a general-purpose synchronized
 * list, using the `Collections.synchronizedList()` wrapper on an `ArrayList` is the more modern approach.
 * ----------------------------------------------------------------------------------------------------------------------
 *
 * =======================================================================================
 * | Table of Common Built-in Functions                                                  |
 * =======================================================================================
 * | Method Signature                  | Description                                                                 |
 * |-----------------------------------|-----------------------------------------------------------------------------|
 * | **Standard List Methods** |                                                                             |
 * | `boolean add(E element)`          | Appends the specified element to the end of this Vector.                    |
 * | `E get(int index)`                | Returns the element at the specified position in this Vector.               |
 * | `E set(int index, E element)`     | Replaces the element at the specified position with the specified element.  |
 * | `E remove(int index)`             | Removes the element at the specified position in this Vector.               |
 * | `int size()`                      | Returns the number of elements in this Vector.                              |
 * |-----------------------------------|-----------------------------------------------------------------------------|
 * | **Vector-Specific & Legacy Methods** |                                                                          |
 * | `void addElement(E obj)`          | Same as `add()`. Adds the component to the end of the vector.               |
 * | `E elementAt(int index)`          | Same as `get()`. Returns the component at the specified index.              |
 * | `void removeElement(Object obj)`  | Removes the first occurrence of the argument from this vector.              |
 * | `void removeAllElements()`        | Removes all components from this vector and sets its size to zero.          |
 * | `E firstElement()`                | Returns the first component (the item at index 0) of this vector.           |
 * | `E lastElement()`                 | Returns the last component of the vector.                                   |
 * | `int capacity()`                  | Returns the current capacity of this vector.                                |
 * | `Enumeration<E> elements()`       | Returns an enumeration of the components of this vector.                    |
 * =======================================================================================
 */
public class VectorExample {

    public static void main(String[] args) {

        // ==================================================
        // 1. Creating a Vector
        // ==================================================
        // We can create a vector with an initial capacity.
        List<String> techCompanies = new Vector<>(5);
        System.out.println("1. Created an empty Vector: " + techCompanies);

        // ==================================================
        // 2. Adding Elements (`add` and legacy `addElement`)
        // ==================================================
        // The add() method appends elements to the end of the vector.
        techCompanies.add("Google");
        techCompanies.add("Microsoft");
        
        // addElement() is a legacy method that does the same thing as add().
        // To use it, we must cast our list to a Vector.
        ((Vector<String>) techCompanies).addElement("Apple");
        System.out.println("\n2. After adding elements: " + techCompanies);

        // ==================================================
        // 3. Accessing Elements (`get` and legacy `elementAt`)
        // ==================================================
        // get() is the modern way to retrieve an element by its index.
        String firstCompany = techCompanies.get(0);
        System.out.println("\n3. Accessing elements:");
        System.out.println("   Element at index 0 (using get): " + firstCompany);
        
        // elementAt() is the legacy equivalent.
        String secondCompany = ((Vector<String>) techCompanies).elementAt(1);
        System.out.println("   Element at index 1 (using elementAt): " + secondCompany);

        // ==================================================
        // 4. Modifying Elements (`set`)
        // ==================================================
        techCompanies.set(2, "Amazon"); // Replaces "Apple" with "Amazon" at index 2
        System.out.println("\n4. After modifying index 2 with 'Amazon': " + techCompanies);

        // ==================================================
        // 5. Removing Elements (`remove` and legacy `removeElement`)
        // ==================================================
        // Removing by index
        techCompanies.remove(1); // Removes "Microsoft" at index 1
        System.out.println("\n5. After removing element at index 1: " + techCompanies);

        // Removing by object value (legacy method)
        ((Vector<String>) techCompanies).removeElement("Google");
        System.out.println("   After removing the object 'Google' (using removeElement): " + techCompanies);
        
        // ==================================================
        // 6. Size and Capacity (`size` and `capacity`)
        // ==================================================
        // Size is the number of elements in the vector.
        System.out.println("\n6. Checking size and capacity:");
        System.out.println("   Current size of the vector: " + techCompanies.size());
        
        // Capacity is the total space allocated in the internal array.
        int currentCapacity = ((Vector<String>) techCompanies).capacity();
        System.out.println("   Current capacity of the vector: " + currentCapacity);

        // ==================================================
        // 7. Iterating over a Vector
        // ==================================================
        techCompanies.add("Netflix");
        techCompanies.add("Meta");
        System.out.println("\n7. Iterating over the vector: " + techCompanies);

        // Using an enhanced for-loop (modern and preferred)
        System.out.println("   Using enhanced for-loop:");
        for (String company : techCompanies) {
            System.out.println("   - " + company);
        }

        // Using the legacy Enumeration interface
        System.out.println("   Using legacy Enumeration:");
        Enumeration<String> enumeration = ((Vector<String>) techCompanies).elements();
        while (enumeration.hasMoreElements()) {
            System.out.println("   - " + enumeration.nextElement());
        }

        // ==================================================
        // 8. Thread Safety Note
        // ==================================================
        System.out.println("\n8. Thread Safety Note:");
        System.out.println("   Every method like add(), get(), remove() on a Vector is synchronized.");
        System.out.println("   This makes it safe to share a Vector instance between multiple threads");
        System.out.println("   without external synchronization, but it can be slower than an ArrayList.");
    }
}