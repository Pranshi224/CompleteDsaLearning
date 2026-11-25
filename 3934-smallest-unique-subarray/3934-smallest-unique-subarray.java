import java.util.*;

class Solution {
    private static final long BASE1 = 100003;
    private static final long MOD1 = 1000000007;
    private static final long BASE2 = 100019;
    private static final long MOD2 = 1000000009;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        // Precompute powers and prefix hashes for O(1) range hash computation
        long[] pow1 = new long[n + 1];
        long[] pow2 = new long[n + 1];
        long[] hash1 = new long[n + 1];
        long[] hash2 = new long[n + 1];

        pow1[0] = 1;
        pow2[0] = 1;

        for (int i = 0; i < n; i++) {
            pow1[i + 1] = (pow1[i] * BASE1) % MOD1;
            pow2[i + 1] = (pow2[i] * BASE2) % MOD2;

            hash1[i + 1] = (hash1[i] * BASE1 + nums[i]) % MOD1;
            hash2[i + 1] = (hash2[i] * BASE2 + nums[i]) % MOD2;
        }

        // Binary search for the minimum length
        int low = 1, high = n;
        int ans = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (hasUniqueSubarray(nums, mid, hash1, hash2, pow1, pow2)) {
                ans = mid;
                high = mid - 1; // Try to find a smaller valid length
            } else {
                low = mid + 1;  // Need a longer length
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarray(int[] nums, int len, long[] hash1, long[] hash2, 
                                     long[] pow1, long[] pow2) {
        int n = nums.length;
        Map<Long, Integer> counts = new HashMap<>();

        for (int i = 0; i <= n - len; i++) {
            // Compute double hash for nums[i ... i + len - 1]
            long h1 = (hash1[i + len] - (hash1[i] * pow1[len]) % MOD1 + MOD1) % MOD1;
            long h2 = (hash2[i + len] - (hash2[i] * pow2[len]) % MOD2 + MOD2) % MOD2;

            // Combine both hashes into a single 64-bit key
            long combinedHash = (h1 << 32) | h2;

            counts.put(combinedHash, counts.getOrDefault(combinedHash, 0) + 1);
        }

        // Check if any subarray hash appears exactly once
        for (int count : counts.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}