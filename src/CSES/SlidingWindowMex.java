package CSES;

import java.util.*;
import java.io.*;

public class SlidingWindowMex {
    static int LOG = 30;
    static Reader sc = new Reader();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st = new StringTokenizer("");
    static final int IMAX = Integer.MAX_VALUE, IMIN = Integer.MIN_VALUE, IMOD = (int)1e9 + 7;
    static final long LMAX = Long.MAX_VALUE, LMIN = Long.MIN_VALUE, LMOD = (long)1e9 + 7;
    static int[] tree;
    /*
     * - key idea here is that mex can't be greater than k,
     * - for a window of size k, mex has to lie in the range [0, k]
     * - use a seg tree with min function as the merge op
     * - you don't care about arr values greater than k
     * - for a value in range k, first add all values in the seg tree(activate them)
     * - then do a seg tree walk, if the min value in the left node of tree is 0, go there
     * - if not, that means all the values in [0, i] have been activated and are present (can't be the mex)
     * - so go right
     * - if the entire tree (tree[1] is not 0), that means all values in [0, k - 1] are there and mex has to be k
     * - O(lgn) for the walk, O(lgn) for the update, hence the entire sol is O(nlgn)
     */

    public static void main(String[] args) throws Exception {
        // int n = ni();
        // int k = ni();
        // int[] arr = ni(n);
        // Scanner scan = new Scanner(new File("breedflip.in"));
        // PrintWriter writer = new PrintWriter(new File("breedflip.out"));
        // // int n = ni();
        // // int k = ni();
        // // int[] arr = ni(n);
        // int n = scan.nextInt(); scan.nextLine();
        // String A = scan.nextLine(), B = scan.nextLine();
        int t = 1;
        while (t-- > 0) {
            solve();
        }

        bw.flush();
        bw.close();
        // writer.println(toggles);
        // writer.close();
    }
    private static void solve() throws IOException {
        int n = ni(), k = ni();
        tree = new int[4 * k];
        int[] arr = new int[n];
        int[] res = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            arr[i] = ni();
        }
        for (int i = 0; i < k; i++) {
            int val = arr[i];
            if (val >= k)
                continue;
            update(0, k - 1, 1, val, 1);
        }
        res[0] = tree[1] != 0 ? k : walk(0, k - 1, 1);
        for (int i = k; i < n; i++) {
            int sub = arr[i - k], add = arr[i];
            if (sub < k)
                update(0, k - 1, 1, sub, -1);
            if (add < k)
                update(0, k - 1, 1, add, 1);
            res[i - k + 1] = tree[1] != 0 ? k : walk(0, k - 1, 1);
        }
        for (int r : res) {
            printSp(r);
        }
        bw.newLine();


    }
    private static int walk(int l, int r, int node) {
        if (l == r) 
            return l;
        int mid = l + (r - l)/2;
        if (tree[2 * node] == 0)
            return walk(l, mid, 2 * node);
        else    
            return walk(mid + 1, r, 2 * node + 1);
    }
    private static void update(int l, int r, int node, int idx, int val) {
        if (l == r) {
            tree[node] += val;
            return;
        }
        int mid = l + (r - l)/2;
        if (idx <= mid)
            update(l, mid, 2 * node, idx, val);
        else
            update(mid + 1, r, 2 * node + 1, idx, val);
        tree[node] = Math.min(tree[2 * node], tree[2 * node + 1]);
        
    }




    private static int find(int x, int[] parent) {
        if (x == parent[x])
            return x;
        return find(parent[x], parent);
    }
    private static void union(int x, int y, int[] parent, int[] rank) {
        int X = parent[x], Y = parent[y];
        if (X == Y)
            return;
        if (rank[X] < rank[Y])
            parent[X] = Y;
        else if (rank[X] > rank[Y])
            parent[Y] = X;
        else {
            parent[Y] = X;
            rank[X]++;
        }
    }











    static class SegmentTree {
        int[] tree, lazy;
        int n;

        public SegmentTree(int[] arr) {
            n = arr.length;
            tree = new int[4 * n];
            lazy = new int[4 * n];
            build(arr, 1, 0, n - 1);
        }

        private void build(int[] arr, int node, int l, int r) {
            if (l == r) {
                tree[node] = arr[l];
                return;
            }
            int mid = (l + r) / 2;
            build(arr, 2 * node, l, mid);
            build(arr, 2 * node + 1, mid + 1, r);
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }

        private void push(int node, int l, int r) {
            if (lazy[node] != 0) {
                tree[node] += (r - l + 1) * lazy[node]; // Apply lazy value
                if (l != r) { // Propagate to children
                    lazy[2 * node] += lazy[node];
                    lazy[2 * node + 1] += lazy[node];
                }
                lazy[node] = 0; // Clear lazy value at current node
            }
        }

        public void update(int ql, int qr, int val) {
            update(1, 0, n - 1, ql, qr, val);
        }

        private void update(int node, int l, int r, int ql, int qr, int val) {
            push(node, l, r);
            if (r < ql || l > qr) return; // No overlap
            if (ql <= l && r <= qr) {
                lazy[node] += val;
                push(node, l, r);
                return;
            }
            int mid = (l + r) / 2;
            update(2 * node, l, mid, ql, qr, val);
            update(2 * node + 1, mid + 1, r, ql, qr, val);
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }

        public int query(int ql, int qr) {
            return query(1, 0, n - 1, ql, qr);
        }

        private int query(int node, int l, int r, int ql, int qr) {
            push(node, l, r);
            if (r < ql || l > qr) return 0; // No overlap
            if (ql <= l && r <= qr) return tree[node]; // Total overlap
            int mid = (l + r) / 2;
            return query(2 * node, l, mid, ql, qr) + query(2 * node + 1, mid + 1, r, ql, qr);
        }
    }


































































    static class Pii {
        int a, b;
        Pii(int a, int b) {
            this.a = a;
            this.b = b;
        }
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pii)) return false;
            Pii other = (Pii) o;
            return this.a == other.a && this.b == other.b;
        }
        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }
    static class Pll {
        long a, b;
        Pll(long a, long b) {
            this.a = a;
            this.b = b;
        }
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pll)) return false;
            Pll other = (Pll) o;
            return this.a == other.a && this.b == other.b;
        }
        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }

    }
    static class Pil {
        int a;
        long b;
        Pil(int a, long b) {
            this.a = a;
            this.b = b;
        }
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pil)) return false;
            Pil other = (Pil) o;
            return this.a == other.a && this.b == other.b;
        }
        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }

    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                baos.write(c);
            }
            return baos.toString("UTF-8");
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }
            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }

    public static int ni()throws IOException
    {
        return sc.nextInt();
    }
    public static long nl()throws IOException
    {
        return sc.nextLong();
    }
    public static double nd()throws IOException
    {
        return sc.nextDouble();
    }
    public static String ns()throws IOException
    {
        String str = sc.readLine().trim();
        if (str.isEmpty())
            str = sc.readLine().trim();
        return str;
    }
    public static char nc() throws IOException {
        byte c;
        do {
            c = sc.read();
        } while (c <= ' ');
        return (char) c;
    }
    public static String nt() throws IOException {
        while (!st.hasMoreTokens()) {
            String line = sc.readLine();
            if (line == null) return null;
            st = new StringTokenizer(line);
        }
        return st.nextToken();
    }
    public static void print(String a)throws IOException
    {
        bw.write(a);
    }
    public static void printSp(String a)throws IOException
    {
        bw.write(a+" ");
    }
    public static void println(String a)throws IOException
    {
        bw.write(a+"\n");
    }
    public static void print(int a) throws IOException {
        bw.write(Integer.toString(a));
    }

    public static void println(int a) throws IOException {
        bw.write(Integer.toString(a));
        bw.newLine();
    }

    public static void print(long a) throws IOException {
        bw.write(Long.toString(a));
    }

    public static void println(long a) throws IOException {
        bw.write(Long.toString(a));
        bw.newLine();
    }

    public static void printSp(int a) throws IOException {
        bw.write(Integer.toString(a));
        bw.write(' ');
    }

    public static void printSp(long a) throws IOException {
        bw.write(Long.toString(a));
        bw.write(' ');
    }


}
