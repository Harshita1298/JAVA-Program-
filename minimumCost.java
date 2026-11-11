class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth,
                            int need1, int need2) {

        long common = Math.min(need1, need2);

        long ans = common * Math.min(costBoth, cost1 + cost2);

        need1 -= common;
        need2 -= common;

        ans += (long) need1 * Math.min(cost1, costBoth);
        ans += (long) need2 * Math.min(cost2, costBoth);

        return ans;
    }
}
