class Solution {
    public int totalNumbers(int[] digits) {
        Set<Integer> uniqueNums = new HashSet<>();
        int n = digits.length;

        for (int i = 0; i < n; i++) {
            if (digits[i] == 0) continue; // Leading zero not allowed

            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) continue;

                    // Units digit must be even
                    if (digits[k] % 2 == 0) {
                        int num = digits[i] * 100 + digits[j] * 10 + digits[k];
                        uniqueNums.add(num);
                    }
                }
            }
        }

        return uniqueNums.size();
    }
}