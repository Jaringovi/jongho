// 메모리:22352	 시간: 244
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer tokens;
    int n, m;
    int[] moneys;

    public static void main(String[] args) throws IOException {
        new Main().solve();
    }

    // 입력
    void init() throws IOException {
        tokens = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());
        moneys = new int[n];

        for (int i = 0; i < n; i++) {
            moneys[i] = Integer.parseInt(bf.readLine());
        }
    }

    void binarySearch() {
        int left = 0;
        int right = 0;
        for (int i : moneys) {
            left = Math.max(i, left);
            right += i;
        }
        int ans = right;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isValid(mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(ans);
    }

    boolean isValid(int money) {
        int current = 0;
        int withdrawCount = 1; // 첫 인출을 포함하여 시작

        for (int today : moneys) {
            if (current + today > money) { // 인출해야 하는 경우
                withdrawCount++;
                current = 0;
            }
            current += today;
        }

        return withdrawCount <= m;
    }

    void solve() throws IOException {
        init();
        binarySearch();
    }
}
