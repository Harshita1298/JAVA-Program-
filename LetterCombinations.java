import java.util.*;

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;

        // Digit to letters mapping
        String[] mapping = {
            "",    // 0
            "",    // 1
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl", // 5
            "mno", // 6
            "pqrs",// 7
            "tuv", // 8
            "wxyz" // 9
        };

        backtrack(result, digits, mapping, new StringBuilder(), 0);
        return result;
    }

    private void backtrack(List<String> result, String digits, String[] mapping, StringBuilder current, int index) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        String letters = mapping[digits.charAt(index) - '0'];
        for (char c : letters.toCharArray()) {
            current.append(c);
            backtrack(result, digits, mapping, current, index + 1);
            current.deleteCharAt(current.length() - 1); // backtrack
        }
    }
}
