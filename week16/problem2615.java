// 8% 실패
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    int[][] board = new int[19][19];
    int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 }; // 8방향 x 좌표 변화
    int[] dy = { 1, 1, 0, -1, -1, -1, 0, 1 }; // 8방향 y 좌표 변화
    int winnerColor = 0;
    int winX = -1, winY = -1;

    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder output = new StringBuilder();
    StringTokenizer tokens;

    public static void main(String[] args) throws IOException {
        new Main().Solve();
    }

    void init() throws IOException {
        // 바둑판 상태 입력받기
        for (int i = 0; i < 19; i++) {
            tokens = new StringTokenizer(input.readLine());
            for (int j = 0; j < 19; j++) {
                board[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
    }

    boolean checkWinner(int x, int y, int color) {
        for (int dir = 0; dir < 8; dir++) {
            int count = 1; // 현재 위치 포함
            int startX = x, startY = y;
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 같은 방향으로 연속된 바둑알 카운트
            while (nx >= 0 && nx < 19 && ny >= 0 && ny < 19 && board[nx][ny] == color) {
                count++;
                nx += dx[dir];
                ny += dy[dir];
            }

            // 반대 방향 카운트
            nx = x - dx[dir];
            ny = y - dy[dir];
            while (nx >= 0 && nx < 19 && ny >= 0 && ny < 19 && board[nx][ny] == color) {
                count++;
                startX = nx;
                startY = ny;
                nx -= dx[dir];
                ny -= dy[dir];
            }

            // 정확히 5개 연속인 경우 승리
            if (count == 5) {
                winX = startX + 1;
                winY = startY + 1;
                return true;
            }
        }
        return false;
    }

    void Solve() throws IOException {
        init();

        // 모든 바둑알에 대해 승리 조건 체크
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (board[i][j] != 0) {
                    if (checkWinner(i, j, board[i][j])) {
                        winnerColor = board[i][j];
                        break;
                    }
                }
            }
            if (winnerColor != 0)
                break;
        }

        // 결과 출력
        output.append(winnerColor).append("\n");
        if (winnerColor != 0) {
            output.append(winX).append(" ").append(winY);
        }

        System.out.println(output);
    }
}