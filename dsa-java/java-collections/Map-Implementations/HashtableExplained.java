// File: HashtableExplained.java

/*
 ====================================================================================================
 |                                  THEORY OF HASHTABLE IN JAVA                                     |
 ====================================================================================================

 ## 1. What is a Hashtable?

 A `Hashtable` is one of the original data structures from the Java 1.0 release, predating the
 Collections Framework. It stores key-value pairs in a hash table, much like a `HashMap`. The
 primary and most defining characteristic of a `Hashtable` is that it is **synchronized**.

 ## 2. Core Characteristics & Key Differences from HashMap

 While `Hashtable` and `HashMap` are both hash-table-based `Map` implementations, they have
 critical differences that dictate their use.

 ### A. Synchronization (Thread-Safety) 🔒
 - **Hashtable**: All of its public methods (like `put`, `get`, `remove`, `size`) are declared
   as `synchronized`. This means only one thread can execute any of these methods at a given
   time. This provides a simple way to achieve thread safety, but it can lead to performance
   bottlenecks in highly concurrent applications because threads have to wait for each other.
 - **HashMap**: Is **not** synchronized. It is not thread-safe. If multiple threads access a
   `HashMap` concurrently and at least one of them modifies the map structurally, it must be
   synchronized externally.

 ### B. Null Key/Value Handling
 - **Hashtable**: Does **not** allow `null` keys or `null` values. Attempting to store a `null`
   key or value will result in a `NullPointerException`.
 - **HashMap**: Allows one `null` key and multiple `null` values.

 ### C. Legacy Status
 - **Hashtable**: Is considered a **legacy class**. It was retrofitted to implement the `Map`
   interface when the Collections Framework was introduced. It also extends the old `Dictionary`
   class.
 - **HashMap**: Is the standard, non-synchronized map implementation and is generally preferred in
   non-concurrent scenarios. For modern concurrent applications, `ConcurrentHashMap` is the
   recommended choice over `Hashtable`.

 ### D. Performance
 - Due to the synchronization overhead on every method call, `Hashtable` is generally slower
   than `HashMap`. The performance difference becomes significant in multi-threaded environments
   where `ConcurrentHashMap` offers much better scalability.

 ---

 ## 3. How Hashtable Works Internally

 The internal mechanics of `Hashtable` are very similar to the classic implementation of `HashMap`:
 - **Hash Table**: It uses an array of buckets to store entries.
 - **`hashCode()`**: To store a key-value pair, it calculates the key's `hashCode()`.
 - **Index Calculation**: It computes an index into the array using the hash code, typically via
   a modulo operation: `index = (hashCode() & 0x7FFFFFFF) % array.length`. The `& 0x7FFFFFFF`
   part ensures the number is positive.
 - **Collision Handling**: It uses **Separate Chaining** with **Linked Lists** to handle collisions
   (when multiple keys map to the same bucket index). Unlike modern `HashMap`, it does not upgrade
   these linked lists to balanced trees.

 ====================================================================================================
 |                                 BUILT-IN HASHTABLE FUNCTIONS                                     |
 ====================================================================================================

 `Hashtable` implements the `Map` interface, so it has methods similar to `HashMap`. All these
 methods are `synchronized`.

 ### Core Operations
 - `V put(K key, V value)`: Associates the specified value with the specified key.
 - `V get(Object key)`: Returns the value for the specified key.
 - `V remove(Object key)`: Removes the mapping for a key.
 - `boolean containsKey(Object key)`: Returns `true` if the key exists.
 - `boolean containsValue(Object value)`: Returns `true` if the value exists. This can be slow.
 - `int size()`: Returns the number of key-value mappings.
 - `boolean isEmpty()`: Returns `true` if the table is empty.
 - `void clear()`: Removes all mappings.

 ### View Collections
 - `Set<K> keySet()`: Returns a `Set` view of the keys.
 - `Collection<V> values()`: Returns a `Collection` view of the values.
 - `Set<Map.Entry<K, V>> entrySet()`: Returns a `Set` view of the mappings.

 ### Legacy Methods (from Dictionary)
 - `Enumeration<K> keys()`: Returns an enumeration of the keys (less preferred than `keySet()`).
 - `Enumeration<V> elements()`: Returns an enumeration of the values (less preferred than `values()`).

*/

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * This class demonstrates the theory and practical usage of Hashtable in Java.
 * All the detailed theory is provided in the multi-line comment block at the top of this file.
 */
