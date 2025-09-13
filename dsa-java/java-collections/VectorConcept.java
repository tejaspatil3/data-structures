import java.util.Vector;
import java.util.Enumeration;

/*
        ==================================================
        THEORY: WHAT IS A VECTOR?
        ==================================================
        * **Concept**: A `Vector` is a resizable-array implementation of the `List` interface.
          Functionally, it is very similar to an `ArrayList`—it acts as a dynamic array that can
          grow or shrink in size as needed.
        * **Key Differentiator: Synchronization** ⚙️
          The single most important characteristic of a `Vector` is that it is **synchronized**.
          This means all of its methods are thread-safe. Only one thread can execute a `Vector`'s
          method at a time, which prevents data corruption in multi-threaded applications. However,
          this synchronization adds a significant performance overhead.
        * **Legacy Class**: `Vector` is a **legacy class** from the very first version of Java (JDK 1.0).
          It was later retrofitted to be part of the Java Collections Framework. Because of its age and
          the performance cost of its synchronization, it is rarely used in modern code.
        * **Modern Alternatives**:
          * For **single-threaded** applications, **`ArrayList` is the clear winner**. It provides the same
            functionality without the performance penalty.
          * For **multi-threaded** applications, it's often better to use `Collections.synchronizedList(new ArrayList<>())`
            or more advanced classes like `CopyOnWriteArrayList` from the `java.util.concurrent` package.
        * **Capacity vs. Size**:
          * **`size()`**: The number of actual elements currently in the vector.
          * **`capacity()`**: The total space allocated in memory for the vector. The capacity is
            always greater than or equal to the size. The vector grows its capacity automatically
            when you add elements beyond its current capacity.
        * **Key Methods**:
          * `add(E element)`: Appends an element to the end.
          * `get(int index)`: Returns the element at the specified index.
          * `remove(int index)`: Removes the element at the specified index.
          * `size()`: Returns the number of elements.
          * `capacity()`: Returns the current capacity.
          * `elements()`: A legacy method that returns an `Enumeration`, which is an older version of `Iterator`.
        */

public class VectorConcept {

    public static void main(String[] args) {     
        
        // --------------------------------------------------
        // EXAMPLE: DEMONSTRATING VECTOR USAGE
        // --------------------------------------------------

        // 1. **Creating a Vector**
        // You can specify an initial capacity. By default, it's 10.
        Vector<String> languageVector = new Vector<>();

        System.out.println("1. New Vector created.");
        System.out.println("   Initial size: " + languageVector.size());
        System.out.println("   Initial capacity: " + languageVector.capacity());

        // 2. **Adding elements**
        // `add` is the modern Collections method. `addElement` is a legacy Vector method.
        languageVector.add("Java");
        languageVector.add("Python");
        languageVector.addElement("C++"); // Legacy method, does the same as add()
        
        System.out.println("\n2. After adding 3 elements:");
        System.out.println("   Vector contents: " + languageVector);
        System.out.println("   Size: " + languageVector.size());
        System.out.println("   Capacity: " + languageVector.capacity()); // Still 10

        // 3. **Accessing elements**
        // `get` is the modern method, `elementAt` is the legacy equivalent.
        String firstElement = languageVector.get(0);
        String secondElement = languageVector.elementAt(1); // Legacy method
        System.out.println("\n3. Accessing elements:");
        System.out.println("   Element at index 0 (using get): " + firstElement);
        System.out.println("   Element at index 1 (using elementAt): " + secondElement);

        // 4. **Iterating through a Vector**
        // You can use a modern for-each loop.
        System.out.println("\n4. Iterating with a for-each loop:");
        for (String lang : languageVector) {
            System.out.println("   - " + lang);
        }

        // Or you can use the legacy Enumeration interface.
        System.out.println("\n   Iterating with legacy Enumeration:");
        Enumeration<String> elements = languageVector.elements();
        while (elements.hasMoreElements()) {
            System.out.println("   - " + elements.nextElement());
        }
        
        // 5. **Removing an element**
        languageVector.remove(1); // Removes "Python"
        System.out.println("\n5. After removing element at index 1:");
        System.out.println("   Vector contents: " + languageVector);
        System.out.println("   Size: " + languageVector.size());

        // Note: The thread-safety of Vector is its main feature, but demonstrating it
        // requires setting up multiple threads, which is complex for a basic example.
        // In single-threaded code like this, an ArrayList would be much more performant.
    }
}