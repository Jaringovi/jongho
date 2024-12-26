import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    static int N; // 최대 고유 알파벳 개수
    static String inputString; // 입력 문자열

    static void Input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        inputString = br.readLine();
    }

    static int Solve() {
        int start = 0, maxLength = 0;
        HashMap<Character, Integer> charCount = new HashMap<>();

        for (int end = 0; end < inputString.length(); end++) {
            // 현재 문자 추가
            char currentChar = inputString.charAt(end);
            charCount.put(currentChar, charCount.getOrDefault(currentChar, 0) + 1);

            // 고유 알파벳 개수가 N을 초과하면 start 포인터 이동
            while (charCount.size() > N) {
                char startChar = inputString.charAt(start);
                charCount.put(startChar, charCount.get(startChar) - 1);

                // 해당 알파벳의 빈도가 0이 되면 제거
                if (charCount.get(startChar) == 0) {
                    charCount.remove(startChar);
                }

                // 윈도우 시작점 이동
                start++;
            }

            // 현재 윈도우 길이 갱신
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) throws IOException {
        Input();
        System.out.println(Solve());
    }
}