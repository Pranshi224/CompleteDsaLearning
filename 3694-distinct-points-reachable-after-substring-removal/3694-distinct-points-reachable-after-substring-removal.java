import java.util.HashSet;
import java.util.Set;

class Solution {
    public int distinctPoints(String s, int k) {
        int totalX = 0, totalY = 0;
        int n = s.length();

        // Step 1: Calculate total displacement of the entire string
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'U') totalY++;
            else if (c == 'D') totalY--;
            else if (c == 'L') totalX--;
            else if (c == 'R') totalX++;
        }

        // Step 2: Calculate displacement for the first window of size k
        int winX = 0, winY = 0;
        for (int i = 0; i < k; i++) {
            char c = s.charAt(i);
            if (c == 'U') winY++;
            else if (c == 'D') winY--;
            else if (c == 'L') winX--;
            else if (c == 'R') winX++;
        }

        Set<Long> uniquePoints = new HashSet<>();
        // Unique hash for (finalX, finalY)
        uniquePoints.add(hash(totalX - winX, totalY - winY));

        // Step 3: Slide the window of size k across the string
        for (int i = k; i < n; i++) {
            // Remove character entering left side of window
            char outChar = s.charAt(i - k);
            if (outChar == 'U') winY--;
            else if (outChar == 'D') winY++;
            else if (outChar == 'L') winX++;
            else if (outChar == 'R') winX--;

            // Add new character entering right side of window
            char inChar = s.charAt(i);
            if (inChar == 'U') winY++;
            else if (inChar == 'D') winY--;
            else if (inChar == 'L') winX--;
            else if (inChar == 'R') winX++;

            uniquePoints.add(hash(totalX - winX, totalY - winY));
        }

        return uniquePoints.size();
    }

    // Helper to map 2D coordinates into a single long for HashSet
    private long hash(int x, int y) {
        return (((long) x + 200000L) << 32) | ((long) y + 200000L);
    }
}
