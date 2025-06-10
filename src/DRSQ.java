import java.util.*;
import java.io.*;

public class DRSQ {
    static int LOG = 30;
    static Reader sc = new Reader();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        // int n = ni();
        // int k = ni();
        // int[] arr = ni(n);
        int n = ni(), q = ni();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = ni();
        }
        long[] tree = new long[4 * n];
        build(tree, arr, 0, n - 1, 1);
        while (q-- > 0) {
            int type = ni();
            int a, b;
            if (type == 2) {
                a = ni() - 1;
                b = ni() - 1;
                println(query(tree, arr, 0, n - 1, a, b, 1));
            }
            else {
                a = ni() - 1;
                b = ni();
                update(tree, arr, 0, n - 1, 1, a, b);
            }

        }
        
        bw.flush();
        bw.close();
    }
    private static void build(long[] tree, int[] arr, int l, int r, int node) {
        if (l == r) {
            tree[node] = (long)arr[l];
            return;
        }
        int mid = l + (r - l)/2;
        build(tree, arr, l, mid, 2 * node);
        build(tree, arr, mid + 1, r, 2 * node + 1);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
        return;
    }
    private static long query(long[] tree, int[] arr, int l, int r, int ql, int qr, int node) {
        if (l > qr || r < ql)
            return 0L;
        if (l >= ql && r <= qr)
            return tree[node];
        int mid = l + (r - l)/2;
        return query(tree, arr, l, mid, ql, qr, 2 * node) + query(tree, arr, mid + 1, r, ql, qr, 2 * node + 1);
    }
    private static void update(long[] tree, int[] arr, int l, int r, int node, int idx, int val) {
        if (l == r) {
            tree[node] = (long)val;
            return;
        }
        int mid = l + (r - l)/2;
        if (idx <= mid) {
            update(tree, arr, l, mid, 2 * node, idx, val);
        }
        else    
            update(tree, arr, mid + 1, r, 2 * node + 1, idx, val);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
        return;
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
