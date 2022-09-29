import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 백준10026 {
    static char[][] arr_1;
    static char[][] arr_2;
    static boolean[][] check_1;
    static boolean[][] check_2;

    static int N;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int count1 = 0;
    static int count2 = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr_1 = new char[N+1][N+1];
        arr_2 = new char[N+1][N+1];
        check_1 = new boolean[N+1][N+1];
        check_2 = new boolean[N+1][N+1];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                arr_1[i][j] = s.charAt(j);
                arr_2[i][j] = s.charAt(j);
                if (s.charAt(j) == 'R') {
                    arr_2[i][j] = 'G';
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!check_1[i][j]) {
                    BFS_1(new color_weak(i, j));
                    count1++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!check_2[i][j]) {
                    BFS_2(new color_weak(i, j));
                    count2++;
                }
            }
        }
        System.out.println(count1 + " " + count2);
    }

    private static void BFS_1(color_weak color) {
        Queue<color_weak> q = new LinkedList<>();
        q.add(color);
        check_1[color.x][color.y] = true;
        while (!q.isEmpty()) {
            color_weak start = q.poll();
            for (int i = 0; i < 4; i++) {
                int X = start.x + dx[i];
                int Y = start.y + dy[i];

                if (X >= N || X < 0 || Y >= N || Y < 0)
                    continue;

                if (arr_1[start.x][start.y] == arr_1[X][Y] && !check_1[X][Y]) {
                    q.offer(new color_weak(X, Y));
                    check_1[X][Y] = true;
                }
            }
        }
    }

    private static void BFS_2(color_weak point) {
        Queue<color_weak> queue = new LinkedList<>();
        queue.offer(point);
        check_2[point.x][point.y] = true;
        while (!queue.isEmpty()) {
            color_weak point2 = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x2 = point2.x + dx[i];
                int y2 = point2.y + dy[i];

                if (x2 >= N || x2 < 0 || y2 >= N || y2 < 0)
                    continue;
                    
                if (arr_2[point2.x][point2.y] == arr_2[x2][y2] && !check_2[x2][y2]) {
                    queue.offer(new color_weak(x2, y2));
                    check_2[x2][y2] = true;
                }
            }
        }
    }

    static class color_weak {
        int x;
        int y;

        public color_weak(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
