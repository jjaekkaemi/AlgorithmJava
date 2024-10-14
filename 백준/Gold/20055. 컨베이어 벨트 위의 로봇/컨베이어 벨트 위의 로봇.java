import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력 처리: 첫 줄에 N과 K가 주어짐
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 벨트 절반 길이
        int K = Integer.parseInt(st.nextToken()); // 내구도가 0인 칸의 개수 조건

        // 벨트 내구도 배열 및 로봇 위치 배열 초기화
        int[] belt = new int[2 * N]; // 2N 길이의 벨트 내구도 저장
        boolean[] robots = new boolean[N]; // 로봇이 있는지 여부를 나타내는 배열

        // 두 번째 줄 입력: 벨트 내구도 값 초기화
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        int step = 0; // 진행 단계 수

        // 시뮬레이션 반복
        while (true) {
            step++; // 단계 수 증가

            // 1. 벨트와 로봇 회전
            rotateBelt(belt, robots, N);

            // 2. 로봇 이동 (먼저 올라간 로봇부터 처리)
            moveRobots(belt, robots, N);

            // 3. 올리는 위치에 로봇 올리기
            if (belt[0] > 0) {
                robots[0] = true;
                belt[0]--;
            }

            // 4. 내구도 0인 칸의 개수가 K개 이상이면 종료
            if (countZeroDurability(belt, K)) {
                break;
            }
        }

        // 결과 출력: 종료된 단계 수
        System.out.println(step);
    }

    // 벨트와 로봇을 한 칸 회전시키는 함수
    private static void rotateBelt(int[] belt, boolean[] robots, int N) {
        // 벨트 회전: 마지막 값을 앞으로 이동
        int lastDurability = belt[2 * N - 1];
        System.arraycopy(belt, 0, belt, 1, 2 * N - 1);
        belt[0] = lastDurability;

        // 로봇 회전: 마지막 로봇을 앞으로 이동 (내리는 위치의 로봇은 제거)
        System.arraycopy(robots, 0, robots, 1, N - 1);
        robots[0] = false;
        robots[N - 1] = false; // 내리는 위치의 로봇 제거
    }

    // 로봇을 이동시키는 함수
    private static void moveRobots(int[] belt, boolean[] robots, int N) {
        // 먼저 올라간 로봇부터 처리 (역순으로 이동)
        for (int i = N - 2; i >= 0; i--) {
            if (robots[i] && !robots[i + 1] && belt[i + 1] > 0) {
                // 로봇 이동
                robots[i] = false;
                robots[i + 1] = true;
                belt[i + 1]--;

                // 내리는 위치에 도달한 경우 로봇 제거
                if (i + 1 == N - 1) {
                    robots[i + 1] = false;
                }
            }
        }
    }

    // 내구도가 0인 칸의 개수를 세는 함수
    private static boolean countZeroDurability(int[] belt, int K) {
        int count = 0;
        for (int durability : belt) {
            if (durability == 0) {
                count++;
            }
        }
        return count >= K;
    }
}
