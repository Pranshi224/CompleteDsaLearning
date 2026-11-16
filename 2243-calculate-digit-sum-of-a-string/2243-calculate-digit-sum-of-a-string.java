class Solution {
    public String digitSum(String s, int k) {
        // Continue the process as long as the length of s is strictly greater than k
        while (s.length() > k) {
            StringBuilder sb = new StringBuilder();
            
            // Process the string in consecutive chunks of size k
            for (int i = 0; i < s.length(); i += k) {
                // Extract group of size k (or smaller for the last group)
                String group = s.substring(i, Math.min(i + k, s.length()));
                
                // Calculate the sum of digits in the current group
                int sum = 0;
                for (char c : group.toCharArray()) {
                    sum += c - '0';
                }
                
                // Append the sum to our new string representation
                sb.append(sum);
            }
            
            // Update s for the next round
            s = sb.toString();
        }
        
        return s;
    }
}