class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;

        int minIdx = 0, maxIdx = 0;
        for (int k = 0; k < n; k++) {
            if (nums[k] < nums[minIdx]) minIdx = k;
            if (nums[k] > nums[maxIdx]) maxIdx = k;
        }

        int i = Math.min(minIdx, maxIdx);
        int j = Math.max(minIdx, maxIdx);

        // Scenario 1: Both from front -> j + 1
        // Scenario 2: Both from back -> n - i
        // Scenario 3: One from front, one from back -> (i + 1) + (n - j)
        return Math.min(j + 1, Math.min(n - i, (i + 1) + (n - j)));
    }
}