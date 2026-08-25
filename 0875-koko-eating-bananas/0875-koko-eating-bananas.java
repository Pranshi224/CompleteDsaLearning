class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1, high = 0;
        for (int pile : piles) {
            high = Math.max(high, pile);
        }
        
        int ans = high;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            long hours = 0;
            
            for (int pile : piles) {
                hours += (pile + mid - 1) / mid; // Math.ceil(pile / mid)
            }
            
            if (hours <= h) {
                ans = mid;
                high = mid - 1; // Seek smaller speed
            } else {
                low = mid + 1; // Need higher speed
            }
        }
        
        return ans;
    }
}