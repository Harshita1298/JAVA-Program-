class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        int[] fact = new int[n + 1];

        fact[0] = 1;

        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i;
            nums.add(i);
        }

        k--;

        StringBuilder ans = new StringBuilder();

        for (int i = n; i > 0; i--) {
            int index = k / fact[i - 1];

            ans.append(nums.get(index));
            nums.remove(index);

            k %= fact[i - 1];
        }

        return ans.toString();
    }
}
