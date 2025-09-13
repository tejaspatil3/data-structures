import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/*
================================================================================
|| NOTES: SOLVING TEST PROBLEMS WITH JAVA'S ITERATOR
================================================================================

This file demonstrates how to solve common problems from technical tests where using an
**Iterator** is the intended or most appropriate approach.

The most critical feature of an `Iterator` is that it provides the **only safe way to
modify a collection (by removing elements) while you are iterating over it**. Attempting
to remove an element using a collection's own `remove()` method inside a standard
`for-each` loop will throw a `ConcurrentModificationException`.

Here in Pune, on a Saturday evening, September 13, 2025, mastering the `Iterator` is
essential for writing safe, robust, and efficient Java code.

Problems covered:
1.  **Safely Remove Elements While Iterating**: The most fundamental `Iterator` problem.
    This demonstrates the correct use of `iterator.remove()` to avoid exceptions.

2.  **Design a Peeking Iterator**: A common interview design question. You are asked to create
    a wrapper around an existing iterator that adds a `peek()` method, allowing you to
    view the next element without advancing the iterator. This tests your understanding
    of the iterator's internal state.

3.  **Flatten a 2D List**: Given a list of lists, this problem requires you to implement
    an iterator that traverses all elements in a single, flattened sequence. This tests
    your ability to manage multiple iterators.
*/

/**
 * Main public class to run the Iterator problem examples.
 */
public class IteratorProblems {

    public static void main(String[] args) {

        // --- Problem 1: Safely Remove Elements While Iterating ---
        System.out.println("## Problem 1: Safely Remove Elements While Iterating");
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        System.out.println("   List before removing even numbers: " + numbers);
        safelyRemoveEvenNumbers(numbers);
        System.out.println("   List after removing even numbers:  " + numbers);
        System.out.println("--------------------------------------------------\n");

        // --- Problem 2: Design a Peeking Iterator ---
        System.out.println("## Problem 2: Design a Peeking Iterator");
        List<String> fruits = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry"));
        PeekingIterator<String> peekingIterator = new PeekingIterator<>(fruits.iterator());
        System.out.println("   Peeking at the first element: " + peekingIterator.peek());
        System.out.println("   Taking the first element (next): " + peekingIterator.next());
        System.out.println("   Peeking at the second element: " + peekingIterator.peek());
        System.out.println("   Does it have a next element? " + peekingIterator.hasNext());
        System.out.println("   Taking the second element (next): " + peekingIterator.next());
        System.out.println("   Taking the third element (next): " + peekingIterator.next());
        System.out.println("   Does it have a next element? " + peekingIterator.hasNext());
        System.out.println("--------------------------------------------------\n");

        // --- Problem 3: Flatten a 2D List ---
        System.out.println("## Problem 3: Flatten a 2D List");
        List<List<Integer>> nestedList = new ArrayList<>();
        nestedList.add(Arrays.asList(1, 2));
        nestedList.add(Arrays.asList(3));
        nestedList.add(Arrays.asList()); // Empty list
        nestedList.add(Arrays.asList(4, 5, 6));
        System.out.println("   Nested List: " + nestedList);
        System.out.print("   Flattened sequence:");
        FlattenedIterator<Integer> flattened = new FlattenedIterator<>(nestedList);
        while (flattened.hasNext()) {
            System.out.print(" " + flattened.next());
        }
        System.out.println("\n--------------------------------------------------\n");
    }

    /**
     * Solution for Problem 1: Safely removes all even numbers from a list.
     * Approach: Use the Iterator's own `remove()` method inside a `while` loop.
     * Time Complexity: O(n).
     */
    public static void safelyRemoveEvenNumbers(List<Integer> list) {
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            Integer number = it.next();
            if (number % 2 == 0) {
                it.remove(); // This is the ONLY safe way to remove during iteration.
            }
        }
    }
}


/**
 * Solution for Problem 2: A wrapper class that adds `peek()` functionality to an iterator.
 */
class PeekingIterator<E> implements Iterator<E> {
    private final Iterator<E> iterator;
    private E nextElement; // Cache the next element

    public PeekingIterator(Iterator<E> iterator) {
        this.iterator = iterator;
        if (this.iterator.hasNext()) {
            this.nextElement = this.iterator.next();
        } else {
            this.nextElement = null;
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public E peek() {
        if (nextElement == null) {
            throw new NoSuchElementException();
        }
        return nextElement;
    }

    @Override
    public boolean hasNext() {
        return nextElement != null;
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        E result = nextElement; // The element to return
        // Advance to the next element for the *next* call to peek() or next()
        if (iterator.hasNext()) {
            nextElement = iterator.next();
        } else {
            nextElement = null;
        }
        return result;
    }
}


/**
 * Solution for Problem 3: An iterator that flattens a list of lists.
 */
class FlattenedIterator<E> implements Iterator<E> {
    private Iterator<List<E>> outerIterator;
    private Iterator<E> innerIterator;

    public FlattenedIterator(List<List<E>> nestedList) {
        this.outerIterator = nestedList.iterator();
        this.innerIterator = null; // Initialize as null
    }

    @Override
    public boolean hasNext() {
        // This loop ensures we find the next available inner iterator, skipping any
        // empty inner lists.
        while ((innerIterator == null || !innerIterator.hasNext()) && outerIterator.hasNext()) {
            innerIterator = outerIterator.next().iterator();
        }
        // The final result depends on whether the current innerIterator has an element.
        return innerIterator != null && innerIterator.hasNext();
    }

    @Override
    public E next() {
        // hasNext() must be called before next() to ensure innerIterator is pointing
        // to a valid element.
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return innerIterator.next();
    }
}