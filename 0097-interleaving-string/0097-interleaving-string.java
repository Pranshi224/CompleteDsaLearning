class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        
        // Base length check
        if (m + n != s3.length()) {
            return false;
        }
        
        // dp[i][j] stores if s1[0...i-1] and s2[0...j-1] form s3[0...i+j-1]
        boolean[][] dp = new boolean[m + 1][n + 1];
        
        // Base case: Empty strings can form empty string
        dp[0][0] = true;
        
        // First column: using only s1
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        }
        
        // First row: using only s2
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] && (s2.charAt(j - 1) == s3.charAt(j - 1));
        }
        
        // Fill the rest of the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char currentS3 = s3.charAt(i + j - 1);
                
                boolean fromS1 = (s1.charAt(i - 1) == currentS3) && dp[i - 1][j];
                boolean fromS2 = (s2.charAt(j - 1) == currentS3) && dp[i][j - 1];
                
                dp[i][j] = fromS1 || fromS2;
            }
        }
        
        return dp[m][n];
    }
}