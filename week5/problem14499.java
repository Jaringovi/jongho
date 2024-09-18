package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem14499 {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer tokens;
    StringBuilder output = new StringBuilder();

    int n, m, x, y, k;
    int map[][];
    int commands[];

    int dice[] = { 0, 0, 0, 0, 0 }; // 뒷면, 윗면, 앞면, 오른쪽면, 왼쪽면
    int bottom = 0; // 밑면 별도 지정
    int dir[][] = { {}, { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } }; // 동 서 북 남 방향

    void Input() throws IOException {
        tokens = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());
        x = Integer.parseInt(tokens.nextToken());
        y = Integer.parseInt(tokens.nextToken());
        k = Integer.parseInt(tokens.nextToken());

        map = new int[n][m];
        commands = new int[k];

        for (int i = 0; i < n; i++) {
            tokens = new StringTokenizer(input.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        tokens = new StringTokenizer(input.readLine());
        for (int i = 0; i < k; i++) {
            commands[i] = Integer.parseInt(tokens.nextToken());
        }
    }

    void Output() {
        System.out.println(output);
    }

    boolean check(int x, int y) {
        if (x >= 0 && y >= 0 && x < n && y < m)
            return true;
        else
            return false;
    }

    void rollDice(int crntDir) {
        int b = dice[0];
        int t = dice[1];
        int f = dice[2];
        int r = dice[3];
        int l = dice[4];

        // 남쪽 방향 이동
        if (crntDir == 4) {
            // 밑면 -> 뒷면
            dice[0] = bottom;
            // 뒷면 -> 윗면
            dice[1] = b;
            // 윗면 -> 앞면
            dice[2] = t;
            // 앞면 -> 밑면
            bottom = f;
        }

        // 북쪽 방향 이동
        else if (crntDir == 3) {
            dice[0] = t;
            dice[1] = f;
            dice[2] = bottom;
            bottom = b;
        }

        // 서쪽 방향 이동
        else if (crntDir == 2) {
            dice[4] = t;
            dice[1] = r;
            dice[3] = bottom;
            bottom = l;
        }

        // 동쪽 방향 이동
        else if (crntDir == 1) {
            dice[3] = t;
            dice[1] = l;
            dice[4] = bottom;
            bottom = r;
        }
    }

    void update(int x, int y) {
        // 현 위치에 숫자가 0이 아니다 -> 칸의 숫자가 주사위 바닥면으로, 칸 숫자는 0으로.
        // 현 위치에 숫자가 0이다 -> 주사위 바닥면 숫자가 칸에 복사
        if (map[x][y] != 0) {
            bottom = map[x][y];
            map[x][y] = 0;
        } else
            map[x][y] = bottom;
    }

    void Solve() throws IOException {
        Input();
        for (int i = 0; i < k; i++) {
            int crntDir = commands[i];
            int nx = dir[crntDir][0] + x;
            int ny = dir[crntDir][1] + y;

            if (check(nx, ny)) {
                x = nx;
                y = ny;
                rollDice(crntDir);
                update(nx, ny);
                output.append(dice[1]).append("\n");
            }
        }
        Output();
    }

    public static void main(String[] args) throws Exception {
        new problem14499().Solve();
    }

}
