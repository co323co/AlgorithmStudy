package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
 
 * 왼->왼->왼->왼
 * 북 서 남 동
 * 상 좌 하 우
 */
//백준 골드5 140503 로봇 청소기
public class BJ_G5_14503_로봇청소기 {

	static int R, C;
	static int[][] map;
	public static void main(String[] args) throws IOException {

		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Robot start = new Robot(Integer.parseInt(st.nextToken()) , Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		if(start.d%2==1) start.d = start.d == 1 ? 3 : 1;
		map = new int[R][C];
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//solve
		System.out.println(bfs(start));
	}

	static class Robot{
		int r, c, d;
		public Robot(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	
	static int bfs(Robot start) {
		
		//북 서 남 동
		int[] dr = {-1,0,1,0};
		int[] dc = {0,-1,0,1};
		
		int cnt=1;
		
		Queue<Robot> Q = new LinkedList<>();
		
		Q.add(start);
		//0이면 청소안한 빈공간, 1이면 벽, -1이면 청소 한 빈공간
		map[start.r][start.c] = -1;
		
		while(!Q.isEmpty()) {
			Robot curr =  Q.poll();

			int nr, nc, nd = curr.d;
			boolean flag = false;
			//왼쪽으로 한번씩 회전함
			for(int i=0; i<4; i++) {
				nd = (nd+1)%4;
				nr = curr.r + dr[nd];
				nc = curr.c + dc[nd];
				//왼쪽 방향에 아직 (청소할 수 있는) 청소하지 않은 공간이 존재한다면
				if(map[nr][nc]==0) {
					Q.add(new Robot(nr, nc, nd));
					flag = true;
					cnt++;
					map[nr][nc] = -1; // 청소했으면 -1
					break;
				} 
			}
			//4방향 모두 청소할 수 있는 공간이 없다면
			if(!flag) {
				nr = curr.r - dr[curr.d];
				nc = curr.c - dc[curr.d];
				if(map[nr][nc]!=1) {
					Q.add(new Robot(nr, nc, curr.d));
				} else {
					return cnt;
				}
			}
			
		}
		return cnt;
	}
}
