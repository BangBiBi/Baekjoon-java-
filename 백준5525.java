import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준5525 {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        char[] c = br.readLine().toCharArray();
        int[] arr = new int[M+1];
        int count = 0;

        for (int i = 0; i < M; i++) {
            if (c[i] == 'O' && c[i + 1] == 'I') {
                arr[i + 1] = arr[i - 1] + 1;

                if (arr[i + 1] >= N && c[i - 2 * N + 1] == 'I') {
                    count++;
                }
            }
        }
        System.out.println(count); 
    }   
}
