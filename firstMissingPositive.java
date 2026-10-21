class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        
        // Step 1: Place each positive integer x at index x - 1
        int i = 0;
        while (i < n) {
            int correctIndex = nums[i] - 1;
            // Check if nums[i] is within the range [1, n] and not already at its correct position
            if (nums[i] > 0 && nums[i] <= n && nums[i] != nums[correctIndex]) {
                // Swap nums[i] with nums[correctIndex]
                int temp = nums[i];
                nums[i] = nums[correctIndex];
                nums[correctIndex] = temp;
            } else {
                i++;
            }
        }
        
        // Step 2: Find the first index where the value is not i + 1
        for (i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        
        // If all 1 to n are present, the answer is n + 1
        return n + 1;
    }
}