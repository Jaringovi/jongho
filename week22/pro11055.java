import java.io.*;
import java.util.*;

public class Main {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer tokens;
    int N;
    int arr[] = new int[1001];
    int dp[] = new int[1001];

    void init() throws IOException {
        N = Integer.parseInt(input.readLine());
        tokens = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokens.nextToken());
        }
    }

    void solve() throws IOException {
        init();
        int ans = 0;
        for (int i = 0; i < N; i++) {
            dp[i] = arr[i];
            for (int j = 0; j <= i; j++) {
                if (arr[i] > arr[j] && dp[i] < dp[j] + arr[i]) {
                    dp[i] = dp[j] + arr[i];
                }
                ans = Math.max(ans, dp[i]);
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        new Main().solve();
    }
}