import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, D, X, Y; // N, M : 방크기, D : 방향, X, Y : 위치
    static int[][] arr; // 장소 상태
    static int answer;

    // 방향: 0 - 북, 1 - 동, 2 - 남, 3 - 서
    static int[] dx = {-1, 0, 1, 0}; // 행 이동
    static int[] dy = {0, 1, 0, -1}; // 열 이동

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        // 로봇 청소기 초기 위치와 방향
        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        // 방의 상태 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 청소 시작
        clean();

        // 청소한 칸의 개수 출력
        System.out.println(answer);
    }

    static void clean() {
        while (true) {
            // 1. 현재 위치 청소
            if (arr[X][Y] == 0) {
                arr[X][Y] = 2; // 청소 완료 표시
                answer++;
            }

            boolean moved = false;

            // 2. 주변 4칸 중 청소되지 않은 칸이 있는지 확인
            for (int i = 0; i < 4; i++) {
                D = (D + 3) % 4; // 왼쪽으로 회전
                int nx = X + dx[D];
                int ny = Y + dy[D];

                // 청소되지 않은 빈 칸이 있는 경우 전진
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && arr[nx][ny] == 0) {
                    X = nx;
                    Y = ny;
                    moved = true;
                    break;
                }
            }

            // 청소되지 않은 빈 칸이 없는 경우
            if (!moved) {
                // 후진
                int back = (D + 2) % 4;
                int bx = X + dx[back];
                int by = Y + dy[back];

                // 뒤가 벽이라 후진할 수 없으면 종료
                if (bx < 0 || bx >= N || by < 0 || by >= M || arr[bx][by] == 1) {
                    break;
                }

                // 후진 가능하면 위치만 이동
                X = bx;
                Y = by;
            }
        }
    }
}
