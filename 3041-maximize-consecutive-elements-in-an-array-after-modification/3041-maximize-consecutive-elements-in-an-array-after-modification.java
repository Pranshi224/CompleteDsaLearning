import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int x : nums) {
            int incLen = dp.getOrDefault(x, 0) + 1;
            dp.put(x + 1, incLen);
            ans = Math.max(ans, incLen);

            int sameLen = dp.getOrDefault(x - 1, 0) + 1;
            dp.put(x, sameLen);
            ans = Math.max(ans, sameLen);
        }

        return ans;
    }
}