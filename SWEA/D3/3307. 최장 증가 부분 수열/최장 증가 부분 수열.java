import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int TEST_CASE = Integer.parseInt(st.nextToken());
        for(int t = 1 ; t<TEST_CASE+1 ; t++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i<n ; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int[] dp = new int[n];
            int max = 0 ;
            for(int i = 0 ; i<n ; i++){
                dp[i] = 1;
                for(int j = i-1 ; j>-1 ; j--){
                    if(arr[i]>arr[j]) {
                        if (dp[i] <= dp[j]) {
                            dp[i] = dp[j] + 1;

                        }
                    }
                    max = Math.max(dp[i], max);
                }
            }
            System.out.println("#"+t+" "+max);
        }
    }
}