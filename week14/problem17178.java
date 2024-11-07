// 실패!!
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer tokens;
    List<String> queue = new ArrayList<>();
    Stack<String> stack = new Stack<>();

    void init() throws IOException {
        int n = Integer.parseInt(input.readLine());
        for (int i = 0; i < n; i++) {
            tokens = new StringTokenizer(input.readLine());
            while (tokens.hasMoreTokens()) {
                queue.add(tokens.nextToken());
            }
        }
        // 티켓을 알파벳과 숫자 기준으로 정렬
        queue.sort((a, b) -> {
            String[] aSplit = a.split("-");
            String[] bSplit = b.split("-");
            int alphaCompare = aSplit[0].compareTo(bSplit[0]);
            if (alphaCompare != 0) return alphaCompare;
            return Integer.parseInt(aSplit[1]) - Integer.parseInt(bSplit[1]);
        });
    }

    void Solve() throws IOException {
        init();
        Queue<String> line = new LinkedList<>(queue);
        int idx = 0;

        while (!line.isEmpty()) {
            String ticket = line.poll();
            // 스택에 있는 사람이 순서대로 입장할 수 있는지 체크
            while (!stack.isEmpty() && stack.peek().equals(queue.get(idx))) {
                stack.pop();
                idx++;
            }
            // 스택에 넣지 않고 바로 입장할 수 있는지 체크
            if (ticket.equals(queue.get(idx))) {
                idx++;
            } else {
                stack.push(ticket);
            }
        }

        // 스택에 남은 사람들도 입장 가능한지 체크
        while (!stack.isEmpty()) {
            if (!stack.pop().equals(queue.get(idx))) {
                System.out.println("BAD");
                return;
            }
            idx++;
        }
        System.out.println("GOOD");
    }

    public static void main(String[] args) throws Exception {
        new Main().Solve();
    }
}
