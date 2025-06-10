import java.io.*;
import java.util.*;

public class CFDiv1B305 {
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        int n = ni();
        int[] arr = ni(n);
        Stack<Integer> stack = new Stack<>();
        int[] left = new int[n], right = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            stack.pop();
        }
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int len = right[i] - left[i] - 2;
            res[len] = Math.max(res[len], arr[i]);
        }
        for (int i = n - 2; i >= 0; i--) {
            res[i] = Math.max(res[i], res[i + 1]);
        }
        for (int i = 0; i < n; i++) {
            System.out.print(res[i] + " ");
        }

        System.out.println();

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