import java.util.*;

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        backtrack(root, result);
        return result;
    }

    private void backtrack(TreeNode node, List<Integer> result) {
        if (node == null) return;

        backtrack(node.left, result);  // Left
        backtrack(node.right, result); // Right
        result.add(node.val);          // Root
    }
}