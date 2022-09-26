import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class 백준2667 {
    static int N;
    static int count;

    static int[][] arr;
    static boolean[][] Check;

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static ArrayList<Integer> Dangi = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        Check = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                count = 0;
                if (arr[i][j] == 1 && !Check[i][j]) {
                    DFS(i, j);
                    Dangi.add(count);
                }
            }
        }
        System.out.println(Dangi.size());
        Collections.sort(Dangi);

        for (int i = 0; i < Dangi.size(); i++) {
            System.out.println(Dangi.get(i));
        }

    }

    private static void DFS(int x, int y) {
        Check[x][y] = true;
        count++;
        for (int i = 0; i < 4; i++) {
            int X = x + dx[i];
            int Y = y + dy[i];

            if (X >= N || X < 0 || Y >= N || Y < 0) {
                continue;
            }

            if (Check[X][Y] || arr[X][Y] == 0) {
                continue;
            }

            if (!Check[X][Y] && arr[X][Y] == 1) {
                Check[X][Y] = true;
                DFS(X, Y);
            }
        }
    }
}
