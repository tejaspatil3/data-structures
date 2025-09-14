// File: EnumMapExplained.java

/*
 ====================================================================================================
 |                                  THEORY OF ENUMMAP IN JAVA                                       |
 ====================================================================================================

 ## 1. What is an EnumMap?

 An **EnumMap** is a highly specialized and extremely efficient `Map` implementation designed to be used
 exclusively with keys that are of an **enum type**. It provides a memory-efficient and high-performance
 alternative to using a `HashMap` when your keys are from a single enum.

 ## 2. Underlying Implementation: A Simple Array! 🚀

 Unlike `HashMap` or `Hashtable`, an `EnumMap` is **not a hash table**. Internally, it is backed by a
 simple, compact **array**. This is the secret to its superior performance.

 - **Direct Indexing**: Every enum constant has a unique, non-negative integer value returned by its
   `ordinal()` method (the first constant declared is 0, the second is 1, and so on).
 - **Array Mapping**: The `EnumMap` uses this ordinal value as a direct index into its internal array.
   The value is stored at `internalArray[key.ordinal()]`.
 - **No Hashing, No Collisions**: Because it uses direct indexing, there is no need to compute hash
   codes, no risk of collisions, and no need for complex data structures like linked lists or trees.

 The operation `map.put(Day.MONDAY, "Task 1")` is as fast as a simple array write:
 `tasks[0] = "Task 1";` (assuming `Day.MONDAY.ordinal()` is 0).

 

 ## 3. Key Characteristics and Advantages

 - **Extreme Performance**: All basic operations (`put`, `get`, `containsKey`, `remove`) take
   **guaranteed O(1) constant time**. This is even faster than `HashMap`'s *amortized* O(1) time
   because there is never a need to resize the underlying data structure.
 - **Memory Efficiency**: It is very compact. The memory footprint is just a small object header
   plus an array whose size is exactly the number of constants in the enum. This is significantly
   less memory than a `HashMap` would use for the same number of entries.
 - **Naturally Ordered**: The map is always ordered according to the **natural order** of the enum
   keys (i.e., the order in which the enum constants are declared). Iterators for `keySet()`,
   `values()`, and `entrySet()` will always reflect this order.
 - **Type Safety**: The constructor requires you to pass the `Class` object of the enum key (e.g.,
   `Day.class`). This ensures, at compile time, that only keys of that specific enum type can be
   inserted.
 - **No `null` Keys**: `EnumMap` does not permit `null` keys. Attempting to insert one will result in
   a `NullPointerException`. It does, however, allow `null` values.

 ---

 ## 4. EnumMap vs. HashMap

 | Feature            | EnumMap                                        | HashMap                                             |
 |--------------------|------------------------------------------------|-----------------------------------------------------|
 | **Key Type** | A single, specific `enum` type only.           | Any object type.                                    |
 | **Performance** | Extremely fast, true O(1).                     | Fast, amortized O(1).                               |
 | **Memory Usage** | Very low.                                      | Higher, due to hash table overhead.                 |
 | **Ordering** | Ordered by the enum's natural declaration order. | Unordered.                                          |
 | **Implementation** | A simple array.                                | A hash table (array of linked lists/trees).         |

 ====================================================================================================
 |                                 BUILT-IN ENUMMAP FUNCTIONS                                       |
 ====================================================================================================

 `EnumMap` implements the `Map` interface, so it offers all the standard map methods.

 ### Primary Constructor
 - `EnumMap(Class<K> keyType)`: The constructor used to create an `EnumMap`. You **must** provide the
   `.class` literal of the enum that will be used for keys.

 ### Core Operations
 - `V put(K key, V value)`: Associates a value with an enum key.
 - `V get(Object key)`: Returns the value for the specified enum key.
 - `V remove(Object key)`: Removes the mapping for an enum key.
 - `boolean containsKey(Object key)`: Checks if a mapping for the enum key exists.
 - `int size()`: Returns the number of key-value mappings.
 - `void clear()`: Removes all mappings.

 ### View Collections (all ordered by the enum's natural order)
 - `Set<K> keySet()`: Returns a `Set` of the enum keys.
 - `Collection<V> values()`: Returns a `Collection` of the values.
 - `Set<Map.Entry<K, V>> entrySet()`: Returns a `Set` of the key-value mappings.

*/

