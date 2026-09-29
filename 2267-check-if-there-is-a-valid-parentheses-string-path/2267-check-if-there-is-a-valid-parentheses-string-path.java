class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Path length must be even to form valid parentheses
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // Must start with '(' and end with ')'
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        int maxBalance = (m + n) / 2;
        boolean[][][] visited = new boolean[m][n][maxBalance + 1];

        return dfs(0, 0, 0, grid, visited, maxBalance);
    }

    private boolean dfs(int r, int c, int balance, char[][] grid, boolean[][][] visited, int maxBalance) {
        int m = grid.length;
        int n = grid[0].length;

        // Update balance
        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }

        // Invalid state: negative balance or exceeding maximum possible open brackets
        if (balance < 0 || balance > maxBalance) {
            return false;
        }

        // Reached destination: check if balance is 0
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        // If already visited this (r, c, balance) state
        if (visited[r][c][balance]) {
            return false;
        }
        visited[r][c][balance] = true;

        // Move right
        if (c + 1 < n && dfs(r, c + 1, balance, grid, visited, maxBalance)) {
            return true;
        }

        // Move down
        if (r + 1 < m && dfs(r + 1, c, balance, grid, visited, maxBalance)) {
            return true;
        }

        return false;
    }
}