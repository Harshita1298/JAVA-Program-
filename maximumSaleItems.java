class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] gain = new int[n];

        for (int i = 0; i < n; i++) {
            int f = items[i][0];

            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (items[j][0] % f == 0) {
                    cnt++;
                }
            }
            gain[i] = cnt;
        }

        int[] dp = new int[budget + 1];

        for (int i = 0; i < n; i++) {
            int cost = items[i][1];

            int[] ndp = dp.clone();

            for (int b = cost; b <= budget; b++) {
                ndp[b] = Math.max(ndp[b], dp[b - cost] + gain[i]);
            }

            for (int b = cost; b <= budget; b++) {
                ndp[b] = Math.max(ndp[b], ndp[b - cost] + 1);
            }

            dp = ndp;
        }

        int ans = 0;
        for (int x : dp) ans = Math.max(ans, x);

        return ans;
    }
}
