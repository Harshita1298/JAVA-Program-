import java.util.Arrays;

class Solution {
    public String[] createGrid(int m, int n) {
        // Initialize the grid with '#' (all obstacles)
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');
        }
        
        // Option 1: Traverse all the way right along the top row (row 0)
        for (int j = 0; j < n; j++) {
            grid[0][j] = '.';
        }
        
        // Then traverse all the way down along the last column (column n - 1)
        for (int i = 0; i < m; i++) {
            grid[i][n - 1] = '.';
        }
        
        // Convert char[][] to String[]
        String[] result = new String[m];
        for (int i = 0; i < m; i++) {
            result[i] = new String(grid[i]);
        }
        
        return result;
    }
}