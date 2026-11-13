
class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        int LOG = 32 - Integer.numberOfLeadingZeros(n);

        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int[] next = new int[n];
        int[] stack = new int[n];
        int top = -1;

        // Next greater or equal element
        for (int i = n - 1; i >= 0; i--) {
            while (top >= 0 && nums[stack[top]] < nums[i]) {
                top--;
            }

            next[i] = (top >= 0) ? stack[top] : n;
            stack[++top] = i;
        }

        int[][] up = new int[LOG][n];
        long[][] cost = new long[LOG][n];

        for (int i = 0; i < n; i++) {
            up[0][i] = next[i];

            cost[0][i] =
                (long) nums[i] * (next[i] - i)
                - (prefix[next[i]] - prefix[i]);
        }

        // Binary lifting
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                int mid = up[j - 1][i];

                if (mid < n) {
                    up[j][i] = up[j - 1][mid];
                    cost[j][i] =
                        cost[j - 1][i] + cost[j - 1][mid];
                } else {
                    up[j][i] = n;
                    cost[j][i] = cost[j - 1][i];
                }
            }
        }

        long ans = 0;

        // Sliding window using binary search
        for (int r = 0; r < n; r++) {
            int low = 0;
            int high = r;

            while (low < high) {
                int mid = low + (high - low) / 2;

                if (getCost(mid, r, nums, prefix, up, cost) <= k) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }

            ans += r - low + 1;
        }

        return ans;
    }

    private long getCost(
        int l,
        int r,
        int[] nums,
        long[] prefix,
        int[][] up,
        long[][] cost
    ) {
        int LOG = up.length;
        int cur = l;
        long operations = 0;

        for (int j = LOG - 1; j >= 0; j--) {
            int next = up[j][cur];

            if (next <= r) {
                operations += cost[j][cur];
                cur = next;
            }
        }

        operations += (long) nums[cur] * (r - cur + 1)
                    - (prefix[r + 1] - prefix[cur]);

        return operations;
    }
}
