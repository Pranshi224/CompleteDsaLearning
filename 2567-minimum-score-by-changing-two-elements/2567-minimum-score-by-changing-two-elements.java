import java.util.Arrays;

class Solution {
    public int minimizeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        
        // Option 1: Modify 2 smallest numbers
        int option1 = nums[n - 1] - nums[2];
        
        // Option 2: Modify 2 largest numbers
        int option2 = nums[n - 3] - nums[0];
        
        // Option 3: Modify 1 smallest and 1 largest number
        int option3 = nums[n - 2] - nums[1];
        
        return Math.min(option1, Math.min(option2, option3));
    }
}