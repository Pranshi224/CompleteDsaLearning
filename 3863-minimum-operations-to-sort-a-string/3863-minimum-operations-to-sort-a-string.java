class Solution {
    public int minOperations(String s) {
        boolean isSorted = true;
        char[] cs = s.toCharArray();
        int n = cs.length;
        char mn = cs[0], mx = cs[0];
        
        for (int i = 1; i < n; ++i) {
            mn = (char) Math.min(mn, cs[i]);
            mx = (char) Math.max(mx, cs[i]);
            if (cs[i] < cs[i - 1]) {
                isSorted = false;
            }
        }
        
        // Case 1: Already sorted
        if (isSorted) {
            return 0;
        }
        
        // Case 2: Length 2 cannot be sorted using proper substrings
        if (n == 2) {
            return -1;
        }
        
        // Case 3: Min at start or Max at end requires 1 operation
        if (cs[0] == mn || cs[n - 1] == mx) {
            return 1;
        }
        
        // Case 4: Check if min or max exists in the interior proper substring
        for (int i = 1; i < n - 1; ++i) {
            if (cs[i] == mn || cs[i] == mx) {
                return 2;
            }
        }
        
        // Fallback case requiring 3 operations
        return 3;
    }
}