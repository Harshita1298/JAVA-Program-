import java.util.*;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;

        // Step 1: differences निकालो
        int[] diff = new int[n];
        int maxDiff = 0;
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        // Step 2: frequency array बनाओ
        long[] freq = new long[maxDiff + 1];
        for (int d : diff) {
            freq[d]++;
        }

        // Step 3: greedy reduction
        for (int i = maxDiff; i > 0 && k > 0; i--) {
            if (freq[i] == 0) continue;
            long reduce = Math.min(k, freq[i]);
            freq[i] -= reduce;
            freq[i - 1] += reduce;
            k -= reduce;
        }

        // Step 4: final sum of squares
        long ans = 0;
        for (int i = 0; i <= maxDiff; i++) {
            if (freq[i] > 0) {
                ans += (long) i * i * freq[i];
            }
        }
        return ans;
    }
}
