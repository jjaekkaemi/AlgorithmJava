import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int n;
    static int answer ;
    static int[][] arr ;
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
        arr = new int[n+1][n+1];
        for(int i = 1 ; i<n+1 ;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j<n+1 ; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());

            }
        }
        Queue<Pipe> queue = new LinkedList<>();
        answer = 0 ;
        dfs(1,2,0);
        System.out.println(answer);
    }
    static void dfs(int x, int y, int direction){
        if(x==n && y==n){
            answer++;
            return;
        }
        if(direction==0 || direction==1){
            if(y+1<=n && arr[x][y+1]==0) {
                dfs(x, y+1, 0);
            }
        }
        if(direction==1 || direction==2){
            if(x+1<=n && arr[x+1][y]==0){
                dfs(x+1, y, 2);
            }
        }
        if(x+1<=n && y+1<=n && arr[x+1][y+1]==0 && arr[x+1][y]==0 && arr[x][y+1]==0){
            dfs(x+1, y+1, 1);
        }
    }
}
