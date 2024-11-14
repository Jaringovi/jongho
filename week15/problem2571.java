import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer tokens;
    int n;
    boolean[][] paper = new boolean[100][100]; // 도화지 초기화

    public static void main(String[] args) throws IOException {
        new Main().solve();
    }

    // 입력을 초기화하고 색종이를 도화지에 표시하는 메서드
    void init() throws IOException {
        n = Integer.parseInt(bf.readLine());
        for (int i = 0; i < n; i++) {
            tokens = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(tokens.nextToken());
            int y = Integer.parseInt(tokens.nextToken());

            // 10x10 크기의 색종이를 도화지에 표시
            for (int j = x; j < x + 10; j++) {
                for (int k = y; k < y + 10; k++) {
                    paper[j][k] = true;
                }
            }
        }
    }

    // 최대 검은색 직사각형 넓이를 구하는 메서드
    int getMaxBlackArea() {
        int maxArea = 0;

        // 행과 열 각각에 대해 가장 큰 검은색 직사각형 넓이를 구함
        for (int i = 0; i < 100; i++) {
            int[] height = new int[100];
            for (int j = i; j < 100; j++) {
                for (int k = 0; k < 100; k++) {
                    if (paper[j][k])
                        height[k]++;
                    else
                        height[k] = 0;
                }
                maxArea = Math.max(maxArea, getMaxRectangle(height));
            }
        }

        return maxArea;
    }

    // 주어진 높이 배열에서 최대 직사각형 넓이를 구하는 메서드
    int getMaxRectangle(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            int minHeight = height[i];
            for (int j = i; j < height.length; j++) {
                minHeight = Math.min(minHeight, height[j]);
                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }
        return maxArea;
    }

    void solve() throws IOException {
        init();
        System.out.println(getMaxBlackArea());
    }
}
