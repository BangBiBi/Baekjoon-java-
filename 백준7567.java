package main.src;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준7567 {
    static int N;
    static int M;
    static int Day;

    static int[][] arr;
    static boolean[][] visit;

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static Queue<tomato> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    q.add(new tomato(j, i, 0));
                    visit[i][j] = true;
                } else if (arr[i][j] == 0) {
                    Day++;
                }
            }
        }
        if (Day == 0) {
            System.out.println("0");
        } else {
            find();
        }

    }

    static class tomato {
        int x;
        int y;
        int day;

        tomato(int x, int y, int day) {
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }

    private static void find() {
        while (!q.isEmpty()) {
            tomato start = q.poll();

            for (int i = 0; i < 4; i++) {
                int X = start.x + dx[i];
                int Y = start.y + dy[i];

                if (X >= M || X < 0 || Y >= N || Y < 0) {
                    continue;
                }

                if (!visit[Y][X] && arr[Y][X] == 0) {
                    q.add(new tomato(X, Y, start.day + 1));
                    visit[Y][X] = true;
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
}
