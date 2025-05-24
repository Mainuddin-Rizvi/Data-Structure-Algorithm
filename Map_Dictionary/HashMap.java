package Map_Dictionary;

/**
 * Custom Map_Dictionary.HashMap implementation where both key and value are integers.
 *
 * Supports basic operations: put, get, and remove.
 * Uses chaining with linked list to resolve collisions.
 * Suitable for simple use cases and learning how hash maps work internally.
 */
class HashMap {
    /**
     * Inner class representing each key-value pair in the map.
     */
    private class Entry {
        int key;
        int value;
        Entry next;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry[] buckets; // Array of buckets
    private int size; // Number of buckets

    /**
     * Constructor initializes the map with 769 buckets (prime number).
     * Helps reduce collisions due to better distribution of hash values.
     */
    public HashMap() {
        this.size = 769;
        this.buckets = new Entry[size];
    }

    /**
     * Hash function to determine bucket index for a given key.
     */
    private int hash(int key) {
        return key % size;
    }

    /**
     * Inserts or updates a key-value pair in the map.
     * Time Complexity:
     * - Average: O(1)
     * - Worst Case: O(n)
     */
    public void put(int key, int value) {
        int index = hash(key);
        Entry head = buckets[index];

        while (head != null) {
            if (head.key == key) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        Entry newEntry = new Entry(key, value);
        newEntry.next = buckets[index];
        buckets[index] = newEntry;
    }

    /**
     * Retrieves the value associated with the given key.
     * Returns -1 if the key does not exist.
     * Time Complexity:
     * - Average: O(1)
     * - Worst Case: O(n)
     */
    public int get(int key) {
        int index = hash(key);
        Entry head = buckets[index];

        while (head != null) {
            if (head.key == key) return head.value;
            head = head.next;
        }

        return -1;
    }

    /**
     * Removes the key-value pair associated with the given key.
     * Time Complexity:
     * - Average: O(1)
     * - Worst Case: O(n)
     */
    public void remove(int key) {
        int index = hash(key);
        Entry head = buckets[index];
        Entry prev = null;

        while (head != null) {
            if (head.key == key) {
                if (prev == null) {
                    buckets[index] = head.next;
                } else {
                    prev.next = head.next;
                }
                return;
            }
            prev = head;
            head = head.next;
        }
    }

    /**
     * Main method to test Map_Dictionary.HashMap functionality.
     */
    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        System.out.println("Get 1: " + hashMap.get(1)); // 1
        System.out.println("Get 3: " + hashMap.get(3)); // -1
        hashMap.put(2, 1); // Update
        System.out.println("Get 2: " + hashMap.get(2)); // 1
        hashMap.remove(2);
        System.out.println("Get 2 after remove: " + hashMap.get(2)); // -1
    }
}