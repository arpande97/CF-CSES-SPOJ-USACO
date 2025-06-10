import java.util.*;
import java.io.*;

public class CFRectangles {
    static int LOG = 30;
    static Reader sc = new Reader();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int m, n, mat[][];

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
            m = ni();
            n = ni();
            mat = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    mat[i][j] = ni();
                }
            }
            println(solve(mat, m, n));

        }
        
        bw.flush();
        bw.close();
    }
    private static long solve(int[][] mat, int m, int n) {
        int[] heights = new int[n];
        long res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0)
                    heights[j] = 1;
                else
                    heights[j] = mat[i][j] == mat[i - 1][j] ? heights[j] + 1 : 1;
            }
            res += countSub(heights, i);
        }
        return res;
    }
    private static long countSub(int[] heights, int row) {
        long count = 0, sum[] = new long[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && mat[row][i] == mat[row][stack.peek()] && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                sum[i] = (long)(i + 1) * heights[i];
            }
            else {
                int col = stack.peek();
                if (mat[row][i] == mat[row][col]) {
                    sum[i] = (long)(i - col) * heights[i];
                    sum[i] += sum[col];
                }
                else {
                    sum[i] = (long)(i - col) * heights[i];
                }
            }
            stack.push(i);
        }
        for (long s : sum) {
            count += s;
        }
        return count;
        
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
