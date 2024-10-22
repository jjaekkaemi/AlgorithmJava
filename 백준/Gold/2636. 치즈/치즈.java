import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] cheese;
    static final int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int step = 0;
        int cheeseCount = 0;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cheese = new int[N][M];

        // 치즈 배열 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 치즈가 남아있는 동안 반복
        while (true) {
            int count = countCheese();
            if (count == 0) break; // 더 이상 치즈가 남아있지 않으면 종료
            cheeseCount = count; // 마지막으로 남은 치즈 개수 저장
            step++; // 시간 증가
        }

        // 결과 출력
        System.out.println(step); // 치즈가 다 녹는 데 걸린 시간
        System.out.println(cheeseCount); // 마지막으로 남은 치즈의 개수
    }

    // 치즈를 녹이고 남은 치즈 개수를 반환하는 함수
    public static int countCheese() {
        int count = 0;
        visited = new boolean[N][M]; // 매번 visited 배열을 새로 초기화
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true; // 외부 공기 시작점 방문 처리

        // BFS를 통해 치즈 녹이기
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            for (int[] m : move) {
                int mx = point[0] + m[0];
                int my = point[1] + m[1];
                if (mx >= 0 && mx < N && my >= 0 && my < M && !visited[mx][my]) {
                    visited[mx][my] = true;
                    if (cheese[mx][my] == 1) {
                        cheese[mx][my] = 0; // 치즈를 녹임
                        count++; // 녹인 치즈 개수 증가
                    } else {
                        queue.add(new int[]{mx, my}); // 공기인 경우 계속 탐색
                    }
                }
            }
        }
        return count;
    }
}