public class HashtableExplained {

    public static void main(String[] args) {

        System.out.println("--- Hashtable Demonstration ---");

        // ## Creating a Hashtable ##
        // Note: It's good practice to code to the interface (Map).
        Map<String, Integer> productPrices = new Hashtable<>();


        // ## 1. put(key, value) - Adding elements ##
        // This is a synchronized operation.
        System.out.println("\n1. Adding elements with put():");
        productPrices.put("Apple", 150);
        productPrices.put("Banana", 80);
        productPrices.put("Cherry", 200);
        System.out.println("Hashtable content: " + productPrices);

        // Trying to add nulls will cause an exception.
        try {
            productPrices.put("Dragonfruit", null);
        } catch (NullPointerException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
        try {
            productPrices.put(null, 500);
        } catch (NullPointerException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }


        // ## 2. get(key) - Accessing elements ##
        // This is also a synchronized operation.
        System.out.println("\n2. Accessing elements with get():");
        Integer applePrice = productPrices.get("Apple");
        System.out.println("Price of an Apple: " + applePrice);


        // ## 3. remove(key) - Removing elements ##
        System.out.println("\n3. Removing elements with remove():");
        productPrices.remove("Banana");
        System.out.println("Hashtable after removing Banana: " + productPrices);


        // ## 4. containsKey(key) and containsValue(value) ##
        System.out.println("\n4. Checking for keys and values:");
        boolean hasApple = productPrices.containsKey("Apple"); // true
        boolean hasBanana = productPrices.containsKey("Banana"); // false
        System.out.println("Does the table contain 'Apple'? " + hasApple);
        System.out.println("Does the table contain 'Banana'? " + hasBanana);

        boolean hasPrice200 = productPrices.containsValue(200); // true
        System.out.println("Does any product have a price of 200? " + hasPrice200);


        // ## 5. Iterating over a Hashtable ##
        System.out.println("\n5. Iterating using entrySet (preferred method):");
        // entrySet() provides the most efficient way to iterate over keys and values.
        Set<Map.Entry<String, Integer>> entries = productPrices.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println("Product: " + entry.getKey() + ", Price: " + entry.getValue());
        }
        
        // ## 6. Thread Safety Demonstration (Conceptual) ##
        // To truly see the effect of synchronization, you would need to run this code with
        // multiple threads trying to modify the Hashtable simultaneously. The Hashtable
        // would prevent data corruption by ensuring only one thread modifies it at a time.
        // In contrast, using a HashMap in a multi-threaded context without external
        // synchronization could lead to an inconsistent state or infinite loops.
        System.out.println("\n6. Thread Safety:");
        System.out.println("Hashtable is inherently thread-safe. All its methods like put() and get() are synchronized.");
        System.out.println("For modern applications, java.util.concurrent.ConcurrentHashMap is preferred for better performance.");

    }
}

/*
 ====================================================================================================
 |                                 WHEN TO USE A HASHTABLE?                                         |
 ====================================================================================================

 A `Hashtable` should primarily be used when you are working with **legacy code** that already uses it
 or has an API that requires it. For all new development, there are almost always better alternatives.
 Its simple, heavy-handed synchronization (locking the entire map for every operation) is a major
 performance bottleneck in concurrent applications.

 Consider these scenarios:
 - **Legacy Systems**: If you are maintaining or interfacing with an older Java application or
   library that was built before the Java Collections Framework (pre-Java 1.2), you may encounter
   and need to use `Hashtable`.
 - **Simple Thread-Safety Requirement (with caution)**: If you need a quick and simple thread-safe map
   and performance is not a critical concern (e.g., a low-traffic, read-heavy map for application-wide
   settings), a `Hashtable` *could* work. However, this is strongly discouraged.

 For modern applications, the recommended alternatives are:
 - **For non-threaded use**: Use `HashMap`. It is faster because it lacks synchronization overhead.
 - **For threaded use**: Use `java.util.concurrent.ConcurrentHashMap`. It is the modern, high-performance,
   thread-safe map. It provides much better scalability by using sophisticated techniques like lock
   striping, which allows multiple threads to read and write to the map concurrently without blocking each other.

 In summary, unless you have a specific reason tied to legacy code, you should **avoid using `Hashtable`**
 in favor of `HashMap` or `ConcurrentHashMap`.

*/