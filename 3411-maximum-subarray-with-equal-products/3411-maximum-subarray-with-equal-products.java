class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 0;

        // 1 se 10 tak ke numbers ka maximum possible LCM 2520 hota hai, 
        // aur max GCD 10 ho sakta hai. Toh prod 25200 se bada nahi ho sakta.
        int maxPossibleProd = 25200;

        for (int i = 0; i < n; i++) {
            int prod = 1;
            int currentGcd = 0;
            int currentLcm = 1;

            for (int j = i; j < n; j++) {
                prod *= nums[j];
                
                // Overflow aur unnecessary checks se bachne ke liye break
                if (prod > maxPossibleProd) {
                    break;
                }

                currentGcd = gcd(currentGcd, nums[j]);
                currentLcm = lcm(currentLcm, nums[j]);

                if (prod == currentLcm * currentGcd) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }
}