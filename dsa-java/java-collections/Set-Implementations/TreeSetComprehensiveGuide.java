// File: TreeSetComprehensiveGuide.java

/*
 * ======================================================================================
 * | THEORY: A Deep Dive into TreeSet                                                   |
 * ======================================================================================
 *
 * ## 1. Core Concept: The Sorted Set 📚
 *
 * TreeSet is a `Set` implementation that stores its elements in a **sorted order**. It is the
 * go-to choice when you need a collection of unique items that must always be maintained
 * in a sorted sequence.
 *
 * The magic behind its ordering is its underlying data structure: a **self-balancing
 * binary search tree**. Specifically, it is backed by a `TreeMap` in the Java standard
 * library. This tree structure ensures that elements are always in order and that most
 * operations (like add, remove, and search) are very efficient.
 *
 * **Analogy: A Self-Organizing Dictionary**
 * Imagine a dictionary where, no matter what order you insert new words, they instantly
 * find their correct alphabetical spot. When you open this dictionary, the words are always
 * perfectly sorted. `TreeSet` works just like this, but for any type of object you define.
 * It allows you to quickly find the "first" or "last" element, or elements that are "just before"
 * or "just after" another one.
 * 
 *
 * ## 2. Key Characteristics
 *
 * - **Sorted Order**: This is its defining feature. Elements are sorted either by their
 * **natural ordering** or by a custom **`Comparator`** provided at creation time.
 * - **Uniqueness**: Like all `Set` implementations, it does not allow duplicate elements.
 * - **Performance**: Basic operations like `add()`, `remove()`, and `contains()` are guaranteed
 * to have logarithmic time complexity: **O(log n)**. This is slightly slower than `HashSet`'s
 * O(1) but is often a worthy trade-off for the benefit of a constantly sorted collection.
 * - **No `null` Elements**: A `TreeSet` does not permit `null` elements. Attempting to add
 * `null` will throw a `NullPointerException` because `null` cannot be compared to other objects
 * to determine its position in the sorted order.
 * - **`NavigableSet` Implementation**: It implements the `NavigableSet` interface, which provides
 * a rich set of methods for searching and navigating the sorted elements.
 * - **Not Synchronized**: It is not thread-safe and must be synchronized externally.
 *
 * ## 3. Sorting Mechanism: `Comparable` vs. `Comparator`
 *
 * `TreeSet` needs to know how to compare two objects to sort them. It does this in one of two ways:
 *
 * 1.  **Comparable Interface (Natural Ordering)**: If the objects you are adding implement the
 * `Comparable` interface (like `Integer`, `String`, `LocalDate`), the `TreeSet` will use their
 * `compareTo()` method to sort them. This is called "natural ordering."
 *
 * 2.  **Comparator Interface (Custom Ordering)**: If you want a different sorting logic, or if the
 * objects don't implement `Comparable`, you can pass a `Comparator` object to the `TreeSet`'s
 * constructor. This gives you full control over the sorting criteria (e.g., sorting strings by
 * length instead of alphabetically).
 *
 * ## 4. When to Use TreeSet? 🤔
 *
 * You should use `TreeSet` when your primary requirement is to maintain a collection of
 * **unique elements in a sorted order**. It is the ideal data structure if you frequently need
 * to perform range queries (e.g., "find all users with a score between 500 and 1000"), or if you
 * constantly need access to the smallest or largest item in the set.
 *
 * **Common Scenarios:**
 * - **Leaderboards**: Storing player scores and easily retrieving the top or bottom players.
 * - **Calendars**: Managing a set of unique appointments or events and iterating through them
 * chronologically.
 * - **Data Analysis**: Finding the median, or elements above/below a certain threshold in a
 * unique dataset.
 *
 * ## 5. Table of Key Navigational Methods
 *
 * `TreeSet` supports standard `Set` methods, but its power comes from the `NavigableSet` interface.
 *
 * | Method Signature           | Description                                                        |
 * |----------------------------|--------------------------------------------------------------------|
 * | `E first()`                | Returns the first (lowest) element.                                |
 * | `E last()`                 | Returns the last (highest) element.                                |
 * | `E lower(E e)`             | Returns the greatest element strictly less than `e`.                 |
 * | `E floor(E e)`             | Returns the greatest element less than or equal to `e`.            |
 * | `E higher(E e)`            | Returns the smallest element strictly greater than `e`.              |
 * | `E ceiling(E e)`           | Returns the smallest element greater than or equal to `e`.           |
 * | `E pollFirst()`            | Retrieves and removes the first (lowest) element.                  |
 * | `E pollLast()`             | Retrieves and removes the last (highest) element.                  |
 * | `NavigableSet<E> subSet()` | Returns a view of the portion of this set within a range.          |
 * | `NavigableSet<E> descendingSet()`| Returns a reverse-order view of the set.                   |
 *
 */

