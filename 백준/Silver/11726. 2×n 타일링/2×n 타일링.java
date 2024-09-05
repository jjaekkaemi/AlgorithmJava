import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int answer = 0;
        int[] dp = new int[n + 1];
        if (n == 1) {
            answer = 1;
        } else if (n == 2) {
            answer = 2;
        } else {
            dp[1] = 1;
            dp[2] = 2;

            for (int i = 3; i < n + 1; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2])%10007;
            }
            answer = dp[n];
        }
        System.out.println(answer);
    }
}