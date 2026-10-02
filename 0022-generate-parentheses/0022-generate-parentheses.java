import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, String current, int open, int close, int max) {
        // Base case: Jab string ki length 2 * n ho jaye
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }

        // Rule 1: Add open bracket if open < max
        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max);
        }

        // Rule 2: Add close bracket if close < open
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max);
        }
    }
}