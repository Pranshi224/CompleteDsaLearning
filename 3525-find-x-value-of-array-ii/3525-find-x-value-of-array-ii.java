class Solution {
    static class SegmentTree {
        int n;
        int k;
        int[] prod;
        int[][] cnt;

        public SegmentTree(int[] nums, int k) {
            this.n = nums.length;
            this.k = k;
            this.prod = new int[4 * n];
            this.cnt = new int[4 * n][k];
            build(1, 0, n - 1, nums);
        }

        private void build(int node, int l, int r, int[] nums) {
            if (l == r) {
                int val = nums[l] % k;
                prod[node] = val;
                cnt[node][val] = 1;
                return;
            }
            int mid = l + (r - l) / 2;
            build(2 * node, l, mid, nums);
            build(2 * node + 1, mid + 1, r, nums);
            merge(node, 2 * node, 2 * node + 1);
        }

        private void merge(int node, int left, int right) {
            prod[node] = (prod[left] * prod[right]) % k;
            
            // Copy counts from left child
            for (int i = 0; i < k; i++) {
                cnt[node][i] = cnt[left][i];
            }
            
            // Add shifted counts from right child
            int leftProd = prod[left];
            for (int i = 0; i < k; i++) {
                int newRem = (leftProd * i) % k;
                cnt[node][newRem] += cnt[right][i];
            }
        }

        public void update(int node, int l, int r, int idx, int val) {
            if (l == r) {
                int rem = val % k;
                prod[node] = rem;
                for (int i = 0; i < k; i++) {
                    cnt[node][i] = 0;
                }
                cnt[node][rem] = 1;
                return;
            }
            int mid = l + (r - l) / 2;
            if (idx <= mid) {
                update(2 * node, l, mid, idx, val);
            } else {
                update(2 * node + 1, mid + 1, r, idx, val);
            }
            merge(node, 2 * node, 2 * node + 1);
        }

        public int query(int node, int l, int r, int ql, int qr, int targetX) {
            // Helper wrapper for range query
            int[] currentProd = new int[]{1};
            return queryHelper(1, 0, n - 1, ql, qr, targetX, currentProd);
        }

        private int queryHelper(int node, int l, int r, int ql, int qr, int targetX, int[] currentProd) {
            if (ql <= l && r <= qr) {
                int count = 0;
                // Find how many prefixes in node contribute to targetX given currentProd
                for (int i = 0; i < k; i++) {
                    if ((currentProd[0] * i) % k == targetX) {
                        count += cnt[node][i];
                    }
                }
                currentProd[0] = (currentProd[0] * prod[node]) % k;
                return count;
            }
            
            int mid = l + (r - l) / 2;
            int res = 0;
            if (ql <= mid) {
                res += queryHelper(2 * node, l, mid, ql, qr, targetX, currentProd);
            }
            if (qr > mid) {
                res += queryHelper(2 * node + 1, mid + 1, r, ql, qr, targetX, currentProd);
            }
            return res;
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        SegmentTree st = new SegmentTree(nums, k);
        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            st.update(1, 0, n - 1, idx, val);
            ans[i] = st.query(1, 0, n - 1, start, n - 1, x);
        }

        return ans;
    }
}