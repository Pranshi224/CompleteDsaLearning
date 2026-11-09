public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        
        while (left < right) {
            // Integer overflow se bachne ke liye ye formula use karein
            int mid = left + (right - left) / 2;
            
            if (isBadVersion(mid)) {
                // Agar mid bad hai, toh pehla bad version ya toh mid hai ya left side mein hai
                right = mid;
            } else {
                // Agar mid bad nahi hai, toh pehla bad version right side mein hoga
                left = mid + 1;
            }
        }
        
        return left;
    }
}