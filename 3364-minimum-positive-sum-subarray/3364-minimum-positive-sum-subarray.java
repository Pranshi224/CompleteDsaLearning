import java.util.List;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int minSum = Integer.MAX_VALUE;
        int n = nums.size();
        
        // Iterate over all possible starting points of the subarray
        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            // Iterate over all possible lengths from l to r
            for (int j = i; j < n && (j - i + 1) <= r; j++) {
                currentSum += nums.get(j);
                int length = j - i + 1;
                
                // Check if length is within [l, r] and sum is strictly greater than 0
                if (length >= l && currentSum > 0) {
                    minSum = Math.min(minSum, currentSum);
                }
            }
        }
        
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}