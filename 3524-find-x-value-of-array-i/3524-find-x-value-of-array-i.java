class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            long[] nextDp = new long[k];
            int mod = num % k;
            
            // Subarray starting at the current element
            nextDp[mod]++;

            // Extend existing subarrays ending at the previous element
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newMod = (r * mod) % k;
                    nextDp[newMod] += dp[r];
                }
            }

            // Update DP array and accumulate results
            dp = nextDp;
            for (int r = 0; r < k; r++) {
                ans[r] += dp[r];
            }
        }

        return ans;
    }
}