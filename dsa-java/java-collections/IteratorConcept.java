
// Java Iterator
// An Iterator is an object that can be used to loop through collections, like ArrayList and HashSet. It is called an "iterator" because "iterating" is the technical term for looping.

// To use an Iterator, you must import it from the java.util package.

// Getting an Iterator
// The iterator() method can be used to get an Iterator for any collection:

// Removing Items from a Collection
// Iterators are designed to easily change the collections that they loop through. The remove() method can remove items from a collection while looping.

// Import necessary classes from the java.util package.
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorConcept {

    public static void main(String[] args) {

        /*
        --------------------------
        -- THEORY: WHAT IS AN ITERATOR? --
        --------------------------

        1.  **A Universal Cursor**: An Iterator is an object that enables you to traverse through a collection,
            element by element. Think of it as a cursor 🖱️ that points to the elements of a collection one after another.
            It provides a uniform way to access elements from any collection class (like ArrayList, HashSet, etc.).

        2.  **The Iterable Interface**: Any collection class that wants to be traversable using an iterator (or a for-each loop)
            must implement the `Iterable` interface. This interface has a single method, `iterator()`, which returns an
            Iterator object.

        3.  **Key Characteristics**:
            * **Forward-Only Traversal**: An Iterator can only move forward. There is no way to go back to a previous element.
            * **Read and Remove**: The primary operations are reading the next element and, importantly, safely removing the element
                the iterator is currently pointing to.
            * **Safe Removal**: The Iterator's `remove()` method is the ONLY safe way to modify a collection (i.e., remove elements)
                while you are iterating over it. Modifying the collection directly using the collection's own `remove()` method inside
                a loop will result in a `ConcurrentModificationException`.
            * **Fail-Fast Behavior**: Iterators are "fail-fast." If the collection is structurally modified (e.g., by another thread or
                by calling `list.add()` or `list.remove()`) after the iterator has been created, in any way other than by the iterator's
                own `remove()` method, the iterator will throw a `ConcurrentModificationException` as soon as you try to use it.

        4.  **Core Methods**:
            * `boolean hasNext()`: Returns `true` if there are more elements to iterate over. This is typically used to control the loop.
            * `E next()`: Returns the next element in the collection and advances the iterator's position.
            * `void remove()`: Removes the last element returned by the `next()` method from the underlying collection. This method can
                only be called once after each call to `next()`.

        */

        // --------------------------
        // -- EXAMPLE: A PRACTICAL DEMONSTRATION --
        // --------------------------

        // 1. **Create a collection**
        // We'll use a List (implemented by ArrayList) for this example.
        List<String> programmingLanguages = new ArrayList<>();
        programmingLanguages.add("Java");
        programmingLanguages.add("Python");
        programmingLanguages.add("JavaScript");
        programmingLanguages.add("Go");
        System.out.println("1. Original List: " + programmingLanguages);

        // 2. **Get an Iterator** from the collection.
        // You get it by calling the .iterator() method on the collection object.
        Iterator<String> iterator = programmingLanguages.iterator();
        System.out.println("\n2. Simple traversal using an Iterator:");

        // 3. **Traverse the collection** using a while loop.
        // The loop continues as long as iterator.hasNext() returns true.
        while (iterator.hasNext()) {
            // Get the next element.
            String language = iterator.next();
            System.out.println("   - " + language);
        }

        // 4. **Safe Removal during Iteration**
        // Let's remove any language that contains the letter 'a'.
        // We MUST get a new iterator because the old one has already reached the end.
        System.out.println("\n3. Safely removing elements containing 'a' while iterating:");
        Iterator<String> removalIterator = programmingLanguages.iterator();
        while (removalIterator.hasNext()) {
            String language = removalIterator.next();
            if (language.contains("a")) {
                // This is the ONLY safe way to remove an element during iteration.
                removalIterator.remove();
                System.out.println("   - Removed: " + language);
            }
        }
        
        System.out.println("\n4. List after removal: " + programmingLanguages);
        
        /*
        // DANGEROUS EXAMPLE: This will cause a ConcurrentModificationException!
        // You cannot use a for-each loop (which uses an iterator internally)
        // and modify the collection with the collection's own remove() method.
        System.out.println("\nAttempting unsafe removal (will cause an error):");
        for (String language : programmingLanguages) {
             if (language.equals("Python")) {
                 programmingLanguages.remove(language); // This line throws the exception!
             }
        }
        */
    }
}
