import java.util.*;

class Solution {
    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return result;
        }

        int m = heights.length;
        int n = heights[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        // Top & Bottom edges
        for (int c = 0; c < n; c++) {
            dfs(heights, 0, c, pacific, heights[0][c]);
            dfs(heights, m - 1, c, atlantic, heights[m - 1][c]);
        }

        // Left & Right edges
        for (int r = 0; r < m; r++) {
            dfs(heights, r, 0, pacific, heights[r][0]);
            dfs(heights, r, n - 1, atlantic, heights[r][n - 1]);
        }

        // Collect common cells reachable by both oceans
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (pacific[r][c] && atlantic[r][c]) {
                    result.add(Arrays.asList(r, c));
                }
            }
        }

        return result;
    }

    private void dfs(int[][] heights, int r, int c, boolean[][] ocean, int prevHeight) {
        int m = heights.length;
        int n = heights[0].length;

        // Boundary, visited check, and reverse flow height check
        if (r < 0 || r >= m || c < 0 || c >= n || ocean[r][c] || heights[r][c] < prevHeight) {
            return;
        }

        ocean[r][c] = true;

        for (int[] dir : dirs) {
            dfs(heights, r + dir[0], c + dir[1], ocean, heights[r][c]);
        }
    }
}