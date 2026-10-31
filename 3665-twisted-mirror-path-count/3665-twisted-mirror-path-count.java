import java.util.*;

class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long MOD = 1_000_000_007;

        // go[i][j][0] -> landing spot if moving RIGHT from (i, j)
        // go[i][j][1] -> landing spot if moving DOWN from (i, j)
        int[][][] go = new int[m][n][2];

        // Precompute jump targets for each cell and direction
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 1. If we try to move RIGHT from (i, j)
                int r = i, c = j + 1;
                int dir = 0; // 0 for moving right, 1 for moving down
                boolean valid = true;
                
                while (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1) {
                    if (dir == 0) {
                        // entered mirror while moving right -> turns down
                        r++;
                        dir = 1;
                    } else {
                        // entered mirror while moving down -> turns right
                        c++;
                        dir = 0;
                    }
                }
                if (r < 0 || r >= m || c < 0 || c >= n) {
                    go[i][j][0] = -1; // Out of bounds
                } else {
                    go[i][j][0] = r * n + c; // Encoded 1D coordinate
                }

                // 2. If we try to move DOWN from (i, j)
                r = i + 1; 
                c = j;
                dir = 1;
                while (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1) {
                    if (dir == 0) {
                        r++;
                        dir = 1;
                    } else {
                        c++;
                        dir = 0;
                    }
                }
                if (r < 0 || r >= m || c < 0 || c >= n) {
                    go[i][j][1] = -1;
                } else {
                    go[i][j][1] = r * n + c;
                }
            }
        }

        // DP table storing number of ways to reach each cell
        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) continue;

                // Transition via RIGHT move
                int targetRight = go[i][j][0];
                if (targetRight != -1) {
                    int nextR = targetRight / n;
                    int nextC = targetRight % n;
                    dp[nextR][nextC] = (dp[nextR][nextC] + dp[i][j]) % MOD;
                }

                // Transition via DOWN move
                int targetDown = go[i][j][1];
                if (targetDown != -1) {
                    int nextR = targetDown / n;
                    int nextC = targetDown % n;
                    dp[nextR][nextC] = (dp[nextR][nextC] + dp[i][j]) % MOD;
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}