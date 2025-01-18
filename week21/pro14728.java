// 11% 틀렸습니다 ㅠ

import java.io.*;

public class Main {
    final int SIZE = 5;
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    char[][] arr = new char[SIZE][SIZE];
    boolean[][] visited;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int ans;

    void init() throws IOException {
        for (int i = 0; i < SIZE; i++) {
            String str = bf.readLine();
            for (int j = 0; j < SIZE; j++) {
                arr[i][j] = str.charAt(j);
            }
        }
    }

    void dfs(int scnt, int ycnt, int cx, int cy) {
        if (ycnt >= 4) // 'Y'가 4개 이상이면 종료
            return;
        if (scnt + ycnt == 7) { // 7명의 팀을 완성한 경우
            ans++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];
            if (checkVaild(nx, ny)) {
                visited[nx][ny] = true;
                if (arr[nx][ny] == 'S') // 'S'를 만난 경우
                    dfs(scnt + 1, ycnt, nx, ny);
                else // 'Y'를 만난 경우
                    dfs(scnt, ycnt + 1, nx, ny);
                visited[nx][ny] = false; 
            }
        }
    }

    boolean checkVaild(int x, int y) {
        return (x >= 0 && x < SIZE && y >= 0 && y < SIZE && !visited[x][y]);
    }

    void solve() throws IOException {
        init();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(!visited[i][j])
                    dfs(0, 0, i, j);
            }
        }
        System.out.println(ans);
    }

    public static void main(String args[]) throws IOException {
        new Main().solve();
    }
}
