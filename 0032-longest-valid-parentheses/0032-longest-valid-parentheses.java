import java.util.Stack;

class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        // Push -1 onto the stack as a base index for boundary calculation
        stack.push(-1);
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // Store the index of '('
                stack.push(i);
            } else {
                // Pop the last opening bracket index or boundary mark
                stack.pop();

                if (stack.isEmpty()) {
                    // If empty, push the current closing bracket's index as a new boundary mark
                    stack.push(i);
                } else {
                    // Calculate length of the valid substring ending at index i
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }
}