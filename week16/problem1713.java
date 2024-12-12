// 14612kb	124ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer tokens;
    int n;
    int total;
    Student[] arr;

    class Student {
        int num;
        int cnt;
        int time; // 학생이 사진틀에 게시된 시간

        Student(int num, int time) {
            this.num = num;
            this.cnt = 1;
            this.time = time;
        }
    }

    void init() throws IOException {
        n = Integer.parseInt(input.readLine());
        total = Integer.parseInt(input.readLine());
        arr = new Student[n];
    }

    boolean check(int num) {
        for (int i = 0; i < n; i++) {
            if (arr[i] != null && arr[i].num == num) {
                arr[i].cnt++;
                return true;
            }
        }
        return false;
    }

    void changeStudent(int newStu, int time) {
        int minCnt = Integer.MAX_VALUE;
        int oldestTime = Integer.MAX_VALUE;
        int idx = -1;

        // 가장 적은 추천 횟수와 가장 오래된 사진을 찾는다
        for (int i = 0; i < n; i++) {
            if (arr[i] == null) { // 빈 슬롯이 있으면 즉시 사용
                idx = i;
                break;
            }
            if (arr[i].cnt < minCnt || (arr[i].cnt == minCnt && arr[i].time < oldestTime)) {
                minCnt = arr[i].cnt;
                oldestTime = arr[i].time;
                idx = i;
            }
        }

        // 새로운 학생으로 교체
        arr[idx] = new Student(newStu, time);
    }

    void Solve() throws IOException {
        init();
        tokens = new StringTokenizer(input.readLine());
        for (int i = 0; i < total; i++) {
            int tmp = Integer.parseInt(tokens.nextToken());
            if (!check(tmp)) { // 이미 있는 학생이 아니라면
                changeStudent(tmp, i);
            }
        }

        // null 값 제거 후 학생 번호를 기준으로 정렬
        Arrays.stream(arr)
                .filter(student -> student != null) // null 제거
                .sorted(Comparator.comparingInt(student -> student.num)) // 번호 기준 정렬
                .forEach(student -> System.out.print(student.num + " "));
    }

    public static void main(String[] args) throws Exception {
        new Main().Solve();
    }
}