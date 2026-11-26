class Solution {
    public boolean canAliceWin(int n) {
        int stonesToRemove = 10;
        boolean isAliceTurn = true;

        while (n >= stonesToRemove) {
            n -= stonesToRemove;
            stonesToRemove--;
            isAliceTurn = !isAliceTurn;
        }

        // If it's Alice's turn and she can't make the move, she loses (false).
        // If it's Bob's turn and he can't make the move, Alice wins (true).
        return !isAliceTurn;
    }
}