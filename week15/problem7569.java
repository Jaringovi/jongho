import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 130928	620
public class Main {

    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer tokens;
    
    int M, N, H;
    int[][][] box;
    int[][][] days; // 며칠이 걸렸는지 저장
    int[] dz = {0, 0, 0, 0, 1, -1}; // 위, 아래, 왼쪽, 오른쪽, 앞, 뒤
    int[] dx = {0, 0, -1, 1, 0, 0};
    int[] dy = {-1, 1, 0, 0, 0, 0};

    void init() throws IOException {
        tokens = new StringTokenizer(input.readLine());
        M = Integer.parseInt(tokens.nextToken());
        N = Integer.parseInt(tokens.nextToken());
        H = Integer.parseInt(tokens.nextToken());
        
        box = new int[H][N][M];
        days = new int[H][N][M];
        
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                tokens = new StringTokenizer(input.readLine());
                for (int m = 0; m < M; m++) {
                    box[h][n][m] = Integer.parseInt(tokens.nextToken());
                    if (box[h][n][m] == 0) {
                        days[h][n][m] = -1; // 익지 않은 토마토는 -1로 초기화
                    }
                }
            }
        }
    }
    
    void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        
        // 익은 토마토들(1)을 모두 큐에 넣습니다.
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (box[h][n][m] == 1) {
                        queue.add(new int[]{h, n, m});
                    }
                }
            }
        }
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int z = current[0], y = current[1], x = current[2];
            
            for (int i = 0; i < 6; i++) {
                int nz = z + dz[i];
                int ny = y + dy[i];
                int nx = x + dx[i];
                
                if (nz >= 0 && nz < H && ny >= 0 && ny < N && nx >= 0 && nx < M) {
                    if (box[nz][ny][nx] == 0 && days[nz][ny][nx] == -1) {
                        days[nz][ny][nx] = days[z][y][x] + 1;
                        queue.add(new int[]{nz, ny, nx});
                    }
                }
            }
        }
    }
    
    int getDaysToRipenAll() {
        int maxDays = 0;
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (days[h][n][m] == -1) { // 익지 않은 토마토가 남아 있는 경우
                        return -1;
                    }
                    maxDays = Math.max(maxDays, days[h][n][m]);
                }
            }
        }
        return maxDays;
    }

    void Solve() throws IOException {
        init();
        bfs();
        System.out.println(getDaysToRipenAll());
    }

    public static void main(String[] args) throws Exception {
        new Main().Solve();
    }
}
