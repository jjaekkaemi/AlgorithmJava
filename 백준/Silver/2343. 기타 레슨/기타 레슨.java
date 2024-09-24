import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] video = new int[N];
        st = new StringTokenizer(br.readLine());
        
        int left = 0, right = 0;
        
        // 입력을 받으면서 배열의 최대값과 전체 합을 계산
        for (int i = 0; i < N; i++) {
            video[i] = Integer.parseInt(st.nextToken());
            left = Math.max(left, video[i]);  // 최대값
            right += video[i];               // 전체 합
        }
        
        int answer = 0;
        
        // 이진 탐색
        while (left <= right) {
            int mid = (left + right) / 2;
            int count = 0;
            int videoSum = 0;
            
            for (int v : video) {
                if (videoSum + v > mid) {
                    count++;
                    videoSum = 0;
                }
                videoSum += v;
            }
            
            // 남은 부분을 카운트
            if (videoSum > 0) {
                count++;
            }
            
            if (count > M) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
        }
        
        System.out.println(answer);
    }
}
