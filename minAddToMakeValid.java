class Solution {
    public int minAddToMakeValid(String s) {
        int balance = 0;     // count of unmatched '('
        int insertions = 0;  // count of extra ')' needing '('

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                balance++;
            } else { // ch == ')'
                if (balance > 0) {
                    balance--; // match with previous '('
                } else {
                    insertions++; // need an extra '('
                }
            }
        }

        // remaining '(' need closing ')'
        return insertions + balance;
    }
}
