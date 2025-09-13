import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/*
================================================================================
|| NOTES: SOLVING TEST PROBLEMS WITH JAVA'S VECTOR
================================================================================

This file demonstrates how to solve problems that specifically test the unique concepts
of Java's **`Vector`**.

A `Vector` is a legacy class from JDK 1.0. While functionally similar to an `ArrayList`,
it has two key distinctions that are often the focus of test questions:

1.  **Thread-Safety**: `Vector` is **synchronized**. All of its methods are thread-safe,
    meaning they can be safely used in multi-threaded environments without external
    synchronization. This comes at a performance cost.
2.  **Legacy Features**: It includes older methods like `capacity()` to inspect its internal
    storage size and `elements()` which returns an `Enumeration`, the predecessor to
    the modern `Iterator`.

Here in Pune, on a Saturday evening, September 13, 2025, while `ArrayList` is the standard
choice for single-threaded list operations, understanding `Vector` is crucial for working with
legacy code and for grasping core concepts of concurrency in Java.

Problems covered:
1.  **Demonstrate Thread-Safety**: The most important `Vector` problem. This shows how a
    `Vector` prevents data corruption in a multi-threaded scenario, while a standard
    `ArrayList` fails.
2.  **Observe Capacity Growth**: A problem to illustrate how a `Vector` manages its internal
    array, automatically doubling its capacity when it becomes full.
3.  **Filter using Legacy Enumeration**: A task that requires using the older `Enumeration`
    interface, which is unique to `Vector` and other legacy collection classes.
*/

/**
 * Main public class to run the Vector problem examples.
 */
public class VectorProblems {

    public static void main(String[] args) throws InterruptedException {

        // --- Problem 1: Demonstrate Thread-Safety ---
        System.out.println("## Problem 1: Demonstrate Thread-Safety");
        demonstrateThreadSafety();
        System.out.println("--------------------------------------------------\n");

        // --- Problem 2: Observe Capacity Growth ---
        System.out.println("## Problem 2: Observe Capacity Growth");
        demonstrateCapacityGrowth();
        System.out.println("--------------------------------------------------\n");

        // --- Problem 3: Filter using Legacy Enumeration ---
        System.out.println("## Problem 3: Filter using Legacy Enumeration");
        Vector<String> languages = new Vector<>();
        languages.add("Java");
        languages.add("JavaScript");
        languages.add("Python");
        languages.add("Julia");
        System.out.println("   Original vector: " + languages);
        Vector<String> filtered = filterWithEnumeration(languages, "Java");
        System.out.println("   Filtered for words starting with 'Java': " + filtered);
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Solution for Problem 1: Shows that Vector is thread-safe while ArrayList is not.
     * Approach: Create multiple threads that add elements concurrently to both a Vector
     * and an ArrayList. The Vector's final size will be correct, while the ArrayList's
     * will likely be incorrect due to race conditions.
     */
    public static void demonstrateThreadSafety() throws InterruptedException {
        int numThreads = 10;
        int elementsPerThread = 1000;
        int expectedSize = numThreads * elementsPerThread;

        Vector<Integer> safeVector = new Vector<>();
        List<Integer> unsafeList = new ArrayList<>(); // Not thread-safe

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < elementsPerThread; j++) {
                    safeVector.add(j);
                    unsafeList.add(j);
                }
            });
            threads.add(thread);
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("   Expected final size: " + expectedSize);
        System.out.println("   Vector's final size (Correct): " + safeVector.size());
        System.out.println("   ArrayList's final size (Likely Incorrect): " + unsafeList.size());
    }

    /**
     * Solution for Problem 2: Illustrates the automatic capacity growth of a Vector.
     * Approach: Create a Vector with a small initial capacity and add elements in a
     * loop, printing the size and capacity at each step.
     */
    public static void demonstrateCapacityGrowth() {
        // Start with a small initial capacity to see the growth quickly.
        Vector<Integer> vector = new Vector<>(2);
        System.out.println("   Initial Capacity: " + vector.capacity());

        for (int i = 0; i < 9; i++) {
            System.out.println("   -> Adding element " + (i + 1) + ". " +
                               "Current Size: " + vector.size() +
                               ", Current Capacity: " + vector.capacity());
            vector.add(i);
            // After this add, if size > capacity, a resize will occur for the *next* add.
        }
        System.out.println("   Final Size: " + vector.size() + ", Final Capacity: " + vector.capacity());
    }

    /**
     * Solution for Problem 3: Filters a Vector using the legacy Enumeration interface.
     * Approach: Use the `elements()` method to get an Enumeration and iterate through it,
     * adding matching elements to a new Vector.
     * Time Complexity: O(n).
     */
    public static Vector<String> filterWithEnumeration(Vector<String> vector, String prefix) {
        Vector<String> result = new Vector<>();
        Enumeration<String> e = vector.elements();

        while (e.hasMoreElements()) {
            String element = e.nextElement();
            if (element.startsWith(prefix)) {
                result.add(element);
            }
        }
        return result;
    }
}