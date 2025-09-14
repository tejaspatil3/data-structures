// File: HashMapExplained.java

/*
 ====================================================================================================
 |                                  THEORY OF HASHMAP IN JAVA                                     |
 ====================================================================================================

 ## What is a HashMap?

 A HashMap is a part of Java's collection framework and is located in the `java.util` package.
 It implements the `Map` interface and stores data in **key-value pairs**.
 Key Features:
 - **Unordered**: It does not guarantee the order of its elements. The order can change over time.
 - **No Duplicate Keys**: Each key in a HashMap must be unique. If you try to insert a key that
   already exists, the old value associated with that key will be replaced with the new value.
 - **Allows Nulls**: It allows one `null` key and multiple `null` values.
 - **Not Synchronized**: It is not thread-safe. For a thread-safe alternative, use `ConcurrentHashMap`.
 - **Performance**: It offers constant-time performance, $O(1)$, for basic operations like `get()` and
   `put()` on average, assuming the hash function disperses elements properly among the buckets.

 ---

 ## How HashMap Works Internally (The Hash Table)

 A HashMap uses a hash table as its underlying data structure. A hash table is essentially an
 array of "buckets". Each bucket can store one or more key-value pairs.

 The process of adding or retrieving an element involves these steps:

 ### Step 1: Hashing (Calculating the Hash Code)
 When you perform an operation like `put(K key, V value)`, Java first calls the `hashCode()`
 method on the `key` object. This method returns an integer value, known as the hash code.
 This hash code is a numerical representation of the key.

 ### Step 2: Index Calculation
 The HashMap needs to decide which bucket (i.e., which index in the internal array) to store
 the key-value pair in. It uses the hash code to calculate this index.
 A simple (but not the exact) way to think about it is:
 `index = hashCode() % array.length`
 The actual implementation in Java is more sophisticated to ensure a better distribution
 of keys across the array, but the principle is the same: convert the hash code into a
 valid array index.

 ### Step 3: Collision Handling
 A **collision** occurs when two different keys produce the same index. For example, `key1.hashCode()`
 and `key2.hashCode()` might result in the same index after the index calculation.
 HashMap handles collisions using a technique called **Separate Chaining**.

 - **Linked List**: Initially, each bucket is a pointer to a linked list. If a collision
   occurs, the new key-value pair (as a `Node` object) is simply added to the end of the
   linked list at that bucket's index.

 - **Retrieval during Collision**: When you call `get(key)`, the HashMap first calculates the
   index. It then goes to that bucket and traverses the linked list, using the `key.equals()`
   method to compare the provided key with the key of each node in the list until it
   finds a match. This is why it's crucial to have a proper `equals()` and `hashCode()`
   implementation for your key objects.

 ### Step 4: Performance Improvement (Treeify)
 In a poorly designed hash function, many keys could end up in the same bucket, turning the
 linked list into a very long chain. In this worst-case scenario, the lookup time degrades
 from $O(1)$ to $O(n)$, where `n` is the number of entries in the map.

 To prevent this, since Java 8, if the number of entries in a single bucket's linked list
 reaches a certain threshold (**TREEIFY_THRESHOLD**, default is 8), the linked list is
 converted into a **Balanced Tree** (specifically, a Red-Black Tree).
 This improves the worst-case search time for that bucket from $O(n)$ to $O(\log n)$.

 

 ---

 ## Important Internal Concepts

 ### Load Factor
 - **Definition**: A measure of how full the hash table is allowed to get before its capacity
   is automatically increased. It's a value between 0.0 and 1.0.
 - **Default Value**: 0.75 (or 75%).
 - **How it works**: When the number of entries in the map exceeds `(capacity * load factor)`,
   the map is rehashed.
 - **Example**: If capacity is 16 and load factor is 0.75, rehashing will occur when the
   13th element (`16 * 0.75 = 12`) is added.

 ### Initial Capacity
 - **Definition**: The initial number of buckets in the hash table when it's created.
 - **Default Value**: 16.
 - **Impact**: If you know you'll be storing many items, setting a higher initial capacity
   can prevent the overhead of multiple rehashing operations.

 ### Rehashing
 - **Definition**: The process of resizing the internal array.
 - **When it happens**: When the number of entries surpasses the `(capacity * load factor)` threshold.
 - **What happens**: A new array is created (usually double the size of the old one). All
   existing key-value pairs are then iterated over, their hash codes are recalculated based
   on the new capacity, and they are placed into new buckets in the new, larger array.
 - **Cost**: Rehashing is an expensive operation, so it's good to initialize the HashMap with
   an appropriate capacity if the number of elements is known in advance.

 ====================================================================================================
 |                                 BUILT-IN HASHMAP FUNCTIONS                                     |
 ====================================================================================================

 ### Core Operations
 - `V put(K key, V value)`: Associates the specified value with the specified key.
 - `V get(Object key)`: Returns the value to which the specified key is mapped, or `null` if the map contains no mapping for the key.
 - `V remove(Object key)`: Removes the mapping for a key from this map if it is present.
 - `boolean containsKey(Object key)`: Returns `true` if this map contains a mapping for the specified key.
 - `boolean containsValue(Object value)`: Returns `true` if this map maps one or more keys to the specified value.
 - `int size()`: Returns the number of key-value mappings in this map.
 - `boolean isEmpty()`: Returns `true` if this map contains no key-value mappings.
 - `void clear()`: Removes all of the mappings from this map.

 ### Bulk Operations / Views
 - `Set<K> keySet()`: Returns a `Set` view of the keys contained in this map.
 - `Collection<V> values()`: Returns a `Collection` view of the values contained in this map.
 - `Set<Map.Entry<K, V>> entrySet()`: Returns a `Set` view of the mappings (key-value pairs) contained in this map.

 ### Java 8+ Additions
 - `V getOrDefault(Object key, V defaultValue)`: Returns the value for the key, or `defaultValue` if the key is not found.
 - `V putIfAbsent(K key, V value)`: If the specified key is not already associated with a value, associates it with the given value.
 - `boolean remove(Object key, Object value)`: Removes the entry for the specified key only if it is currently mapped to the specified value.
 - `V replace(K key, V value)`: Replaces the entry for the specified key only if it is currently mapped to some value.
 - `void forEach(BiConsumer<? super K, ? super V> action)`: Performs the given action for each entry in this map.

*/

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Collection;

