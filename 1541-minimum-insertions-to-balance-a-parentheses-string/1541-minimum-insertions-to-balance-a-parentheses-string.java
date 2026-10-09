class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int open = 0;
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                // Agar current character ')' hai, check karo ki agli character bhi ')' hai kya
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i++; // Next ')' ko consume kar liya
                } else {
                    // Agar agla character ')' nahi hai, to 1 ')' insert karna padega
                    insertions++;
                }
                
                // Ab humare paas complete '))' hai
                if (open > 0) {
                    open--; // Pehle se open '(' ke saath pair ban gaya
                } else {
                    insertions++; // '(' missing tha, to 1 '(' insert karna padega
                }
            }
        }
        
        // Jitne '(' unmatched bach gaye, unke liye 2 * open closing brackets lagenge
        insertions += 2 * open;
        
        return insertions;
    }
}