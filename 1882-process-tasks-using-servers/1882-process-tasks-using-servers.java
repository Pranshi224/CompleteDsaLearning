import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // Priority queue for available servers: ordered by weight, then by index
        PriorityQueue<int[] > freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // 🛠️ Yahan int[] ki jagah long[] karein taaki freeTime (long) store ho sake
        PriorityQueue<long[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Long.compare(a[0], b[0]);
            }
            if (a[1] != b[1]) {
                return Long.compare(a[1], b[1]);
            }
            return Long.compare(a[2], b[2]);
        });

        // Initially all servers are free. Store {weight, index}
        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[] { servers[i], i });
        }

        long currentTime = 0;

        for (int j = 0; j < m; j++) {
            // Time cannot be less than the current task arrival index j
            currentTime = Math.max(currentTime, j);

            // If no free servers are available, jump time to when the next server becomes free
            if (freeServers.isEmpty()) {
                currentTime = Math.max(currentTime, busyServers.peek()[0]);
            }

            // Free up all servers that have finished their tasks by currentTime
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                long[] freed = busyServers.poll();
                // freed[1] = weight, freed[2] = index
                freeServers.offer(new int[] { (int) freed[1], (int) freed[2] });
            }

            // Assign the current task to the best available server
            int[] server = freeServers.poll();
            int weight = server[0];
            int index = server[1];

            ans[j] = index;

            // Push this server into the busy queue with its new free time
            long freeTime = currentTime + tasks[j];
            busyServers.offer(new long[] { freeTime, weight, index });
        }

        return ans;
    }
}