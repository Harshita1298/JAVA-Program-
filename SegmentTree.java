import java.util.*;

class Solution {
    static final long MOD = 1000000007L;

    class SegmentTree {
        long[] sum;
        long[] sq;
        long[] lazy;

        SegmentTree(int n) {
            sum = new long[4 * n];
            sq = new long[4 * n];
            lazy = new long[4 * n];
        }

        void apply(int idx, int l, int r, long add) {
            long len = r - l + 1L;

            sq[idx] += 2L * add * sum[idx] + len * add * add;
            sum[idx] += len * add;
            lazy[idx] += add;
        }

        void push(int idx, int l, int r) {
            if (lazy[idx] == 0) return;

            int mid = (l + r) >> 1;

            apply(idx * 2, l, mid, lazy[idx]);
            apply(idx * 2 + 1, mid + 1, r, lazy[idx]);

            lazy[idx] = 0;
        }

        void update(int idx, int l, int r, int ql, int qr, long add) {
            if (ql > r || qr < l) return;

            if (ql <= l && r <= qr) {
                apply(idx, l, r, add);
                return;
            }

            push(idx, l, r);

            int mid = (l + r) >> 1;

            update(idx * 2, l, mid, ql, qr, add);
            update(idx * 2 + 1, mid + 1, r, ql, qr, add);

            sum[idx] = sum[idx * 2] + sum[idx * 2 + 1];
            sq[idx] = sq[idx * 2] + sq[idx * 2 + 1];
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;

        SegmentTree seg = new SegmentTree(n);
        Map<Integer, Integer> last = new HashMap<>();

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = last.getOrDefault(nums[i], -1);

            seg.update(1, 0, n - 1, prev + 1, i, 1);

            ans = (ans + seg.sq[1]) % MOD;

            last.put(nums[i], i);
        }

        return (int) ans;
    }
}
