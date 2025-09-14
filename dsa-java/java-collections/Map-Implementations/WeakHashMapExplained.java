// File: WeakHashMapExplained.java

/*
 ====================================================================================================
 |                                 THEORY OF WEAKHASHMAP IN JAVA                                    |
 ====================================================================================================

 ## 1. What is a WeakHashMap?

 A `WeakHashMap` is a special implementation of the `Map` interface that stores its keys using
 **"weak references"**. This core feature means that an entry in a `WeakHashMap` can be automatically
 discarded and removed by the garbage collector (GC) if its key is no longer in ordinary ("strong")
 use elsewhere in the application.

 This behavior makes it an excellent tool for building memory-sensitive caches.

 ---

 ## 2. The Core Concept: Strong vs. Weak References

 To understand `WeakHashMap`, you must first understand the difference between a strong and a
 weak reference in Java.

 ### A. Strong Reference (The Default)
 This is the normal way you create references in Java.
 `Object myObject = new Object();` // myObject is a strong reference
 As long as a **strong reference** points to an object, the garbage collector **cannot** reclaim
 that object's memory. This is usually what you want, but it can cause memory leaks.

 **Problem with HashMap:** If you put a key-value pair into a regular `HashMap`, the map creates
 a strong reference to the key. Even if you lose all *other* strong references to that key object,
 the one inside the `HashMap` will keep it alive forever, preventing it from being garbage
 collected. This can lead to the map growing indefinitely with obsolete entries.

 ### B. Weak Reference
 A **weak reference** is a reference that is not strong enough to force an object to remain in
 memory. An object that is only reachable via weak references is considered "weakly reachable"
 and is eligible for garbage collection at the next GC cycle.

 `WeakHashMap` uses exactly this principle for its keys.

 ---

 ## 3. How WeakHashMap Works

 1.  **Storage**: When you call `put(key, value)`, the `WeakHashMap` does not store the key
     directly. Instead, it wraps the key in a special `WeakReference` object and stores that.
     The value, however, is held by a strong reference from the map's entry.

 2.  **Garbage Collection**: When the GC runs, it scans for objects that are no longer strongly
     referenced anywhere in your application. If it finds a key object that was used in the
     `WeakHashMap` and all other strong references to it are gone, the GC will reclaim that
     key object's memory.

 3.  **Automatic Removal**: After the GC has reclaimed a key, the `WeakReference` object inside
     the `WeakHashMap` now points to nothing. The `WeakHashMap` is designed to detect this.
     During subsequent operations (like `get()`, `put()`, `size()`, etc.), it checks for these
     "stale" entries and removes them from the map.

 The outcome is that the entry magically disappears from the map once its key is no longer needed
 by the rest of your program, thus preventing memory leaks.

 

 ## 4. Key Characteristics

 - **Self-Cleaning**: It automatically removes entries when their keys are no longer in use.
 - **Unpredictable Size**: Because the GC runs in the background at non-deterministic times, the
   `size()` of a `WeakHashMap` can appear to change unexpectedly.
 - **Implementation**: Internally, it uses a `java.lang.ref.ReferenceQueue` to efficiently
   get notified about keys that have been garbage collected, allowing for quick cleanup.

 ====================================================================================================
 |                                BUILT-IN WEAKHASHMAP FUNCTIONS                                    |
 ====================================================================================================

 `WeakHashMap` implements the `Map` interface and provides all the standard methods. There are no
 unique public methods to learn, but you must understand that the behavior of these common methods
 is influenced by the garbage collector.

 ### Core Operations
 - `V put(K key, V value)`: Adds an entry. The key is stored as a weak reference.
 - `V get(Object key)`: Retrieves a value. May trigger a cleanup of stale entries.
 - `V remove(Object key)`: Removes an entry.
 - `boolean containsKey(Object key)`: Checks for a key.
 - `int size()`: Returns the *current* number of entries. This value can decrease if the GC runs.
 - `void clear()`: Removes all mappings.

*/

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * This class demonstrates the theory and practical usage of WeakHashMap in Java.
 * All the detailed theory is provided in the multi-line comment block at the top of this file.
 */
