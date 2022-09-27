import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준1389 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N + 1][N + 1];
        
        for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
                if (i == j) {
                    arr[i][j] = 0;
                }
                else {
                    arr[i][j] = 99999999;
                }
			}
		}

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            arr[A][B] = 1;
            arr[B][A] = 1;
        }

        for (int a = 1; a <= N; a++) {
            for (int b = 1; b <= N; b++) {
                for (int c = 1; c <= N; c++) {
                    arr[b][c] = Math.min(arr[b][c], arr[b][a] + arr[a][c]);
                }
            }
        }

        int Min = Integer.MAX_VALUE;
        int result = Integer.MAX_VALUE;
        int sum;
        for (int i = 1; i <= N; i++) {
            sum = 0;
            for (int j = 1; j <= N; j++) {
                sum += arr[i][j];
            }
            if (result > sum) {
                result = sum;
                Min = i;
            }
        }
        System.out.println(Min);
    }
}
