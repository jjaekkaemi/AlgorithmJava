import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        System.out.println(findMooChar(N));
    }

    // 재귀적으로 Moo 수열의 N번째 문자를 찾는 함수
    static char findMooChar(long N) {
        long length = 3; // S(0)의 길이 "moo"
        int k = 0;

        // N이 포함된 S(k)를 찾기 위해 S(k) 수열의 길이를 증가시킴
        while (length < N) {
            k++;
            length = 2 * length + (k + 3); // S(k)의 길이
        }

        // 이제 k를 기준으로 역순으로 탐색하며 N번째 문자를 찾음
        return mooRecursive(N, k, length);
    }

    // 재귀 함수: Moo 수열에서 N번째 문자를 찾음
    static char mooRecursive(long N, int k, long length) {
        if (k == 0) {
            return (N == 1) ? 'm' : 'o'; // S(0)에서는 1번째가 'm', 나머지는 'o'
        }

        long prevLength = (length - (k + 3)) / 2; // 이전 수열 S(k-1)의 길이

        if (N <= prevLength) {
            // N이 왼쪽 S(k-1)에 속한 경우
            return mooRecursive(N, k - 1, prevLength);
        } else if (N > prevLength + (k + 3)) {
            // N이 오른쪽 S(k-1)에 속한 경우
            return mooRecursive(N - prevLength - (k + 3), k - 1, prevLength);
        } else {
            // N이 가운데 "m" + "o" * (k+2) 부분에 속한 경우
            return (N == prevLength + 1) ? 'm' : 'o';
        }
    }
}
