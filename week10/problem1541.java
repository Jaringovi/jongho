// 문제 해결 아이디어: -가 등장한 시점부터 전부 빼기 (무지무지 탐욕스럽게)
// 메모리: 14228kb   시간: 96ms
package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1541 {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder output = new StringBuilder();
    StringTokenizer tokens;

    String exp = "";

    public static void main(String[] args) throws IOException {
        new problem1541().Solve();
    }

    void init() throws IOException {
        exp = input.readLine();
    }

    void Solve() throws IOException {
        init();
        int flag = 0;
        int num = 0;
        String tmp = "";

        for (int i = 0; i <= exp.length(); i++) {
            if (i < exp.length() && exp.charAt(i) != '-' && exp.charAt(i) != '+') {
                tmp += exp.charAt(i);
            } else {
                if (flag == 1)
                    num -= Integer.parseInt(tmp);
                else
                    num += Integer.parseInt(tmp);
                tmp = "";
            }
            if (i < exp.length() && exp.charAt(i) == '-')
                flag = 1;
        }

        System.out.println(num);
    }
}