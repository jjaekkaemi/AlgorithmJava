import java.util.Scanner;

public class Main {
    static int N;
    static char[] current, target;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        current = sc.next().toCharArray();
        target = sc.next().toCharArray();

        // 첫 번째 스위치를 누르지 않는 경우
        int resultA = getSwitchCount(false);
        // 첫 번째 스위치를 누르는 경우
        int resultB = getSwitchCount(true);

        // 두 경우 중 최소 조작 횟수를 선택하여 출력
        if (resultA == -1 && resultB == -1) {
            System.out.println(-1);
        } else if (resultA == -1) {
            System.out.println(resultB);
        } else if (resultB == -1) {
            System.out.println(resultA);
        } else {
            System.out.println(Math.min(resultA, resultB));
        }
    }

    // 스위치 조작 횟수를 계산하는 메서드
    static int getSwitchCount(boolean pressFirst) {
        char[] bulbs = current.clone();
        int count = 0;

        // 첫 번째 스위치를 누르는 경우 처리
        if (pressFirst) {
            pressSwitch(bulbs, 0);
            count++;
        }

        // 두 번째 전구부터 N-1번째 전구까지 목표 상태로 맞추기
        for (int i = 1; i < N; i++) {
            if (bulbs[i - 1] != target[i - 1]) {
                pressSwitch(bulbs, i);
                count++;
            }
        }

        // 마지막 전구 상태 확인
        if (bulbs[N - 1] != target[N - 1]) {
            return -1; // 목표 상태로 만들 수 없는 경우
        }
        return count;
    }

    // i번째 스위치를 눌러 전구 상태를 변경하는 메서드
    static void pressSwitch(char[] bulbs, int i) {
        bulbs[i] = (bulbs[i] == '0') ? '1' : '0'; // i번째 전구 상태 변경
        if (i > 0) {
            bulbs[i - 1] = (bulbs[i - 1] == '0') ? '1' : '0'; // i-1번째 전구 상태 변경
        }
        if (i < N - 1) {
            bulbs[i + 1] = (bulbs[i + 1] == '0') ? '1' : '0'; // i+1번째 전구 상태 변경
        }
    }
}
