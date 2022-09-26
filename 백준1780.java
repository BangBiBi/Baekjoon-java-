import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준1780 {
    public static int Minus_one = 0;
    public static int Zero = 0;
    public static int One = 0;

    public static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Divide(0, 0, N);

        System.out.println(Minus_one);
        System.out.println(Zero);
        System.out.println(One);

    }

    public static void Divide(int x, int y, int size) {
        if (Check(x, y, size)) {
            if (arr[x][y] == -1) {
                Minus_one++;
            } else if (arr[x][y] == 0) {
                Zero++;
            } else {
                One++;
            }
            return;
        }
        int Size = size / 3;

        Divide(x, y, Size);
        Divide(x, y + Size, Size);
        Divide(x, y + 2 * Size, Size);

        Divide(x + Size, y, Size);
        Divide(x + Size, y + Size, Size);
        Divide(x + Size, y + 2 * Size, Size);

        Divide(x + 2 * Size, y, Size);
        Divide(x + 2 * Size, y + Size, Size);
        Divide(x + 2 * Size, y + 2 * Size, Size);
    }
    
    public static boolean Check(int x, int y, int size) {
        int start = arr[x][y];

        for (int i = x; i < x + size; i++){
            for(int j=y; j<y+size;j++){
                if (start != arr[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
}
