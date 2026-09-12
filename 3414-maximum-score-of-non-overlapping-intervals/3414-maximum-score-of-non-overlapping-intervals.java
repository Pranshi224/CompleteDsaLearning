import java.util.*;

class Solution {
    class Interval {
        int l, r, weight, id;
        Interval(int l, int r, int weight, int id) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.id = id;
        }
    }

    class Pair {
        long weight;
        List<Integer> ids;
        Pair(long weight, List<Integer> ids) {
            this.weight = weight;
            this.ids = ids;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] arr = new Interval[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(intervals.get(i).get(0), intervals.get(i).get(1), intervals.get(i).get(2), i);
        }

        // End point ke according sort karein
        Arrays.sort(arr, (a, b) -> Integer.compare(a.r, b.r));

        // dp[i][k] -> i-th interval tak k elements lene par Best Result
        Pair[][] dp = new Pair[n + 1][5];
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new Pair(0, new ArrayList<>());
            }
        }

        for (int i = 1; i <= n; i++) {
            Interval curr = arr[i - 1];

            // Binary search to find last non-overlapping interval
            int low = 0, high = i - 2, prevIdx = 0;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (arr[mid].r < curr.l) {
                    prevIdx = mid + 1;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            for (int k = 1; k <= 4; k++) {
                // Option 1: Skip current interval
                Pair best = dp[i - 1][k];

                // Option 2: Take current interval
                long newWeight = dp[prevIdx][k - 1].weight + curr.weight;
                List<Integer> newIds = new ArrayList<>(dp[prevIdx][k - 1].ids);
                newIds.add(curr.id);
                Collections.sort(newIds);

                Pair candidate = new Pair(newWeight, newIds);

                // Update dp state using tie-breaker rules
                if (isBetter(candidate, best)) {
                    best = candidate;
                }
                dp[i][k] = best;
            }
        }

        // Overall best answer (across 1 to 4 intervals)
        Pair ans = new Pair(0, new ArrayList<>());
        for (int k = 1; k <= 4; k++) {
            if (isBetter(dp[n][k], ans)) {
                ans = dp[n][k];
            }
        }

        int[] res = new int[ans.ids.size()];
        for (int i = 0; i < ans.ids.size(); i++) {
            res[i] = ans.ids.get(i);
        }
        return res;
    }

    private boolean isBetter(Pair a, Pair b) {
        if (a.weight != b.weight) {
            return a.weight > b.weight;
        }
        // Weights equal hain toh lexicographically compare karein
        for (int i = 0; i < Math.min(a.ids.size(), b.ids.size()); i++) {
            if (!a.ids.get(i).equals(b.ids.get(i))) {
                return a.ids.get(i) < b.ids.get(i);
            }
        }
        return a.ids.size() < b.ids.size();
    }
}