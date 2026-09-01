import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startX = -1, startY = -1;
        List<int[]> litters = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    startX = i;
                    startY = j;
                } else if (c == 'L') {
                    litters.add(new int[]{i, j});
                }
            }
        }
        
        int totalLitter = litters.size();
        int fullMask = (1 << totalLitter) - 1;
        
        // Map (row, col) of litter to an index (0 to totalLitter - 1)
        int[][] litterIdx = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(litterIdx[i], -1);
        for (int i = 0; i < totalLitter; i++) {
            litterIdx[litters.get(i)[0]][litters.get(i)[1]] = i;
        }

        // Track max energy seen for state (row, col, mask)
        int[][][] bestEnergy = new int[m][n][1 << totalLitter];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(bestEnergy[i][j], -1);
            }
        }

        // Queue stores: {x, y, mask, current_energy, steps}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY, 0, energy, 0});
        bestEnergy[startX][startY][0] = energy;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], mask = curr[2], e = curr[3], steps = curr[4];

            if (mask == fullMask) {
                return steps;
            }

            if (e == 0) continue; // Out of energy to move to next cell

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n && classroom[nr].charAt(nc) != 'X') {
                    int nextMask = mask;
                    char cell = classroom[nr].charAt(nc);

                    if (cell == 'L' && litterIdx[nr][nc] != -1) {
                        nextMask |= (1 << litterIdx[nr][nc]);
                    }

                    int nextEnergy = e - 1;
                    if (cell == 'R') {
                        nextEnergy = energy; // Energy resets to maximum
                    }

                    // Only process if we found a path with strictly better energy for this state
                    if (nextEnergy > bestEnergy[nr][nc][nextMask]) {
                        bestEnergy[nr][nc][nextMask] = nextEnergy;
                        queue.offer(new int[]{nr, nc, nextMask, nextEnergy, steps + 1});
                    }
                }
            }
        }

        return -1;
    }
}