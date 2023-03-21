import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 백준11725 {
    private static List<List<Integer>> Tree = new ArrayList<>(); // 양방향 그래프
    private static int[] parents;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        parents = new int[N + 1];

        for (int i = 0; i <= N; i++) { // 트리 초기화
            Tree.add(new ArrayList<>());
        }

        while (N-- > 1) {
            st = new StringTokenizer(br.readLine(), " ");

            int P_node = Integer.parseInt(st.nextToken());
            int node = Integer.parseInt(st.nextToken());

            Tree.get(P_node).add(node); // 양방향으로 삽입
            Tree.get(node).add(P_node);
        }
        DFS(1);

        for (int i = 2; i < parents.length; i++) {
            System.out.println(parents[i]);
        }
    }

    private static void DFS(int x) {  // DFS 연산 함수
        for (int i : Tree.get(x)) { 
            if (parents[i] == 0) {
                parents[i] = x;
                DFS(i);
            }
        }
    }
}
