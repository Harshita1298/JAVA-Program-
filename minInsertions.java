class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int open = 0; // count of unmatched '('

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                open++;
            } else { // c == ')'
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    // Found "))"
                    if (open > 0) {
                        open--; // match with one '('
                    } else {
                        insertions++; // need to insert '('
                    }
                    i++; // skip next ')'
                } else {
                    // Single ')'
                    if (open > 0) {
                        open--; // match with one '('
                        insertions++; // need one more ')' to complete pair
                    } else {
                        insertions += 2; // need '(' + ')' to balance
                    }
                }
            }
        }

        // Remaining '(' need 2 ')' each
        insertions += open * 2;
        return insertions;
    }
}
