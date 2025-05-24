package Map_Dictionary;

import java.util.LinkedList;

/**
 * Custom Map_Dictionary.HashSet implementation without using built-in hash table libraries.
 *
 * This class provides basic operations: add, remove, and contains.
 * Uses chaining with LinkedList to handle collisions.
 * Designed for educational purposes and interviews.
 */
class HashSet {
    // Array of buckets. Each bucket is a linked list of integer keys.
    private LinkedList<Integer>[] buckets;
    private int base; // Number of buckets

    /**
     * Constructor initializes the hash set with 769 buckets (a prime number).
     * Prime numbers reduce collision probability by spreading keys more evenly.
     */
    public HashSet() {
        this.base = 769;
        this.buckets = new LinkedList[base];
        for (int i = 0; i < base; ++i) {
            buckets[i] = new LinkedList<>();
        }
    }

    /**
     * Adds a key into the set if not already present.
     * Time Complexity:
     * - Average: O(1)
     * - Worst Case: O(n) if many collisions occur
     */
    public void add(int key) {
        int index = key % base;
        if (!buckets[index].contains(key)) {
            buckets[index].add(key);
        }
    }

    /**
     * Removes a key from the set if it exists.
     * Time Complexity:
     * - Average: O(1)
     * - Worst Case: O(n)
     */
    public void remove(int key) {
        int index = key % base;
        buckets[index].remove(Integer.valueOf(key));
    }

    /**
     * Checks if the set contains a given key.
     * Time Complexity:
     * - Average: O(1)
     * - Worst Case: O(n)
     */
    public boolean contains(int key) {
        int index = key % base;
        return buckets[index].contains(key);
    }

    /**
     * Main method to test Map_Dictionary.HashSet functionality.
     */
    public static void main(String[] args) {
        HashSet hashSet = new HashSet();
        hashSet.add(1);
        hashSet.add(2);
        System.out.println("Contains 1? " + hashSet.contains(1)); // true
        System.out.println("Contains 3? " + hashSet.contains(3)); // false
        hashSet.add(2);
        System.out.println("Contains 2? " + hashSet.contains(2)); // true
        hashSet.remove(2);
        System.out.println("Contains 2 after removal? " + hashSet.contains(2)); // false
    }
}