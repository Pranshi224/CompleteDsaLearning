import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[][] indexedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            indexedQueries[i][0] = queries[i];
            indexedQueries[i][1] = i;
        }

        // Sort queries by time
        Arrays.sort(indexedQueries, Comparator.comparingInt(a -> a[0]));
        // Sort logs by time
        Arrays.sort(logs, Comparator.comparingInt(a -> a[1]));

        int[] ans = new int[m];
        int[] serverCount = new int[n + 1];
        int activeServers = 0;
        
        int left = 0, right = 0;
        int numLogs = logs.length;

        for (int i = 0; i < m; i++) {
            int qTime = indexedQueries[i][0];
            int originalIdx = indexedQueries[i][1];
            int windowStart = qTime - x;
            int windowEnd = qTime;

            // Add logs that fall within or before the windowEnd
            while (right < numLogs && logs[right][1] <= windowEnd) {
                int serverId = logs[right][0];
                if (serverCount[serverId] == 0) {
                    activeServers++;
                }
                serverCount[serverId]++;
                right++;
            }

            // Remove logs that fall outside the windowStart (i.e., strictly less than windowStart)
            while (left < numLogs && logs[left][1] < windowStart) {
                int serverId = logs[left][0];
                serverCount[serverId]--;
                if (serverCount[serverId] == 0) {
                    activeServers--;
                }
                left++;
            }

            // Servers with zero requests = total servers (n) - servers with at least one request (activeServers)
            ans[originalIdx] = n - activeServers;
        }

        return ans;
    }
}