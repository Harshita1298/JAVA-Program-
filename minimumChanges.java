class Solution {

    public int minimumChanges(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();

        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int len = j - i + 1;
                int best = Integer.MAX_VALUE;

                for (int d = 1; d < len; d++) {

                    if (len % d != 0) continue;

                    int cur = 0;

                    for (int r = 0; r < d; r++) {

                        int l1 = r;
                        int r1 = len - d + r;

                        while (l1 < r1) {
                            if (arr[i + l1] != arr[i + r1]) {
                                cur++;
                            }
                            l1 += d;
                            r1 -= d;
                        }
                    }

                    best = Math.min(best, cur);
                }

                cost[i][j] = best;
            }
        }

        int INF = 1_000_000_000;

        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }

        dp[0][0] = 0;

        for (int i = 0; i < n; i++) {

            for (int p = 0; p < k; p++) {

                if (dp[i][p] == INF) continue;

                for (int end = i + 1; end < n; end++) {

                    if (dp[i][p] + cost[i][end]
                            < dp[end + 1][p + 1]) {

                        dp[end + 1][p + 1] =
                                dp[i][p] + cost[i][end];
                    }
                }
            }
        }

        return dp[n][k];
    }
}
