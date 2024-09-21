package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.LinkedList;

public class problem13649 {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder output = new StringBuilder();
    StringTokenizer tokens;

    final int MAX = 100000;
    int N, K, ans = Integer.MAX_VALUE;
    int dx[] = { -1, 1 };
    boolean visited[] = new boolean[MAX + 1];

    public static void main(String[] args) throws IOException {
        new problem13649().Solve();
    }

    void init() throws IOException {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());
    }

    void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { N, 0 });

        while (!q.isEmpty()) {
            int tmp[] = q.poll();
            int crnt = tmp[0];
            int time = tmp[1];
            // System.out.println(crnt + " " + time);

            if (crnt == K) {
                ans = Math.min(ans, time);
                break;
            }

            if (crnt * 2 <= MAX && crnt >= 0 && !visited[crnt * 2]) {
                q.offer(new int[] { crnt * 2, time });
                visited[crnt * 2] = true;
            }

            for (int i = 0; i < 2; i++) {
                int nx = crnt + dx[i];
                if (nx <= MAX && nx >= 0 && !visited[nx]) {
                    q.offer(new int[] { nx, time + 1 });
                    visited[nx] = true;
                }
            }

        }
    }

    void Solve() throws IOException {
        init();
        bfs();
        System.out.println(ans);
    }
}