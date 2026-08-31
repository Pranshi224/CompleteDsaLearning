class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int firstIndex = -1;
        int prevIndex = -1;
        int minDistance = Integer.MAX_VALUE;
        
        ListNode prev = head;
        ListNode curr = head.next;
        int index = 1; // head.next ki position 1 maan rahe hain

        while (curr.next != null) {
            ListNode next = curr.next;
            
            // Critical Point check
            boolean isMaxima = (curr.val > prev.val && curr.val > next.val);
            boolean isMinima = (curr.val < prev.val && curr.val < next.val);

            if (isMaxima || isMinima) {
                if (firstIndex == -1) {
                    firstIndex = index;
                } else {
                    minDistance = Math.min(minDistance, index - prevIndex);
                }
                prevIndex = index;
            }
            
            prev = curr;
            curr = next;
            index++;
        }

        // Agar 2 se kam critical points milte hain
        if (firstIndex == -1 || prevIndex == firstIndex) {
            return new int[]{-1, -1};
        }

        int maxDistance = prevIndex - firstIndex;
        return new int[]{minDistance, maxDistance};
    }
}