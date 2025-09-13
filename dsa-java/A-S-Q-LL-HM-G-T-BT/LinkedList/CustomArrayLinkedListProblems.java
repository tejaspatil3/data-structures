import java.util.Arrays;

/**
 * Main public class to run test problems on our custom-built MyArrayList.
 */
public class CustomArrayLinkedListProblems {
    public static void main(String[] args) {
        // --- Problem 1: Reverse the List In-Place ---
        System.out.println("## Problem 1: Reverse the List In-Place");
        MyArrayList<Integer> list1 = new MyArrayList<>();
        list1.add(10);
        list1.add(20);
        list1.add(30);
        list1.add(40);
        list1.add(50);
        System.out.println("   List before reverse: " + list1);
        reverse(list1);
        System.out.println("   List after reverse:  " + list1);
        System.out.println("--------------------------------------------------\n");

        // --- Problem 2: Find the Middle Element ---
        System.out.println("## Problem 2: Find the Middle Element");
        System.out.println("   List: " + list1); // Using the now-reversed list
        Integer middle = findMiddleElement(list1);
        System.out.println("   The middle element is: " + middle);
        list1.add(60);
        System.out.println("   List with even size: " + list1);
        middle = findMiddleElement(list1);
        System.out.println("   The middle element is: " + middle);
        System.out.println("--------------------------------------------------\n");

        // --- Problem 3: Find the Nth Element from the End ---
        System.out.println("## Problem 3: Find the Nth Element from the End");
        System.out.println("   List: " + list1);
        int n = 3;
        Integer nth = findNthFromEnd(list1, n);
        System.out.println("   The " + n + "rd element from the end is: " + nth);
        System.out.println("--------------------------------------------------\n");

        // --- Problem 4: Remove All Occurrences of an Element ---
        System.out.println("## Problem 4: Remove All Occurrences of an Element");
        MyArrayList<String> list2 = new MyArrayList<>();
        list2.add("A");
        list2.add("B");
        list2.add("A");
        list2.add("C");
        list2.add("A");
        System.out.println("   List before removing all 'A's: " + list2);
        removeAllOccurrences(list2, "A");
        System.out.println("   List after removing all 'A's:  " + list2);
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Problem 1: Reverses the list in-place.
     * Approach: Use a two-pointer technique. One pointer starts at the beginning (`left`)
     * and one at the end (`right`). Swap the elements at these pointers and move
     * them towards the center until they meet.
     * Time Complexity: O(n), Space Complexity: O(1).
     */
    public static <E> void reverse(MyArrayList<E> list) {
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            // Swap elements at left and right pointers
            E temp = list.get(left);
            list.set(left, list.get(right));
            list.set(right, temp);

            left++;
            right--;
        }
    }

    /**
     * Problem 2: Finds the middle element of the list.
     * Approach: For an array-based list, this is a simple calculation. The middle
     * index is simply `size / 2`. This highlights the O(1) random access advantage.
     * Time Complexity: O(1).
     */
    public static <E> E findMiddleElement(MyArrayList<E> list) {
        if (list.isEmpty()) {
            throw new IllegalStateException("List is empty.");
        }
        return list.get(list.size() / 2);
    }

    /**
     * Problem 3: Finds the Nth element from the end of the list.
     * Approach: Calculate the equivalent index from the beginning.
     * The index from the start is `size - n`.
     * Time Complexity: O(1).
     */
    public static <E> E findNthFromEnd(MyArrayList<E> list, int n) {
        if (n <= 0 || n > list.size()) {
            throw new IllegalArgumentException("Invalid value for N: " + n);
        }
        int indexFromStart = list.size() - n;
        return list.get(indexFromStart);
    }

    /**
     * Problem 4: Removes all occurrences of a given element from the list.
     * Approach: Iterate through the list with an index `i`. When an element to be
     * removed is found at `list.get(i)`, call `list.remove(i)`.
     * Crucially, after removing, we must decrement `i` because all subsequent
     * elements have shifted left, and we need to re-check the current index.
     * Time Complexity: O(n^2) in the worst case because each remove is O(n).
     */
    public static <E> void removeAllOccurrences(MyArrayList<E> list, E element) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(element)) {
                list.remove(i);
                i--; // Decrement i to re-check the current index
            }
        }
    }
}


/**
 * A custom implementation of a Dynamic Array (ArrayList).
 * It is generic, meaning it can hold any type of object.
 * @param <E> The type of element this list will hold.
 */
class MyArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data;
    private int size;
    private int capacity;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative.");
        }
        this.capacity = initialCapacity;
        this.data = new Object[this.capacity];
        this.size = 0;
    }

    public void add(E element) {
        ensureCapacity();
        data[size++] = element;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) data[index];
    }

    /**
     * NEW METHOD: Sets the element at a specific index to a new value.
     */
    public void set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        data[index] = element;
    }
    
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        E removedElement = get(index);
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null;
        return removedElement;
    }
    
    private void ensureCapacity() {
        if (size == capacity) {
            int newCapacity = (capacity == 0) ? DEFAULT_CAPACITY : capacity * 2;
            this.data = Arrays.copyOf(this.data, newCapacity);
            this.capacity = newCapacity;
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}