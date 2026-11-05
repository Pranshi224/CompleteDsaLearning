import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int distanceValue = 0;
        
        for (int x : arr1) {
            if (isValid(arr2, x, d)) {
                distanceValue++;
            }
        }
        
        return distanceValue;
    }
    
    private boolean isValid(int[] arr2, int target, int d) {
        int left = 0, right = arr2.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (Math.abs(arr2[mid] - target) <= d) {
                return false; // Found an element within distance d
            }
            
            if (arr2[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return true; // No element in arr2 is within distance d of target
    }
}