
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int N, adjMatrix[][], cnt, radjMatrix[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine().trim());
        for(int tc = 1 ; tc<=TC; tc++){
            N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());

            adjMatrix = new int[N+1][N+1];// 학생번호 1번부터 시작
            radjMatrix = new int[N+1][N+1];// 학생번호 1번부터 시작, 나보다 작은 학생정보 행으로 유지
            StringTokenizer st = null;
            for(int i = 0 ; i<M ; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                radjMatrix[b][a] = adjMatrix[a][b] = 1;
            }
            int ans = 0 ;
            for(int i = 1 ; i<=N ; i++){
                cnt = 0 ;
                dfs(i, adjMatrix, new boolean[N+1]);
                dfs(i, radjMatrix, new boolean[N+1]);
                if(cnt==N-1) ans++;
            }
            System.out.println("#"+tc+" "+ans);
        }
    }

    public static void dfs(int cur, int[][] matrix, boolean[] visited){ // 자신보다 작은 학생따라 탐색
        visited[cur] = true;
        for(int i = 1 ; i<=N ; i++){
            if(!visited[i]&&matrix[cur][i] !=0){


                dfs(i, matrix, visited);
                cnt++; // 나보다 작은 대상 카운팅
            }
        }
    }
}

