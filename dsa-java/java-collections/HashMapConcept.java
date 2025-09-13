

// Java HashMap
// In the ArrayList chapter, you learned that Arrays store items as an ordered collection, and you have to access them with an index number (int type). A HashMap however, store items in "key/value" pairs, and you can access them by an index of another type (e.g. a String).

// One object is used as a key (index) to another object (value). It can store different types: String keys and Integer values, or the same type, like: String keys and String values:

// Import the HashMap and Map classes from the java.util package.


import java.util.HashMap;
import java.util.Map;

public class HashMapConcept {

    public static void main(String[] args) {

        /*
        --------------------------
        -- THEORY: WHAT IS A HASHMAP? --
        --------------------------

        1.  **Key-Value Pairs**: A HashMap is a data structure that stores items in "key-value" pairs. 🔑
            You use a unique 'key' to store a 'value', and you use that same key to retrieve the value later.
            Think of it like a dictionary where the 'key' is the word and the 'value' is its definition.

        2.  **Hashing Mechanism**: It uses a technique called hashing. When you add a pair, the HashMap uses the key's
            `hashCode()` method to calculate an index where the value should be stored. This makes retrieving data
            extremely fast, typically in constant time O(1) on average.

        3.  **Part of Collections Framework**: It's part of the Java Collections Framework and implements the Map interface.

        4.  **Key Characteristics**:
            * **Unordered**: A HashMap does NOT guarantee any specific order for its elements. The order can even change over time.
            * **Unique Keys**: Keys must be unique. If you try to add a key that already exists, the old value associated with that key will be overwritten with the new value.
            * **Allows One Null Key**: You can have one key that is `null`.
            * **Allows Multiple Null Values**: You can store the `null` value multiple times with different keys.
            * **Not Synchronized**: It is not thread-safe. For a thread-safe alternative, consider using `ConcurrentHashMap`.

        5.  **Generics (`<K, V>`)**:
            * When you create a HashMap, you must specify the data types for both the Key (`K`) and the Value (`V`).
            * For example, `HashMap<String, Integer>` creates a map where keys are Strings and values are Integers.
            * This provides type-safety, ensuring you don't mix up data types, which helps catch errors at compile-time.
            * Like ArrayList, you must use wrapper classes for primitive types (e.g., `Integer` for `int`, `Character` for `char`).

        6.  **Common Methods**:
            * `put(key, value)`: Associates the specified value with the specified key.
            * `get(key)`: Returns the value to which the specified key is mapped, or `null` if the map contains no mapping for the key.
            * `remove(key)`: Removes the mapping for a key from this map if it is present.
            * `containsKey(key)`: Returns `true` if this map contains a mapping for the specified key.
            * `containsValue(value)`: Returns `true` if this map maps one or more keys to the specified value.
            * `size()`: Returns the number of key-value mappings in this map.
            * `keySet()`: Returns a Set view of the keys contained in this map. Used for iteration.
            * `values()`: Returns a Collection view of the values contained in this map.
            * `entrySet()`: Returns a Set view of the key-value mappings (entries). This is the most efficient way to iterate over both keys and values.
        */


        // --------------------------
        // -- EXAMPLE: A PRACTICAL DEMONSTRATION --
        // --------------------------

        // 1. **Creating a HashMap**
        // This HashMap will store student names (String) as keys and their scores (Integer) as values.
        Map<String, Integer> studentScores = new HashMap<>();
        System.out.println("1. Created an empty HashMap.");

        // 2. **Adding key-value pairs** using the put() method.
        studentScores.put("Alice", 95);
        studentScores.put("Bob", 82);
        studentScores.put("Charlie", 99);
        studentScores.put("David", 76);
        System.out.println("2. After adding elements: " + studentScores);

        // 3. **Overwriting a value**
        // If you `put` a key that already exists, it updates the value.
        studentScores.put("Bob", 85); // Bob's score is now updated.
        System.out.println("3. After updating Bob's score: " + studentScores);

        // 4. **Accessing a value** using the get() method.
        // You provide the key to get its corresponding value.
        Integer aliceScore = studentScores.get("Alice");
        System.out.println("4. Alice's score is: " + aliceScore);

        // 5. **Removing a pair** using the remove() method.
        studentScores.remove("David");
        System.out.println("5. After removing David: " + studentScores);

        // 6. **Checking for existence**
        boolean hasCharlie = studentScores.containsKey("Charlie");
        boolean hasScore100 = studentScores.containsValue(100);
        System.out.println("6. Does the map contain the key 'Charlie'? " + hasCharlie);
        System.out.println("   Does the map contain the value 100? " + hasScore100);

        // 7. **Getting the size** of the HashMap.
        System.out.println("7. The number of students in the map is: " + studentScores.size());

        // 8. **Iterating through the HashMap**
        // The most efficient way is to iterate over the entrySet.
        System.out.println("8. Iterating through all students and scores:");
        for (Map.Entry<String, Integer> entry : studentScores.entrySet()) {
            String studentName = entry.getKey();
            Integer score = entry.getValue();
            System.out.println("   - " + studentName + " has a score of: " + score);
        }

        // You can also iterate over just keys or just values.
        System.out.println("\n   Just the student names (keys): " + studentScores.keySet());
        System.out.println("   Just the scores (values): " + studentScores.values());

        // 9. **Clearing the HashMap**
        studentScores.clear();
        System.out.println("\n9. After clearing the map, its size is: " + studentScores.size());
        System.out.println("   The map is now empty: " + studentScores);
    }
}
