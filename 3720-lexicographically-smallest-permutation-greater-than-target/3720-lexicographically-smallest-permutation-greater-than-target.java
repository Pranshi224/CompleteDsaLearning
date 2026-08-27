class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Try matching target[0...i-1] exactly, and make target[i] larger
        for (int i = n - 1; i >= 0; i--) {
            // Check if target[0...i-1] can be formed using available frequencies
            int[] prefixCount = new int[26];
            boolean possible = true;
            for (int j = 0; j < i; j++) {
                int idx = target.charAt(j) - 'a';
                prefixCount[idx]++;
                if (prefixCount[idx] > count[idx]) {
                    possible = false;
                    break;
                }
            }
            if (!possible) continue;

            // Remaining available characters after filling prefix
            int[] rem = new int[26];
            for (int k = 0; k < 26; k++) {
                rem[k] = count[k] - prefixCount[k];
            }

            // Find smallest character greater than target[i]
            int targetChar = target.charAt(i) - 'a';
            int choice = -1;
            for (int c = targetChar + 1; c < 26; c++) {
                if (rem[c] > 0) {
                    choice = c;
                    break;
                }
            }

            if (choice != -1) {
                // Build the answer
                StringBuilder sb = new StringBuilder();
                sb.append(target.substring(0, i));
                sb.append((char) ('a' + choice));
                rem[choice]--;

                for (int c = 0; c < 26; c++) {
                    while (rem[c] > 0) {
                        sb.append((char) ('a' + c));
                        rem[c]--;
                    }
                }
                return sb.toString();
            }
        }

        return "";
    }
}