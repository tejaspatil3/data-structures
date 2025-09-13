
/* ArrayList vs. LinkedList
The LinkedList class is a collection which can contain many objects of the same type, just like the ArrayList.

The LinkedList class has all of the same methods as the ArrayList class because they both implement the List interface. This means that you can add items, change items, remove items and clear the list in the same way.

However, while the ArrayList class and the LinkedList class can be used in the same way, they are built very differently.

How the ArrayList works
The ArrayList class has a regular array inside it. When an element is added, it is placed into the array. If the array is not big enough, a new, larger array is created to replace the old one and the old one is removed.

How the LinkedList works
The LinkedList stores its items in "containers." The list has a link to the first container and each container has a link to the next container in the list. To add an element to the list, the element is placed into a new container and that container is linked to one of the other containers in the list.

When To Use
Use an ArrayList for storing and accessing data, and LinkedList to manipulate data.


Method	        Description	
addFirst()	    Adds an item to the beginning of the list	
addLast()	    Add an item to the end of the list	
removeFirst()	Remove an item from the beginning of the list	
removeLast()	Remove an item from the end of the list	
getFirst()	    Get the item at the beginning of the list	
getLast()	    Get the item at the end of the list 
*/

// Import the LinkedList class from the java.util package.
import java.util.LinkedList;
// import java.util.List;

public class LinkedListConcept {

    public static void main(String[] args) {

        /*
        --------------------------
        -- THEORY: WHAT IS A LINKEDLIST? --
        --------------------------

        1.  **A Chain of Nodes**: A LinkedList stores its elements in a sequence of "nodes". ⛓️ Each node holds
            three pieces of information:
            - The actual element (the data).
            - A reference (or "link") to the previous node.
            - A reference (or "link") to the next node.
            This structure is known as a **doubly-linked list**.

            

            Analogy: Think of a train, where each car is a node. Each car is connected to the one
            before it and the one after it.

        2.  **Performance Characteristics**: The node-based structure gives LinkedList a unique performance profile
            compared to ArrayList.
            * **Fast Insertions and Deletions**: Adding or removing elements from the beginning, end, or middle of the list
                is very fast (O(1) time complexity) because it only involves updating a few links. There's no need to shift
                all the subsequent elements as you would in an ArrayList. This is its biggest advantage.
            * **Slow Random Access**: Getting an element by its index (e.g., `list.get(500)`) is slow (O(n) time complexity).
                The list must be traversed from the beginning (or end) node by node until it reaches the desired index.

        3.  **Implements List and Deque**:
            * Like ArrayList, it implements the `List` interface, so it has all the standard list methods (`add`, `get`, `remove`, `set`, etc.).
            * It also implements the `Deque` (Double Ended Queue) interface. This provides a rich set of methods for efficiently
                adding and removing elements from both the head (beginning) and the tail (end) of the list.

        4.  **Key Characteristics**:
            * **Ordered**: It maintains the insertion order of elements.
            * **Dynamic Size**: It can grow and shrink as needed.
            * **Allows Duplicates & Nulls**: You can store duplicate values and null values.
            * **Higher Memory Usage**: It typically consumes more memory than an ArrayList because each node needs to store the data
                plus two extra references (pointers).
            * **Not Synchronized**: It is not thread-safe.

        5.  **When to use LinkedList?**:
            * Use a LinkedList when you have a large list and frequently perform insertions or deletions, especially at the
                beginning or end.
            * Use it when you need the functionality of a queue or a deque (e.g., `addFirst`, `removeLast`).
            * Avoid it when you need frequent random access to elements by index, as this is slow. For that, `ArrayList` is much better.

        6.  **Common Deque Methods**:
            * `addFirst(element)` / `addLast(element)`: Adds an element to the beginning/end of the list.
            * `removeFirst()` / `removeLast()`: Removes and returns the element from the beginning/end.
            * `getFirst()` / `getLast()`: Retrieves, but does not remove, the element from the beginning/end.
        */

        // --------------------------
        // -- EXAMPLE: A PRACTICAL DEMONSTRATION --
        // --------------------------

        // 1. **Creating a LinkedList**
        // It's common practice to declare it as a List or a LinkedList depending on your needs.
        // Declaring as LinkedList gives access to methods like addFirst(), etc.
        LinkedList<String> travelItinerary = new LinkedList<>();

        // 2. **Adding elements**
        // Using standard List methods
        travelItinerary.add("Paris");
        travelItinerary.add("Rome");

        // Using specific LinkedList (Deque) methods
        travelItinerary.addFirst("London"); // Add to the beginning
        travelItinerary.addLast("Athens");   // Add to the end
        System.out.println("1. Initial itinerary: " + travelItinerary);

        // 3. **Accessing elements**
        // Using Deque methods is efficient for ends.
        String firstCity = travelItinerary.getFirst();
        String lastCity = travelItinerary.getLast();
        System.out.println("2. First stop: " + firstCity);
        System.out.println("   Last stop: " + lastCity);

        // Accessing by index is possible but less efficient.
        String secondCity = travelItinerary.get(1);
        System.out.println("3. Second stop (using get(1)): " + secondCity);

        // 4. **Removing elements**
        // Removing from the ends is very efficient.
        String removedFirst = travelItinerary.removeFirst();
        System.out.println("4. Visited and removed '" + removedFirst + "'.");
        String removedLast = travelItinerary.removeLast();
        System.out.println("   Cancelled and removed '" + removedLast + "'.");
        System.out.println("   Updated itinerary: " + travelItinerary);
        
        // You can also remove by object or index.
        travelItinerary.remove("Paris");
        System.out.println("5. After removing 'Paris': " + travelItinerary);

        // 6. **Iterating through the LinkedList**
        // Iteration works just like with an ArrayList.
        travelItinerary.add("Venice");
        travelItinerary.add("Berlin");
        System.out.println("\n6. Final itinerary stops:");
        for (String city : travelItinerary) {
            System.out.println("   - " + city);
        }
    }
}
