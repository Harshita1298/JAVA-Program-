class Solution {
    private static final long MOD = 1000000007L;
    private long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;

        comb = new long[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        long ans = dfs(nums);
        return (int)((ans - 1 + MOD) % MOD);
    }

    private long dfs(int[] nums) {
        int n = nums.length;
        if (n <= 2) return 1;

        int root = nums[0];

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            if (nums[i] < root)
                left.add(nums[i]);
            else
                right.add(nums[i]);
        }

        int[] leftArr = left.stream().mapToInt(Integer::intValue).toArray();
        int[] rightArr = right.stream().mapToInt(Integer::intValue).toArray();

        long leftWays = dfs(leftArr);
        long rightWays = dfs(rightArr);

        long ways = comb[left.size() + right.size()][left.size()];

        return (((ways * leftWays) % MOD) * rightWays) % MOD;
    }
}
