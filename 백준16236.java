import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준16236 {
    static int N;
    static int f_cnt;
    static int size = 2; //상어의 크기 초기값

    static int[][] arr;

    static int[] dx = { -1, 1, 0, 0 }; //상하좌우
    static int[] dy = { 0, 0, -1, 1 };

    static Queue<baby> q1 = new LinkedList<>(); //물고기가 한마리일때.
       static boolean[][] check;

    static Queue<baby> q2 = new LinkedList<>(); //물고기가 2마리 이상일때.
       static int S_X, S_Y;
       static int[][] time;
       static int distance = 0;
       static int minX, minY;
       static int eat_cnt = 0;
       static int result_Time = 0;
       
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        time = new int[N][N];
        check = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 9) {
                    q1.add(new baby(i, j, 0)); //한마리일 경우
                    check[i][j] = true;

                    S_X = i;                         //두마리 이상일 경우
                    S_Y = j;
                }
                if (arr[i][j] > 0 && arr[i][j] < 7) {
                    f_cnt++;
                }
            }
        }

        if (f_cnt == 0) {
            System.out.println('0');
        } else if (f_cnt == 1) {
            System.out.println(Single_BFS());
        } else {
            Check();
            System.out.println(result_Time);
        }
    }

    private static void Check() {
        while (true) {
            minX = Integer.MAX_VALUE;
            minY = Integer.MAX_VALUE;
            distance = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    time[i][j] = -1;
                }
            }

            Multi_BFS(S_X, S_Y);

            if (minX != Integer.MAX_VALUE && minY != Integer.MAX_VALUE) {
                result_Time += distance;
                eat_cnt++;

                if (eat_cnt == size) {
                    size++;
                    eat_cnt = 0;
                }
                arr[minX][minY] = 0;
                S_X = minX;
                S_Y = minY;
            } else {
                break;
            }
        }
    }
    
    private static void Multi_BFS(int x, int y) {
        q2.add(new baby(x, y));
        arr[x][y] = 0;
        time[x][y] = 0;
        while (!q2.isEmpty()) {
            baby shark = q2.poll();
            for (int i = 0; i < 4; i++) {
                int X = shark.x + dx[i];
                int Y = shark.y + dy[i];

                if (X >= N || X < 0 || Y >= N || Y < 0) {
                    continue;
                }

                if (arr[X][Y] > size || time[X][Y] != -1) {
                    continue;
                }

                time[X][Y] = time[shark.x][shark.y] + 1;

                if (arr[X][Y] > 0 && arr[X][Y] < size) {
                    if (time[X][Y] < distance) {
                        minX = X;
                        minY = Y;
                        distance = time[X][Y];
                    } else if (time[X][Y] == distance) {
                        if (X < minX) {
                            minX = X;
                            minY = Y;
                        } else if (X == minX) {
                            if (Y < minY) {
                                minX = X;
                                minY = Y;
                            }
                        }
                    }
                }
                q2.add(new baby(X, Y));
            }
        }
    }

    private static int Single_BFS() {
        int CNT = 0;
        while (!q1.isEmpty()) {
            baby shark = q1.poll();
            for (int i = 0; i < 4; i++) {
                int X = shark.x + dx[i];
                int Y = shark.y + dy[i];

                if (X >= N || X < 0 || Y >= N || Y < 0) {
                    continue;
                }

                if (!check[X][Y] && arr[X][Y] >= size) {
                    break;
                }

                if (!check[X][Y] && arr[X][Y] < size) {
                    check[X][Y] = true;
                    if (arr[X][Y] > 0 && arr[X][Y] < size) {
                        CNT = shark.count + 1;
                        break;
                    }
                    q1.add(new baby(X, Y, shark.count + 1));
                }

            }
        }
        return CNT;
    }

    public static class baby {
        int x;
        int y;
        int count;

        public baby(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public baby(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
