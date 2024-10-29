import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int N;
    static char[][] corridor;
    static List<int[]> teachers = new ArrayList<>();
    static List<int[]> empties = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        corridor = new char[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                corridor[i][j] = sc.next().charAt(0);
                if (corridor[i][j] == 'T') {
                    teachers.add(new int[]{i, j});
                } else if (corridor[i][j] == 'X') {
                    empties.add(new int[]{i, j});
                }
            }
        }

        // 장애물을 배치하고 감시를 피할 수 있는지 확인
        if (placeObstacles(0, 0)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    // 3개의 장애물을 배치하는 조합을 찾는 메서드
    static boolean placeObstacles(int count, int start) {
        if (count == 3) {
            return canAvoidSurveillance();
        }

        for (int i = start; i < empties.size(); i++) {
            int[] pos = empties.get(i);
            corridor[pos[0]][pos[1]] = 'O';
            if (placeObstacles(count + 1, i + 1)) {
                return true;
            }
            corridor[pos[0]][pos[1]] = 'X';
        }
        return false;
    }

    // 모든 학생이 감시를 피할 수 있는지 확인하는 메서드
    static boolean canAvoidSurveillance() {
        for (int[] teacher : teachers) {
            if (!checkTeacher(teacher[0], teacher[1])) {
                return false;
            }
        }
        return true;
    }

    // 특정 선생님이 4방향으로 감시하는 메서드
    static boolean checkTeacher(int x, int y) {
        for (int dir = 0; dir < 4; dir++) {
            int nx = x, ny = y;
            while (true) {
                nx += dx[dir];
                ny += dy[dir];
                
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || corridor[nx][ny] == 'O') break;
                if (corridor[nx][ny] == 'S') return false; // 학생이 감시에 노출된 경우
            }
        }
        return true; // 이 선생님의 감시 범위 내에 학생이 없음
    }
}
