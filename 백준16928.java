import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준16928 {
    static int N;
    static int M;
    static int count = Integer.MAX_VALUE;

    static int[] ladder = new int[101];
    static int[] snake = new int[101];
    static boolean[] check = new boolean[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            ladder[x] = y;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            snake[x] = y;
        }
        BFS();
        System.out.println(count);
    }

    private static void BFS() {
        Queue<Dice> q = new LinkedList<>();
        q.add(new Dice(1, 0));
        check[1] = true;
        while (!q.isEmpty()) {
            Dice start = q.poll();
            if (start.x == 100) {
                count = Math.min(count, start.y);
                break;
            }
            for (int i = 1; i <= 6; i++) {
                int Next = start.x + i;

                if (Next > 100 || check[Next]) {
                    continue;
                }

                check[Next] = true;

                if (ladder[Next] != 0) {
                    q.add(new Dice(ladder[Next], start.y + 1));
                }

                else if (snake[Next] != 0) {
                    q.add(new Dice(snake[Next], start.y + 1));  
                }

                else {
                    q.add(new Dice(Next, start.y + 1));
                }
            }
        }
    }

    static class Dice {
        int x; //현재 좌표
        int y; //주사위 횟수
        Dice(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
