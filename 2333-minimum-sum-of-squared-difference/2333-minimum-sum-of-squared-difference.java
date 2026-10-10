class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        
        int maxDiff = 0;
        int[] diff = new int[n];
        
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
        }
        
        // Frequency array to store counts of each difference
        int[] count = new int[maxDiff + 1];
        for (int d : diff) {
            count[d]++;
        }
        
        // Greedily reduce differences starting from the maximum
        for (int d = maxDiff; d > 0 && k > 0; d--) {
            if (count[d] == 0) continue;
            
            long take = Math.min((long) count[d], k);
            count[d] -= take;
            count[d - 1] += take;
            k -= take;
        }
        
        // Calculate the final sum of squared differences
        long result = 0;
        for (int d = 1; d <= maxDiff; d++) {
            if (count[d] > 0) {
                result += (long) count[d] * d * d;
            }
        }
        
        return result;
    }
}