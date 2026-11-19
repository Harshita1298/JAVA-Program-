class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;

        if (n <= 3) return 0;

        Arrays.sort(nums);

        int op1 = nums[n - 1] - nums[2];
        int op2 = nums[n - 2] - nums[1];
        int op3 = nums[n - 3] - nums[0];

        return Math.min(op1, Math.min(op2, op3));
    }
}
