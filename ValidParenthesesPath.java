import java.util.*;

class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length, n = grid[0].length;

        if ((m + n - 1) % 2 != 0) return false;

        // Memoization cache: key = row, col, balance
        Boolean[][][] dp = new Boolean[m][n][m + n];

        return dfs(0, 0, 0, grid, dp);
    }

    private boolean dfs(int i, int j, int balance, char[][] grid, Boolean[][][] dp) {
        int m = grid.length, n = grid[0].length;
        if (grid[i][j] == '(') balance++;
        else balance--;
        if (balance < 0) return false;
        if (i == m - 1 && j == n - 1) {
            return balance == 0;
        }
        if (dp[i][j][balance] != null) return dp[i][j][balance];

        boolean ans = false;
        if (i + 1 < m) ans |= dfs(i + 1, j, balance, grid, dp);
        if (j + 1 < n) ans |= dfs(i, j + 1, balance, grid, dp);

        return dp[i][j][balance] = ans;
    }
}
