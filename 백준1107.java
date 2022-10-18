import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준1107 {
    static boolean[] button = new boolean[10];

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int count = Math.abs(N - 100);

        if (M !=0) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < M; i++) {
                int n = Integer.parseInt(st.nextToken());
                button[n] = true;
            }
        }
        
        for (int i = 0; i <= 1000000; i++) {
            int cnt = check(i);
            if (cnt > 0) {
                int Button = Math.abs(N - i);
                count = Math.min(count, cnt + Button);
            }
        }
        System.out.println(count);
    }

    private static int check(int i) {
        if (i == 0) {
            if (button[0]) {
                return 0;
            } else {
                return 1;
            }
        }
        int count = 0;
        while (i > 0) {
            if (button[i % 10]) { 
                return 0;
            }
            i /= 10;
            count += 1; 
        }
        return count;
    }
}
