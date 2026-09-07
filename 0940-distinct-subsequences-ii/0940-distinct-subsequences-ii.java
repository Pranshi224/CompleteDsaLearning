class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        long[] ends = new long[26];

        for (char ch : s.toCharArray()) {
            int idx = ch - 'a';
            
            long totalSoFar = 0;
            for (long count : ends) {
                totalSoFar = (totalSoFar + count) % MOD;
            }

            // New subsequences ending in `ch` = all existing subsequences + 1 (for `ch` itself)
            ends[idx] = (totalSoFar + 1) % MOD;
        }

        long result = 0;
        for (long count : ends) {
            result = (result + count) % MOD;
        }

        return (int) result;
    }
}