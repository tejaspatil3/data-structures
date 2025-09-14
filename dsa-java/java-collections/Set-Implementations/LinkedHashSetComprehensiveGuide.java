// File: LinkedHashSetComprehensiveGuide.java

/*
 * ======================================================================================
 * | THEORY: A Deep Dive into LinkedHashSet                                             |
 * ======================================================================================
 *
 * ## 1. Core Concept: The Best of Both Worlds 🤝
 *
 * LinkedHashSet is a public class that implements the `Set` interface and extends `HashSet`.
 * It's a fantastic hybrid data structure that combines the strengths of two other structures:
 *
 * 1.  **Hash Table (from HashSet)**: It uses a hash table internally to store its elements.
 * This provides the primary benefit of a `Set`: **storing only unique elements** and offering
 * very fast, near-constant time **O(1)** performance for adding, removing, and checking
 * for the existence of an element (`add()`, `remove()`, `contains()`).
 *
 * 2.  **Doubly-Linked List**: This is its key differentiator. A `LinkedHashSet` maintains a
 * doubly-linked list that runs through all of its entries. This list is what preserves
 * the order. When you add an element, it's added to the hash table for fast lookup *and*
 * added to the end of the linked list to remember its position.
 *
 * The result is a collection that enforces uniqueness like a `HashSet` but remembers the
 * sequence of insertion like a `List`.
 *
 * 
 *
 * ## 2. Key Characteristics
 *
 * - **Insertion Order**: The most important feature. The iterator will always traverse
 * elements in the exact order they were first added to the set.
 * - **Uniqueness**: It strictly prohibits duplicate elements. The `add()` method will return
 * `false` if you try to add an element that is already present.
 * - **Allows One `null`**: You can store a single `null` value.
 * - **Performance**: While `add()`, `remove()`, and `contains()` operations are O(1), they are
 * marginally slower than in a `HashSet` due to the overhead of maintaining the linked list.
 * However, iterating over a `LinkedHashSet` is faster than a `HashSet` because it just
 * follows the linked list pointers instead of scanning the underlying hash table buckets.
 * - **Not Synchronized**: It is not thread-safe. If multiple threads access it concurrently
 * and at least one modifies it, it must be synchronized externally.
 *
 * ## 3. When to Use LinkedHashSet? 🤔
 *
 * You should use a `LinkedHashSet` when your problem requires a collection that satisfies
 * **both** of these conditions: **1) elements must be unique**, and **2) the original
 * order of the elements is important**. It's the perfect tool for "de-duplicating while
 * preserving sequence." For example, imagine you are processing a log file where a user
 * performs a series of actions ("login", "view_page", "click_button", "view_page"). If you
 * want to find the unique sequence of actions the user took, a `LinkedHashSet` is ideal.
 * Adding all actions to it would result in a collection holding ["login", "view_page",
 * "click_button"], which filters out the duplicate "view_page" while preserving the original
 * chronological order of the first occurrences. Other use cases include caching strategies where
 * insertion order matters or ensuring data in a UI dropdown menu is both unique and ordered.
 *
 *
 * ## 4. Table of Basic Operations & Built-in Functions
 *
 * | Method Signature             | Description                                                   | Return Type |
 * |------------------------------|---------------------------------------------------------------|-------------|
 * | `boolean add(E e)`           | Adds the element `e` if not already present.                  | `boolean`   |
 * | `boolean remove(Object o)`   | Removes the element `o` if it is present.                     | `boolean`   |
 * | `boolean contains(Object o)` | Returns `true` if the set contains the element `o`.           | `boolean`   |
 * | `int size()`                 | Returns the number of elements in the set.                    | `int`       |
 * | `boolean isEmpty()`          | Returns `true` if the set contains no elements.               | `boolean`   |
 * | `void clear()`               | Removes all elements from the set.                            | `void`      |
 * | `Iterator<E> iterator()`     | Returns an iterator over the elements in insertion order.     | `Iterator`  |
 * | `Object[] toArray()`         | Returns an array containing all elements in insertion order.  | `Object[]`  |
 *
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * This class provides practical, executable examples for every concept and
 * function of LinkedHashSet discussed in the theory section above.
 */
public class LinkedHashSetComprehensiveGuide {

    public static void main(String[] args) {

        System.out.println("--- 1. The `add()` Method and Insertion Order ---");
        LinkedHashSet<String> programmingLanguages = new LinkedHashSet<>();
        // add(E e) returns true if the element was successfully added.
        System.out.println("Adding 'Java': " + programmingLanguages.add("Java"));
        System.out.println("Adding 'Python': " + programmingLanguages.add("Python"));
        System.out.println("Adding 'C++': " + programmingLanguages.add("C++"));
        System.out.println("Current Set (Order is preserved): " + programmingLanguages);
        System.out.println("--------------------------------------------------\n");


        System.out.println("--- 2. Uniqueness: `add()` with Duplicates ---");
        // add(E e) returns false if the element is already in the set.
        System.out.println("Adding 'Python' again: " + programmingLanguages.add("Python"));
        System.out.println("Set remains unchanged: " + programmingLanguages);
        System.out.println("--------------------------------------------------\n");


        System.out.println("--- 3. The `contains()` Method ---");
        // contains(Object o) checks for the existence of an element.
        System.out.println("Does the set contain 'Java'? " + programmingLanguages.contains("Java")); // true
        System.out.println("Does the set contain 'Go'? " + programmingLanguages.contains("Go"));     // false
        System.out.println("--------------------------------------------------\n");


        System.out.println("--- 4. The `remove()` Method ---");
        // remove(Object o) returns true if the element was found and removed.
        System.out.println("Removing 'C++': " + programmingLanguages.remove("C++"));
        System.out.println("Removing 'Go': " + programmingLanguages.remove("Go")); // Not present
        System.out.println("Set after removal (order is still maintained): " + programmingLanguages);
        System.out.println("--------------------------------------------------\n");


        System.out.println("--- 5. The `size()` and `isEmpty()` Methods ---");
        System.out.println("Current size of the set: " + programmingLanguages.size());
        System.out.println("Is the set empty? " + programmingLanguages.isEmpty());
        System.out.println("--------------------------------------------------\n");


        System.out.println("--- 6. The `iterator()` Method ---");
        System.out.println("Iterating over the set (guarantees insertion order):");
        Iterator<String> iterator = programmingLanguages.iterator();
        while (iterator.hasNext()) {
            System.out.println(" -> " + iterator.next());
        }
        System.out.println("--------------------------------------------------\n");


        System.out.println("--- 7. The `clear()` Method ---");
        programmingLanguages.clear();
        System.out.println("Set after calling clear(): " + programmingLanguages);
        System.out.println("Is the set empty now? " + programmingLanguages.isEmpty());
        System.out.println("Current size: " + programmingLanguages.size());
        System.out.println("--------------------------------------------------\n");


        System.out.println("--- 8. Practical Use Case: De-duplicating a List while Preserving Order ---");
        List<String> userActions = new ArrayList<>();
        userActions.add("Login");
        userActions.add("View Dashboard");
        userActions.add("View Profile");
        userActions.add("View Dashboard"); // Duplicate
        userActions.add("Logout");
        userActions.add("Login");           // Duplicate

        System.out.println("Original list of actions with duplicates: " + userActions);

        // Use a LinkedHashSet to get the unique sequence of actions
        LinkedHashSet<String> uniqueActions = new LinkedHashSet<>(userActions);

        System.out.println("Unique actions in their original order: " + uniqueActions);
        System.out.println("--------------------------------------------------\n");
    }
}