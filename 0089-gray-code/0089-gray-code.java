import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        int totalNums = 1 << n; // 2^n elements

        for (int i = 0; i < totalNums; i++) {
            result.add(i ^ (i >> 1));
        }

        return result;
    }
}