import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int m = queries.length;
        int[][] q = new int[m][2];

        for (int i = 0; i < m; i++) {
            q[i][0] = queries[i];
            q[i][1] = i;
        }

        Arrays.sort(q, (a, b) -> Integer.compare(a[0], b[0]));

        int[] ans = new int[m];
        Map<Integer, Integer> freq = new HashMap<>();

        int left = 0, right = 0;
        int activeServers = 0;

        for (int[] query : q) {
            int time = query[0];

            while (right < logs.length && logs[right][1] <= time) {
                int server = logs[right][0];

                freq.put(server, freq.getOrDefault(server, 0) + 1);
                if (freq.get(server) == 1) {
                    activeServers++;
                }

                right++;
            }

            while (left < logs.length && logs[left][1] < time - x) {
                int server = logs[left][0];

                freq.put(server, freq.get(server) - 1);
                if (freq.get(server) == 0) {
                    activeServers--;
                    freq.remove(server);
                }

                left++;
            }

            ans[query[1]] = n - activeServers;
        }

        return ans;
    }
}
