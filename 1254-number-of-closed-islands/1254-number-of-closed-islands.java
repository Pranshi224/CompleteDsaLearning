class Solution {
    public int closedIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // 1. Flood-fill all 0s connected to the top and bottom borders
        for (int c = 0; c < cols; c++) {
            if (grid[0][c] == 0) {
                dfs(grid, 0, c);
            }
            if (grid[rows - 1][c] == 0) {
                dfs(grid, rows - 1, c);
            }
        }

        // 2. Flood-fill all 0s connected to the left and right borders
        for (int r = 0; r < rows; r++) {
            if (grid[r][0] == 0) {
                dfs(grid, r, 0);
            }
            if (grid[r][cols - 1] == 0) {
                dfs(grid, r, cols - 1);
            }
        }

        // 3. Count the remaining enclosed islands
        int closedIslandsCount = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) {
                    closedIslandsCount++;
                    dfs(grid, r, c); // Sink this entire closed island
                }
            }
        }

        return closedIslandsCount;
    }

    private void dfs(int[][] grid, int r, int c) {
        // Check boundaries and water cells (1)
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 1) {
            return;
        }

        // Mark the current cell as visited/water
        grid[r][c] = 1;

        // Traverse in all 4 directions
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
}