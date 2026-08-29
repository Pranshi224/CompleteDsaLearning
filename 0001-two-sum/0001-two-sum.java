import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Key: Element Value, Value: Element Index
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            // Agar zaroori partner number HashMap me mil jaye
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            
            // Nahi milne par current element ko map me index ke saath daal dein
            map.put(nums[i], i);
        }
        
        return new int[] {}; // Agar koi pair na mile
    }
}