// File: LinkedHashMapExplained.java

/*
 ====================================================================================================
 |                                 THEORY OF LINKEDHASHMAP IN JAVA                                  |
 ====================================================================================================

 ## What is a LinkedHashMap?

 A `LinkedHashMap` is a direct subclass of `HashMap`. It provides all the functionality of a `HashMap`
 with one major addition: **it maintains a predictable iteration order**.
 Key Features:
 - **Ordered**: It maintains a doubly-linked list running through all its entries, which defines the
   iteration ordering.
 - **Extends HashMap**: It inherits the hash table structure from `HashMap`, providing fast,
   constant-time performance $O(1)$ on average for `put`, `get`, and `remove` operations.
 - **Slightly More Overhead**: The cost of maintaining the linked list adds a small overhead to
   insertion and removal operations compared to `HashMap`.
 - **Faster Iteration**: Iteration is faster than in `HashMap` because it traverses the internal
   linked list instead of the underlying array of buckets.

 ---

 ## How LinkedHashMap Works Internally

 `LinkedHashMap` combines two data structures:
 1.  **Hash Table**: The same array of buckets used by `HashMap` for fast key lookups.
 2.  **Doubly-Linked List**: A linked list that connects all the entries in the map.

 

 When an operation occurs:
 - **`put(key, value)`**: The key-value pair is first inserted into the hash table just like in a
   `HashMap`. Then, this new entry is also added to the **end** of the doubly-linked list.
 - **`get(key)`**: The lookup is performed using the hash table, providing the same $O(1)$ average
   performance as `HashMap`.
 - **Iteration**: When you iterate over the map (e.g., using `keySet()` or `entrySet()`), it simply
   traverses the doubly-linked list from its head to its tail, providing the elements in a
   predictable sequence.

 ---

 ## Ordering Modes

 `LinkedHashMap` supports two different ordering modes, which can be specified during construction.

 ### 1. Insertion Order (Default)
 This is the default behavior. The entries in the map are ordered based on the sequence in which
 their keys were **first inserted**. If you re-insert a key that already exists (updating its value),
 the original insertion order is preserved.

 ### 2. Access Order
 This mode can be enabled by using a special constructor: `new LinkedHashMap<>(capacity, loadFactor, true)`.
 In this mode, the iteration order reflects the sequence in which entries were **last accessed**.
 Whenever an entry is accessed (via `get()`, `put()`, or `putIfAbsent()`), it is moved to the
 **end** of the linked list. The element at the head of the list is therefore the "least recently accessed"
 one. This behavior is extremely useful for building **LRU (Least Recently Used) Caches**.

 ---

 ## The `removeEldestEntry()` Method

 To facilitate the creation of caches, `LinkedHashMap` provides a protected method:
 `protected boolean removeEldestEntry(Map.Entry<K,V> eldest)`
 - This method is called by `put()` and `putAll()` after a new entry is added.
 - By default, it returns `false`, so the map grows indefinitely.
 - You can override this method in a subclass to implement a custom removal policy. For an LRU
   cache, you would override it to return `true` when the map's size exceeds its capacity, which
   causes the "eldest" (least recently used) entry to be automatically removed.

 ====================================================================================================
 |                              BUILT-IN LINKEDHASHMAP FUNCTIONS                                  |
 ====================================================================================================

 `LinkedHashMap` inherits all the methods from `HashMap`, including:
 - `put(K key, V value)`
 - `get(Object key)`
 - `remove(Object key)`
 - `containsKey(Object key)`
 - `size()`
 - `keySet()`, `values()`, `entrySet()`

 ### Special Constructors
 - `LinkedHashMap()`: Creates a `LinkedHashMap` with default capacity, load factor, and **insertion order**.
 - `LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder)`:
   The key constructor. Setting `accessOrder` to `true` enables **access order**.

 ### Method for Caching
 - `protected boolean removeEldestEntry(Map.Entry<K,V> eldest)`: A hook method that allows the
   subclass to define a condition for removing the oldest entry.

*/

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class demonstrates the theory and practical usage of LinkedHashMap in Java.
 * All the detailed theory is provided in the multi-line comment block at the top of this file.
 */
public class LinkedHashMapExplained {

