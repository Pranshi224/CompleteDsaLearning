class Solution {
    public boolean checkValidString(String s) {
        int low = 0;  // Minimum possible count of open brackets
        int high = 0; // Maximum possible count of open brackets

        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                low--;
                high--;
            } else if (c == '*') {
                low--;  // treat '*' as ')'
                high++; // treat '*' as '('
            }

            // More ')' than '(' + '*' seen so far
            if (high < 0) {
                return false;
            }

            // low balance cannot be negative
            if (low < 0) {
                low = 0;
            }
        }

        return low == 0;
    }
}