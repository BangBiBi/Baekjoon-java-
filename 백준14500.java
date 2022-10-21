import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준14500 {
    static int[][] arr;
    static boolean[][] check;

    static int N;
    static int M;
    static int count = -1;

    static int dx[] = { 0, 0, 1, -1 };
    static int dy[] = { -1, 1, 0, 0 };

    static int Dx[][] = { { 0, 0, 0, 1 }, { 0, 1, 2, 1 }, { 0, 0, 0, -1 }, { 0, 1, 2, 1 } };
    static int Dy[][] = { { 0, 1, 2, 1 }, { 0, 0, 0, 1 }, { 0, 0, -1, 1 }, { 0, 1, 1, 1 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        check = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                check[i][j] = true;
                DFS(i, j, arr[i][j], 1);
                check[i][j] = false;
                FIND(i, j);
            }
        }
        System.out.println(count);
    }

    private static void DFS(int x, int y, int sum, int length) {
        if (length == 4) {
            count = Math.max(count, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int X = x + dx[i];
            int Y = y + dy[i];

            if (X >= N || X < 0 || Y >= M || Y < 0) {
                continue;
            }
            if (!check[X][Y]) {
                check[X][Y] = true;
                DFS(X, Y, sum + arr[X][Y], length + 1);
                check[X][Y] = false;
            }
        }
    }

    private static void FIND(int x, int y) {
        for (int i = 0; i < 4; i++) {
            boolean Check = false;
            int Sum = 0;
            for (int j = 0; j < 4; j++) {
                int X = x + Dx[i][j];
                int Y = y + Dy[i][j];

                if (X >= N || X < 0 || Y >= M || Y < 0) {
                    Check = true;
                    break;
                } else {
                    Sum += arr[X][Y];
                }
            }
            if (!Check) {
                count = Math.max(count, Sum);
            }
        }
    }
}
