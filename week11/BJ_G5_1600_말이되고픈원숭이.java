package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//백준 골드5 1600 말이 되고픈 원숭이
public class BJ_G5_1600_말이되고픈원숭이 {

    static int[][] hores = {
        {0,1,0,1,0},
        {1,0,0,0,1},
        {0,0,0,0,0},
        {1,0,0,0,1},
        {0,1,0,1,0}
    };
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int K,R,C;
    static int[][] map;
    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());   //순서 반대로 줌...ㅠㅠ
        R = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());   //백준에서 null 날 곳이 없는데 null pointer exception이 터졌다면 입력이 잘못됐는지 확인해보자..^^;;!!!
            }
        }

        int result = bfs();
        System.out.println(result);
    }

    static class Point{
        int r,c,k,d;

        public Point(int r, int c, int k, int d) {
            super();
            this.r = r;
            this.c = c;
            this.k = k;
            this.d = d;
        }

        @Override
        public String toString() {
            return "(" + r + "," + c +","+k+","+d+")";
        }

    }
    static int bfs() {

        Queue<Point> Q = new LinkedList<>();
        //r,c번째 칸에, k번만큼 말의 움직임을 사용해서 도착한 적이 있음
        boolean[][][] v = new boolean[R][C][K+1];

        Q.add(new Point(0,0,0,0));
        v[0][0][0] = true;
        while(!Q.isEmpty()) {

            Point curr = Q.poll();
            //도착지에 도착
            if(curr.r == R-1 && curr.c == C-1) {
                return curr.d;
            }
            //원숭이의 움직임
            for(int d=0; d<4; d++) {
                int nr = curr.r+dr[d];
                int nc = curr.c+dc[d];
                //똑같은 말인데 이게 웨않돼지...;;
//              if(!outOfBoundary(nr, nc) && !v[nr][nc][curr.k] && map[nr][nr]==0) {
//                  Q.add(new Point(nr,nc,curr.k,curr.d+1));
//                  v[nr][nc][curr.k] = true; 
//              }
                if(outOfBoundary(nr, nc) || v[nr][nc][curr.k] || map[nr][nc]==1) continue;
                Q.add(new Point(nr,nc,curr.k,curr.d+1));
                v[nr][nc][curr.k] = true; 
            }

            //말의 움직임은 K번 까지만 할 수 있음
            if(curr.k == K) continue;

            //말의 움직임
            for(int i=0; i<hores.length; i++) {
                for(int j=0; j<hores[i].length; j++) {
                    if(hores[i][j]==0) continue;
                    /*
                     * 말의 위치 : 2,2 (5/2임).  
                     * ex) h( 0 , 1 )를 적용하려면 원래위치+( -2, -1 )해야함 
                     * -2 : 0 - (5/2)
                     * -1 : 1 - (5/2)
                     */
                    int nr = curr.r + (i - 5/2);
                    int nc = curr.c + (j - 5/2);
                    if(!outOfBoundary(nr, nc) && !v[nr][nc][curr.k+1] && map[nr][nc]==0) {
                        Q.add(new Point(nr, nc, curr.k+1, curr.d+1));
                        v[nr][nc][curr.k+1] = true;
                    }
                }
            }
        }
        return -1;
    }
    static boolean outOfBoundary(int r, int c) {
        if(r>=R || r<0 || c>=C|| c<0) return true;
        return false;
    }

}