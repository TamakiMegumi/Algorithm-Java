package Algorithm;

/**
 * 并查集
 */
public class UnionFind {
    private int cnt;
    private int[] parent;

    public UnionFind() {
        this(5);
    }

    public UnionFind(int n) {
        cnt = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public boolean merge(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return false;
        }
        parent[rootP] = rootQ;
        cnt--;
        return true;
    }

    public boolean isSame(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public int getCount() {
        return cnt;
    }
}
