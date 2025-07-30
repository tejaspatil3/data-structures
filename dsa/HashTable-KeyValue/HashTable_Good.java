// import java.util.Objects;

/**
 * Node class represents a single entry (key-value pair) in the hash table's linked list.
 * @param <T> The type of the value.
 */
class Node<T> {
    String key;
    T value;
    Node<T> next;

    public Node(String key, T value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}

/**
 * A generic Hash Table implementation using separate chaining for collision resolution.
 * @param <T> The type of the values stored in the hash table.
 */
class HashTable<T> {
    private int cs; // current size
    private int ts; // table size
    private Node<T>[] table;

    /**
     * Constructor initializes the hash table with a default size.
     */
    @SuppressWarnings("unchecked")
    public HashTable() {
        this.cs = 0;
        this.ts = 10; // Initial table size
        this.table = (Node<T>[]) new Node[ts]; // Create the array of Node references

        // Initialize all buckets to null
        for (int i = 0; i < ts; i++) {
            table[i] = null;
        }
    }

    /**
     * Calculates the hash index for a given key.
     * This function now correctly uses the table size (ts) for the modulo operation,
     * ensuring keys are distributed across the entire table.
     * @param key The string key.
     * @return The calculated bucket index.
     */
    private int hashFunction(String key) {
        // A simple but effective polynomial rolling hash.
        // Using Objects.hashCode() is also a great and simple alternative.
        int hashCode = 0;
        int power = 1;
        final int prime = 37; // A larger prime number for better distribution

        for (char ch : key.toCharArray()) {
            hashCode = (hashCode + (ch * power));
            power = (power * prime);
        }
        // Ensure the index is non-negative and within the table bounds.
        return Math.abs(hashCode) % ts;
    }

    /**
     * Inserts a key-value pair into the hash table.
     * If the load factor exceeds 0.7, it triggers a rehash.
     * @param key The key for the entry.
     * @param value The value to be stored.
     */
    public void insert(String key, T value) {
        int idx = hashFunction(key);

        Node<T> newNode = new Node<>(key, value);

        // Insert at the head of the linked list for that bucket
        newNode.next = table[idx];
        table[idx] = newNode;
        cs++;

        // Check load factor and rehash if necessary
        float loadFactor = (float) cs / (float) ts;
        if (loadFactor > 0.7) {
            System.out.println("Load factor " + String.format("%.2f", loadFactor) + " > 0.7. Rehashing...");
            reHash();
        }
    }

    /**
     * Doubles the size of the hash table and re-inserts all existing elements.
     * This is a private helper method called when the load factor is too high.
     */
    @SuppressWarnings("unchecked")
    private void reHash() {
        Node<T>[] oldTable = table;
        int oldTs = ts;

        // Update table size and reset current size
        ts = ts * 2 + 1; // Using +1 to keep the size odd, can help with some hashing strategies
        cs = 0; // Reset size, as insert will recount the elements
        table = (Node<T>[]) new Node[ts];

        // Initialize new table buckets
        for (int i = 0; i < ts; i++) {
            table[i] = null;
        }

        // Re-insert all elements from the old table into the new, larger table
        for (int i = 0; i < oldTs; i++) { // **FIX**: Iterate over the OLD table size
            Node<T> temp = oldTable[i];
            while (temp != null) {
                // insert() will use the new 'ts' to calculate the correct new index
                insert(temp.key, temp.value);
                temp = temp.next;
            }
        }
        System.out.println("Rehashing complete. New table size: " + ts);
    }

    /**
     * Prints the contents of the hash table, showing each bucket and the chain of nodes within it.
     */
    public void print() {
        System.out.println("\n--- Hash Table ---");
        for (int i = 0; i < ts; i++) {
            System.out.print("Bucket " + i + ": ");
            Node<T> temp = table[i];
            while (temp != null) {
                System.out.print("[" + temp.key + ":" + temp.value + "] -> ");
                temp = temp.next;
            }
            System.out.println("null"); // Nicer formatting
        }
        System.out.println("--------------------\n");
    }
    public T searchHashTable(String key){
        int idx = hashFunction(key);
        Node<T> temp = table[idx];

        while(temp != null){
            if(temp.key == key){
                return temp.value;
            }
            temp = temp.next;
        }
        return null;
    }

};

/**
 * Main class to demonstrate the HashTable functionality.
 */
public class HashTable_Good {
    public static void main(String[] args) {
        // Use generics for type safety
        HashTable<Integer> priceMenu = new HashTable<>();

        System.out.println("Inserting initial items...");
        priceMenu.insert("Apple", 120);
        priceMenu.insert("Mango", 100);
        priceMenu.insert("Papaya", 60);
        priceMenu.insert("Guavaa", 80);
        priceMenu.insert("Banana", 40);
        priceMenu.insert("Orange", 90);
        priceMenu.insert("Grape", 150);
        
        priceMenu.print();

        System.out.println("Inserting 'Pineapple', which should trigger a rehash...");
        priceMenu.insert("Pineapple", 75); // This 8th element makes load factor 8/10 = 0.8

        priceMenu.print();
        String key = "Grape";
        System.out.println("Price of "+ key+" = "+priceMenu.searchHashTable(key));
    }
}
