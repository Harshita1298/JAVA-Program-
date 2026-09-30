import java.util.*;

public class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] ans = new int[n];
        int depth = 0;

        for (int i = 0; i < n; i++) {
            char ch = seq.charAt(i);
            if (ch == '(') {
                depth++;
                ans[i] = depth % 2;  // alternate assignment
            } else {
                ans[i] = depth % 2;
                depth--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.maxDepthAfterSplit("(()())")));
        System.out.println(Arrays.toString(sol.maxDepthAfterSplit("()(())()")));
    }
}
