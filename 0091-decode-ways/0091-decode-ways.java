import java.util.Arrays;

class Solution {
    public int numDecodings(String s) {
        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return backtrack(0, s, memo);
    }

    private int backtrack(int index, String s, int[] memo) {
        // Base case: Reached the end of the string
        if (index == s.length()) return 1;

        // Base case: Leading zero cannot be decoded
        if (s.charAt(index) == '0') return 0;

        // Return cached result
        if (memo[index] != -1) return memo[index];

        // Choice 1: Take single digit
        int ways = backtrack(index + 1, s, memo);

        // Choice 2: Take two digits if within range "10" - "26"
        if (index + 1 < s.length()) {
            int twoDigit = Integer.parseInt(s.substring(index, index + 2));
            if (twoDigit >= 10 && twoDigit <= 26) {
                ways += backtrack(index + 2, s, memo);
            }
        }

        return memo[index] = ways;
    }
}