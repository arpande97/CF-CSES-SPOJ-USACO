import java.io.*;
import java.util.*;

public class Div2DCF {
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        int n = ni();
        int[] arr = ni(n);
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            //mono-decreasing stack
            while (!stack.isEmpty() && stack.peek() < arr[i]) {
                res = Math.max(res, stack.pop() ^ arr[i]);
            }
            if (!stack.isEmpty())
                res = Math.max(res, stack.peek() ^ arr[i]);
            stack.push(arr[i]);
        }
        System.out.println(res);

    }
    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }
    static int ni() throws IOException {
        return Integer.parseInt(next());
    }
    static int[] ni(int n) throws IOException {
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = ni();
        }
        return res;
    }
}
