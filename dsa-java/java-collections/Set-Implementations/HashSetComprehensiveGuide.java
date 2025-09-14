// File: HashSetComprehensiveGuide.java

/*
 * ======================================================================================
 * | THEORY: A Deep Dive into HashSet                                                   |
 * ======================================================================================
 *
 * ## 1. Core Concept: The Unordered Bag of Unique Items 🛍️
 *
 * HashSet is the most widely used implementation of the `Set` interface in Java. Its primary
 * purpose is to store a collection of **unique elements** in an **unordered** fashion.
 *
 * The magic behind its speed is its underlying data structure: a **hash table**. Internally, a
 * `HashSet` is backed by a `HashMap`. When you add an element to the `HashSet`, that element
 * becomes a *key* in the internal `HashMap`, and a constant dummy object is used as the value.
 *
 * **Analogy: A Magical Filing Cabinet**
 * Think of a filing cabinet with an infinite number of drawers. When you want to store a
 * document (an element), you instantly know which drawer to put it in based on a special
 * code (`hashCode()`) derived from the document's content. To check if a document is already
 * there, you just go directly to that specific drawer instead of searching the whole cabinet.
 * This direct access is what makes `HashSet` operations so fast.
 * 
 *
 * ## 2. Key Characteristics
 *
 * - **Unordered**: This is the most critical trait to remember. `HashSet` makes **absolutely no
 * guarantee** concerning the iteration order of the elements. The order may seem random and can
 * even change as more elements are added.
 * - **Uniqueness**: It only stores unique elements. Duplicates are ignored. Uniqueness is determined
 * by the `equals()` method.
 * - **High Performance**: It offers constant time **O(1)** performance on average for the basic
 * operations (`add`, `remove`, `contains`, `size`), assuming the hash function disperses elements
 * properly among the "buckets".
 * - **Allows One `null`**: You can store a single `null` value in a `HashSet`.
 * - **Performance Factors**: The performance is influenced by two main factors:
 * - **Initial Capacity**: The number of "buckets" in the hash table when it's created.
 * - **Load Factor**: A measure of how full the hash table is allowed to get before its capacity
 * is automatically increased. The default is 0.75 (or 75% full).
 * - **Not Synchronized**: It is not thread-safe. For concurrent access, consider using
 * `Collections.synchronizedSet` or `CopyOnWriteArraySet`.
 *
 * ## 3. The `hashCode()` and `equals()` Contract 📜
 *
 * The correct functioning of a `HashSet` relies entirely on the proper implementation of the
 * `hashCode()` and `equals()` methods for the objects it stores.
 *
 * 1.  If two objects are equal according to `equals(Object)`, then they **must** have the same hash code.
 * (`a.equals(b)` -> `a.hashCode() == b.hashCode()`)
 * 2.  If two objects have the same hash code, they are **not required** to be equal. This is called a
 * hash collision, and the `HashSet` will then use the `equals()` method to differentiate them.
 *
 * **If you store custom objects in a `HashSet`, you MUST override both `hashCode()` and `equals()`**.
 *
 * ## 4. When to Use HashSet? 🤔
 *
 * `HashSet` is your default choice for a `Set` when your primary needs are **uniqueness and speed**,
 * and you **do not care about the order** of the elements. It's the perfect tool for quickly
 * checking if an item has been seen before or for getting a unique collection of items from a larger
 * dataset.
 *
 * **Common Scenarios:**
 * - **Finding Unique Items**: Finding all the unique words in a book or unique visitors to a website.
 * - **Fast Lookups**: Tracking items that have already been processed in an algorithm to avoid duplicate work.
 * - **Graph Algorithms**: Keeping track of visited nodes in a graph traversal (like DFS or BFS) to prevent infinite loops.
 *
 * ## 5. Table of Constructors & Basic Operations
 *
 * ### Common Constructors
 * | Constructor Signature               | Description                                           |
 * |-------------------------------------|-------------------------------------------------------|
 * | `HashSet()`                         | Creates an empty set with default capacity (16) & load factor (0.75). |
 * | `HashSet(Collection<? extends E> c)`| Creates a set and populates it with elements from another collection (a great way to de-duplicate a List). |
 * | `HashSet(int initialCapacity)`      | Creates an empty set with a specified initial capacity. |
 *
 * ### Basic Operations
 * `add()`, `remove()`, `contains()`, `size()`, `isEmpty()`, and `clear()` are the same as any other `Set`.
 *
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
// import java.util.Objects;

/**
 * A simple class to demonstrate the importance of hashCode() and equals().
 */
class Person {
    String name;
    int id;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', id=" + id + '}';
    }
    
    // --- IMPORTANT: Uncomment the following methods to see the correct behavior ---
    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
    */
}


public class HashSetComprehensiveGuide {

    public static void main(String[] args) {

        System.out.println("--- 1. `add()` and Uniqueness ---");
        HashSet<String> planets = new HashSet<>();
        planets.add("Earth");
        planets.add("Mars");
        planets.add("Jupiter");
        System.out.println("Adding 'Mars' again: " + planets.add("Mars")); // Returns false
        System.out.println("Set of planets: " + planets);
        System.out.println("--------------------------------------------------\n");


        System.out.println("--- 2. Unordered Nature ---");
        HashSet<Integer> numbers = new HashSet<>();
        numbers.add(500);
        numbers.add(1);
        numbers.add(99);
        numbers.add(10);
        // The iteration order is NOT guaranteed. It will likely not be [500, 1, 99, 10].
        System.out.println("Numbers added in a sequence: " + numbers);
        System.out.println("--------------------------------------------------\n");


        System.out.println("--- 3. `contains()` and `remove()` ---");
        System.out.println("Does the set contain 'Earth'? " + planets.contains("Earth"));
        System.out.println("Removing 'Mars': " + planets.remove("Mars"));
        System.out.println("Set after removal: " + planets);
        System.out.println("--------------------------------------------------\n");


        System.out.println("--- 4. Handling `null` ---");
        numbers.add(null);
        numbers.add(null); // Ignored
        System.out.println("Set after adding null: " + numbers);
        System.out.println("Size of set: " + numbers.size());
        System.out.println("--------------------------------------------------\n");


        System.out.println("--- 5. Creating from a Collection (De-duplicating a List) ---");
        List<String> userLogins = new ArrayList<>();
        userLogins.add("alice");
        userLogins.add("bob");
        userLogins.add("charlie");
        userLogins.add("alice"); // Duplicate
        userLogins.add("bob");   // Duplicate
        System.out.println("Original list with duplicates: " + userLogins);

        HashSet<String> uniqueLogins = new HashSet<>(userLogins);
        System.out.println("Unique logins (order not guaranteed): " + uniqueLogins);
        System.out.println("--------------------------------------------------\n");
        

        System.out.println("--- 6. Custom Objects (WITHOUT hashCode/equals) ---");
        HashSet<Person> people = new HashSet<>();
        people.add(new Person("Alice", 101));
        people.add(new Person("Alice", 101)); // Should be a duplicate, but isn't!
        
        // This will print 2 because without hashCode/equals, Java thinks they are different objects.
        System.out.println("Size of people set: " + people.size());
        System.out.println("People: " + people);
        System.out.println("\n>> NOTE: Now, go to the Person class, uncomment the equals() and hashCode()");
        System.out.println(">> methods, and run this file again to see the correct behavior (size will be 1).");
        System.out.println("--------------------------------------------------\n");
    }
}