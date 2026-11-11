class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        // Arrays to store the last seen positions of characters.
        // Using 256 size to cover all valid ASCII characters.
        int[] mapS = new int[256];
        int[] mapT = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            // If the last seen positions don't match, the mapping is inconsistent.
            if (mapS[c1] != mapT[c2]) {
                return false;
            }

            // Store the 1-based index (i + 1) of the current character's last appearance.
            mapS[c1] = i + 1;
            mapT[c2] = i + 1;
        }

        return true;
    }
}