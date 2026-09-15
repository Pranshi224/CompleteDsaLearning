class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int count = 0;
        int lastEnd = -1; // Last chosen palindrome ka ending index track karne ke liye

        for (int center = 0; center < 2 * n; center++) {
            int left = center / 2;
            int right = left + (center % 2);

            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                // Check karo ki overlap na ho aur length at least k ho
                if (left > lastEnd && (right - left + 1) >= k) {
                    count++;
                    lastEnd = right; // Current palindrome consume ho gaya
                    break;           // Shortest valid palindrome lena hi greedy optimal hai
                }
                left--;
                right++;
            }
        }
        return count;
    }
}