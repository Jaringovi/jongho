package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1182 {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer tokens;

    int n, s, ans;
    int[] arr;

    public static void main(String[] args) throws IOException {
        new problem1182().Solve();
    }

    void Input() throws IOException {
        tokens = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokens.nextToken());
        s = Integer.parseInt(tokens.nextToken());

        arr = new int[n];
        tokens = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tokens.nextToken());
        }
    }

    void Solve() throws IOException {
        Input();
        subset(0, 0);
        if (s == 0)
            ans -= 1;
        System.out.println(ans);
    }

    void subset(int cnt, int sum) {
        if (cnt == n) {
            if (sum == s)
                ans++;
            return;
        }

        subset(cnt + 1, sum + arr[cnt]);
        subset(cnt + 1, sum);
    }
}
