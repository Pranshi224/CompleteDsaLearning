class Solution {
    private int matchingNodeCount = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return matchingNodeCount;
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0}; // [sum, count]
        }

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        int totalSum = root.val + left[0] + right[0];
        int totalCount = 1 + left[1] + right[1];

        if (totalSum / totalCount == root.val) {
            matchingNodeCount++;
        }

        return new int[]{totalSum, totalCount};
    }
}