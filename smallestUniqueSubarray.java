import java.util.*;

class Solution {
    private static final long MOD = 1_000_000_007L;
    private static final long BASE = 911382323L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        long[] pow = new long[n + 1];
        long[] pref = new long[n + 1];

        pow[0] = 1;

        for (int i = 0; i < n; i++) {
            pow[i + 1] = (pow[i] * BASE) % MOD;
            pref[i + 1] = (pref[i] * BASE + nums[i]) % MOD;
        }

        int lo = 1, hi = n;
        int ans = n;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (hasUnique(mid, pref, pow, n)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUnique(int len, long[] pref, long[] pow, int n) {
        HashMap<Long, Integer> freq = new HashMap<>();

        for (int i = 0; i + len <= n; i++) {
            long hash =
                    (pref[i + len] - (pref[i] * pow[len]) % MOD + MOD) % MOD;

            freq.put(hash, freq.getOrDefault(hash, 0) + 1);
        }

        for (int cnt : freq.values()) {
            if (cnt == 1) return true;
        }

        return false;
    }
}