    public static void main(String[] args) {

        System.out.println("--- 1. Insertion-Order Demonstration (Default) ---");
        // By default, LinkedHashMap maintains insertion order.
        Map<String, String> config = new LinkedHashMap<>();
        config.put("database.url", "jdbc:mysql://localhost/db");
        config.put("username", "admin");
        config.put("password", "secret");
        config.put("timeout", "3000");

        System.out.println("Initial map (order is preserved):");
        config.forEach((key, value) -> System.out.println(key + " = " + value));

        // Accessing an element does NOT change the order in insertion-order mode.
        System.out.println("\nAccessing 'username': " + config.get("username"));
        System.out.println("Order remains the same after access:");
        config.forEach((key, value) -> System.out.println(key + " = " + value));

        // Updating a value does NOT change the order either.
        config.put("timeout", "5000");
        System.out.println("\nOrder remains the same after updating 'timeout':");
        config.forEach((key, value) -> System.out.println(key + " = " + value));


        System.out.println("\n\n--- 2. Access-Order Demonstration ---");
        // Create a LinkedHashMap in access-order mode by passing 'true' as the last argument.
        Map<Integer, String> accessOrderMap = new LinkedHashMap<>(16, 0.75f, true);
        accessOrderMap.put(1, "One");
        accessOrderMap.put(2, "Two");
        accessOrderMap.put(3, "Three");
        accessOrderMap.put(4, "Four");

        System.out.println("Initial access-order map: " + accessOrderMap.keySet());

        // Access the element with key 2. It will move to the end of the list.
        System.out.println("Accessing value for key 2: " + accessOrderMap.get(2));
        System.out.println("Keys after accessing 2 (2 is now last): " + accessOrderMap.keySet());

        // Access the element with key 1. It will now move to the end.
        System.out.println("Accessing value for key 1: " + accessOrderMap.get(1));
        System.out.println("Keys after accessing 1 (1 is now last): " + accessOrderMap.keySet());


        System.out.println("\n\n--- 3. LRU Cache Implementation Example ---");
        // We use our custom LRUCache class defined below.
        LRUCache<Integer, String> cache = new LRUCache<>(3); // Cache capacity is 3

        System.out.println("Cache initialized with capacity 3.");
        cache.put(10, "A");
        cache.put(20, "B");
        cache.put(30, "C");
        System.out.println("Cache state after adding 3 items: " + cache.keySet());

        // Accessing item 10 makes it the most recently used.
        cache.get(10);
        System.out.println("Cache state after accessing 10: " + cache.keySet());

        // Adding a 4th item (40) should evict the least recently used item (20).
        System.out.println("Adding item 40...");
        cache.put(40, "D");
        System.out.println("Cache state after adding 40 (item 20 was removed): " + cache.keySet());
    }
}

/**
 * A simple implementation of an LRU (Least Recently Used) Cache using LinkedHashMap.
 * @param <K> The type of keys maintained by this cache.
 * @param <V> The type of mapped values.
 */
class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    /**
     * Constructs an LRUCache with a specified capacity.
     * @param capacity The maximum number of items the cache can hold.
     */
    public LRUCache(int capacity) {
        // Call the LinkedHashMap constructor with accessOrder = true
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    /**
     * This method is called by put() and putAll(). We override it to implement
     * the cache eviction policy. It returns true if the eldest entry should be
     * removed, which we do when the size exceeds the capacity.
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // Return true to remove the least recently used entry
        return size() > capacity;
    }
}


/*
 ====================================================================================================
 |                               WHEN TO USE A LINKEDHASHMAP                                        |
 ====================================================================================================

 A `LinkedHashMap` is the perfect choice when you need the fast $O(1)$ lookups of a `HashMap` but also
 require that the order of elements is preserved and predictable. Its dual nature as a hash table
 and a linked list makes it uniquely suited for specific problems where order matters.

 Common use cases include:
 - **Maintaining Insertion Order**: If you are reading configuration data from a file and need to
   write it back in the same order, a `LinkedHashMap` is ideal. It ensures that iterating through
   the map will always yield keys in the same sequence they were inserted.

 - **Building Caches (LRU Cache)**: This is one of the most powerful applications of `LinkedHashMap`.
   By enabling **access order**, the map automatically keeps track of which items were used most
   recently. This allows you to easily implement a Least Recently Used (LRU) cache, where you can
   automatically evict the oldest, unused items when the cache reaches its capacity. This is
   useful for caching expensive resources like database connections, file handles, or network requests.

 - **Ordered Data Processing**: In situations where you process data and need to remember the
   original sequence, such as parsing JSON objects where field order might be significant, or
   building a playlist where song order must be maintained while still allowing quick lookups by song ID.

 In short, choose **`HashMap`** when order doesn't matter, and choose **`LinkedHashMap`** when you need
 fast lookups *and* a consistent iteration order.

*/