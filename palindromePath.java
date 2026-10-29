
import java.util.*;

class Solution {
    private List<Integer>[] graph;
    private int[] tin, tout, depth, parent, order, prefix;
    private int[][] up;
    private int[] bit;
    private char[] chars;
    private int n, timer;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        this.n = n;
        chars = s.toCharArray();

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        parent = new int[n];
        order = new int[n];
        prefix = new int[n];

        Arrays.fill(parent, -1);

        int LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        up = new int[LOG][n];

        // Iterative DFS to avoid stack overflow
        int[] stack = new int[n];
        int top = 0;
        stack[top++] = 0;

        parent[0] = 0;
        prefix[0] = 1 << (chars[0] - 'a');

        timer = 0;

        while (top > 0) {
            int u = stack[--top];

            tin[u] = timer;
            order[timer++] = u;

            for (int v : graph[u]) {
                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                prefix[v] = prefix[u] ^ (1 << (chars[v] - 'a'));
                stack[top++] = v;
            }
        }

        // Calculate subtree intervals
        for (int i = 0; i < n; i++) {
            tout[i] = tin[i];
            up[0][i] = parent[i];
        }

        for (int i = n - 1; i > 0; i--) {
            int u = order[i];
            int p = parent[u];
            tout[p] = Math.max(tout[p], tout[u]);
        }

        // Binary lifting
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        // Fenwick tree for subtree range XOR updates
        bit = new int[n + 1];

        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                if (chars[u] == c) continue;

                int delta = (1 << (chars[u] - 'a'))
                          ^ (1 << (c - 'a'));

                chars[u] = c;

                rangeUpdate(tin[u], tout[u], delta);
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int w = lca(u, v);

                int maskU = prefix[u] ^ pointQuery(tin[u]);
                int maskV = prefix[v] ^ pointQuery(tin[v]);

                int mask = maskU ^ maskV
                         ^ (1 << (chars[w] - 'a'));

                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < up.length; j++) {
            if (((diff >> j) & 1) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) return u;

        for (int j = up.length - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return parent[u];
    }

    private void fenwickUpdate(int pos, int delta) {
        for (int i = pos + 1; i <= n; i += i & -i) {
            bit[i] ^= delta;
        }
    }

    private int pointQuery(int pos) {
        int result = 0;

        for (int i = pos + 1; i > 0; i -= i & -i) {
            result ^= bit[i];
        }

        return result;
    }

    private void rangeUpdate(int l, int r, int delta) {
        fenwickUpdate(l, delta);

        if (r + 1 < n) {
            fenwickUpdate(r + 1, delta);
        }
    }
}
