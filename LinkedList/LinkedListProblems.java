package LinkedList;

/**
 * Q1: Detect if a linked list has a cycle.
 * Return true if there is a cycle, false otherwise.
 *
 * Approach: Floyd's Cycle Detection (Tortoise and Hare)
 */

public class LinkedListProblems {

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // Q1: Detect Cycle
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // Traverse the list
        while (fast != null && fast.next != null) {
            slow = slow.next;         // Move one step
            fast = fast.next.next;    // Move two steps
            if (slow == fast) return true; // If they meet, cycle exists
        }
        return false; // If fast hits null, no cycle
    }

    /**
     * Q2: Return the node where the cycle begins.
     * Return null if there is no cycle.
     *
     * Approach: After detecting the cycle using Floyd’s algorithm,
     * reset one pointer to head and move both one step at a time.
     * They will meet at the cycle start.
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // Detect cycle using Floyd’s algorithm
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                // Cycle detected
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr; // Start of the cycle
            }
        }
        return null; // No cycle
    }

    /**
     * Q3: Remove the cycle in the linked list, if present.
     *
     * Approach:
     * - Detect cycle
     * - Find the start of the cycle
     * - Traverse to the last node in the cycle and break it
     */
    public void removeCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // Step 1: Detect cycle
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }

        if (!hasCycle) return; // No cycle to remove

        // Step 2: Find start of the cycle
        ListNode ptr = head;
        while (ptr != slow) {
            ptr = ptr.next;
            slow = slow.next;
        }

        // Step 3: Find the last node in the cycle and break it
        ListNode prev = ptr;
        while (prev.next != ptr) {
            prev = prev.next;
        }
        prev.next = null; // Break the cycle
    }
}
