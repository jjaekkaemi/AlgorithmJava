import java.util.Scanner;

public class Main {
    static int N; // 계란의 수
    static int[][] eggs; // 계란의 내구도와 무게를 저장할 배열
    static int maxBrokenEggs = 0; // 최대 깨진 계란 수를 저장할 변수

    // 백트래킹 함수
    public static void smashEggs(int index) {
        // 모든 계란을 확인한 경우
        if (index == N) {
            int brokenCount = countBrokenEggs();
            maxBrokenEggs = Math.max(maxBrokenEggs, brokenCount);
            return;
        }

        // 현재 손에 든 계란이 이미 깨져 있으면 다음 계란으로 넘어감
        if (eggs[index][0] <= 0) {
            smashEggs(index + 1);
            return;
        }

        boolean smashed = false; // 한 번이라도 다른 계란을 친 적이 있는지 체크

        // 다른 계란들과 충돌
        for (int i = 0; i < N; i++) {
            if (i != index && eggs[i][0] > 0) { // 자기 자신이 아니고, 깨지지 않은 계란일 때
                smashed = true;

                // 계란끼리 충돌
                eggs[index][0] -= eggs[i][1]; // 손에 든 계란의 내구도는 상대 계란의 무게만큼 깎임
                eggs[i][0] -= eggs[index][1]; // 상대 계란의 내구도는 손에 든 계란의 무게만큼 깎임

                // 다음 계란으로 넘어감
                smashEggs(index + 1);

                // 충돌 결과를 되돌림 (백트래킹)
                eggs[index][0] += eggs[i][1];
                eggs[i][0] += eggs[index][1];
            }
        }

        // 만약 손에 든 계란으로 다른 계란을 칠 수 없다면 다음 계란으로 넘어감
        if (!smashed) {
            smashEggs(index + 1);
        }
    }

    // 깨진 계란의 수를 세는 함수
    public static int countBrokenEggs() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (eggs[i][0] <= 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // 계란의 수 입력
        eggs = new int[N][2]; // 계란의 내구도와 무게를 저장할 배열

        // 계란 정보 입력
        for (int i = 0; i < N; i++) {
            eggs[i][0] = sc.nextInt(); // 내구도
            eggs[i][1] = sc.nextInt(); // 무게
        }

        // 백트래킹을 통해 문제 해결
        smashEggs(0);

        // 최대 깨진 계란 수 출력
        System.out.println(maxBrokenEggs);
    }
}
