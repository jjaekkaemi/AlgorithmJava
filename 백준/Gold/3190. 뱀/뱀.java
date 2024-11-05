import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K, L;
    static int[][] apples;
    static Queue<int[]> loads;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][N + 1];

        // 사과 위치 입력
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int appleRow = Integer.parseInt(st.nextToken());
            int appleCol = Integer.parseInt(st.nextToken());
            arr[appleRow][appleCol] = 1; // 사과 위치
        }

        // 방향 전환 정보 입력
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        loads = new LinkedList<>();
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char direction = st.nextToken().charAt(0);
            loads.add(new int[]{time, direction == 'D' ? 1 : -1}); // 오른쪽이면 1, 왼쪽이면 -1
        }

        int answer = 0;
        int[] head = {1, 1}; // 초기 뱀 위치
        int dir = 0; // 초기 방향 오른쪽
        int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 오른쪽, 아래, 왼쪽, 위
        LinkedList<int[]> snake = new LinkedList<>(); // 뱀의 몸통 위치 저장
        snake.add(new int[]{1, 1});
        arr[1][1] = 2; // 뱀의 시작 위치 표시

        while (true) {
            answer++;

            // 머리 이동
            head[0] += move[dir][0];
            head[1] += move[dir][1];

            // 게임 종료 조건: 벽에 부딪히거나 자기 몸에 부딪힐 경우
            if (head[0] < 1 || head[1] < 1 || head[0] > N || head[1] > N || arr[head[0]][head[1]] == 2) {
                break;
            }

            // 이동한 위치에 사과가 있는 경우
            if (arr[head[0]][head[1]] == 1) {
                arr[head[0]][head[1]] = 2; // 머리 위치로 업데이트
                snake.add(new int[]{head[0], head[1]});
            } else { // 사과가 없는 경우
                arr[head[0]][head[1]] = 2; // 머리 위치로 업데이트
                snake.add(new int[]{head[0], head[1]});
                int[] tail = snake.poll(); // 꼬리 제거
                arr[tail[0]][tail[1]] = 0; // 꼬리 위치 비우기
            }

            // 방향 전환 처리
            if (!loads.isEmpty() && loads.peek()[0] == answer) {
                dir = (dir + loads.poll()[1] + 4) % 4; // 방향 업데이트
            }
        }

        System.out.println(answer);
    }
}
