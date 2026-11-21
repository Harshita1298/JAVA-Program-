class Solution {
    List<Integer>[] tree;
    List<Integer>[] finalTree;
    int[] newParent;
    int[] ans;
    String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;

        tree = new ArrayList[n];
        finalTree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
            finalTree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        newParent = new int[n];
        newParent[0] = -1;

        int[] last = new int[26];
        Arrays.fill(last, -1);

        dfsBuild(0, parent, last);

        for (int i = 1; i < n; i++) {
            finalTree[newParent[i]].add(i);
        }

        ans = new int[n];
        dfsSize(0);

        return ans;
    }

    private void dfsBuild(int u, int[] parent, int[] last) {
        int ch = s.charAt(u) - 'a';

        int prev = last[ch];

        if (u != 0) {
            newParent[u] = (prev == -1) ? parent[u] : prev;
        }

        last[ch] = u;

        for (int v : tree[u]) {
            dfsBuild(v, parent, last);
        }

        last[ch] = prev;
    }

    private int dfsSize(int u) {
        int size = 1;

        for (int v : finalTree[u]) {
            size += dfsSize(v);
        }

        ans[u] = size;
        return size;
    }
}
