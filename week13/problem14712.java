//메모리:15776	시간:444
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer tokens;
    int n, m;
    boolean[][] board;
    int count = 0;

    void Input() throws IOException {
        tokens = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());
        board = new boolean[n][m];
    }

    boolean isValid(int x, int y) {
        // 현재 위치가 2x2 사각형을 이루는지 확인
        if (x >= 1 && y >= 1) {
            if (board[x][y] && board[x-1][y] && board[x][y-1] && board[x-1][y-1]) {
                return false;
            }
        }
        return true;
    }

    void backtrack(int x, int y) {
        if (x == n) {  // 마지막 행을 넘어가면 유효한 배치를 찾은 것
            count++;
            return;
        }

        int nextX = (y + 1 == m) ? x + 1 : x;
        int nextY = (y + 1 == m) ? 0 : y + 1;

        // 넴모를 두지 않는 경우
        backtrack(nextX, nextY);

        // 넴모를 두는 경우
        board[x][y] = true;
        if (isValid(x, y)) {
            backtrack(nextX, nextY);
        }
        board[x][y] = false;  // 원상복구
    }

    void Solve() throws IOException {
        Input();
        backtrack(0, 0);
        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        new Main().Solve();
    }
}
