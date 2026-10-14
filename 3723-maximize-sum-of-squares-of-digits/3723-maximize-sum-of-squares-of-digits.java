class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // Check if the sum is within the valid bounds
        if (sum < 1 || sum > (long) num * 9) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        int remainingSum = sum;
        
        for (int i = 0; i < num; i++) {
            int remainingPositions = num - 1 - i;
            // The maximum digit we can place here without starving the remaining positions
            int maxDigit = Math.min(9, remainingSum - remainingPositions);
            
            // To maximize the number itself when scores are tied, 
            // the greedy approach naturally puts the largest digits first.
            // Wait, let's look closer at the problem: "If there are multiple possible integers, return the maximum one."
            // Since we want the lexicographically largest number for the same max score, 
            // putting the largest possible digit at the current position is optimal.
            
            // Let's refine the digit choice: 
            // We want to maximize d_i^2. The function x^2 is strictly increasing for x >= 0.
            // Thus, we want as many 9s as possible.
            int digit = Math.min(9, remainingSum);
            // But we must leave enough sum for the remaining positions if minimum needed is 0,
            // wait, can trailing/subsequent positions be 0? Yes! 
            // Example 1: num = 2, sum = 3 -> outputs "30" (3^2 + 0^2 = 9).
            // So a digit can be 0 as long as the total sum is exhausted.
            // Thus, the max we can put at position i is min(9, remainingSum) 
            // PROVIDED the rest of the sum can be accommodated by the remaining digits (min 0, max remainingPositions * 9).
            
            int chosen = 0;
            for (int d = Math.min(9, remainingSum); d >= 0; d--) {
                if (remainingSum - d <= remainingPositions * 9) {
                    chosen = d;
                    break;
                }
            }
            
            sb.append(chosen);
            remainingSum -= chosen;
        }
        
        return sb.toString();
    }
}