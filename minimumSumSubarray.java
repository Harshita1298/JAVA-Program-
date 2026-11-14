import java.util.List;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int minSum = Integer.MAX_VALUE;
        int n = nums.size();
        
        // Subarray ki har possible length ke liye loop (l se r tak)
        for (int len = l; len <= r; len++) {
            // Sliding window ya simple iteration se subarrays check karna
            for (int i = 0; i <= n - len; i++) {
                int currentSum = 0;
                for (int j = i; j < i + len; j++) {
                    currentSum += nums.get(j);
                }
                
                // Agar sum 0 se bada hai, toh minimum sum update karo
                if (currentSum > 0) {
                    minSum = Math.min(minSum, currentSum);
                }
            }
        }
        
        // Agar koi valid positive sum nahi mila toh -1 return karein
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}