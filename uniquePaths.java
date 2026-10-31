import java.util.Arrays;

class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        // dpR[r][c] = number of valid paths ending at (r, c) moving Right
        // dpD[r][c] = number of valid paths ending at (r, c) moving Down
        long[][] dpR = new long[m][n];
        long[][] dpD = new long[m][n];

        // Base case: We start at (0, 0). 
        // We can simulate the initial moves from (0, 0).
        // If we move Right from (0,0):
        int curR = 0, curC = 0;
        int d = 'R';
        
        // Let's use a standard DP filling order by r + c sum from 0 to m + n - 2
        // To properly seed the DP, let's find all valid cells reachable from (0,0).
        
        // Instead of complex seeding, let's consider that at each cell (r, c), 
        // we can accumulate paths coming into (r, c) with direction 'R' or 'D', 
        // and then push/transition them to their next valid state.
        
        long[][] waysR = new long[m][n];
        long[][] waysD = new long[m][n];
        
        // Start state: we can imagine coming into (0,0) isn't a move, but let's initialize:
        // Actually, from (0,0) we can make a move. Let's trace next states from (0,0) for 'R' and 'D'.
        
        // Let's use standard push DP:
        // waysR[r][c] = ways to arrive at (r, c) and be facing/moving Right
        // waysD[r][c] = ways to arrive at (r, c) and be facing/moving Down
        
        // Since (0,0) is the start, let's set a virtual path count of 1 at (0,0) before first move.
        // Or simpler: simulate transitions from every (r, c).
        
        long[][] dp_right = new long[m][n];
        long[][] dp_down = new long[m][n];
        
        // We can initialize the starting cell (0,0) with 1 way of being ready to move.
        // Let's define helper to get next state with reflection chain:
        
        // Forward DP:
        // For each cell (r, c), if it has paths, we can try moving Right and Down.
        
        // Let's precompute or compute on the fly:
        long[][] rightWays = new long[m][n];
        long[][] downWays = new long[m][n];
        
        rightWays[0][0] = 1; // Start
        downWays[0][0] = 1;  // Start
        
        // Total paths arriving at (r, c):
        long[][] totalR = new long[m][n];
        long[][] totalD = new long[m][n];
        
        // Actually, let's implement the standard propagation:
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (r == 0 && c == 0) {
                    // From (0,0), we can initiate a move Right or Down
                    // Moving Right from (0,0):
                    int[] nextR = getNext(grid, 0, 0, 'R');
                    if (nextR != null) {
                        totalR[nextR[0]][nextR[1]] = (totalR[nextR[0]][nextR[1]] + 1) % MOD;
                    }
                    // Moving Down from (0,0):
                    int[] nextD = getNext(grid, 0, 0, 'D');
                    if (nextD != null) {
                        totalD[nextD[0]][nextD[1]] = (totalD[nextD[0]][nextD[1]] + 1) % MOD;
                    }
                    continue;
                }
                
                // For other cells, paths can arrive via incoming Right or Down
                long waysR_val = totalR[r][c];
                long waysD_val = totalD[r][c];
                long totalWays = (waysR_val + waysD_val) % MOD;
                
                if (totalWays == 0) continue;
                
                // If we are at (r, c), we can choose to move Right or Down from here
                // Move Right:
                int[] nextR = getNext(grid, r, c, 'R');
                if (nextR != null) {
                    totalR[nextR[0]][nextR[1]] = (totalR[nextR[0]][nextR[1]] + totalWays) % MOD;
                }
                
                // Move Down:
                int[] nextD = getNext(grid, r, c, 'D');
                if (nextD != null) {
                    totalD[nextD[0]][nextD[1]] = (totalD[nextD[0]][nextD[1]] + totalWays) % MOD;
                }
            }
        }
        
        return (int)((totalR[m - 1][n - 1] + totalD[m - 1][n - 1]) % MOD);
    }
    
    private int[] getNext(int[][] grid, int r, int c, char dir) {
        int m = grid.length;
        int n = grid[0].length;
        int currR = r, currC = c;
        char d = dir;
        
        while (true) {
            int nxtR = currR + (d == 'D' ? 1 : 0);
            int nxtC = currC + (d == 'R' ? 1 : 0);
            
            if (nxtR < 0 || nxtR >= m || nxtC < 0 || nxtC >= n) {
                return null; // Out of bounds
            }
            
            if (grid[nxtR][nxtC] == 0) {
                return new int[] {nxtR, nxtC};
            } else {
                // Hit a mirror, reflect:
                currR = nxtR;
                currC = nxtC;
                d = (d == 'R' ? 'D' : 'R');
            }
        }
    }
}