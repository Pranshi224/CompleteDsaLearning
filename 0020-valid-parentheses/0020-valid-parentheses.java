import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            // Opening brackets ke liye unka closing counterpart push karo
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } 
            // Closing bracket milne par check karo
            else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }

        // Agar stack empty hai matlab sab balanced hain
        return stack.isEmpty();
    }
}