package week11;

//메모리: 14248kb	시간: 104ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem10844 {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder output = new StringBuilder();
    StringTokenizer tokens;
    int n;
    long[][] dp;
    final long num = 1000000000;

    public static void main(String[] args) throws IOException {
        new problem10844().Solve();
    }

    void init() throws IOException {
        n = Integer.parseInt(input.readLine());
        dp = new long[n + 1][9];

        for (int i = 0; i < 9; i++) {
            dp[0][i] = 1;
            dp[1][i] = 1;
        }
    }

    void Solve() throws IOException {
        init();
        long total = 0;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 9; j++) {
                if (j == 0)
                    dp[i][j] = dp[i - 1][j + 1] % num + dp[i - 2][j] % num;
                else if (j == 8)
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = dp[i - 1][j - 1] % num + dp[i - 1][j + 1] % num;
            }
        }
        for (int i = 0; i < 9; i++) {
            total = (total % num + dp[n][i] % num) % num;
        }
        System.out.println(total);
    }
}