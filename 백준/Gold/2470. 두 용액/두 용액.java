import java.io.*;
import java.util.*;

public class Main {
    static int answer[] ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int arr[] = new int[N];
        answer = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        for(int i = 0 ; i<N ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int left = 0, right = N-1;
        int closestSum = Integer.MAX_VALUE;
        int leftAnswer = 0 ;
        int rightAnswer = 0;
        while(left<right){
            int sum = arr[left]+arr[right];
            if(Math.abs(sum)<Math.abs(closestSum)){
                leftAnswer = arr[left];
                rightAnswer = arr[right] ;
                closestSum = sum;
            }
            if(sum>0){
                right--;

            } else if(sum<0){
                left++;
            } else {
                break;
            }

        }
        System.out.println(leftAnswer+" "+rightAnswer);

    }
}
