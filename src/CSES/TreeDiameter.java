package CSES;

import java.util.*;
import java.io.*;

public class TreeDiameter {
    static int LOG = 30;
    static Reader sc = new Reader();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st = new StringTokenizer("");
    static final int IMAX = Integer.MAX_VALUE, IMIN = Integer.MIN_VALUE, IMOD = (int)1e9 + 7;
    static final long LMAX = Long.MAX_VALUE, LMIN = Long.MIN_VALUE, LMOD = (long)1e9 + 7;
    static List<List<Integer>> g = new ArrayList<>();
    static int[] dist;
    static int max = 0;


    public static void main(String[] args) throws Exception {
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
        int n = ni();
        if (n == 1) {
            println(0);
            return;
        }
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int u = ni() - 1, v = ni() - 1;
            g.get(u).add(v);
            g.get(v).add(u);
        }
        dist = new int[n];
        int node = bfs(0);
        node = bfs(node);
        println(dist[node]);
        //this tles due to recursion depth -> skewed trees will mess up because in java ~10000 frames are available, use bfs instead
        // dist = new int[n];
        // solve(0, -1);
        // int max = 0, node = -1;
        // for (int i = 0; i < n; i++) {
        //     if (dist[i] > max) {
        //         max = dist[i];
        //         node = i;
        //     }
        // }
        // Arrays.fill(dist, 0);
        // solve(node, -1);

        // max = 0;
        // for (int i = 0; i < n; i++) {
        //     if (dist[i] > max)
        //         max = dist[i];
        // }
        // println(max);
        
    }
    
    private static int bfs(int start) {
        Queue<int[]> q = new LinkedList<>();
        Arrays.fill(dist, 0);
        q.add(new int[]{start, 0, -1});
        int farthest = start;
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int u = node[0], d = node[1], parent = node[2];
            for (int v : g.get(u)) {
                if (v == parent)
                    continue;
                dist[v] = dist[u] + 1;
                if (dist[v] > dist[farthest])
                    farthest = v;
                q.add(new int[]{v, dist[v], u});
            }
        }
        return farthest;
    }
    // private static void solve(int u, int parent) {
    //     for (int v : g.get(u)) {
    //         if (v == parent)
    //             continue;
    //         dist[v] = dist[u] + 1;
    //         solve(v, u);
    //     }
        
    // }
    // private static void solve() throws IOException {
    //     int n = ni();
    //     for (int i = 0; i < n; i++) {
    //         g.add(new ArrayList<>());
    //     }
    //     for (int i = 0; i < n - 1; i++) {
    //         int u = ni() - 1, v = ni() - 1;
    //         g.get(u).add(v);
    //         g.get(v).add(u);
    //     }
    //     solve(0, -1);
    //     println(max);
    // }
    // private static int solve(int u, int parent) {
    //     if (g.get(u).isEmpty())
    //         return 1;
    //     int first = 0, second = 0;
    //     for (int v : g.get(u)) {
    //         if (v == parent)
    //             continue;
    //         int child = solve(v, u);
    //         if (child >= first) {
    //             second = first;     
    //             first = child;
    //         }
    //         else if (child >= second) {
    //             second = child;
    //         }
    //     }
    //     if (first + second > max) 
    //         max = first + second;
    //     return first + 1;
        

    // }




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
