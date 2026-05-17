package Algorithm.DataStructure;

import java.util.Arrays;

public class SegmentTree {
    private int n;
    private long[] tree;

    private long mergeVal(long a, long b) {
        return Math.max(a, b);
    }

    public SegmentTree(int n, long initVal) {
        this.n = n;
        long[] a = new long[n];
        Arrays.fill(a, initVal);
        tree = new long[2 << (32 - Integer.numberOfLeadingZeros(n - 1))];
        build(a, 1, 0, n - 1);
    }

    public SegmentTree(long[] a) {
        n = a.length;
        tree = new long[2 << (32 - Integer.numberOfLeadingZeros(n - 1))];
        build(a, 1, 0, n - 1);
    }

    public void update(int i, long val) {
        update(1, 0, n - 1, i, val);
    }

    public long query(int ql, int qr) {
        return query(1, 0, n - 1, ql, qr);
    }

    public long get(int i) {
        return query(1, 0, n - 1, i, i);
    }

    private void maintain(int node) {
        tree[node] = mergeVal(tree[node * 2], tree[node * 2 + 1]);
    }

    private void build(long[] a, int node, int l, int r) {
        if (l == r) {
            tree[node] = a[l];
            return;
        }
        int m = (l + r) >> 1;
        build(a, node * 2, l, m);
        build(a, node * 2 + 1, m + 1, r);
        maintain(node);
    }

    private void update(int node, int l, int r, int i, long val) {
        if (l == r) {
            tree[node] = mergeVal(tree[node], val);
            return;
        }
        int m = (l + r) >> 1;
        if (i <= m) {
            update(node * 2, l, m, i, val);
        } else {
            update(node * 2 + 1, m + 1, r, i, val);
        }
        maintain(node);
    }

    private long query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[node];
        }
        int m = (l + r) >> 1;
        if (qr <= m) {
            return query(node * 2, l, m, ql, qr);
        }
        if (ql > m) {
            return query(node * 2 + 1, m + 1, r, ql, qr);
        }
        long lRes = query(node * 2, l, m, ql, qr);
        long rRes = query(node * 2 + 1, m + 1, r, ql, qr);
        return mergeVal(lRes, rRes);
    }
}
