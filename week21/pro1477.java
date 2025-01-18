import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 단원 수
        int T = Integer.parseInt(st.nextToken()); // 공부할 수 있는 시간
        
        int[] K = new int[N + 1]; // 각 단원의 공부 시간
        int[] S = new int[N + 1]; // 각 단원의 점수
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            K[i] = Integer.parseInt(st.nextToken());
            S[i] = Integer.parseInt(st.nextToken());
        }
        
        int[][] dp = new int[N + 1][T + 1]; // DP 배열
        
        for (int i = 1; i <= N; i++) { // 단원별로
            for (int t = 0; t <= T; t++) { // 시간별로
                if (t < K[i]) {
                    // 현재 단원을 공부할 시간이 부족하면 이전 값 유지
                    dp[i][t] = dp[i - 1][t];
                } else {
                    // 공부하지 않을 경우와 공부할 경우 중 최대값 선택
                    dp[i][t] = Math.max(dp[i - 1][t], dp[i - 1][t - K[i]] + S[i]);
                }
            }
        }
        
        // 최대 점수 출력
        System.out.println(dp[N][T]);
    }
}