import java.util.Comparator;
import java.util.TreeSet;

/**
 * A simple Book class. Note that it does NOT implement Comparable.
 * We will sort it using an external Comparator.
 */
class Book {
    String title;
    int pages;

    public Book(String title, int pages) {
        this.title = title;
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{title='" + title + "', pages=" + pages + '}';
    }
}


public class TreeSetComprehensiveGuide {

    public static void main(String[] args) {

        System.out.println("--- 1. Natural Ordering (with Integers) ---");
        // TreeSet uses the natural ordering of Integers (ascending).
        TreeSet<Integer> scores = new TreeSet<>();
        scores.add(85);
        scores.add(50);
        scores.add(95);
        scores.add(70);
        scores.add(50); // Duplicate, will be ignored.

        // The elements are automatically sorted.
        System.out.println("Sorted scores: " + scores);
        System.out.println("--------------------------------------------------\n");


        System.out.println("--- 2. Custom Ordering with a Comparator (with Book objects) ---");
        // Create a comparator to sort books by their title.
        Comparator<Book> sortByTitle = Comparator.comparing(b -> b.title);
        
        TreeSet<Book> library = new TreeSet<>(sortByTitle);
        library.add(new Book("The Hobbit", 310));
        library.add(new Book("1984", 328));
        library.add(new Book("A Brief History of Time", 256));
        
        System.out.println("Library sorted by title:");
        library.forEach(System.out::println);
        System.out.println("--------------------------------------------------\n");


        System.out.println("--- 3. Using Navigational Methods ---");
        System.out.println("Scores: " + scores);
        System.out.println("Lowest score (first()): " + scores.first());
        System.out.println("Highest score (last()): " + scores.last());
        
        // Find numbers around 80
        System.out.println("Score just below 85 (lower(85)): " + scores.lower(85)); // 70
        System.out.println("Score <= 85 (floor(85)): " + scores.floor(85)); // 85
        System.out.println("Score just above 85 (higher(85)): " + scores.higher(85)); // 95
        System.out.println("Score >= 85 (ceiling(85)): " + scores.ceiling(85)); // 85
        System.out.println("--------------------------------------------------\n");


        System.out.println("--- 4. Getting a Subset View ---");
        // Get all scores from 70 (inclusive) up to 95 (exclusive).
        TreeSet<Integer> midRangeScores = (TreeSet<Integer>) scores.subSet(70, true, 95, false);
        System.out.println("Scores between 70 and 95 (exclusive): " + midRangeScores);
        System.out.println("--------------------------------------------------\n");


        System.out.println("--- 5. Descending Order View ---");
        System.out.println("Scores in descending order: " + scores.descendingSet());
        System.out.println("--------------------------------------------------\n");


        System.out.println("--- 6. Removing Elements with `pollFirst()` and `pollLast()` ---");
        System.out.println("Original scores: " + scores);
        System.out.println("Removed lowest: " + scores.pollFirst()); // Removes and returns 50
        System.out.println("Removed highest: " + scores.pollLast()); // Removes and returns 95
        System.out.println("Scores after polling: " + scores);
        System.out.println("--------------------------------------------------\n");

        
        System.out.println("--- 7. No `null` is Allowed ---");
        try {
            // This line would throw a NullPointerException if uncommented.
            // scores.add(null);
            System.out.println("Attempting to add null would throw a NullPointerException.");
        } catch (NullPointerException e) {
            System.out.println("Caught expected exception: " + e);
        }
        System.out.println("--------------------------------------------------\n");
    }
}