class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String result = "";
        
        for (int i = 0; i < n; i++) {
            int countOnes = 0;
            for (int j = i; j < n; j++) {
                if (s.charAt(j) == '1') {
                    countOnes++;
                }
                
                if (countOnes == k) {
                    String sub = s.substring(i, j + 1);
                    
                    if (result.isEmpty()) {
                        result = sub;
                    } else if (sub.length() < result.length()) {
                        result = sub;
                    } else if (sub.length() == result.length() && sub.compareTo(result) < 0) {
                        result = sub;
                    }
                    
                    // Since adding more characters won't make the length shorter for this starting index i,
                    // we can stop expanding the window from i.
                    break; 
                }
            }
        }
        
        return result;
    }
}