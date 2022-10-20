import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준9019 {
    static int[] arr;
    static int A;
    static int B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr = new int[10000];

            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            System.out.println(BFS());
        }
    }

    private static String BFS() {
        Queue<resister> q = new LinkedList<>();
        q.add(new resister("", A));
        arr[A] = 1;
        while (!q.isEmpty()) {
            resister start = q.remove();

            if (start.n == B) {
                return start.command;
            }
            int d = D(start.n);
            if (arr[d] == 0) {
                arr[d] = arr[start.n] + 1;
                q.add(new resister(start.command + "D", d));
            }
            int s = S(start.n);
            if (arr[s] == 0) {
                arr[s] = arr[start.n] + 1;
                q.add(new resister(start.command + "S", s));
            }
            int l = L(start.n);
            if (arr[l] == 0) {
                arr[l] = arr[start.n] + 1;
                q.add(new resister(start.command + "L", l));
            }
            int r = R(start.n);
            if (arr[r] == 0) {
                arr[r] = arr[start.n] + 1;
                q.add(new resister(start.command + "R", r));
            }
        }
        return "";
    }

    private static int D(int x) {
        x *= 2;
        if (9999 < x) {
            x %= 10_000;
        }
        return x;
    }

    private static int S(int x) {
        if (x == 0) {
            x = 9999;
        } else {
            x -= 1;
        }
        return x;
    }

    private static int L(int x) {
        return x % 1000 * 10 + x / 1000;
    }

    private static int R(int x) {
        return x % 10 * 1000 + x / 10;
    }

    public static class resister {
        String command;
        int n;

        public resister(String command, int n) {
            this.command = command;
            this.n = n;
        }
    }
}