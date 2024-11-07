//메모리: 15192	시간: 168
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer tokens;
    int n;
    double ans;
    int per[] = new int[4];
    int arr[][] = new int[30][30];
    boolean visited[][] = new boolean[30][30];
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};


    public static void main(String[] args) throws IOException {
        new Main().solve();
    }

    // 입력
    void init() throws IOException {
        tokens = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(tokens.nextToken());
        for (int i = 0; i < 4; i++) {
            per[i] = Integer.parseInt(tokens.nextToken());
        }
    }

    void dfs(int x, int y, int cnt, double percent) {
        // 기저 조건
        if (visited[x][y]) return;
        if (cnt == n) {
            ans += percent;
            return;
        }

        // 현재 위치 방문 표시
        visited[x][y] = true;

        // 동서남북 방향으로 이동
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (per[i] > 0) { // 해당 방향의 확률이 0이 아닐 때만 이동
                dfs(nx, ny, cnt + 1, percent * per[i] / 100.0);
            }
        }

        // 방문 해제 (백트래킹)
        visited[x][y] = false;
    }

    void solve() throws IOException {
        init();
        dfs(15, 15, 0, 1.0); // 중간 지점에서 시작
        System.out.printf("%.9f\n", ans);
    }
}