import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준1992 {
    static int[][] arr;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }
        Divide(0, 0, N);

        System.out.println(sb);
    }

    private static void Divide(int x, int y, int size) {

        if (check(x, y, size)) {
            sb.append(arr[x][y]);
            return;
        }

        int Size = size / 2;

        sb.append('(');
        Divide(x, y, Size);
        Divide(x, y + Size, Size);
        Divide(x + Size, y, Size);
        Divide(x + Size, y + Size, Size);
        sb.append(')');
    }

    private static boolean check(int x, int y, int size) {
        int start = arr[x][y];

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (start != arr[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

}
