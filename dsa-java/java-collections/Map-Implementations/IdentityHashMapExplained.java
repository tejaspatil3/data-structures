// File: IdentityHashMapExplained.java

/*
 ====================================================================================================
 |                                THEORY OF IDENTITYHASHMAP IN JAVA                                 |
 ====================================================================================================

 ## 1. What is an IdentityHashMap?

 An `IdentityHashMap` is a specialized implementation of the `Map` interface that intentionally
 violates the `Map`'s general contract. Instead of comparing keys using the standard `equals()`
 method (value equality), it uses the `==` operator (**reference equality**).

 This means two keys are considered equal only if they are the **exact same object** in memory.

 ## 2. The Core Concept: Reference Equality (`==`) vs. Value Equality (`.equals()`)

 This distinction is the entire reason `IdentityHashMap` exists.

 ### A. Value Equality (`.equals()`) - Used by `HashMap`
 - This is the standard for object comparison in Java collections.
 - `key1.equals(key2)` returns `true` if two objects are considered "meaningfully" the same,
   according to the logic defined in their class.
 - **Example**: For `String s1 = new String("test");` and `String s2 = new String("test");`,
   the expression `s1.equals(s2)` is **true**, because their character sequences are identical.

 ### B. Reference Equality (`==`) - Used by `IdentityHashMap`
 - This checks if two reference variables point to the **exact same memory location**.
 - `key1 == key2` is `true` only if `key1` and `key2` are aliases for the same object.
 - **Example**: For `String s1 = new String("test");` and `String s2 = new String("test");`,
   the expression `s1 == s2` is **false**, because `s1` and `s2` are two distinct objects
   in memory, even though their contents are the same.

 ---

 ## 3. How IdentityHashMap Works Internally

 `IdentityHashMap` is still a hash table, but with a twist:
 - **Hashing**: It does **not** use the key's `hashCode()` method. Instead, it computes a hash
   code using `System.identityHashCode(object)`. This method returns a hash code based on an
   object's memory address, which remains constant for the object's lifetime.
 - **Comparison**: When checking if a key already exists or retrieving a value, it compares keys
   using the `==` operator.

 This combination ensures that the map operates based on object identity rather than object value.

 ## 4. Key Characteristics

 - **Breaks Map Contract**: It's a special-purpose tool. Do not use it as a general replacement
   for `HashMap`, as its behavior is different from what most code expects from a `Map`.
 - **Performance**: It provides amortized O(1) time for basic operations (`put`, `get`). It can be
   faster than `HashMap` because `System.identityHashCode()` and `==` comparisons are often
   quicker than executing potentially complex `hashCode()` and `equals()` methods.
 - **Handles Mutable Keys**: It works correctly even with mutable keys whose `hashCode()` and
   `equals()` methods might change based on their state, because the identity hash code never
   changes for a given object.

 ====================================================================================================
 |                              BUILT-IN IDENTITYHASHMAP FUNCTIONS                                  |
 ====================================================================================================

 `IdentityHashMap` implements the `Map` interface and provides all the standard methods. The key
 is to remember that the *behavior* of these methods is different due to the use of reference
 equality for key comparisons.

 ### Core Operations
 - `V put(K key, V value)`: Associates a value with a key. A new entry is created if the key
   is not the *exact same instance* as any existing key.
 - `V get(Object key)`: Returns the value for a key, but only if the provided key is the
   *exact same instance* as the one in the map.
 - `boolean containsKey(Object key)`: Returns `true` only if the map contains the *exact
   same instance* as a key.
 - `V remove(Object key)`: Removes the entry for a key, but only if the provided key is the
   *exact same instance*.

*/

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * This class demonstrates the theory and practical usage of IdentityHashMap in Java.
 * All the detailed theory is provided in the multi-line comment block at the top of this file.
 */
public class IdentityHashMapExplained {

    public static void main(String[] args) {

        System.out.println("--- IdentityHashMap vs. HashMap Demonstration ---");

        // We create one of each type of map to compare their behavior.
        Map<Integer, String> identityMap = new IdentityHashMap<>();
        Map<Integer, String> hashMap = new HashMap<>();

        // Create two Integer objects that are "equal" in value but are DIFFERENT instances in memory.
        // NOTE: We use `new Integer()` instead of `Integer key1 = 100;` to avoid autoboxing,
        // which might cache the object and return the same instance, hiding the effect.
        Integer key1 = 100;
        Integer key2 = 100;

        System.out.println("Comparing keys...");
        System.out.println("Is key1 == key2? " + (key1 == key2));           // false -> Different objects
        System.out.println("Is key1.equals(key2)? " + key1.equals(key2)); // true  -> Same value

        System.out.println("\n--- Populating the maps with key1 ---");
        identityMap.put(key1, "IdentityHashMap: Value for key1");
        hashMap.put(key1, "HashMap: Value for key1");

        System.out.println("IdentityHashMap: " + identityMap);
        System.out.println("HashMap: " + hashMap);

        System.out.println("\n--- Accessing maps with key2 ---");

        // ## HashMap ## (Uses .equals())
        // Since key1.equals(key2) is true, HashMap considers them the same key.
        System.out.println("HashMap get(key2): " + hashMap.get(key2)); // Finds the value!

        // ## IdentityHashMap ## (Uses ==)
        // Since key1 != key2, IdentityHashMap considers them to be completely different keys.
        System.out.println("IdentityHashMap get(key2): " + identityMap.get(key2)); // Returns null!

        System.out.println("\n--- Adding key2 to IdentityHashMap ---");
        // Because key2 is a different object, it creates a new, separate entry.
        identityMap.put(key2, "IdentityHashMap: Value for key2");

        System.out.println("IdentityHashMap size: " + identityMap.size()); // Size is 2
        System.out.println("IdentityHashMap content: " + identityMap);
    }
}

/*
 ====================================================================================================
 |                             WHEN TO USE AN IDENTITYHASHMAP?                                      |
 ====================================================================================================

 An `IdentityHashMap` is a specialized tool for situations where you need to distinguish between
 objects based on their **memory location (identity)** rather than their **content (value)**. It's
 the right choice when two objects that might be `.equals()` to each other must be treated as
 distinct entities within your map.

 Common use cases include:

 - **Object Graph Traversal**: When performing algorithms like deep-copying, serialization, or
   cycle detection in an object graph, you need to keep track of the specific object *instances*
   you have already visited to avoid processing the same object twice or getting stuck in an
   infinite loop. An `IdentityHashMap` is perfect for mapping an object instance to a "visited"
   flag or its copied counterpart.

 - **Topology-Preserving Transformations**: Imagine you are cloning a complex data structure with
   nodes that point to each other. If two original nodes point to the *same* child object, you
   want their clones to also point to the *same* cloned child object. An `IdentityHashMap` can
   store the mapping from `originalObject -> clonedObject` to ensure these shared references are
   preserved correctly.

 - **Metadata Storage**: When you need to associate metadata with specific objects without modifying
   their classes. For example, mapping a UI component object to its current state or permissions,
   where different component instances should have different metadata even if they are structurally
-  identical.

*/