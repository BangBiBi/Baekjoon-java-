import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class 백준7662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            TreeMap<Integer, Integer> q = new TreeMap<>();

            int k = Integer.parseInt(br.readLine());
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine(), " ");

                String s = st.nextToken();
                int n = Integer.parseInt(st.nextToken());

                if (s.equals("I")) {
                    q.put(n, q.getOrDefault(n, 0) + 1);
                }

                else {
                    if (q.isEmpty()) {
                        continue;
                    }

                    if (n == 1) {
                        int last = q.lastKey();
                        if (q.get(last) == 1) {
                            q.remove(last);
                        } else {
                            q.put(last, q.get(last) - 1);
                        }
                    } else {
                        int first = q.firstKey();
                        if (q.get(first) == 1) {
                            q.remove(first);
                        } else {
                            q.put(first, q.get(first) - 1);
                        }
                    }
                }
            }

            if (q.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                System.out.println(q.lastKey() + " " + q.firstKey());
            }

        }
    }
}