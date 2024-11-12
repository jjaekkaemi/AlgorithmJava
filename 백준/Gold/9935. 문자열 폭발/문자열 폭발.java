import java.util.Stack;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 받기
        String input = scanner.nextLine();
        String explosion = scanner.nextLine();
        scanner.close();

        // 폭발 문자열 처리 함수 호출 및 결과 출력
        String result = processExplosion(input, explosion);
        System.out.println(result);
    }

    public static String processExplosion(String input, String explosion) {
        Stack<Character> stack = new Stack<>();
        int explosionLength = explosion.length();

        // 문자열의 각 문자를 순회하면서 스택에 하나씩 추가
        for (char ch : input.toCharArray()) {
            stack.push(ch);
            
            // 스택의 끝부분이 폭발 문자열과 일치하는지 확인
            if (stack.size() >= explosionLength) {
                boolean isMatch = true;
                
                // 스택의 끝에서부터 폭발 문자열과 비교
                for (int i = 0; i < explosionLength; i++) {
                    if (stack.get(stack.size() - explosionLength + i) != explosion.charAt(i)) {
                        isMatch = false;
                        break;
                    }
                }
                
                // 폭발 문자열이 일치할 경우 스택에서 제거
                if (isMatch) {
                    for (int i = 0; i < explosionLength; i++) {
                        stack.pop();
                    }
                }
            }
        }
        
        // 스택에 남아 있는 문자들을 최종 결과 문자열로 변환
        StringBuilder result = new StringBuilder();
        for (char ch : stack) {
            result.append(ch);
        }
        
        return result.length() > 0 ? result.toString() : "FRULA";
    }
}
