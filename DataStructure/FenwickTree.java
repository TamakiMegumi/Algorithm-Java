package Algorithm.DataStructure;

public class FenwickTree {
    private final long[] tree;

    public FenwickTree(int n) {
        tree = new long[n + 1];
    }

    public static int lowbit(int x) {
        return x & (-x);
    }

    public void update(int i, long val) {
        for (; i < tree.length; i += lowbit(i)) {
            tree[i] += val;
        }
    }

    public long pre(int i) {
        long res = 0;
        for (; i > 0; i &= i - 1) {
            res += tree[i];
        }
        return res;
    }

    public long query(int l, int r) {
        if (r < l) {
            return 0;
        }
        return pre(r) - pre(l - 1);
    }
}
