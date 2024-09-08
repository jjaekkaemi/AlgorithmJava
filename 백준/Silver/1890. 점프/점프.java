import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] board = new int[N][N];
        long[][] dp = new long[N][N];

        // 게임판 입력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        // 시작점
        dp[0][0] = 1;

        // DP로 경로 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 현재 칸이 종착점이면 더 이상 갈 수 없음
                if (i == N - 1 && j == N - 1) break;
                int jump = board[i][j]; // 현재 칸에서 점프할 수 있는 거리
                if (jump == 0) continue;

                // 오른쪽으로 점프
                if (j + jump < N) {
                    dp[i][j + jump] += dp[i][j];
                }

                // 아래로 점프
                if (i + jump < N) {
                    dp[i + jump][j] += dp[i][j];
                }
            }
        }

        // 가장 오른쪽 아래 칸에 도달하는 경로의 수 출력
        System.out.println(dp[N - 1][N - 1]);
    }
}
