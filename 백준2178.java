import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준2178{
    static int[][] arr;             //미로 배열
    static boolean[][] check;       //지나간 길 확인

    static int[] dx = { -1, 1, 0, 0 }; //상하좌우
    static int[] dy = { 0, 0, -1, 1 };

    static int N;
    static int M;

    static Queue<Maze> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());           //배열의 크기 입력

        arr = new int[N][M];
        check = new boolean[N][M];
        
        for (int i = 0; i < N; i++) {             //배열 입력
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }
        BFS();
        System.out.println(arr[N-1][M-1]);
    }

    static class Maze {  //미로의 좌표를 큐에 저장하기 위한 클래스
        int x;
        int y;

        Maze(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    protected static void BFS() { //너비우선탐색으로 최단거리 구함
        check[0][0] = true;           //시작좌표 체크
        q.add(new Maze(0, 0));    //시작좌표 큐에넣음
        
        while (!q.isEmpty()) {
            Maze start = q.poll();              //현재 위치값 저장
            for (int i = 0; i < 4; i++) {           //현재 위치에서 상하좌우 탐색
                int X = start.x + dx[i];
                int Y = start.y + dy[i];

                if (X >= M || X < 0 || Y >= N || Y < 0) {   //배열을 벗어나면 컨티뉴
                    continue;
                }
                if (check[Y][X] || arr[Y][X] == 0) {      //이미 지나간 길이거나 길이 아니면 컨티뉴
                    continue;
                }

                if (!check[Y][X] && arr[Y][X] == 1) {    //지나간 길이 아니면서 올바른 길이면 
                    q.add(new Maze(X,Y));                 //큐에 현재 좌표 추가
                    check[Y][X] = true;                         //체크
                    arr[Y][X] = arr[start.y][start.x] + 1;       //다음좌표 탐색
                }
            }
        }
    }
}