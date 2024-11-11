import java.io.*;
import java.util.*;

public class Main {
    static List<LinkedList<Integer>> gears = new ArrayList<>();
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 4개의 톱니바퀴를 입력 받음
        for (int i = 0; i < 4; i++) {
            String input = br.readLine();
            LinkedList<Integer> gear = new LinkedList<>();
            for (char c : input.toCharArray()) {
                gear.add(c - '0');
            }
            gears.add(gear);
        }

        // 회전 횟수 K를 입력 받음
        K = Integer.parseInt(br.readLine());
        
        // 회전 명령 처리
        for (int i = 0; i < K; i++) {
            String[] command = br.readLine().split(" ");
            int gearIndex = Integer.parseInt(command[0]) - 1; // 톱니바퀴 번호 (0부터 시작하도록 변환)
            int direction = Integer.parseInt(command[1]);     // 회전 방향 (1: 시계, -1: 반시계)

            // 각 톱니바퀴가 회전할 방향을 계산
            int[] rotations = new int[4];
            rotations[gearIndex] = direction;

            // 왼쪽 톱니바퀴 회전 여부 결정
            for (int j = gearIndex; j > 0; j--) {
                if (gears.get(j).get(6) != gears.get(j - 1).get(2)) {
                    rotations[j - 1] = -rotations[j];
                } else {
                    break;
                }
            }

            // 오른쪽 톱니바퀴 회전 여부 결정
            for (int j = gearIndex; j < 3; j++) {
                if (gears.get(j).get(2) != gears.get(j + 1).get(6)) {
                    rotations[j + 1] = -rotations[j];
                } else {
                    break;
                }
            }

            // 각 톱니바퀴 회전
            for (int j = 0; j < 4; j++) {
                if (rotations[j] == 1) {
                    rotateClockwise(gears.get(j));
                } else if (rotations[j] == -1) {
                    rotateCounterClockwise(gears.get(j));
                }
            }
        }

        // 점수 계산
        int score = 0;
        for (int i = 0; i < 4; i++) {
            if (gears.get(i).get(0) == 1) {
                score += (1 << i);
            }
        }

        System.out.println(score);
    }

    // 시계 방향 회전
    static void rotateClockwise(LinkedList<Integer> gear) {
        gear.addFirst(gear.removeLast());
    }

    // 반시계 방향 회전
    static void rotateCounterClockwise(LinkedList<Integer> gear) {
        gear.addLast(gear.removeFirst());
    }
}
