import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(reader.readLine());

        for (int t = 0; t < testCases; t++) {
            int operations = Integer.parseInt(reader.readLine());
            Map<Integer, Integer> frequencyMap = new HashMap<>();
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

            for (int i = 0; i < operations; i++) {
                String[] command = reader.readLine().split(" ");
                char action = command[0].charAt(0);
                int value = Integer.parseInt(command[1]);

                if (action == 'I') {
                    frequencyMap.put(value, frequencyMap.getOrDefault(value, 0) + 1);
                    minHeap.offer(value);
                    maxHeap.offer(value);
                } else {
                    if (frequencyMap.isEmpty()) continue;

                    PriorityQueue<Integer> targetHeap = (value == 1) ? maxHeap : minHeap;
                    cleanHeap(targetHeap, frequencyMap);
                }
            }

            if (frequencyMap.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                int maxVal = cleanHeap(maxHeap, frequencyMap);
                int minVal = frequencyMap.isEmpty() ? maxVal : cleanHeap(minHeap, frequencyMap);
                System.out.println(maxVal + " " + minVal);
            }
        }
    }

    // 특정 힙에서 유효한 값을 꺼내는 메서드
    static int cleanHeap(PriorityQueue<Integer> heap, Map<Integer, Integer> freqMap) {
        int element;
        while (true) {
            element = heap.poll();
            int count = freqMap.getOrDefault(element, 0);

            if (count == 0) continue;

            if (count == 1) {
                freqMap.remove(element);
            } else {
                freqMap.put(element, count - 1);
            }
            break;
        }
        return element;
    }
}
