import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준1260 {
    static int N;
    static int[][] arr;
    static boolean[] check;
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        int x, y;

        arr = new int[N + 1][N + 1];
        check = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            arr[x][y] = 1;
            arr[y][x] = 1;
        }

        check[V] = true;
        System.out.print(V + " ");
        DFS(V);
        System.out.println();

        check = new boolean[N + 1];
        BFS(V);

    }

    protected static void DFS(int n) {
        for (int i = 1; i < N + 1; i++) {
            if (!check[i] && arr[n][i] == 1) {
                System.out.print(i + " ");
                check[i] = true;
                DFS(i);
            }
        }
        
    }    
    
    protected static void BFS(int n) {
        q.add(n);
        check[n] = true;
        System.out.print(n+" ");
        while (!q.isEmpty()) {
            int start = q.poll();
            for (int i = 0; i < N + 1; i++) {
                if (!check[i] && arr[start][i] == 1) {
                    System.out.print(i + " ");
                    check[i] = true;
                    q.add(i);
                }
            }
        }
        System.out.println();
    }
}
