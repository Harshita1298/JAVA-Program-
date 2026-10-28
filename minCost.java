import java.util.HashSet;
import java.util.Set;

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // dp[r][c] stores all possible XOR values for paths from (0,0) to (r,c)
        Set<Integer>[][] dp = new HashSet[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new HashSet<>();
            }
        }
        
        // Base case: start cell
        dp[0][0].add(grid[0][0]);
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                // If coming from the top cell (r-1, c)
                if (r > 0) {
                    for (int val : dp[r - 1][c]) {
                        dp[r][c].add(val ^ grid[r][c]);
                    }
                }
                // If coming from the left cell (r, c-1)
                if (c > 0) {
                    for (int val : dp[r][c - 1]) {
                        dp[r][c].add(val ^ grid[r][c]);
                    }
                }
            }
        }
        
        // Find the minimum XOR value at the bottom-right cell
        int minXor = Integer.MAX_VALUE;
        for (int val : dp[m - 1][n - 1]) {
            minXor = Math.min(minXor, val);
        }
        
        return minXor;
    }
}