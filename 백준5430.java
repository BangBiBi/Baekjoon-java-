import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class 백준5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Deque<Integer> d = new ArrayDeque<Integer>();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int count = 1;
            Boolean error = false;

            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String arr = br.readLine();

            String[] temp = arr.split(",");

            if (n != 0) {
                for (int j = 0; j < n; j++) {
                    d.add(Integer.parseInt(temp[j].replaceAll("[^0-9]", "")));
                }
            }

            for (int j = 0; j < p.length(); j++) {
                switch (p.charAt(j)) {
                    case 'R':
                        count *= -1;
                        break;
                    case 'D':
                        if (d.isEmpty()) {
                            error = true;
                            break;
                        }
                        if (count > 0) {
                            d.removeFirst();
                        } else {
                            d.removeLast();
                        }
                        break;
                }
            }

            if (error) {
                sb.append("error").append('\n');
            } else {
                sb.append("[");
                while (d.size() > 0) {
                    if (count > 0) {
                        sb.append(d.removeFirst());
                    } else {
                        sb.append(d.removeLast());
                    }
                    if (d.size() != 0) {
                        sb.append(",");
                    }
                }
                sb.append("]").append('\n');
            }
        }
        System.out.println(sb.toString());
    }
}