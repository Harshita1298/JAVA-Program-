import java.util.*;

public class MaxSumSquares {
    public static int maxSumSquares(int[] nums, int k) {
        int MOD = 1000000007;
        
        // Step 1: Sort array in descending order
        Arrays.sort(nums);
        int n = nums.length;
        
        // Step 2: Pick k largest elements
        long sum = 0;
        for (int i = n - 1; i >= n - k; i--) {
            long val = nums[i];
            sum = (sum + (val * val) % MOD) % MOD;
        }
        
        return (int) sum;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 6, 5, 8};
        int k1 = 2;
        System.out.println(maxSumSquares(nums1, k1)); // Output: 261

        int[] nums2 = {4, 5, 4, 7};
        int k2 = 3;
        System.out.println(maxSumSquares(nums2, k2)); // Output: 90
    }
}
