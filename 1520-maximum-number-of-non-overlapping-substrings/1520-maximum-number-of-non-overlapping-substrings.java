import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';
            if (first[ch] == -1) first[ch] = i;
            last[ch] = i;
        }

        List<int[]> validIntervals = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) continue;

            int start = first[i];
            int end = last[i];
            boolean isValid = true;

            for (int j = start; j <= end; j++) {
                int ch = s.charAt(j) - 'a';
                if (first[ch] < start) {
                    isValid = false; // Range expand hoke start se pehle ja rahi hai
                    break;
                }
                end = Math.max(end, last[ch]);
            }

            if (isValid) {
                validIntervals.add(new int[]{start, end});
            }
        }

        // End index ke hisab se sort karein
        validIntervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> result = new ArrayList<>();
        int prevEnd = -1;

        for (int[] interval : validIntervals) {
            if (interval[0] > prevEnd) {
                result.add(s.substring(interval[0], interval[1] + 1));
                prevEnd = interval[1];
            }
        }

        return result;
    }
}