import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer tokens;

    final int INF = 100; // 친구 관계 최대 거리보다 큰 값으로 설정
    int[][] dist; // 인접 행렬
    int N; // 회원 수

    void init() throws IOException {
        // 회원 수 입력
        N = Integer.parseInt(input.readLine());
        dist = new int[N + 1][N + 1];

        // 인접 행렬 초기화
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    dist[i][j] = 0; // 자기 자신과의 거리
                } else {
                    dist[i][j] = INF; // 초기값 설정
                }
            }
        }

        // 친구 관계 입력
        while (true) {
            tokens = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            if (a == -1 && b == -1) break;

            // 친구 관계는 양방향
            dist[a][b] = 1;
            dist[b][a] = 1;
        }
    }

    void calculateScores() {
        // 플로이드-워셜 알고리즘으로 최단 거리 계산
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }

    void Output() {
        int[] scores = new int[N + 1]; // 각 회원의 점수
        int minScore = INF; // 최소 점수
        ArrayList<Integer> candidates = new ArrayList<>(); // 회장 후보 목록

        // 각 회원의 점수 계산
        for (int i = 1; i <= N; i++) {
            int maxDist = 0; // 해당 회원에서 가장 먼 거리
            for (int j = 1; j <= N; j++) {
                if (dist[i][j] != INF) {
                    maxDist = Math.max(maxDist, dist[i][j]);
                }
            }
            scores[i] = maxDist;
            minScore = Math.min(minScore, maxDist);
        }

        // 최소 점수를 가진 후보 찾기
        for (int i = 1; i <= N; i++) {
            if (scores[i] == minScore) {
                candidates.add(i);
            }
        }

        // 출력
        System.out.println(minScore + " " + candidates.size());
        Collections.sort(candidates); // 후보 번호를 오름차순 정렬
        for (int candidate : candidates) {
            System.out.print(candidate + " ");
        }
    }

    void Solve() throws IOException {
        init(); // 입력 처리 및 초기화
        calculateScores(); // 최단 거리 계산
        Output(); // 결과 출력
    }

    public static void main(String[] args) throws Exception {
        new Main().Solve();
    }
}
