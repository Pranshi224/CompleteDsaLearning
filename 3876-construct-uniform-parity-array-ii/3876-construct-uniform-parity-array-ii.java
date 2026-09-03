class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;

        // Step 1: Find the smallest odd number
        for (int x : nums1) {
            if (x % 2 != 0) {
                minOdd = Math.min(minOdd, x);
            }
        }

        // Step 2: Check if any even number is smaller than minOdd
        for (int x : nums1) {
            if (x % 2 == 0 && minOdd != Integer.MAX_VALUE && x < minOdd) {
                return false;
            }
        }

        return true;
    }
}