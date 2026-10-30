import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build adjacency list: u -> list of {v, weight}
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(new int[]{edge[1], edge[2]});
        }

        // Min-priority queue storing {distance, node, consecutive_count}
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        
        // dist[u][count] stores the minimum weight to reach node u with a specific consecutive count.
        // Since count can range from 1 to k, we use size k + 1.
        long[][] dist = new long[n][k + 1];
        for (long[] row : dist) {
            Arrays.fill(row, Long.MAX_VALUE);
        }

        // Initial state at node 0
        int startCount = 1;
        pq.offer(new long[]{0, 0, startCount});
        dist[0][startCount] = 0;

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long d = curr[0];
            int u = (int) curr[1];
            int count = (int) curr[2];

            // If we reached the destination node n - 1, return the total weight
            if (u == n - 1) {
                return (int) d;
            }

            if (d > dist[u][count]) continue;

            for (int[] neighbor : adj[u]) {
                int v = neighbor[0];
                int weight = neighbor[1];

                int nextCount;
                if (labels.charAt(u) == labels.charAt(v)) {
                    nextCount = count + 1;
                } else {
                    nextCount = 1;
                }

                // If nextCount exceeds k, the path is invalid
                if (nextCount > k) continue;

                if (d + weight < dist[v][nextCount]) {
                    dist[v][nextCount] = d + weight;
                    pq.offer(new long[]{d + weight, v, nextCount});
                }
            }
        }

        // If node n - 1 is unreachable
        return -1;
    }
}