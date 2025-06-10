
package CF;
import java.util.*;
import java.io.*;

public class Racing {
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
        /*
         * - I had no clue so looked at the editorial
         * - editorial talks about how you can check if it's possible to complete the obstacle, 
         * - idea is you maintain, in what range you can be at each idx
         * - L = R = 0
         * - if d[i] = 1, new range is (l + 1, r + 1) -> but you can't be over r[i] and never lower than l[i], so (max(l + 1, l[i]), min(r + 1, r[i]))
         * - if d[i] = 0, new range is the same (l, r) check your min max like in above
         * - if d[i] = -1, you can chose to rise or not, so the range you can be in is (l, r + 1)
         * - if at any point, l > r you can't go through
         * - for example, you arrive at idx where l[i] = 2, r[i] = 4, and from previous  you could be in (L = 5, R = 7), new range would then become (max (L = 5 + 1, l[i]), min(R = 7 + 1, 4)) = (6, 4)
         * - this means you  can't drop now, or say you were in (L = 4, R = 6) and l[i] = 8, r[i] = 9, new range would be (8, 7), meaning you can't suddenly jump that high
         * - but how does this explanation relate to the code below ?
         * - you are checking for each index:
         *      1) if its possible to even achieve that l[i], meaning if pref is not atleast l[i], you should have enough -1s to change them to 1 and get to l[i];
         *      2) second while loop, the way I understand, just removes the option of changing -1s to 1 in future if for this current i it will make it go over r[i]
         * - 
         */
        int n = ni();
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = ni();
        }
        int[] l = new int[n], r = new int[n];
        for (int i = 0; i < n; i++) {
            l[i] = ni();
            r[i] = ni();
        }
        Stack<Integer> stack = new Stack<>();
        int pref = 0;
        for (int i = 0; i < n; i++) {
            if (d[i] == -1)
                stack.push(i);
            else
                pref += d[i];
            while (pref < l[i]) {
                if (stack.isEmpty()) {
                    println(-1);
                    return;
                }
                d[stack.pop()] = 1;
                pref++;
            }
            while (pref + stack.size() > r[i]) {
                if (stack.isEmpty()) {
                    println(-1);
                    return;
                }
                d[stack.pop()] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            printSp(Math.max(d[i], 0));
        }
        bw.newLine();
        
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
