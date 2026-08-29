import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        // Element aur uske original index ko pair karke store karein
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }
        
        // Values ke according sort karein
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] result = new int[n];
        int i = 0;
        
        while (i < n) {
            int j = i;
            // Consecutive elements check karke same group identify karein
            while (j + 1 < n && pairs[j + 1][0] - pairs[j][0] <= limit) {
                j++;
            }
            
            // Current group (from index i to j) ke original indices nikaal kar sort karein
            List<Integer> indices = new ArrayList<>();
            for (int k = i; k <= j; k++) {
                indices.add(pairs[k][1]);
            }
            Collections.sort(indices);
            
            // Group ki values (jo pehle se sorted hain) ko sorted indices par place karein
            for (int k = 0; k < indices.size(); k++) {
                result[indices.get(k)] = pairs[i + k][0];
            }
            
            i = j + 1; // Next group par move karein
        }
        
        return result;
    }
}