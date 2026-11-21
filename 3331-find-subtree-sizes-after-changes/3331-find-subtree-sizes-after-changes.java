import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            adj.get(parent[i]).add(i);
        }

        // Har node ka updated parent store karne ke liye
        int[] newParent = Arrays.copyOf(parent, n);
        // Current DFS path par har character ka sabse recent ancestor
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        // Step 1: DFS karke closest matching ancestor dhundhein
        findNewParents(0, s, adj, lastSeen, newParent);

        // Step 2: Updated parents se naya tree banayein
        List<List<Integer>> newAdj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            newAdj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            newAdj.get(newParent[i]).add(i);
        }

        // Step 3: Final tree ke subtree sizes calculate karein
        int[] ans = new int[n];
        computeSizes(0, newAdj, ans);
        return ans;
    }

    private void findNewParents(int u, String s, List<List<Integer>> adj, int[] lastSeen, int[] newParent) {
        int charIdx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[charIdx];

        if (prevAncestor != -1) {
            newParent[u] = prevAncestor;
        }

        lastSeen[charIdx] = u;
        for (int v : adj.get(u)) {
            findNewParents(v, s, adj, lastSeen, newParent);
        }
        // Backtrack: purana state restore karein
        lastSeen[charIdx] = prevAncestor;
    }

    private int computeSizes(int u, List<List<Integer>> newAdj, int[] ans) {
        int size = 1;
        for (int v : newAdj.get(u)) {
            size += computeSizes(v, newAdj, ans);
        }
        ans[u] = size;
        return size;
    }
}