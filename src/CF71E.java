import java.util.*;
import java.io.*;

public class CF71E {
    static int LOG = 30;
    static Reader sc = new Reader();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static List<List<String>> comb = new ArrayList<>();
    static StringTokenizer st;
    
    static int[] maskSum, maskList, target;
    static Map<String, Integer> map = new HashMap<>();
    static String[] need;
    static Boolean[][] dp;

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
        //in case line is bunch of space separated strings, just use str[i] = next();
        int n = ni(), k = ni();
        fillAtomicNumbers();
        Map<Integer, String> pos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            pos.put(i, next());
        }
        need = new String[k];
        target = new int[k];
        for (int i = 0; i < k; i++) {
            need[i] = next();
            target[i] = map.get(need[i]);
        }
        int mask = (1 << n) - 1;
        maskSum = new int[mask + 1];
        maskList = new int[k];
        for (int s = mask; s > 0; s = ((s - 1) & mask)) {
            maskSum[s] = calculateSum(s, pos, map);
        }
        dp = new Boolean[(1 << n)][k];
        if (solve(0, 0, n, k)) {
            println("YES");
            for (int i = 0; i < k; i++) {
                int atomMask = maskList[i], j = 0;
                while (atomMask > 0) {
                    boolean in = false;
                    if ((atomMask & 1) != 0) {
                        print(pos.get(j));
                        in = true;
                    }
                    atomMask >>= 1;
                    j++;
                    if (in && atomMask != 0)
                        print("+");
                    if (atomMask == 0)
                        print("->");

                }
                println(need[i]);
            }
        }
        else {
            println("NO");
        }
        
        
        
        
        bw.flush();
        bw.close();
    }
    private static boolean solve(int mask, int idx, int n, int k) {
        if (idx == k) {
            return mask == ((1 << n) - 1);
        }
        if (dp[mask][idx] != null)
            return dp[mask][idx];
        int available = ((1 << n) - 1) ^ mask;
        for (int s = available; s > 0; s = ((s - 1) & available)) {
            int submaskSum = maskSum[s];
            if (submaskSum == target[idx]) {
                if (solve(mask | s, idx + 1, n, k)) {
                    maskList[idx] = s;
                    return dp[mask][idx] = true;
                }
            }
        }
        return dp[mask][idx] = false;
    }
    private static int calculateSum(int mask, Map<Integer, String> pos, Map<String, Integer> map) {
        int sum = 0, j = 0;
        while (mask > 0) {
            if ((mask & 1) != 0) {
                String atom = pos.get(j);
                sum += map.get(atom);
            }
            mask >>= 1;
            j++;
        }
        return sum;
    }
    private static void fillAtomicNumbers() {
        String[] symbols = {
            "H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne",
            "Na", "Mg", "Al", "Si", "P", "S", "Cl", "Ar", "K", "Ca",
            "Sc", "Ti", "V", "Cr", "Mn", "Fe", "Co", "Ni", "Cu", "Zn",
            "Ga", "Ge", "As", "Se", "Br", "Kr", "Rb", "Sr", "Y", "Zr",
            "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd", "In", "Sn",
            "Sb", "Te", "I", "Xe", "Cs", "Ba", "La", "Ce", "Pr", "Nd",
            "Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb",
            "Lu", "Hf", "Ta", "W", "Re", "Os", "Ir", "Pt", "Au", "Hg",
            "Tl", "Pb", "Bi", "Po", "At", "Rn", "Fr", "Ra", "Ac", "Th",
            "Pa", "U", "Np", "Pu", "Am", "Cm", "Bk", "Cf", "Es", "Fm"
        };
        for (int i = 0; i < 100; i++) {
            map.put(symbols[i], i + 1);
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
    public static String next() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(sc.readLine());
        return st.nextToken();
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
    public static void println() throws IOException {
        bw.newLine();
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
