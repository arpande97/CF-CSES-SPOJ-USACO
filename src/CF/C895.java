// package CF;

import java.util.*;
import java.io.*;

public class C895 {
    static int LOG = 30;
    static Reader sc = new Reader();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st = new StringTokenizer("");
    static final int IMAX = Integer.MAX_VALUE, IMIN = Integer.MIN_VALUE, IMOD = (int)1e9 + 7;
    static final long LMAX = Long.MAX_VALUE, LMIN = Long.MIN_VALUE, LMOD = (long)1e9 + 7;
    static final int AMAX = 70;
    static int[] count;
    static long[] poss;
    static int[] mask;
    static int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67};
    static final int TOTAL_PRIMES = 19;
    static int[][] dp;
    //count how many times each A occurs
    //calculate 2^(cnt - 1) -> number of times you can select even number or odd number of a's
    //calculate the mask for each number from 1 to 70


    public static void main(String[] args) throws Exception {
        int t = 1;
        while (t-- > 0) {
            solve();
        }

        bw.flush();
        bw.close();
    }
    private static void solve() throws IOException {
        int n = ni();
        count = new int[AMAX + 1];
        poss = new long[AMAX + 1];
        mask = new int[AMAX + 1];
        dp = new int[72][(1 << 19)];
        for (int i = 0; i < n; i++) {
            count[ni()]++;
        }
        
        for (int i = 1; i <= AMAX; i++) {
            long c = count[i];
            if (c >= 1)
                poss[i] = pow(2, c - 1);
            
        }
        //prime factorize each number and calculate how many times each prime factor appears
        for (int i = 1; i <= AMAX; i++) {
            //j index in primes will denote the bit in the mask of that number;
            
            for (int j = 0; j < TOTAL_PRIMES; j++) {
                int val = i;
                int d = primes[j], factor_count = 0;
                while (val % d == 0) {
                    factor_count++;
                    val /= d;
                }
                //bit is 0 when factor_count is even, otherwise its 1
                if ((factor_count & 1) != 0)
                    mask[i] |= (1 << j);
            }
        }
        dp[71][0] = 1;
        for (int at = 70; at >= 0; at--) {
            for (int msk = 0; msk < (1 << 19); msk++) {
                long change = (1l * (poss[at] * dp[at + 1][msk ^ mask[at]]) % LMOD) % LMOD;
                long no_change = (1l * (Math.max(poss[at], 1) * dp[at + 1][msk]) % LMOD) % LMOD;
                dp[at][msk] = (int)((change + no_change) % LMOD);
            }
        }
        println((dp[0][0] - 1 + IMOD) % IMOD);


    }
    // private static long solve(int at, long msk) {
    //     if (at == 71)
    //         return msk == 0 ? 1l : 0l;
    //     if (dp[at][(int)msk] != -1)
    //         return dp[at][(int)msk];
    //     long change = (1l * (poss[at] * solve(at + 1, msk ^ mask[at])) % LMOD) % LMOD;//pick odd number of times
    //     long no_change = (1l * (Math.max(poss[at], 1) * solve(at + 1, msk)) % LMOD) % LMOD;//picked even number of times
    //     return dp[at][(int)msk] = (change + no_change) % LMOD;
    // }
    private static long pow(long a, long b) {
        if (b == 1)
            return a;
        if (b == 0)
            return 1l;
        long X = pow(a, b/2);
        if (b % 2 == 0)
            return (X * X) % LMOD;
        return (((X * X) % LMOD) * a) % LMOD;
    }




    private static int find(int x, int[] parent) {
        if (x == parent[x])
            return x;
        return find(parent[x], parent);
    }
    private static void union(int x, int y, int[] parent, int[] rank) {
        //run parent[i] = find(parent[i], parent) for all at the end if you want the leaders
        int X = find(x, parent), Y = find(y, parent);
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
