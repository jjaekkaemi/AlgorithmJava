import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    
    static class Pipe {
        int x, y, direction; // 머리 좌표 (x, y) 및 방향 (0: 가로, 1: 대각선, 2: 세로)

        public Pipe(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n+1][n+1];
        
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Pipe> queue = new LinkedList<>();
        queue.offer(new Pipe(1, 2, 0)); // 파이프는 처음에 (1,1)에서 (1,2)까지 가로 방향으로 놓여있음
        int answer = 0;

        while (!queue.isEmpty()) {
            Pipe pipe = queue.poll();
            int x = pipe.x;
            int y = pipe.y;
            int direction = pipe.direction;

            if (x == n && y == n) { // 도착지점에 도달
                answer++;
                continue;
            }

            // 가로 방향 (0)
            if (direction == 0 || direction == 1) {
                if (y + 1 <= n && arr[x][y + 1] == 0) { // 가로로 이동
                    queue.offer(new Pipe(x, y + 1, 0));
                }
            }

            // 세로 방향 (2)
            if (direction == 2 || direction == 1) {
                if (x + 1 <= n && arr[x + 1][y] == 0) { // 세로로 이동
                    queue.offer(new Pipe(x + 1, y, 2));
                }
            }

            // 대각선 방향 (1)
            if (x + 1 <= n && y + 1 <= n && arr[x + 1][y] == 0 && arr[x][y + 1] == 0 && arr[x + 1][y + 1] == 0) {
                queue.offer(new Pipe(x + 1, y + 1, 1)); // 대각선으로 이동
            }
        }

        System.out.println(answer);
    }
}
