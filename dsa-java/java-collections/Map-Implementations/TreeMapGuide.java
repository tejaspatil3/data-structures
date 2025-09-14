import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/*
 ====================================================================================================
 # THEORY AND CONCEPTS: JAVA TREEMAP (java.util.TreeMap)
 ====================================================================================================

 ## 1. What is a TreeMap?

 A **TreeMap** is a `Map` implementation that stores its key-value pairs in a sorted order. The
 sorting is based on the **natural ordering** of the keys (e.g., alphabetical for Strings, numerical
 for Integers) or by a custom **`Comparator`** provided when the map is created.

 It implements the `NavigableMap` interface, which provides a rich set of methods for searching,
 navigation, and range-based queries.

 ## 2. Underlying Implementation: The Red-Black Tree 🌳

 - `TreeMap` is internally backed by a **Red-Black Tree**, which is a type of self-balancing
   binary search tree.
 - A binary search tree keeps its elements ordered, allowing for fast lookups.
 - The "self-balancing" nature of the Red-Black Tree ensures that the tree's height remains
   proportional to `log(n)`, where `n` is the number of entries.
 - This structure guarantees that the performance for adding, removing, and searching for
   entries is a consistent **O(log n)**.

 

 ## 3. Key Characteristics

 - **Sorted Order**: Its most defining feature. When you iterate over a `TreeMap`, the keys will
   always be in sorted order.
 - **Performance**: Guarantees `O(log n)` time for `put()`, `get()`, `remove()`, and `containsKey()`
   operations. This is slower than `HashMap`'s average O(1) but provides the benefit of sorting.
 - **No `null` Keys**: A `TreeMap` does not allow `null` keys because `null` cannot be compared to
   other keys to determine its position in the tree. It does, however, allow `null` values.
 - **Not Synchronized**: `TreeMap` is not thread-safe. For concurrent access, you should use
   `java.util.concurrent.ConcurrentSkipListMap` or wrap it with `Collections.synchronizedMap()`.
 - **NavigableMap Interface**: It implements this powerful interface, giving it access to methods
   like `floorKey()`, `ceilingKey()`, `headMap()`, `tailMap()`, etc., which are perfect for
   range-based queries.

 ## 4. `HashMap` vs. `TreeMap`

 | Feature         | `HashMap`                                    | `TreeMap`                                      |
 |-----------------|----------------------------------------------|------------------------------------------------|
 | **Ordering** | Unordered; iteration order is not guaranteed. | Sorted by key (natural order or `Comparator`). |
 | **Performance** | Average **O(1)** for most operations.         | Guaranteed **O(log n)** for most operations.    |
 | **`null` key** | Allows one `null` key.                       | Does **not** allow `null` keys.                |
 | **Interface** | Implements `Map`.                            | Implements `NavigableMap`, `SortedMap`, `Map`.   |
 | **Use Case** | Best for fast lookups where order is irrelevant. | Best when you need sorted keys or range queries. |

 ---

 ====================================================================================================
 # BUILT-IN METHODS
 ====================================================================================================

 | Method                                   | Description                                                                     |
 |------------------------------------------|---------------------------------------------------------------------------------|
 | **----- Basic Map Methods -----** |                                                                                 |
 | `put(K key, V value)`                    | Associates the value with the key. O(log n).                                    |
 | `get(Object key)`                        | Returns the value for the given key, or `null` if not found. O(log n).          |
 | `remove(Object key)`                     | Removes the mapping for a key. O(log n).                                        |
 | `containsKey(Object key)`                | Returns `true` if the map contains a mapping for the key. O(log n).               |
 | `size()`                                 | Returns the number of key-value mappings. O(1).                                 |
 |                                          |                                                                                 |
 | **----- Navigational Methods -----** | (These make TreeMap powerful)                                                   |
 | `firstKey()` / `firstEntry()`            | Returns the first (lowest) key or entry.                                        |
 | `lastKey()` / `lastEntry()`              | Returns the last (highest) key or entry.                                        |
 | `pollFirstEntry()` / `pollLastEntry()`   | Retrieves and removes the first or last entry.                                  |
 | `lowerKey(K key)` / `lowerEntry(K key)`  | Returns the greatest key/entry strictly less than the given key.                |
 | `floorKey(K key)` / `floorEntry(K key)`  | Returns the greatest key/entry less than or equal to the given key.             |
 | `higherKey(K key)` / `higherEntry(K key)`| Returns the least key/entry strictly greater than the given key.                |
 | `ceilingKey(K key)`/`ceilingEntry(K key)`| Returns the least key/entry greater than or equal to the given key.             |
 |                                          |                                                                                 |
 | **----- Submap View Methods -----** | (These return a view of the map, not a new copy)                                |
 | `headMap(K toKey)`                       | Returns a view of the portion of this map whose keys are less than `toKey`.     |
 | `tailMap(K fromKey)`                     | Returns a view of the portion of this map whose keys are >= `fromKey`.          |
 | `subMap(K fromKey, K toKey)`             | Returns a view of the portion of this map whose keys range from `fromKey` to `toKey`. |
 | `descendingMap()`                        | Returns a reverse order view of the mappings contained in this map.             |

*/

