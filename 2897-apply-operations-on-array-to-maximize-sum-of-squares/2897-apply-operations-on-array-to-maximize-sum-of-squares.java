import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCounts = new int[31];
        
        // Count the frequency of each set bit
        for (int num : nums) {
            for (int i = 0; i <= 30; i++) {
                if ((num & (1 << i)) != 0) {
                    bitCounts[i]++;
                }
            }
        }
        
        long mod = 1_000_000_007;
        long totalSum = 0;
        
        // Greedily build the k largest numbers
        for (int i = 0; i < k; i++) {
            long currentNum = 0;
            for (int bit = 0; bit <= 30; bit++) {
                if (bitCounts[bit] > 0) {
                    currentNum |= (1L << bit);
                    bitCounts[bit]--;
                }
            }
            totalSum = (totalSum + (currentNum * currentNum) % mod) % mod;
        }
        
        return (int) totalSum;
    }
}