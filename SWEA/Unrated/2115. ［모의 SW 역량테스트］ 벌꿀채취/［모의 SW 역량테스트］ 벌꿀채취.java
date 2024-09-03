import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Solution {
    static int N, M, C ;
    static int[][] arr;
    static boolean[][] visited ;
    static int answer ;
    static int maxAnswer ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int TEST_CASE = Integer.parseInt(st.nextToken());
        for(int t = 1 ; t<TEST_CASE+1 ; t++){
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            arr = new int[N][N];
            visited = new boolean[N][N];
            answer = 0;
            for(int i = 0 ; i<N ; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ;j<N; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dfs(0, 0,0);
            System.out.println("#"+t+" "+answer);

        }
    }

    public static void dfs(int s, int depth, int idx){
        if(depth==2) { // 2를 선택하면 Max 값 담기
            answer = Math.max(answer, s);
            return ;
        }
        for(int i = idx ; i<N; i++){
            for(int j = 0 ; j<=N-M ; j++){ // 열에서 하나씩 더하기
                if(j+M>N) break; // 만약 현재 열 인덱스에서 N 만큼 담을 수 없는 경우, 다음 열 가기
                if(visited[i][j]) continue;
                int sum = 0 ;

                for(int k = j ; k<j+M ; k++){
                    visited[i][k] = true;


                }
                maxAnswer = 0 ;
                checkH(0, 0, i, j, j+M);
                dfs(s+maxAnswer, depth+1,i+1);
                for(int k = j ; k<j+M ; k++){
                    visited[i][k] = false;
                }

            }
        }
    }

    static void checkHoney(int stX, int stY,int endY, int flag){
        checkH(0, 0, stX, stY, endY);
    }
    static void checkH(int sum, int pow, int x, int y, int yFlag){
        if(sum>C) return ;
        maxAnswer = Math.max(maxAnswer, pow);
        if(y>=yFlag) return ;
        checkH(sum+arr[x][y], pow+(arr[x][y]*arr[x][y]), x, y+1, yFlag);
        checkH(sum, pow, x, y+1, yFlag);
    }
}
