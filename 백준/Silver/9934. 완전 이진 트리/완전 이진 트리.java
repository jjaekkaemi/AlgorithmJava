import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int K, N;
    static int[] arr;
    static int cnt ;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        K = sc.nextInt();
        N = (int)Math.pow(2, K)-1 ;
        cnt = 0 ;
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, N-1, 0});
        while(!queue.isEmpty()){
            int[] num = queue.poll();

            int st = num[0];
            int en = num[1];
            int depth = num[2];
            int mid = (st+en)/2;
            System.out.print(arr[mid]+" ");
            if(++cnt==(int)Math.pow(2,depth)) {
                System.out.println();
                cnt = 0;
            }
            if(st>=en) continue;
            queue.offer(new int[]{st, mid-1, depth+1});
            queue.offer(new int[]{mid+1, en, depth+1});
        }
        //binarySearch(0, N-1, 0);
    }
    public static void binarySearch(int st, int en, int depth ){

        int mid = (st+en)/2;
        System.out.print(arr[mid]+" ");
        if(++cnt==(int)Math.pow(2,depth)) {
            cnt =0;
            System.out.println();
        }

        if(st>=en) return;
        binarySearch(st, mid-1, depth+1);
        binarySearch(mid+1, en, depth+1);
    }



}

