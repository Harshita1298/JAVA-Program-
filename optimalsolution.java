class Solution {
    public int[] resultArray(int[] nums, int k) {
        int[] ans = new int[k];

        // dp[r] = current ending position par
        // product ka remainder r hone wale subarrays ki count
        int[] dp = new int[k];

        for (int num : nums) {
            int rem = num % k;

            int[] next = new int[k];

            // Sirf current element wala subarray
            next[rem]++;

            // Purane subarrays ke end me current element add karo
            for (int r = 0; r < k; r++) {
                int newRem = (r * rem) % k;
                next[newRem] += dp[r];
            }

            dp = next;

            // Is position par end hone wale saare subarrays
            for (int r = 0; r < k; r++) {
                ans[r] += dp[r];
            }
        }

        return ans;
    }
}
