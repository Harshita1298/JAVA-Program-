import java.util.*;

class Solution {
    public int sumCounts(int[] nums) {
        long ans = 0;

        for (int i = 0; i < nums.length; i++) {
            HashSet<Integer> set = new HashSet<>();

            for (int j = i; j < nums.length; j++) {
                set.add(nums[j]);
                int distinct = set.size();
                ans += (long) distinct * distinct;
            }
        }

        return (int) ans;
    }
}
