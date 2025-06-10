import java.util.*;
import java.io.*;

public class Sightseeing {
    static int LOG = 30;
    static Reader sc = new Reader();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static List<List<Integer>> g = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        // int n = ni();
        // int k = ni();
        // int[] arr = ni(n);
        int n = ni(), m = ni(), k = ni();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = ni() - 1, v = ni() - 1;
            g.get(u).add(v);
            g.get(v).add(u);
        }
        Set<List<Integer>> forbidden = new HashSet<>();
        for (int i = 0; i < k; i++) {
            int a = ni() - 1, b = ni() - 1, c = ni() - 1;
            forbidden.add(Arrays.asList(a, b, c));
        }
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = 0;
        Map<Pii, Pii> parentMap = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.add(new int[]{0, 0, 0});
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int prev = node[0], u = node[1], d = node[2];
            for (int v : g.get(u)) {
                if (dist[v][u] > 1 + d && valid(forbidden, prev, u, v)) {
                    dist[v][u] = 1 + d;
                    parentMap.put(new Pii(u, v), new Pii(prev, u));
                    pq.add(new int[]{u, v, d + 1});
                }
            }
        }
        if (!possible(dist, n))
            println(-1);
        else {
            int min = Integer.MAX_VALUE, endPrev = -1;
            for (int i = 0; i < n; i++) {
                if (dist[n - 1][i] < min) {
                    min = dist[n - 1][i];
                    endPrev = i;
                }
            }
            println(min);
            List<Integer> path = new ArrayList<>();
            Pii curr = new Pii(endPrev, n - 1);
            while (curr.a != 0 || curr.b != 0) {
                path.add(curr.b + 1);
                curr = parentMap.get(curr);
            }
            path.add(1);
            Collections.reverse(path);
            for (int v : path) {
                printSp(v);
            }
            println();

        }

        bw.flush(); 
        bw.close();
    }
    private static boolean possible(int[][] dist, int n) {
        for (int i = 0; i < n; i++) {
            if (dist[n - 1][i] != Integer.MAX_VALUE)
                return true;
        }
        return false;
    }
    private static boolean valid(Set<List<Integer>> forbidden, int a, int b, int c) {
        if (forbidden.contains(Arrays.asList(a, b, c)))
            return false;
        return true;
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
    public static void println() throws IOException
    {
        bw.newLine();
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
