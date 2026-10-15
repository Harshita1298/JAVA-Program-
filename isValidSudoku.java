import java.util.*;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char num = board[r][c];

                if (num == '.') continue;

                if (!seen.add(num + " in row " + r) ||
                    !seen.add(num + " in col " + c) ||
                    !seen.add(num + " in box " + (r / 3) + "-" + (c / 3))) {
                    return false;
                }
            }
        }

        return true;
    }
}
