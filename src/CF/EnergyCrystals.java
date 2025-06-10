package CF;

import java.util.*;
import java.io.*;

public class EnergyCrystals {
    static int LOG = 30;
    static Reader sc = new Reader();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

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
        int t = ni();
        while (t-- > 0) {
            solve();
        }
        
        bw.flush();
        bw.close();
        // writer.println(toggles);
        // writer.close();
    }
    private static void solve() throws IOException {
        int x = ni(), count = 0;
        int[] arr = new int[]{0, 0, 0};
        int tr = 80;
        while (arr[0] != x || arr[1] != x || arr[2] != x) {
            if (arr[0] <= arr[1] && arr[0] <= arr[2]) {
                if (arr[1] <= arr[2]) {
                    //arr[1] is the second min
                    if (arr[1] == 0)
                        arr[0] = 1;
                    else {
                        int tmp = arr[1] * 2 + 1;
                        if (tmp/2 <= arr[1] && tmp <= x)
                            arr[0] = tmp;
                        else {
                            tmp = arr[1] * 2;
                            if (tmp/2 <= arr[1] && tmp <= x)
                                arr[0] = tmp;
                            else {
                                tmp = arr[1] * 2 - 1;
                                if (tmp/2 <= arr[1] && tmp <= x)
                                    arr[0] = tmp;
                                else    
                                    arr[0] = x;
                            }
                        }
                    }
                }
                else {
                    //arr[2] is the second min
                    if (arr[2] == 0)
                        arr[0] = 1;
                    else {
                        int tmp = arr[2] * 2 + 1;
                        if (tmp/2 <= arr[2] && tmp <= x)
                            arr[0] = tmp;
                        else {
                            tmp = arr[2] * 2;
                            if (tmp/2 <= arr[2] && tmp <= x)
                                arr[0] = tmp;
                            else {
                                tmp = arr[2] * 2 - 1;
                                if (tmp/2 <= arr[2] && tmp <= x)
                                    arr[0] = tmp;
                                else    
                                    arr[0] = x;
                            }
                        }
                    }
                }
            }
            else if (arr[1] <= arr[0] && arr[1] <= arr[2]) {
                if (arr[0] <= arr[2]) {
                    if (arr[0] == 0)
                        arr[1] = 1;
                    else {
                        int tmp = arr[0] * 2 + 1;
                        if (tmp/2 <= arr[0] && tmp <= x)
                            arr[1] = tmp;
                        else {
                            tmp = arr[0] * 2;
                            if (tmp/2 <= arr[0] && tmp <= x)
                                arr[1] = tmp;
                            else {
                                tmp = arr[0] * 2 - 1;
                                if (tmp/2 <= arr[0] && tmp <= x)
                                    arr[1] = tmp;
                                else    
                                    arr[1] = x;
                            }
                        }
                    }
                }
                else {
                    if (arr[2] == 0)
                        arr[1] = 1;
                    else {
                        int tmp = arr[2] * 2 + 1;
                        if (tmp/2 <= arr[2] && tmp <= x)
                            arr[1] = tmp;
                        else {
                            tmp = arr[2] * 2;
                            if (tmp/2 <= arr[2] && tmp <= x)
                                arr[1] = tmp;
                            else {
                                tmp = arr[2] * 2 - 1;
                                if (tmp/2 <= arr[2] && tmp <= x)
                                    arr[1] = tmp;
                                else    
                                    arr[1] = x;
                            }
                        }
                    }
                }
            }
            else {
                if (arr[0] <= arr[1]) {
                    if (arr[0] == 0)
                        arr[2] = 1;
                    else {
                        int tmp = arr[0] * 2 + 1;
                        if (tmp/2 <= arr[0] && tmp <= x)
                            arr[2] = tmp;
                        else {
                            tmp = arr[0] * 2;
                            if (tmp/2 <= arr[0] && tmp <= x)
                                arr[2] = tmp;
                            else {
                                tmp = arr[0] * 2 - 1;
                                if (tmp/2 <= arr[0] && tmp <= x)
                                    arr[2] = tmp;
                                else    
                                    arr[2] = x;
                            }
                        }
                        
                    }
                }
                else {
                    if (arr[1] == 0)
                        arr[2] = 1;
                    else {
                        int tmp = arr[1] * 2 + 1;
                        if (tmp/2 <= arr[1] && tmp <= x)
                            arr[2] = tmp;
                        else {
                            tmp = arr[0] * 2;
                            if (tmp/2 <= arr[1] && tmp <= x)
                                arr[2] = tmp;
                            else {
                                tmp = arr[1] * 2 - 1;
                                if (tmp/2 <= arr[1] && tmp <= x)
                                    arr[2] = tmp;
                                else    
                                    arr[2] = x;
                            }
                        }
                    }
                }
            }
            count++;
        }
        println(count);

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
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
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
        return sc.readLine();
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
