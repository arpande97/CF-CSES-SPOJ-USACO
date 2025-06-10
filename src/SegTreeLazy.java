public class SegTreeLazy {
    static int n = 10000;
    static int[] tree, lazy;
    public static void main(String[] args) {
        int[] arr = new int[10000];
        tree = new int[4 * n];
        lazy = new int[4 * n];

    }
    private void update(int ql, int qr, int val) {
        update(1, 0, n - 1, ql, qr, val);
    }
    private void update(int node, int l, int r, int ql, int qr, int val) {
        if (l > qr || r < ql)
            return; //no overlap
        if (ql <= l && r <= qr) { //full overlap - apply and return back
            apply(node, l, r, val);
            return;
        }
        //partial overlap
        push(node, l, r);
        int mid = l + (r - l)/2;
        update(2 * node, l, mid, ql, qr, val);
        update(2 * node + 1, mid + 1, r, ql, qr, val);
        tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
    }
    //push the value in node to its children
    private void push(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = l + (r - l)/2;
            apply(2 * node, l, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0; //children have been updated, so this node does not hold any info
        }
    }
    private void apply(int node, int l, int r, int val) {
        if (l != r) {
            lazy[node] += val; //when update operation adds a value to the range (l, r)
            //lazy[node] = val; //when update operation sets the value of a range(l, r)
        }
        tree[node] += val;
        //tree[node] += (r - l + 1) * val; use this if merge() is sum and change the merge in update
    }
}
