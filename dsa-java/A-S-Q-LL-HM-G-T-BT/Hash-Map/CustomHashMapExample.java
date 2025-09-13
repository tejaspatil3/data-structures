/*
================================================================================
|| THEORY: UNDERSTANDING AND BUILDING A HASHMAP FROM SCRATCH
================================================================================

1.  WHAT IS A HASHMAP?
    - A HashMap is a data structure that stores data in **key-value pairs**. It allows for
      very fast (average time O(1)) insertion, deletion, and retrieval of data.
    - It is an implementation of the Map interface. Unlike an array that uses an integer
      index, a HashMap uses a `key` object to access a `value` object.

2.  CORE CONCEPTS
    - **Key-Value Pair**: The fundamental unit. You store a `value` (e.g., a phone number)
      associated with a unique `key` (e.g., a person's name).
    - **Hashing**: The process of converting a key of any type into an integer. This is
      done via the key's `hashCode()` method. This integer will be used to determine where
      to store the key-value pair.
    - **Buckets**: A HashMap internally uses an array. Each slot in this array is called a
      "bucket". The result of the hashing function determines which bucket an entry belongs to.
      `bucketIndex = key.hashCode() % array_capacity` is a simplified view of this.
    - **Collision**: A collision occurs when two different keys produce the same hash code,
      meaning they map to the same bucket index. This is common and must be handled.

3.  THE ANALOGY
    - Think of a large library with 1000 shelves (the `buckets`). 📚
    - To store a new book (the `value`), you don't place it alphabetically. Instead, you
      use its unique ISBN (the `key`). You run the ISBN through a special function (`hashCode()`)
      which tells you to "store this book on shelf #582".
    - To find the book later, you just run the ISBN through the same function again, which
      points you directly to shelf #582, so you don't have to search the whole library.

4.  COLLISION RESOLUTION: SEPARATE CHAINING
    - What if two different books are assigned to shelf #582? This is a collision.
    - With **Separate Chaining**, each shelf (bucket) doesn't just hold one book; it holds
      a **linked list** of books.
    - If a new book hashes to shelf #582, we simply go to that shelf and add the book to the
      end of the small linked list of books already there.
    - When retrieving a book, we go to the correct shelf and then search through the short
      linked list on that shelf to find the one with the matching ISBN.
    - 

5.  LOAD FACTOR AND REHASHING
    - **Load Factor**: A measure of how full the hash map is allowed to get before its
      capacity is automatically increased. It's calculated as `(number of entries / number of buckets)`.
      A common value is 0.75 (or 75%).
    - **Rehashing**: When the load factor is exceeded, the linked lists in the buckets start
      getting too long, and performance degrades. To fix this, the HashMap performs a **rehash**:
      1. It creates a new, larger internal array (usually double the size).
      2. It iterates through every key-value pair in the old map.
      3. For each pair, it recalculates the hash code based on the *new* capacity and inserts it
         into the new, larger array.
      This is an expensive operation but ensures that performance remains fast on average.
*/

/**
 * Main public class to run the HashMap examples.
 */
public class CustomHashMapExample {
    public static void main(String[] args) {

        System.out.println("--- 1. Basic Operations: put, get, remove ---");
        MyHashMap<String, Integer> studentScores = new MyHashMap<>();
        studentScores.put("Alice", 95);
        studentScores.put("Bob", 82);
        studentScores.put("Charlie", 99);
        System.out.println("Map contents:");
        studentScores.printMap();
        System.out.println("Alice's score: " + studentScores.get("Alice"));
        System.out.println("David's score: " + studentScores.get("David")); // Should be null

        System.out.println("\n--- 2. Updating a Key ---");
        System.out.println("Bob's old score: " + studentScores.get("Bob"));
        studentScores.put("Bob", 88); // This should update the existing value
        System.out.println("Bob's new score: " + studentScores.get("Bob"));
        System.out.println("Current map size: " + studentScores.size());

        System.out.println("\n--- 3. Removing a Key ---");
        System.out.println("Removing Charlie...");
        studentScores.remove("Charlie");
        System.out.println("Is Charlie in the map? " + (studentScores.get("Charlie") == null));
        studentScores.printMap();

        System.out.println("\n--- 4. Handling Collisions ---");
        // We use a custom class with a controlled hashCode to guarantee a collision.
        MyHashMap<Book, String> library = new MyHashMap<>();
        Book book1 = new Book("The Hobbit", 1937); // hashCode is based on year
        Book book2 = new Book("Foundation", 1951);
        Book book3 = new Book("Dune", 1965);
        Book book4 = new Book("Neuromancer", 1984);
        Book book5 = new Book("Hyperion", 1989);
        // These two will have the same hashCode % capacity if capacity is 16
        Book collidingBook1 = new Book("Collision 1", 5);
        Book collidingBook2 = new Book("Collision 2", 21);
        
        library.put(book1, "Shelf A1");
        library.put(book2, "Shelf B2");
        library.put(collidingBook1, "Shelf C3");
        System.out.println("Putting '" + collidingBook1.title + "' in the library...");
        System.out.println("Putting '" + collidingBook2.title + "' in the library (will collide)...");
        library.put(collidingBook2, "Shelf D4"); // This will collide with collidingBook1
        System.out.println("Library contents (notice the linked list in one bucket):");
        library.printMap();
        System.out.println("Retrieving '" + collidingBook2.title + "': " + library.get(collidingBook2));

        System.out.println("\n--- 5. Automatic Resizing (Rehashing) ---");
        MyHashMap<Integer, String> rehashTestMap = new MyHashMap<>(4); // Start with a small capacity
        System.out.println("Initial Capacity: 4. Load Factor Threshold: 3 entries.");
        System.out.println("Putting 1, 2, 3...");
        rehashTestMap.put(1, "A");
        rehashTestMap.put(2, "B");
        rehashTestMap.put(3, "C"); // This will trigger the rehash
        System.out.println("Map after rehash:");
        rehashTestMap.printMap();
        System.out.println("Current size: " + rehashTestMap.size());
    }
}

