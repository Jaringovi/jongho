package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem2630 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer token;
    static int n, white = 0, blue = 0;
    static int[][] paper;

    public static void main(String[] args) throws Exception {
        Input();
        slicePaper(n, 0, 0);
        Output();
    }

    static void Input() throws IOException {
        n = Integer.parseInt(input.readLine());
        paper = new int[n][n];

        for (int i = 0; i < n; i++) {
            String s[] = input.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                paper[i][j] = Integer.parseInt(s[j]);
            }
        }
    }

    static void slicePaper(int range, int x, int y) {
        int tempColor = paper[x][y];
        boolean flag = true;

        for (int i = x; i < x + range; i++) {
            for (int j = y; j < y + range; j++) {
                if (tempColor != paper[i][j]) {
                    flag = false;
                    break;
                }
            }
            if (!flag)
                break;
        }

        if (flag) {
            if (tempColor == 0)
                white++;
            else
                blue++;
            return;
        } else {
            slicePaper(range / 2, x, y);
            slicePaper(range / 2, x + (range / 2), y);
            slicePaper(range / 2, x, y + (range / 2));
            slicePaper(range / 2, x + (range / 2), y + (range / 2));
        }
    }

    static void Output() {
        System.out.printf("%d\n%d", white, blue);
    }
}
