class Solution {
    public int numOfSubarrays(int[] arr) {
        long oddCount = 0, evenCount = 1; // evenCount = 1 un prefix sums ke liye jo 0 (even) se shuru hote hain
        long currentSum = 0;
        long totalOddSubarrays = 0;
        int MOD = 1_000_000_007;
        
        for (int num : arr) {
            currentSum += num;
            
            // Agar current prefix sum odd hai, to ise pichle even prefix sums ke sath pair karna hoga
            if (currentSum % 2 == 1) {
                totalOddSubarrays += evenCount;
                oddCount++;
            } else {
                // Agar current prefix sum even hai, to ise pichle odd prefix sums ke sath pair karna hoga
                totalOddSubarrays += oddCount;
                evenCount++;
            }
            
            totalOddSubarrays %= MOD;
        }
        
        return (int) totalOddSubarrays;
    }
}