// Helper class for collision demonstration
class Book {
    String title;
    int year;
    public Book(String title, int year) { this.title = title; this.year = year; }
    
    @Override
    public int hashCode() {
        return year; // Simple hash code based only on the year
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return year == book.year && title.equals(book.title);
    }
}


//==============================================================================
//== FROM-SCRATCH IMPLEMENTATION OF A HASHMAP AND ITS ENTRY
//==============================================================================

/**
 * The building block of our HashMap. This is a node in the linked list
 * that exists within each bucket of the hash map.
 */
class MapEntry<K, V> {
    K key;
    V value;
    MapEntry<K, V> next; // Pointer to the next entry in the case of a collision

    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}

/**
 * A custom, generic implementation of a HashMap using separate chaining.
 */
class MyHashMap<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private MapEntry<K, V>[] buckets;
    private int capacity;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        this(INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int initialCapacity) {
        this.capacity = initialCapacity;
        this.buckets = new MapEntry[this.capacity];
    }

    private int getBucketIndex(K key) {
        if (key == null) return 0;
        // The hash code can be negative, so we use Math.abs
        return Math.abs(key.hashCode() % capacity);
    }

    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        MapEntry<K, V> head = buckets[bucketIndex];

        // Traverse the linked list in the bucket to see if the key already exists
        MapEntry<K, V> current = head;
        while (current != null) {
            if (current.key == null && key == null || (current.key != null && current.key.equals(key))) {
                current.value = value; // Update existing key
                return;
            }
            current = current.next;
        }

        // If key doesn't exist, add a new entry to the front of the linked list
        MapEntry<K, V> newEntry = new MapEntry<>(key, value);
        newEntry.next = head;
        buckets[bucketIndex] = newEntry;
        size++;

        // Check if we need to rehash
        if ((1.0 * size) / capacity >= LOAD_FACTOR) {
            rehash();
        }
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        MapEntry<K, V> current = buckets[bucketIndex];
        while (current != null) {
            if (current.key == null && key == null || (current.key != null && current.key.equals(key))) {
                return current.value; // Key found
            }
            current = current.next;
        }
        return null; // Key not found
    }



    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        MapEntry<K, V> head = buckets[bucketIndex];
        MapEntry<K, V> prev = null;
        MapEntry<K, V> current = head;

        while (current != null) {
            if (current.key == null && key == null || (current.key != null && current.key.equals(key))) {
                if (prev == null) {
                    // It's the head of the linked list
                    buckets[bucketIndex] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null; // Key not found
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        System.out.println("   -> Rehashing: Load factor exceeded. Doubling capacity.");
        MapEntry<K, V>[] oldBuckets = buckets;
        this.capacity *= 2;
        this.buckets = new MapEntry[this.capacity];
        this.size = 0;

        // Iterate through all entries in the old buckets and put them into the new one
        for (MapEntry<K, V> headNode : oldBuckets) {
            MapEntry<K, V> current = headNode;
            while (current != null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
    }

    public int size() {
        return size;
    }

    public void printMap() {
        for (int i = 0; i < capacity; i++) {
            MapEntry<K, V> current = buckets[i];
            if (current != null) {
                System.out.print("   Bucket " + i + ": ");
                while (current != null) {
                    System.out.print("[" + current.key + "=" + current.value + "] -> ");
                    current = current.next;
                }
                System.out.println("null");
            }
        }
    }
}