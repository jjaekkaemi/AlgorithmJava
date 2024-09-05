import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int n;

    static class Pipe {
        int x, y ;
        int direcion ; // 0 : 가로, 1: 대각선, 2: 세로

        public Pipe(int x, int y, int direcion) { // 시작 좌표, 뒤 좌표, 방향
            this.x = x;
            this.y = y;

            this.direcion = direcion;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n+1][n+1];
        for(int i = 1 ; i<n+1 ;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j<n+1 ; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());

            }
        }
        Queue<Pipe> queue = new LinkedList<>();
        int answer = 0 ;
        queue.offer(new Pipe(1,2, 0));
        while(!queue.isEmpty()){
            Pipe pipe = queue.poll();
            int x = pipe.x;
            int y = pipe.y ;
            if(x==n && y==n){
                answer++;
                continue;
            }
            if(pipe.direcion==0 || pipe.direcion==1){
                if(y+1<=n && arr[x][y+1]==0) {
                    queue.offer(new Pipe(x, y+1, 0));
                }
            }
            if(pipe.direcion==1 || pipe.direcion==2){
                if(x+1<=n && arr[x+1][y]==0){
                    queue.offer(new Pipe(x+1, y, 2));
                }
            }
            if(x+1<=n && y+1<=n && arr[x+1][y+1]==0 && arr[x+1][y]==0 && arr[x][y+1]==0){
                queue.offer(new Pipe(x+1, y+1, 1));
            }
        }
        System.out.println(answer);
    }
}