/**
 * This class demonstrates the theory and practical usage of HashMap in Java.
 * All the detailed theory is provided in the multi-line comment block at the top of this file.
 */
public class HashMapExplained {

    public static void main(String[] args) {
        System.out.println("--- HashMap Demonstration ---");

        // ## Creating a HashMap ##
        // Creating a HashMap to store student names (String) and their scores (Integer).
        HashMap<String, Integer> studentScores = new HashMap<>();


        // ## 1. put(key, value) - Adding elements ##
        // This operation adds a key-value pair to the map.
        // If the key already exists, it updates the value.
        System.out.println("\n1. Adding elements with put():");
        studentScores.put("Alice", 95);
        studentScores.put("Bob", 82);
        studentScores.put("Charlie", 98);
        studentScores.put("David", 76);
        System.out.println("Initial Map: " + studentScores);

        // Updating an existing key's value
        studentScores.put("Bob", 85); // Bob's score is updated
        System.out.println("Map after updating Bob's score: " + studentScores);


        // ## 2. get(key) - Accessing elements ##
        // Retrieves the value associated with a given key. Returns null if the key is not found.
        System.out.println("\n2. Accessing elements with get():");
        Integer alicesScore = studentScores.get("Alice");
        System.out.println("Alice's score is: " + alicesScore);
        Integer eveScore = studentScores.get("Eve"); // "Eve" is not in the map
        System.out.println("Eve's score is: " + eveScore); // This will print null


        // ## 3. remove(key) - Removing elements ##
        // Removes the key-value pair for the specified key.
        System.out.println("\n3. Removing elements with remove():");
        studentScores.remove("David");
        System.out.println("Map after removing David: " + studentScores);


        // ## 4. containsKey(key) - Checking for a key ##
        // Returns true if the map contains the specified key.
        System.out.println("\n4. Checking for a key with containsKey():");
        boolean hasCharlie = studentScores.containsKey("Charlie");
        System.out.println("Does the map contain the key 'Charlie'? " + hasCharlie); // true
        boolean hasDavid = studentScores.containsKey("David");
        System.out.println("Does the map contain the key 'David'? " + hasDavid); // false


        // ## 5. containsValue(value) - Checking for a value ##
        // Returns true if the map contains the specified value. Can be slower as it may need to check all values.
        System.out.println("\n5. Checking for a value with containsValue():");
        boolean hasPerfectScore = studentScores.containsValue(100);
        System.out.println("Does any student have a score of 100? " + hasPerfectScore); // false
        boolean hasScore98 = studentScores.containsValue(98);
        System.out.println("Does any student have a score of 98? " + hasScore98); // true


        // ## 6. size() - Getting the map size ##
        // Returns the number of key-value pairs in the map.
        System.out.println("\n6. Getting the map size with size():");
        System.out.println("Number of students in the map: " + studentScores.size());


        // ## 7. isEmpty() & clear() ##
        System.out.println("\n7. Checking if empty with isEmpty() and clearing with clear():");
        System.out.println("Is the map empty? " + studentScores.isEmpty()); // false
        // studentScores.clear(); // Uncomment this line to empty the map
        // System.out.println("Map after clearing: " + studentScores);
        // System.out.println("Is the map empty now? " + studentScores.isEmpty()); // true


        // ## 8. Iterating over a HashMap ##
        System.out.println("\n8. Different ways to iterate over the map:");

        // ### A. Using keySet() to iterate over keys ###
        System.out.println("\n   --> Iterating over keys:");
        Set<String> studentNames = studentScores.keySet();
        for (String name : studentNames) {
            System.out.println("Key: " + name + ", Value: " + studentScores.get(name));
        }

        // ### B. Using values() to iterate over values ###
        System.out.println("\n   --> Iterating over values:");
        Collection<Integer> scores = studentScores.values();
        for (Integer score : scores) {
            System.out.println("Score: " + score);
        }

        // ### C. Using entrySet() to iterate over key-value pairs (Most efficient) ###
        System.out.println("\n   --> Iterating over entries (key-value pairs):");
        Set<Map.Entry<String, Integer>> entries = studentScores.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println("Student: " + entry.getKey() + ", Score: " + entry.getValue());
        }


