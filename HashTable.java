import java.util.LinkedList;

/**
 * HashTable.java
 *
 * A generic hash table implementation using chaining to resolve collisions.
 * Supports dynamic resizing based on load factor.
 */
public class HashTable<K, V> {

    /**
     * Inner class representing a key-value pair.
     */
    private class Entry {
        K key;
        V value;
        Entry next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Array of buckets. Each bucket is a linked list of Entry objects.
    @SuppressWarnings("unchecked")
    private LinkedList<Entry>[] buckets;

    // Current number of buckets
    private int size;

    // Number of key-value pairs currently stored
    private int count;

    // Threshold for load factor to trigger resize
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    /**
     * Default constructor initializes the hash table with 16 buckets.
     * Each bucket is initialized as an empty LinkedList.
     */
    public HashTable() {
        this.size = 16;
        this.buckets = (LinkedList<Entry>[]) new LinkedList[size]; // Suppress unchecked warning
        for (int i = 0; i < size; ++i) {
            buckets[i] = new LinkedList<>();
        }
        this.count = 0;
    }

    /**
     * Hash function that maps a key to a bucket index.
     * Uses Java's built-in hashCode() and modulo operation.
     *
     * @param key The key to be hashed
     * @return Bucket index
     */
    private int hash(K key) {
        return Math.abs(key.hashCode()) % size;
    }

    /**
     * Inserts or updates a key-value pair into the hash table.
     *
     * Time Complexity:
     * - Average Case: O(1)
     * - Worst Case: O(n) if all keys hash to the same bucket
     *
     * @param key   The key to insert/update
     * @param value The value to associate with the key
     */
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");

        // Resize if load factor exceeds threshold
        if ((double) count / size > LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        int index = hash(key);
        LinkedList<Entry> bucket = buckets[index];
        Entry head = bucket.peek();

        // If key already exists, update its value
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        // Key not found, insert new entry at head
        Entry newEntry = new Entry(key, value);
        bucket.addFirst(newEntry); // Add to the front of the list
        count++;
    }

    /**
     * Retrieves the value mapped to the specified key.
     *
     * Time Complexity:
     * - Average Case: O(1)
     * - Worst Case: O(n)
     *
     * @param key The key whose value is to be retrieved
     * @return The value associated with the key, or null if not found
     */
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");

        int index = hash(key);
        LinkedList<Entry> bucket = buckets[index];
        Entry head = bucket.peek();

        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }

        return null; // Key not found
    }

    /**
     * Removes the key-value pair associated with the given key.
     *
     * Time Complexity:
     * - Average Case: O(1)
     * - Worst Case: O(n)
     *
     * @param key The key to be removed
     */
    public void remove(K key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");

        int index = hash(key);
        LinkedList<Entry> bucket = buckets[index];
        Entry prev = null;
        Entry current = bucket.peek();

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    // Removing the head node
                    bucket.removeFirst(); // Remove from the front
                } else {
                    // Removing middle or tail node
                    prev.next = current.next;
                }
                count--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    /**
     * Doubles the number of buckets and rehashes all entries.
     * Called automatically when load factor becomes too high.
     *
     * Time Complexity: O(n), where n is the total number of entries.
     */
    private void resize() {
        int newSize = size * 2;
        @SuppressWarnings("unchecked")
        LinkedList<Entry>[] newBuckets = (LinkedList<Entry>[]) new LinkedList[newSize];

        // Initialize new buckets with empty linked lists
        for (int i = 0; i < newSize; ++i) {
            newBuckets[i] = new LinkedList<>();
        }

        // Rehash all existing entries
        for (LinkedList<Entry> bucket : buckets) {
            for (Entry entry : bucket) {
                int newIndex = Math.abs(entry.key.hashCode()) % newSize;
                newBuckets[newIndex].addFirst(entry);
            }
        }

        // Replace old buckets with resized ones
        this.buckets = newBuckets;
        this.size = newSize;
    }

    /**
     * Returns the number of key-value pairs in the hash table.
     *
     * @return The size of the map
     */
    public int size() {
        return count;
    }

    /**
     * Checks whether the hash table is empty.
     *
     * @return true if no key-value mappings exist
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Main method to test the HashTable functionality.
     */
    public static void main(String[] args) {
        HashTable<String, Integer> table = new HashTable<>();

        // Test put()
        table.put("one", 1);
        table.put("two", 2);
        table.put("three", 3);

        // Test get()
        System.out.println("Get 'one': " + table.get("one"));   // Output: 1
        System.out.println("Get 'four': " + table.get("four")); // Output: null

        // Test update via put()
        table.put("two", 22); // Update
        System.out.println("Updated 'two': " + table.get("two")); // Output: 22

        // Test remove()
        table.remove("three");
        System.out.println("After remove 'three': " + table.get("three")); // Output: null

        // Test size()
        System.out.println("Size after removal: " + table.size()); // Output: 2
        System.out.println("Is table empty? " + table.isEmpty()); // Output: false
    }
}