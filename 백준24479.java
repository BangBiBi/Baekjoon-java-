import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 백준24479 {
    static ArrayList<Integer> arr[];
    static int[] check;
    static int x, y;
    static int N, M, R;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N + 1];
        check = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            arr[x].add(y);
            arr[y].add(x);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(arr[i]);
        }
        
        DFS(R);
        for (int i = 1; i <= N; i++) {
            System.out.println(check[i]);
        }
    }

    private static void DFS(int R) {
        check[R] = ++count;
        for (int i : arr[R]) {
            if (check[i] == 0) {
                DFS(i);
            }
        }
    }
}
