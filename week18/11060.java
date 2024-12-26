import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N; // 미로의 크기
    static int[] A; // 각 칸의 점프 가능한 최대 거리
    static int[] jumps; // 최소 점프 횟수를 저장하는 배열

    // 입력 처리
    static void Input() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(input.readLine());
        A = new int[N];
        jumps = new int[N];
        
        StringTokenizer tokens = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(tokens.nextToken());
            jumps[i] = -1; // 초기화: 방문하지 않은 상태
        }
    }

    // BFS로 최소 점프 횟수 계산
    static int BFS() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0); // 시작점 추가
        jumps[0] = 0; // 시작점까지 점프 횟수는 0
        if(N == 1) return 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // 현재 위치에서 점프 가능한 범위를 탐색
            for (int next = current + 1; next <= Math.min(current + A[current], N - 1); next++) {
                if (jumps[next] == -1) { // 아직 방문하지 않은 칸
                    jumps[next] = jumps[current] + 1; // 점프 횟수 갱신
                    queue.add(next);

                    // 오른쪽 끝에 도달하면 종료
                    if (next == N - 1) {
                        return jumps[next];
                    }
                }
            }
        }

        return -1; // 끝까지 도달할 수 없는 경우
    }

    public static void main(String[] args) throws IOException {
        Input();
        System.out.println(BFS());
    }
}
