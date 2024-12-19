import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 입력예제
5 3
1
2
8
4
9
*/
public class Main {
    static int answer = 0 ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 집 개수
        int C = Integer.parseInt(st.nextToken()); // 공유기 개수

        int[] homes = new int[N];
        for(int i = 0 ; i<N ; i++){
            st = new StringTokenizer(br.readLine());
            homes[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(homes); // 오름차순으로 정렬

        answer = 0 ;
        int start = 1; // 공유기 시작 설치 거리는 1로 시작
        int end = homes[N-1]-homes[0]; // 처음 집과 마지막 집의 거리 차이

        while(start<=end) {  // start가 end보다 작거나 같을 때 까지
            int mid = (start+end)/2; // 거리
            int count = 1 ; // 공유기 설치 개수
            int wifi = homes[0]; // 공유기 설치 거리
            for(int i = 0 ; i<N ; i++){
                if(homes[i]-wifi>=mid){ // 현재 집의 거리와 이전에 설치한 공유 거리 차가 mid 보다 크거나 같다면
                    wifi = homes[i];
                    count++;

                }
            }
            if(count>=C){ // 설치해야 하는 공유기보다 많이 or 같게 설치한 경우 mid 값이 더 커져야 한다 !
                answer = mid;
                start = mid+1;
            } else {
                end = mid-1;
            }
        }
        System.out.println(answer);
    }


}
