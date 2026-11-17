class Solution {
    // Precompute the cost to make substring s[i..j] a semi-palindrome
    private int[][] cost;
    private int[][] memoCost;

    public int minimumChanges(String s, int k) {
        int n = s.length();
        cost = new int[n][n];

        // Step 1: Precompute cost for all substrings s[i..j]
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                cost[i][j] = getMinChanges(s, i, j);
            }
        }

        // Step 2: DP table for partitioning into k parts
        // dp[i][j] represents min changes to partition suffix s[i..n-1] into j parts
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            java.util.Arrays.fill(dp[i], 1000000);
        }
        dp[0][0] = 0;

        for (int parts = 1; parts <= k; parts++) {
            for (int i = 1; i <= n; i++) {
                // Try all possible split points for the last part
                // A semi-palindrome substring must have a length of at least 2 * d >= 2
                for (int prev = parts - 1; prev < i; prev++) {
                    // substring indices from prev to i - 1
                    if (i - prev >= 2) {
                        dp[i][parts] = Math.min(dp[i][parts], dp[prev][parts - 1] + cost[prev][i - 1]);
                    }
                }
            }
        }

        return dp[n][k];
    }

    // Helper to find minimum changes for s[i..j] to become a semi-palindrome
    private int getMinChanges(String s, int start, int end) {
        int len = end - start + 1;
        int minChanges = 1000000;

        // Try all proper divisors d of len
        for (int d = 1; d < len; d++) {
            if (len % d == 0) {
                int totalChanges = 0;
                // Check each of the d groups
                for (int offset = 0; offset < d; offset++) {
                    int l = start + offset;
                    int r = start + len - d + offset;
                    while (l < r) {
                        if (s.charAt(l) != s.charAt(r)) {
                            totalChanges++;
                        }
                        l += d;
                        r -= d;
                    }
                }
                minChanges = Math.min(minChanges, totalChanges);
            }
        }
        return minChanges;
    }
}