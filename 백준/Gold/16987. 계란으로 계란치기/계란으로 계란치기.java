import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N ;
    static int[][] eggs ;
    static int answer ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        eggs = new int[N][2];
        answer = 0 ;
        for(int i = 0 ; i<N ; i++){
            st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken()); // 내구도
            eggs[i][1] = Integer.parseInt(st.nextToken()); // 무게
        }

        countEggs(0);
        System.out.println(answer);
    }

    public static void countEggs(int index){
        if(index == N){
            int count = 0 ;
            for(int i = 0 ; i<N ; i++){
                if(eggs[i][0]<=0) {
                    count++;
                }
            }

            answer = Math.max(count, answer);
            return ;
        }
        if(eggs[index][0]<=0) {
            countEggs(index+1);
            return ;
        }
        boolean check = false;
        for(int i = 0 ; i< N ; i++){
            if(i!=index && eggs[i][0]>0){
                check = true;
                eggs[index][0]-=eggs[i][1];
                eggs[i][0]-=eggs[index][1];

                countEggs(index+1);

                eggs[index][0]+=eggs[i][1];
                eggs[i][0]+=eggs[index][1];
            }
        }
        if(!check) {
            countEggs(index+1);
        }
    }

}
