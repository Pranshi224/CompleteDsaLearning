import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        int maxDepth = getMaxDepth(1, 0, adj);

        // Power of 2^(maxDepth - 1) % MOD
        long ans = 1;
        long base = 2;
        int exp = maxDepth - 1;

        while (exp > 0) {
            if ((exp & 1) == 1) ans = (ans * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }

        return (int) ans;
    }

    private int getMaxDepth(int u, int p, List<Integer>[] adj) {
        int maxD = 0;
        for (int v : adj[u]) {
            if (v != p) {
                maxD = Math.max(maxD, 1 + getMaxDepth(v, u, adj));
            }
        }
        return maxD;
    }
}