        // ## 9. Java 8+ Helper Functions ##
        System.out.println("\n9. Java 8+ helper functions:");

        // ### getOrDefault(key, defaultValue) ###
        // Useful to avoid NullPointerExceptions.
        int bobScore = studentScores.getOrDefault("Bob", 0); // Bob exists, returns 85
        int frankScore = studentScores.getOrDefault("Frank", 0); // Frank doesn't exist, returns default value 0
        System.out.println("getOrDefault -> Bob's score: " + bobScore);
        System.out.println("getOrDefault -> Frank's score: " + frankScore);

        // ### putIfAbsent(key, value) ###
        // Adds the entry only if the key is not already present.
        studentScores.putIfAbsent("Alice", 100); // Alice is already a key, so this does nothing.
        studentScores.putIfAbsent("Frank", 88); // Frank is not a key, so this adds him to the map.
        System.out.println("putIfAbsent -> Map after trying to add Alice and Frank: " + studentScores);

        // ### replace(key, newValue) ###
        // Replaces the value for a given key.
        studentScores.replace("Frank", 90);
        System.out.println("replace -> Map after replacing Frank's score: " + studentScores);

        // ### forEach with Lambda ###
        System.out.println("forEach -> Printing map with a lambda function:");
        studentScores.forEach((name, score) -> {
            System.out.println(name + " has a score of " + score);
        });
    }
}

/*
 ====================================================================================================
 |                                 WHEN TO USE A HASHMAP                                            |
 ====================================================================================================

 A HashMap is an excellent choice when your problem involves requiring fast access, insertion, and
 deletion of data based on a unique identifier (the key). Its average $O(1)$ time complexity for
 these operations makes it incredibly efficient for "lookup" or "dictionary" type problems. You
 should use a HashMap whenever you need to map unique keys to values and the order of the items
 does not matter.

 Common use cases include:
 - **Frequency Counting**: Counting the occurrences of items in a collection. For example, counting
   how many times each word appears in a book. The word would be the key, and its count would be the value.
 - **Caching**: Storing the results of expensive operations to avoid re-computing them. For instance,
   caching database query results where the query is the key and the result set is the value.
 - **Indexing Data**: Creating an index for quick retrieval. For example, a system might store user
   data in a list, but for fast access, it could use a HashMap to map a unique `userID` (key) to
   the user object (value).
 - **Implementing other Data Structures**: HashMaps are fundamental in computer science and can be
   used to build other structures, like the adjacency list for a graph, where each vertex (key)
   maps to a list of its neighbors (value).
 - **Configuration Settings**: Storing application settings or properties where the property name is
   the key and its setting is the value.

*/