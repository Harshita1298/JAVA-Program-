import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        // {finishTime, serverId}
        PriorityQueue<long[]> busy =
                new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        int[] count = new int[k];
        int maxRequests = 0;

        for (int i = 0; i < arrival.length; i++) {

            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                available.add((int) busy.poll()[1]);
            }

            if (available.isEmpty()) {
                continue;
            }

            Integer server = available.ceiling(i % k);

            if (server == null) {
                server = available.first();
            }

            available.remove(server);

            busy.offer(new long[]{
                    (long) arrival[i] + load[i],
                    server
            });

            count[server]++;
            maxRequests = Math.max(maxRequests, count[server]);
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == maxRequests) {
                ans.add(i);
            }
        }

        return ans;
    }
}
