package week5;

// 떡먹는 호랑이
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class problem2502 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int d, k;
    static List<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Input();
        solve();
    }

    private static void Input() throws IOException {
        tokens = new StringTokenizer(input.readLine());
        d = Integer.parseInt(tokens.nextToken());
        k = Integer.parseInt(tokens.nextToken());

        list.add(new int[] { 0, 0 });
        list.add(new int[] { 1, 0 });
        list.add(new int[] { 0, 1 });
    }

    private static void solve() {
        for (int i = 3; i <= d; i++) {
            int tmp[] = { list.get(i - 1)[0] + list.get(i - 2)[0], list.get(i - 1)[1] + list.get(i - 2)[1] };
            list.add(tmp);
        }
        int prev1 = list.get(d)[0];
        int prev2 = list.get(d)[1];
        int a = 0;
        int b = 0;
        int result = 0;
        while (result != k) {
            a++;
            int tmp = k - (prev1 * a);
            if (tmp % prev2 == 0 && a <= tmp / prev2) {
                b = tmp / prev2;
                result = prev1 * a + prev2 * b;
            }
        }
        System.out.printf("%d\n%d\n", a, b);
    }

}
