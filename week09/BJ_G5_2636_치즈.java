package week09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 공기와 치즈 구멍의 구분 방법 : 영역이 테두리를 만나면 공기, 테두리를 만나지 못하면 구멍
 * 
 * 시간마다, (한 회차)
 * 공기(0)로 bfs를 돌림 
 * 		(치즈 녹이기 작업)
 * 		만나는 치즈를 기억해둠 (만약 공기면 녹여줘야 함)
 * 		맵의 가장자리를 만났으면 공기라는 뜻이니 기억해둔 치즈를 녹여줌
 */
//백준 골드5 2636 치즈
public class BJ_G5_2636_치즈 {

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, M;
	static int[][] board;
	public static void main(String[] args) throws IOException {
		
		//입력
		System.setIn(new FileInputStream("src/Baekjoon/치즈.txt"));
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		
		int max = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				 board[i][j] = Integer.parseInt(st.nextToken());
				 max = Math.max(max, board[i][j]);
			}
		}
		
		//solve
		int time = 0;
		int before_cnt = cntCheese();
		while(true) {
			time++;
			bfs();
			int curr_cnt = cntCheese();
			if(curr_cnt==0) {
				System.out.println(time);
				System.out.println(before_cnt);
				return;
			}
			before_cnt = curr_cnt;
		}
	}

	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static void bfs() {
	
		boolean[][] v = new boolean[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				
				if(board[i][j]==1 || v[i][j]) continue;
				ArrayList<Pos> cheese = new ArrayList<>(); //만약 공기면 녹이게 만난 치즈들 저장해둠
				Queue<Pos> Q = new LinkedList<>();
				v[i][j] = true;
				cheese.add(new Pos(i,j));
				Q.add(cheese.get(cheese.size()-1));

				boolean isAir = false;
				while(!Q.isEmpty()) {
					Pos curr = Q.poll();
					int nr, nc;
					for(int d=0; d<4; d++) {
						
						nr = curr.r+dr[d];
						nc = curr.c+dc[d];
						
						if(nr<0 || nr>=N || nc<0 || nc>=M || v[nr][nc]) continue;
						
						//맵의 테두리를 만나면 공기임
						if((nr==0 || nr==N-1 || nc==0 || nc==M-1) && !isAir) isAir = true;
						
						v[nr][nc] = true;
						//치즈를 만나면 기억해두기
						if(board[nr][nc]==1) cheese.add(new Pos(nr,nc));						
						else Q.add(new Pos(nr,nc));
					}
				}
				//치즈구멍이 아니라 공기였으면 만난 치즈들 녹여버리기
				if(isAir) {
					for(Pos pos : cheese) {
						board[pos.r][pos.c] = 0;
					}
				}
			}
		}
	}
	
	//치즈 조각의 수를 세서 반환
	static int cntCheese() {
		int cnt=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) if(board[i][j]==1) cnt++;
		}
		return cnt;
	}
}
