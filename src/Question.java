import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Question {
    static int LOG = 20;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] first = br.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        long k = Long.parseLong(first[1]);
        String[] input = br.readLine().split(" ");
        solve(n, k, input);
    }
    private static void solve(int n, long k, String[] input) {
        int[] arr = new int[2 * n];
        int[][] jump = new int[2 * n][LOG];
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(input[i]);
            arr[i] = value;
            arr[i + n] = value;
        }
        long sum = 0;
        for (int l = 0, r = 0; l < 2 * n; l++) {
            while (r < 2 * n && sum + arr[r] <= k) {
                sum += arr[r];
                r++;
            }
            jump[l][0] = r;
            sum -= arr[l];
        }
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < 2 * n; i++) {
                int next = jump[i][j - 1];
                jump[i][j] = next == 2 * n ? 2 * n : jump[next][j - 1];
            }
        }
        int res = n;
        for (int i = 0; i < n; i++) {
            int node = i;
            int jumps = 0;
            for (int j = LOG - 1; j >= 0; j--) {
                if (jump[node][j] < i + n) {
                    jumps += (1 << j);
                    node = jump[node][j];
                }
            }
            jumps++;
            res = Math.min(jumps, res);
        }
        StringBuilder builder = new StringBuilder();
        System.out.print(builder.append(res).append('\n'));
    }
}
