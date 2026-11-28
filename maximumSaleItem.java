
import java.util.*;

class Solution {
    public int maximumSaleItem(int[][] items, int budget) {

        int n = items.length;

        int[] freq = new int[n + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] freeCount = new int[n + 1];

        for (int f = 1; f <= n; f++) {
            for (int j = f; j <= n; j += f) {
                freeCount[f] += freq[j];
            }
        }

        Arrays.sort(items, (a, b) ->
            Integer.compare(a[1], b[1])
        );

        long spent = 0;
        long bonusItems = 0;

        long limit = 2L * minPrice;

        for (int[] item : items) {

            int factor = item[0];
            int price = item[1];

            int capacity = freeCount[factor] - 1;

            if (price > limit || capacity <= 0) {
                continue;
            }

            long take = Math.min(
                capacity,
                (budget - spent) / price
            );

            spent += take * price;
            bonusItems += take;
        }

        long normalItems = (budget - spent) / minPrice;

        return (int)(2 * bonusItems + normalItems);
    }
}
