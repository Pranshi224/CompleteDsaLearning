import java.util.Arrays;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        int left = 0, currentSum = 0;
        int minLen = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            currentSum += arr[right];
            
            // Shrink window if sum exceeds target
            while (currentSum > target && left <= right) {
                currentSum -= arr[left];
                left++;
            }
            
            // Found a sub-array with sum equal to target
            if (currentSum == target) {
                int currentLen = right - left + 1;
                
                // If there exists a valid non-overlapping sub-array to the left
                if (left > 0 && dp[left - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, currentLen + dp[left - 1]);
                }
                
                minLen = Math.min(minLen, currentLen);
            }
            
            // Maintain the minimum sub-array length up to current index
            dp[right] = minLen;
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}