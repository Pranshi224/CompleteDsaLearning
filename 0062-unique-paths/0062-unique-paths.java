import java.util.Arrays;

class Solution {
    public int uniquePaths(int m, int n) {
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return backtrack(0, 0, m, n, memo);
    }

    private int backtrack(int r, int c, int m, int n, int[][] memo) {
        // Base Cases
        if (r == m - 1 && c == n - 1) return 1; // Reached bottom-right corner
        if (r >= m || c >= n) return 0;         // Out of bounds

        // Return cached result
        if (memo[r][c] != -1) return memo[r][c];

        // Move Down + Move Right
        int down = backtrack(r + 1, c, m, n, memo);
        int right = backtrack(r, c + 1, m, n, memo);

        return memo[r][c] = down + right;
    }
}