import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String t = br.readLine();
        String p = br.readLine();
        int len = p.length() ;
        int tLen = t.length();
        int[] table = new int[p.length()];
        table[0] = 0;
        int j = 0 ;
        int i = 1 ;
        int check = 0 ;
        while(i<len){
            if(p.charAt(i)==p.charAt(j)){
                table[i] = ++check;
                j++;
                i++;
            } else {
                if(j==0) {
                    table[i] = 0;
                    i++;
                } else {
                    j = table[j-1];
                    check = j ;
                }
            }
        }
        i = 0 ;
        j = 0 ;
        check = 0 ;
        int answer = 0 ;
        List<Integer> list = new ArrayList<>();
        while(i<tLen){
            if(t.charAt(i)==p.charAt(j)){
                check++;
                i++;
                j++;
            } else {
                if(j==0){
                    i++;
                } else {
                    j = table[j-1];
                    check = j ;
                }

            }
            if(check==len){
                j = table[j-1];
                check = j ;
                list.add(i-len+1);
            }
        }
        System.out.println(list.size());
        for(int a : list){
            System.out.print(a+" ");
        }
    }
}