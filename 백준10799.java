import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 백준10799 {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<String> st = new Stack<>();
        int result = 0;

        String[] s = br.readLine().split("");

        for (int i = 0; i < s.length; i++) {
            String n = s[i];

            if (n.equals("(")) { // '(' 의 경우 스택에 넣음
                st.push(n);
            } else {
                if (s[i - 1].equals("(")) { // 다른 경우, ')' 인데 s[n-1]의 값이 '(' 이면 즉, 레이저면
                    st.pop();  // 스택의 마지막 값 제거
                    result += st.size(); // 결과값에 현재 스택의 사이즈 값 더함
                } else {  // 막대의 끝이면
                    st.pop(); // 스택의 마지막 값 제거
                    result++; //결과값 ++
                }
            }
        }
        System.out.println(result);  // 결과값 출력
    }
}