import java.util.Arrays;

class Solution {
    public int minMoves2(int[] nums) {
        // Step 1: Sort the array to find the median
        Arrays.sort(nums);
        
        int moves = 0;
        int median = nums[nums.length / 2];
        
        // Step 2: Calculate the absolute difference from each element to the median
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        
        return moves;
    }
}