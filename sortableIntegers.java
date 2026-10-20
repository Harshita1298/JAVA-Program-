class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int totalSum = 0;

        // Iterate through all possible divisors k of n
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            if (isSortable(nums, k)) {
                totalSum += k;
            }
        }

        return totalSum;
    }

    private boolean isSortable(int[] nums, int k) {
        int n = nums.length;
        int m = n / k;
        
        Integer prevMax = null;

        for (int i = 0; i < m; i++) {
            int start = i * k;
            int end = start + k;

            // Find rotation drop points within the current block of length k
            int drops = 0;
            int minVal = nums[start], maxVal = nums[start];
            int rotStartIdx = start;

            for (int j = start; j < end; j++) {
                if (nums[j] < minVal) {
                    minVal = nums[j];
                }
                if (nums[j] > maxVal) {
                    maxVal = nums[j];
                }
                if (j + 1 < end && nums[j] > nums[j + 1]) {
                    drops++;
                    rotStartIdx = j + 1;
                }
            }

            // A single block of length k can be rotated into sorted order 
            // if and only if there is at most 1 drop.
            if (drops > 1) return false;

            // Check if the rotated block is internally sorted
            if (drops == 1) {
                // Verify that the elements wrap around correctly
                if (nums[end - 1] > nums[start]) {
                    return false;
                }
            }

            // Check order consistency between consecutive blocks
            int blockMin = nums[rotStartIdx];
            int blockMax = (rotStartIdx == start) ? nums[end - 1] : nums[rotStartIdx - 1];

            if (prevMax != null && prevMax > blockMin) {
                return false;
            }

            prevMax = blockMax;
        }

        return true;
    }
}