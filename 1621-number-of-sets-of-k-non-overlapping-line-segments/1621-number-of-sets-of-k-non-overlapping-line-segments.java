class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007;
        int N = n + k - 1;
        int R = 2 * k;

        if (R > N) return 0;

        // Calculate C(N, R) % MOD using Pascal's Triangle or Modular Inverse
        long[] dp = new long[R + 1];
        dp[0] = 1;

        for (int i = 1; i <= N; i++) {
            for (int j = Math.min(i, R); j > 0; j--) {
                dp[j] = (dp[j] + dp[j - 1]) % MOD;
            }
        }

        return (int) dp[R];
    }
}