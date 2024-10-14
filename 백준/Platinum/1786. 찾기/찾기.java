
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String t = br.readLine(); // 텍스트
        String p = br.readLine(); // 패턴

        int len = p.length();
        int tLen = t.length();
        int[] table = new int[len]; // LPS 테이블 생성

        // LPS 테이블 계산
        int j = 0;
        for (int i = 1; i < len; i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j)) {
                j = table[j - 1];
            }
            if (p.charAt(i) == p.charAt(j)) {
                table[i] = ++j;
            }
        }

        List<Integer> list = new ArrayList<>();
        j = 0;

        // 패턴 매칭 수행
        for (int i = 0; i < tLen; i++) {
            while (j > 0 && t.charAt(i) != p.charAt(j)) {
                j = table[j - 1];
            }
            if (t.charAt(i) == p.charAt(j)) {
                j++;
            }
            if (j == len) { // 패턴 전체가 일치했을 때
                list.add(i - len + 2); // 1-based 인덱스 보정
                j = table[j - 1]; // 다음 탐색을 위해 j 초기화
            }
        }

        // 결과 출력
        System.out.println(list.size());
        for (int idx : list) {
            System.out.print(idx + " ");
        }
    }
}
