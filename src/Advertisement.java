import java.util.*;
import java.io.*;

public class Advertisement {
    static int LOG = 20;
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        // int n = ni();
        // int k = ni();
        // int[] arr = ni(n);
        int n = ni();
        int[] arr = ni(n);
        int[] l = new int[n], r = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            l[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            stack.pop();
        }
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            r[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        long area = 0;
        for (int i = 0; i < n; i++) {
            long width = r[i] - l[i] - 1;
            area = (long)Math.max(area, width * arr[i]);
        }
        System.out.println(area);

    }





    
    // === Utility Input Methods ===

    static String next() throws IOException {
        while (st == null || !st.hasMoreElements())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }

    static int ni() throws IOException {
        return Integer.parseInt(next());
    }

    static long nl() throws IOException {
        return Long.parseLong(next());
    }

    static double nd() throws IOException {
        return Double.parseDouble(next());
    }

    static String ns() throws IOException {
        return next();
    }

    static int[] ni(int n) throws IOException {
        int[] res = new int[n];
        for (int i = 0; i < n; i++) res[i] = ni();
        return res;
    }

    static long[] nl(int n) throws IOException {
        long[] res = new long[n];
        for (int i = 0; i < n; i++) res[i] = nl();
        return res;
    }

    static String[] ns(int n) throws IOException {
        String[] res = new String[n];
        for (int i = 0; i < n; i++) res[i] = br.readLine();
        return res;
    }
}
