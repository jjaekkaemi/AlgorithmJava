import java.io.*;
import java.util.*;

public class Main {
    static int N, M, x, y, K;
    static int[][] map;
    static int[] dice = new int[6]; // 주사위의 6면을 관리 (0: 윗면, 1: 북쪽, 2: 동쪽, 3: 서쪽, 4: 남쪽, 5: 바닥)
    static int[] dx = {0, 0, -1, 1}; // 동, 서, 북, 남 (x축 이동)
    static int[] dy = {1, -1, 0, 0}; // 동, 서, 북, 남 (y축 이동)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 입력 처리
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 명령 처리
        while (st.hasMoreTokens()) {
            int command = Integer.parseInt(st.nextToken()) - 1; // 명령을 0부터 시작하도록 인덱스 조정
            int nx = x + dx[command];
            int ny = y + dy[command];

            // 지도 범위를 벗어나는 경우 무시
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

            // 위치 갱신
            x = nx;
            y = ny;

            // 주사위 면을 회전
            rotateDice(command);

            // 주사위와 지도의 값 복사
            if (map[x][y] == 0) {
                map[x][y] = dice[5]; // 주사위 바닥면의 값을 지도에 복사
            } else {
                dice[5] = map[x][y]; // 지도의 값을 주사위 바닥면에 복사
                map[x][y] = 0; // 지도 값은 0으로 변경
            }

            // 주사위 윗면의 값을 출력
            sb.append(dice[0]).append("\n");
        }

        System.out.print(sb);
    }

    // 주사위를 회전시키는 메서드
    static void rotateDice(int direction) {
        int[] newDice = dice.clone();
        switch (direction) {
            case 0: // 동쪽
                dice[0] = newDice[3];
                dice[2] = newDice[0];
                dice[5] = newDice[2];
                dice[3] = newDice[5];
                break;
            case 1: // 서쪽
                dice[0] = newDice[2];
                dice[2] = newDice[5];
                dice[5] = newDice[3];
                dice[3] = newDice[0];
                break;
            case 2: // 북쪽
                dice[0] = newDice[4];
                dice[1] = newDice[0];
                dice[5] = newDice[1];
                dice[4] = newDice[5];
                break;
            case 3: // 남쪽
                dice[0] = newDice[1];
                dice[1] = newDice[5];
                dice[5] = newDice[4];
                dice[4] = newDice[0];
                break;
        }
    }
}
