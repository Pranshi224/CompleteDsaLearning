import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans, new ArrayList<>(), nums, 0);
        return ans;
    }
    
    private void backtrack(List<List<Integer>> ans, List<Integer> curr, int[] nums, int start) {
        // Add a copy of the current subset to the final answer
        ans.add(new ArrayList<>(curr));
        
        for (int i = start; i < nums.length; i++) {
            // Include nums[i] in the current subset
            curr.add(nums[i]);
            // Move to the next index
            backtrack(ans, curr, nums, i + 1);
            // Backtrack: remove the element to explore other possibilities
            curr.remove(curr.size() - 1);
        }
    }
}