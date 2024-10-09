package week11;

// 메모리:22420kb	시간: 112ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1149 {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder output = new StringBuilder();
    StringTokenizer tokens;
    int n;
    int rgb[][];
    int dp[][];

    public static void main(String[] args) throws IOException {
        new problem1149().Solve();
    }

    void init() throws IOException {
        n = Integer.parseInt(input.readLine());
        rgb = new int[n][n];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            tokens = new StringTokenizer(input.readLine());
            for (int j = 0; j < 3; j++) {
                rgb[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
    }

    void bottomUp() {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            dp[0][i] = rgb[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    dp[i][j] = rgb[i][j] + Math.min(dp[i - 1][1], dp[i - 1][2]);
                } else if (j == 1)
                    dp[i][j] = rgb[i][j] + Math.min(dp[i - 1][0], dp[i - 1][2]);
                else
                    dp[i][j] = rgb[i][j] + Math.min(dp[i - 1][0], dp[i - 1][1]);

                if (i == n - 1)
                    ans = Math.min(ans, dp[i][j]);
            }
        }
        System.out.println(ans);
    }

    void Solve() throws IOException {
        init();
        bottomUp();
    }
}
