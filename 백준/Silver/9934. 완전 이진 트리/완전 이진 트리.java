import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {
    static int K, size;
    static int[] num;
    static ArrayList<Integer>[] tree;
    public static void main(String[] args) throws IOException{
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        size = (int)(Math.pow(2, K) - 1);
        tree = new ArrayList[K+1];
        num = new int[size+1];
        for(int i=0;i<=K;i++)
            tree[i] = new ArrayList<>();
        int index = 1;
        //중위 순회 탐색 정보 배열에 저장
        while(st.hasMoreTokens())
            num[index++] = Integer.parseInt(st.nextToken());
        search(1, 1, size);		//중위 순회 특성 이용한 Left, Right 나누기
        //각 층에 빌딩 정보 BufferedWriter 저장
        for(int i=1;i<=K;i++){
            for(int j=0;j<tree[i].size();j++)
                bw.write(tree[i].get(j) + " ");
            bw.newLine();
        }
        bw.flush();		//결과 출력
        bw.close();
        br.close();
    }
    //중위 순회 특성 이용한 레벨에 맞는 빌딩 값들 저장 함수
    static void search(int depth, int start, int end){
        int mid = (start + end)/2;		//Root
        tree[depth].add(num[mid]);
        if(depth == K)		//단말 노드일 때
            return;
        search(depth+1, start, mid-1);	//Left
        search(depth+1, mid+1, end);	//Right
    }
}

