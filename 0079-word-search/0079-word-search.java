class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Agar pehla letter match hota hai, tabhi DFS start karo
                if (board[i][j] == word.charAt(0) && dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int r, int c, int index) {
        // Base case: Word Poora match ho gaya
        if (index == word.length()) {
            return true;
        }

        // Boundary condition aur Character matching check
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word.charAt(index)) {
            return false;
        }

        // Cell ko visited mark karo
        char temp = board[r][c];
        board[r][c] = '#';

        // 4 directions me check karo (Up, Down, Left, Right)
        boolean found = dfs(board, word, r + 1, c, index + 1) ||
                        dfs(board, word, r - 1, c, index + 1) ||
                        dfs(board, word, r, c + 1, index + 1) ||
                        dfs(board, word, r, c - 1, index + 1);

        // Backtrack: Original state restore karo
        board[r][c] = temp;

        return found;
    }
}