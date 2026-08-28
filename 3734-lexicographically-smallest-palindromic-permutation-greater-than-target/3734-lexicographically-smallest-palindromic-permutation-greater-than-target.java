import java.util.Arrays;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Validate if s can form a valid palindrome
        int oddCount = 0;
        int midChar = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midChar = i;
            }
        }
        if ((n % 2 == 0 && oddCount > 0) || (n % 2 != 0 && oddCount != 1)) {
            return "";
        }

        // Half counts for constructing the first half of the palindrome
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        int m = n / 2;

        // Try prefix length L from m down to 0
        for (int L = m; L >= 0; L--) {
            int[] currentHalfCount = halfCount.clone();
            boolean validPrefix = true;
            char[] pref = new char[m];

            // Build matching prefix up to L-1
            for (int i = 0; i < L; i++) {
                int c = target.charAt(i) - 'a';
                if (currentHalfCount[c] > 0) {
                    currentHalfCount[c]--;
                    pref[i] = target.charAt(i);
                } else {
                    validPrefix = false;
                    break;
                }
            }

            if (!validPrefix) continue;

            // If L == m, prefix matches entirely up to mid
            if (L == m) {
                // If odd length, try placing a larger character at middle
                if (n % 2 != 0) {
                    int origMid = midChar;
                    for (int newMid = target.charAt(m) - 'a' + 1; newMid < 26; newMid++) {
                        if (newMid == origMid) {
                            String candidate = buildPalindrome(pref, (char) ('a' + newMid), currentHalfCount);
                            if (candidate.compareTo(target) > 0) return candidate;
                        }
                    }
                }
                
                String candidate = buildPalindrome(pref, n % 2 != 0 ? (char) ('a' + midChar) : 0, currentHalfCount);
                if (candidate.compareTo(target) > 0) return candidate;
                
                continue;
            }

            // At index L, pick a character strictly greater than target[L]
            int targetChar = target.charAt(L) - 'a';
            for (int nextChar = targetChar + 1; nextChar < 26; nextChar++) {
                if (currentHalfCount[nextChar] > 0) {
                    int[] tempCounts = currentHalfCount.clone();
                    tempCounts[nextChar]--;
                    
                    char[] fullHalf = Arrays.copyOf(pref, m);
                    fullHalf[L] = (char) ('a' + nextChar);

                    // Fill remaining half greedily with smallest available chars
                    int idx = L + 1;
                    for (int c = 0; c < 26; c++) {
                        while (tempCounts[c] > 0) {
                            fullHalf[idx++] = (char) ('a' + c);
                            tempCounts[c]--;
                        }
                    }

                    String candidate = buildPalindrome(fullHalf, n % 2 != 0 ? (char) ('a' + midChar) : 0, tempCounts);
                    if (candidate.compareTo(target) > 0) return candidate;
                }
            }
        }

        return "";
    }

    private String buildPalindrome(char[] firstHalf, char mid, int[] remainingCounts) {
        StringBuilder sb = new StringBuilder();
        sb.append(firstHalf);
        if (mid != 0) {
            sb.append(mid);
        }
        for (int i = firstHalf.length - 1; i >= 0; i--) {
            sb.append(firstHalf[i]);
        }
        return sb.toString();
    }
}