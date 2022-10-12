import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준7569 {
    static int N;
    static int M;
    static int H;
    static int Day;

    static int[][][] arr;
    static boolean[][][] visit;

    static int[] dx = { -1, 1, 0, 0, 0, 0 };
    static int[] dy = { 0, 0, -1, 1, 0, 0 };
    static int[] dz = { 0, 0, 0, 0, -1, 1 };
    static Queue<tomato> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        arr = new int[H][N][M];
        visit = new boolean[H][N][M];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int k = 0; k < M; k++) {
                    arr[i][j][k] = Integer.parseInt(st.nextToken());
                    if (arr[i][j][k] == 1) {
                        q.add(new tomato(k, j, i, 0));
                        visit[i][j][k] = true;
                    } else if (arr[i][j][k] == 0) {
                        Day++;
                    }
                }
            }
        }
        if (Day == 0) {
            System.out.println("0");
        } else {
            find();
        }
    }

    private static void find() {
        while (!q.isEmpty()) {
            tomato start = q.poll();

            for (int i = 0; i < 6; i++) {
                int X = start.x + dx[i];
                int Y = start.y + dy[i];
                int Z = start.z + dz[i];

                if (X >= M || X < 0 || Y >= N || Y < 0 || Z >= H || Z < 0) {
                    continue;
                }

                if (!visit[Z][Y][X] && arr[Z][Y][X] == 0) {
                    q.add(new tomato(X, Y, Z, start.day + 1));
                    visit[Z][Y][X] = true;
                    Day--;
                    if (Day == 0) {
                        System.out.println(start.day + 1);
                        return;
                    }
                }
            }
        }
        System.out.println("-1");
    }

    static class tomato {
        int x;
        int y;
        int z;
        int day;

        tomato(int x, int y, int z, int day) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.day = day;
        }
    }
}