import java.util.*;
import java.io.*;

public class CSES1 {
    static int LOG = 20;
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        // int n = ni();
        // int k = ni();
        // int[] arr = ni(n);
        int n = ni(), k = ni(), arr[] = ni(n);
        long res = 0L;
        Map<Integer, Integer> map = new HashMap<>();
        for (int r = 0, l = 0; r < n; r++) {
            map.put(arr[r], map.getOrDefault(arr[r], 0) + 1);
            while (map.size() > k) {
                map.put(arr[l], map.get(arr[l]) - 1);
                if (map.get(arr[l]) == 0)
                    map.remove(arr[l]);
                l++;
            }
            res += (r - l + 1);
        }
        System.out.println(res);

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
