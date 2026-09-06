class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // Step 1: Compute the length of the list and find the tail
        ListNode tail = head;
        int len = 1;
        while (tail.next != null) {
            tail = tail.next;
            len++;
        }

        // Step 2: Normalize k
        k = k % len;
        if (k == 0) {
            return head;
        }

        // Step 3: Make the list circular
        tail.next = head;

        // Step 4: Find the new tail (len - k - 1 steps from head)
        ListNode newTail = head;
        for (int i = 0; i < len - k - 1; i++) {
            newTail = newTail.next;
        }

        // Step 5: Set new head and break the ring
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}