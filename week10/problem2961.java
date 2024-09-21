// 문제 해결 아이디어: 부분 집합 및 최소 1개 이상 처리, 연산 최대 100억이므로 long
// 14216kb	108ms

package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem2961 {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder output = new StringBuilder();
    StringTokenizer tokens;

    int n;
    boolean selected[];
    Food[] foods;
    long ans = Long.MAX_VALUE;

    class Food {
        int sour, bitter;

        Food(int sour, int bitter) {
            this.sour = sour;
            this.bitter = bitter;
        }
    }

    public static void main(String[] args) throws IOException {
        new problem2961().Solve();
    }

    void init() throws IOException {
        n = Integer.parseInt(input.readLine());
        foods = new Food[n];
        selected = new boolean[n];
        for (int i = 0; i < n; i++) {
            tokens = new StringTokenizer(input.readLine());
            int s = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            foods[i] = new Food(s, b);
        }
    }

    void subset(int cnt) {
        if (cnt == n) {
            // 차이 계산
            long result = calculator();
            ans = Math.min(result, ans);
            return;
        }
        selected[cnt] = true;
        subset(cnt + 1);

        selected[cnt] = false;
        subset(cnt + 1);
    }

    long calculator() {
        long s = 1;
        long b = 0;
        for (int i = 0; i < n; i++) {
            if (selected[i]) {
                s *= foods[i].sour;
                b += foods[i].bitter;
            }
        }
        if (s == 1 && b == 0)
            return ans;
        return (long) Math.abs(s - b);
    }

    void Solve() throws IOException {
        init();
        subset(0);
        System.out.println(ans);
    }
}