import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        StringBuilder result = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int k = Integer.parseInt(br.readLine()); // 연산의 개수
            PriorityQueue<Integer> minQueue = new PriorityQueue<>(); // 최소값 우선순위 큐
            PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder()); // 최대값 우선순위 큐
            HashMap<Integer, Integer> map = new HashMap<>(); // 숫자의 삽입/삭제 카운트 관리

            for (int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                String command = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if (command.equals("I")) {
                    // 삽입 연산
                    minQueue.add(num);
                    maxQueue.add(num);
                    map.put(num, map.getOrDefault(num, 0) + 1);
                } else {
                    // 삭제 연산
                    if (num == 1) {
                        delete(maxQueue, map); // 최대값 삭제
                    } else {
                        delete(minQueue, map); // 최소값 삭제
                    }
                }
            }

            // 유효한 값들로 큐를 정리
            cleanQueue(maxQueue, map);
            cleanQueue(minQueue, map);

            if (maxQueue.isEmpty() || minQueue.isEmpty()) {
                result.append("EMPTY\n");
            } else {
                result.append(maxQueue.peek()).append(" ").append(minQueue.peek()).append("\n");
            }
        }

        System.out.print(result.toString());
    }

    // 해당 큐에서 유효하지 않은 값을 모두 제거하는 메소드
    private static void cleanQueue(PriorityQueue<Integer> queue, HashMap<Integer, Integer> map) {
        while (!queue.isEmpty() && map.getOrDefault(queue.peek(), 0) == 0) {
            queue.poll();
        }
    }

    // 큐에서 최상위 값을 삭제하고 map의 카운트를 감소시키는 메소드
    private static void delete(PriorityQueue<Integer> queue, HashMap<Integer, Integer> map) {
        while (!queue.isEmpty()) {
            int value = queue.poll();
            int count = map.getOrDefault(value, 0);

            if (count > 0) {
                map.put(value, count - 1);
                break;
            }
        }
    }
}
