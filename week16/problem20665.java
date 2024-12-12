// 예제 컷 ㅇㅅㅇ
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder output = new StringBuilder();
    StringTokenizer tokens;

    // 예약 정보를 담는 클래스
    class Reservation {
        int start, end;

        Reservation(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().Solve();
    }

    void Solve() throws IOException {
        // Parse input
        tokens = new StringTokenizer(input.readLine());
        int N = Integer.parseInt(tokens.nextToken()); // 총 좌석 수
        int T = Integer.parseInt(tokens.nextToken()); // 예약 수
        int P = Integer.parseInt(tokens.nextToken());

        // 예약 정보 저장할 리스트
        List<Reservation> reservations = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            tokens = new StringTokenizer(input.readLine());
            int start = parseTime(tokens.nextToken());
            int end = parseTime(tokens.nextToken());
            reservations.add(new Reservation(start, end));
        }

        // 시작 시간 순으로 정렬
        Collections.sort(reservations, (a, b) -> {
            if (a.start == b.start) {
                return (a.end - a.start) - (b.end - b.start);
            }
            return a.start - b.start;
        });

        // 분단위로 좌석 사용 시간 저장
        int[] seatUsed = new int[N + 1];

        // 좌석 할당
        for (Reservation r : reservations) {
            // 좌석 선택 로직
            int selectedSeat = findSeat(N, seatUsed, r);

            // 선택된 좌석에 예약 시간 기록
            for (int t = r.start; t < r.end; t++) {
                seatUsed[selectedSeat] = Math.max(seatUsed[selectedSeat], t);
            }
        }

        // 민규 좌석 사용 가능 시간 계산
        int mingyuTime = 0;
        for (int t = 540; t < 1260; t++) {
            if (seatUsed[P] < t) {
                mingyuTime++;
            }
        }

        // 결과 출력
        System.out.println(mingyuTime);
    }

    // 좌석 선택 로직
    int findSeat(int N, int[] seatUsed, Reservation r) {
        // 첫 사용이면 1번 좌석 선호
        boolean check = true;
        for (int x : seatUsed) {
            if (x != 0) {
                check = false;
            }
        }
        if (check)
            return 1;

        // 좌석 간 최대 거리 계산
        List<Integer> seatList = new ArrayList<>();
        int maxDis = 0;

        for (int seat = 1; seat <= N; seat++) {
            int minDis = N + 1;

            for (int j = 1; j <= N; j++) {
                // 해당 시간에 사용중인 좌석과의 거리 계산
                if (seatUsed[j] >= r.start) {
                    minDis = Math.min(minDis, Math.abs(seat - j));
                }
            }

            // 최대 거리 갱신
            if (minDis > maxDis) {
                maxDis = minDis;
                seatList.clear();
                seatList.add(seat);
            } else if (minDis == maxDis) {
                seatList.add(seat);
            }
        }

        // 가장 작은 좌석 번호 선택
        int minSeat = Integer.MAX_VALUE;
        for (int seat : seatList) {
            minSeat = Math.min(minSeat, seat);
        }
        return minSeat == Integer.MAX_VALUE ? 1 : minSeat;
    }

    // 시간을 분으로 변환
    int parseTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(2));
        return hour * 60 + minute;
    }

}