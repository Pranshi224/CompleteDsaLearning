class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        // Step 1: Compute Prefix Sums
        long[] pref = new long[n];
        pref[0] = stones[0];
        for (int i = 1; i < n; i++) {
            pref[i] = pref[i - 1] + stones[i];
        }

        // Base case: Taking all stones (index n - 1)
        // Note: Max difference can exceed Integer limits in intermediate states, 
        // but 'ans' fits in long during DP calculations.
        long ans = pref[n - 1];

        // Step 2: Backward pass for DP transition
        // dp[i] = max(dp[i + 1], pref[i] - dp[i + 1])
        for (int i = n - 2; i >= 1; i--) {
            ans = Math.max(ans, pref[i] - ans);
        }

        return (int) ans;
    }
}