public class TreeMapGuide {

    public static void main(String[] args) {

        System.out.println("--- EXAMPLE 1: Basic Operations & Natural Sorted Order ---");
        // Keys (Integers) are stored in their natural ascending order.
        TreeMap<Integer, String> filePermissions = new TreeMap<>();
        filePermissions.put(4, "Read");
        filePermissions.put(2, "Write");
        filePermissions.put(1, "Execute");
        filePermissions.put(6, "Read + Write");

        System.out.println("File Permissions (sorted by key): " + filePermissions);

        // Iterating shows the sorted order
        System.out.println("Iterating over entries:");
        for (Map.Entry<Integer, String> entry : filePermissions.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        System.out.println("\n--- EXAMPLE 2: Using a Custom Comparator (Reverse Order) ---");
        // Using a comparator to store keys (Strings) in descending alphabetical order.
        TreeMap<String, Integer> userScores = new TreeMap<>(Collections.reverseOrder());
        userScores.put("Charlie", 88);
        userScores.put("Alice", 95);
        userScores.put("Bob", 72);
        userScores.put("David", 95);

        System.out.println("User Scores (sorted descending): " + userScores);

        System.out.println("\n--- EXAMPLE 3: Powerful Navigational Methods ---");
        // Using a TreeMap to represent events on a timeline (key = hour of day)
        TreeMap<Integer, String> dailySchedule = new TreeMap<>();
        dailySchedule.put(9, "Morning Standup");
        dailySchedule.put(12, "Lunch Break");
        dailySchedule.put(15, "Team Meeting");
        dailySchedule.put(18, "Log Off");

        System.out.println("Today's Schedule: " + dailySchedule);

        // Find the first and last events of the day
        System.out.println("First event: " + dailySchedule.firstEntry()); // Key: 9
        System.out.println("Last event: " + dailySchedule.lastEntry());   // Key: 18

        // Let's say it's 13:00 (1 PM), what's the next scheduled event?
        System.out.println("Next event after 13:00 is at " + dailySchedule.higherEntry(13));

        // What was the last event that happened at or before 12:00?
        System.out.println("Last event at or before 12:00 was at " + dailySchedule.floorEntry(12));

        System.out.println("\n--- EXAMPLE 4: Using Submap Views for Range Queries ---");
        System.out.println("Original Schedule: " + dailySchedule);

        // Get a view of all events happening in the afternoon (from 12:00 onwards)
        Map<Integer, String> afternoonSchedule = dailySchedule.tailMap(12);
        System.out.println("Afternoon Schedule (tailMap from 12): " + afternoonSchedule);

        // Get a view of all events happening strictly before 15:00 (3 PM)
        Map<Integer, String> morningSchedule = dailySchedule.headMap(15, false); // `false` means exclusive
        System.out.println("Morning/Lunch Schedule (headMap before 15): " + morningSchedule);

        // IMPORTANT: The submap is a VIEW, not a copy. Changes reflect in the original.
        afternoonSchedule.put(16, "Code Review");
        System.out.println("Original schedule after adding to submap view: " + dailySchedule);
    }
}

/*
 ====================================================================================================
 # WHEN TO USE A TREEMAP? (PRACTICAL APPLICATIONS)
 ====================================================================================================

 A `TreeMap` is the perfect data structure when you need not only key-value storage but also the
 ability to maintain a **sorted order** of keys and perform efficient **range queries**. While a
 `HashMap` offers faster lookups on average, its lack of order makes it unsuitable for many
 scenarios. `TreeMap` shines in applications where the relationship and order between keys are as
 important as the values themselves.

 Common use cases include:

 - **Data Indexing and Dictionaries**: When creating an index for a book or a dictionary, you need
   to store words and their page numbers or definitions. `TreeMap` naturally keeps these words in
   alphabetical order, making it easy to print or search.

 - **Calendars, Timelines, and Event Scheduling**: Storing events with `DateTime` objects as keys
   allows a calendar application to easily display events in chronological order. Its navigational
   methods make it trivial to find "all events next week" (`subMap()`), "the next upcoming
   appointment" (`higherEntry()`), or "the last meeting you had" (`floorEntry()`).

 - **Leaderboards and Rankings**: In gaming or analytics, you can store player IDs or usernames as
   keys and their scores as values. The `TreeMap` will keep the players sorted by name. If you need
   to sort by score, you can use a `TreeMap<Score, List<Player>>` to group players with the same
   score. `descendingMap()` is perfect for displaying a leaderboard from highest to lowest.

 - **Price Range Queries**: An e-commerce site can store products in a `TreeMap` with prices as
   keys. This allows for efficient filtering of products within a specific price range, for example,
   finding all items between $50 and $100 using `subMap()`.

 - **Data Aggregation and Histograms**: When analyzing data, you might count occurrences of items
   (like word frequencies). Storing them in a `TreeMap` allows you to present the results in a
   sorted, human-readable format.

*/