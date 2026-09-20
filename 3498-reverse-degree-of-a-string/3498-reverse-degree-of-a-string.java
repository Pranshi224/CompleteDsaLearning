class Solution {
    public int reverseDegree(String s) {
        int totalSum = 0;

        for (int i =0; i < s.length(); i++){
            char ch = s.charAt(i);

            int reversedAlphabetPos = 'z' - ch + 1;
            
            int stringPos = i + 1;

            totalSum += reversedAlphabetPos * stringPos;
        }
        
        return totalSum;
    }
}