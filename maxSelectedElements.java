class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        HashMap<Integer, Integer> dp = new HashMap<>();
        int ans = 1;

        for (int x : nums) {

            int useX = dp.getOrDefault(x - 1, 0) + 1;

            int useXPlus1 = dp.getOrDefault(x, 0) + 1;

            dp.put(x, Math.max(dp.getOrDefault(x, 0), useX));

            dp.put(x + 1,
                   Math.max(dp.getOrDefault(x + 1, 0), useXPlus1));

            ans = Math.max(ans,
                    Math.max(dp.get(x), dp.get(x + 1)));
        }

        return ans;
    }
}
