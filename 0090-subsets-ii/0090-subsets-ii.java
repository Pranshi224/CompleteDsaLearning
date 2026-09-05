import java.util.*;

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // 1. Sort to bring duplicates together
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int[] nums, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current)); // Current subset ko result me add karo

        for (int i = start; i < nums.length; i++) {
            // 2. Duplicate element ko same tree depth par skip karo
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            current.add(nums[i]);
            backtrack(i + 1, nums, current, result); // Recurse
            current.remove(current.size() - 1); // Backtrack
        }
    }
}