import java.util.EnumMap;
import java.util.Map;

// First, we need an enum to use as the key for our EnumMap.
enum DayOfWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

/**
 * This class demonstrates the theory and practical usage of EnumMap in Java.
 * All the detailed theory is provided in the multi-line comment block at the top of this file.
 */
public class EnumMapExplained {

    public static void in(String[] args) {
        System.out.println("--- EnumMap Demonstration ---");

        // ## 1. Creating an EnumMap ##
        // You MUST pass the enum's Class object to the constructor.
        // This map will store tasks for each day of the week.
        EnumMap<DayOfWeek, String> weeklyTasks = new EnumMap<>(DayOfWeek.class);


        // ## 2. put(key, value) - Adding elements ##
        System.out.println("\n1. Adding tasks to the map:");
        weeklyTasks.put(DayOfWeek.MONDAY, "Team meeting at 10 AM");
        weeklyTasks.put(DayOfWeek.WEDNESDAY, "Deploy new feature");
        weeklyTasks.put(DayOfWeek.FRIDAY, "Submit weekly report");

        // The map is naturally ordered according to the enum declaration.
        System.out.println("Current tasks: " + weeklyTasks);


        // ## 3. get(key) - Accessing elements ##
        System.out.println("\n2. Getting a specific task:");
        String wednesdayTask = weeklyTasks.get(DayOfWeek.WEDNESDAY);
        System.out.println("Task for Wednesday: " + wednesdayTask);

        // Getting a key that doesn't exist returns null.
        String tuesdayTask = weeklyTasks.get(DayOfWeek.TUESDAY);
        System.out.println("Task for Tuesday: " + tuesdayTask); // null


        // ## 4. containsKey(key) and size() ##
        System.out.println("\n3. Checking map properties:");
        boolean hasFridayTask = weeklyTasks.containsKey(DayOfWeek.FRIDAY);
        System.out.println("Is there a task for Friday? " + hasFridayTask); // true
        System.out.println("Total number of tasks scheduled: " + weeklyTasks.size()); // 3


        // ## 5. Iterating over an EnumMap ##
        // Iteration always follows the enum's natural order (MONDAY, TUESDAY, ...).
        System.out.println("\n4. Iterating over all entries (natural order is guaranteed):");
        for (Map.Entry<DayOfWeek, String> entry : weeklyTasks.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }


        // ## 6. Removing an element ##
        System.out.println("\n5. Removing an element:");
        weeklyTasks.remove(DayOfWeek.MONDAY);
        System.out.println("Tasks after removing Monday's task: " + weeklyTasks);
    }
}

/*
 ====================================================================================================
 |                                 WHEN TO USE AN ENUMMAP?                                          |
 ====================================================================================================

 An `EnumMap` is the definitive choice whenever you need to associate data with the constants of an
 enum. Its combination of superior performance, memory efficiency, and natural ordering makes it the
 optimal tool for these scenarios, far surpassing what a `HashMap` can offer. If your map's keys
 will all come from a single enum type, you should always prefer `EnumMap`.

 Common use cases include:

 - **Strategy Pattern Implementation**: Mapping enum constants (representing different algorithms or
   strategies, e.g., `CompressionType.ZIP`, `CompressionType.RAR`) to their corresponding strategy
   objects. This allows for clean, fast lookups of the correct implementation.

 - **State-Specific Data**: Storing data related to the states in a state machine. For example,
   mapping an `OrderStatus` enum (`PENDING`, `PROCESSING`, `SHIPPED`) to a descriptive string, an
   icon, or a handler object responsible for that state.

 - **Configuration Management**: Storing configuration values where the configuration keys are a
   fixed, predefined set represented by an enum. For example, mapping a `LogLevel` enum (`DEBUG`,
   `INFO`, `WARN`, `ERROR`) to a boolean indicating whether that level is enabled.

 - **Replacing `switch` Statements**: Instead of a verbose `switch` statement on an enum value to
   determine an action, you can populate an `EnumMap` with `Runnable` actions or `Function` objects
   and simply retrieve and execute the correct one. This is often cleaner and more extensible.

*/