public class WeakHashMapExplained {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("--- 1. WeakHashMap Demonstration (Self-Cleaning) ---");
        Map<Integer, String> weakMap = new WeakHashMap<>();
        
        // Create a key with a strong reference. We use 'new Integer' to ensure it's a new object on the heap.
        Integer userSessionId = 12345;
        
        weakMap.put(userSessionId, "User data for session 12345");
        
        System.out.println("WeakHashMap before GC: " + weakMap);
        System.out.println("WeakHashMap size before GC: " + weakMap.size()); // Should be 1
        
        // Remove the ONLY strong reference to the key.
        System.out.println("\nRemoving the strong reference to the key (userSessionId = null)...");
        userSessionId = null;
        
        // At this point, the key '12345' is only weakly referenced by the map.
        // We suggest that the JVM runs the garbage collector.
        // NOTE: This is not a guaranteed command, but it's usually effective for demonstrations.
        System.out.println("Suggesting Garbage Collection (System.gc())...");
        System.gc();
        
        // For the GC to have time to run and the map to clean up, we can wait a moment.
        Thread.sleep(100);

        // The entry is now likely gone. Accessing the map (e.g., with size()) triggers the cleanup.
        System.out.println("WeakHashMap size after GC: " + weakMap.size()); // Should be 0
        System.out.println("WeakHashMap after GC: " + weakMap);
        

        System.out.println("\n\n--- 2. HashMap Contrast (Potential Memory Leak) ---");
        Map<Integer, String> strongMap = new HashMap<>();

        // Create another key with a strong reference for the HashMap.
        Integer orderId = 98765;
        
        strongMap.put(orderId, "Details for order 98765");
        
        System.out.println("HashMap before GC: " + strongMap);
        
        // Remove the strong reference to the key.
        System.out.println("\nRemoving the strong reference to the key (orderId = null)...");
        orderId = null;
        
        // Even after removing our reference, the HashMap holds its own STRONG reference.
        System.out.println("Suggesting Garbage Collection (System.gc())...");
        System.gc();
        Thread.sleep(100);

        // The entry will NOT be removed because the HashMap's reference prevents GC.
        System.out.println("HashMap size after GC: " + strongMap.size()); // Will still be 1
        System.out.println("HashMap after GC: " + strongMap);
        System.out.println("This demonstrates how a HashMap can hold onto obsolete objects.");
    }
}

/*
 ====================================================================================================
 |                                 WHEN TO USE A WEAKHASHMAP?                                       |
 ====================================================================================================

 A `WeakHashMap` is the perfect tool for implementing **memory-sensitive caches** or storing
 metadata that should not prevent an object from being deallocated. You should use it when you want
 to associate some information with an object, but only for as long as that object is actively being
 used elsewhere in your application. Once the primary object is discarded, you want its associated
 data in the map to be discarded as well, automatically and without any manual cleanup code.

 Common use cases include:

 - **Caching**: This is the canonical example. Imagine caching the results of an expensive
   computation. You can use the input object as the key and the computed result as the value.
   When the input object is no longer used by the application, the `WeakHashMap` automatically
   drops the cached result, freeing up memory. The result can be recomputed if needed again later.

 - **Storing Metadata**: You can use it to associate extra information with an object without
   "owning" it. For example, you could map objects to their last-modified timestamps or permissions.
   When the object is garbage collected, its associated metadata is cleaned up too. This avoids the
   need to alter the original object's class to add a metadata field.

 - **Listener/Observer Registries**: In event-driven systems, a `WeakHashMap` can be used to
   maintain a list of listener objects. This prevents the registry itself from keeping listeners
   alive, which can be a common source of memory leaks in GUI applications or complex systems.

 In essence, use `WeakHashMap` whenever the lifetime of a key-value pair in your map should be
 governed by the lifetime of the key object itself.

*/