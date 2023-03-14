import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class 백준11478 {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<String> set = new HashSet<String>();
        String S = br.readLine();

        for (int i = 0; i <=S.length()-1; i++) {
            for (int j = i+1; j <= S.length(); j++) {
                set.add(S.substring(i,j));
            }
        }
        System.out.println(set.size());
    }
}