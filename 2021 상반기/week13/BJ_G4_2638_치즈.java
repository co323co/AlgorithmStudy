package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
 * 
 * +2636 치즈에 비해 추가된 점 :
 * 		두 면이상 공기랑 맞닿은 치즈만 사라져야함
 * 		=> 치즈가 공기랑 맞닿은 면을 세야 함
 * 		=> 공기 입장에서 bfs돌리니 정확히는 한 치즈를 공기가 어느 방향에서 방문했지를 세야 함 
 * 		
 * 		방문한 공기는 다시 방문하면 안되고, 치즈는 여러 공기에서 계속 방문할 수 있어야 함
 * 		=> 치즈의 방문배열과 공기의 방문 배열을 따로 만들자!
 * 
 */
//백준 골드4 2638 치즈
public class BJ_G4_2638_치즈 {

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
		while(true) {
			time++;
			bfs();
			if(cntCheese()==0) {
				System.out.println(time);
				return;
			}
		}
	}

	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static void bfs() {
	
		boolean[][] airV = new boolean[N][M];
		boolean[][][] cheeseV = new boolean[N][M][4];	//어느 방향으로 만났는지
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				
				if(board[i][j]==1 || airV[i][j]) continue;
				LinkedList<Pos> cheese = new LinkedList<>(); //만약 공기면 녹이게 만난 치즈들 저장해둠
				Queue<Pos> Q = new LinkedList<>();
				airV[i][j] = true;
				cheese.add(new Pos(i,j));
				Q.add(cheese.get(cheese.size()-1));

				boolean isAir = false;
				while(!Q.isEmpty()) {
					Pos curr = Q.poll();
					int nr, nc;
					for(int d=0; d<4; d++) {
						nr = curr.r+dr[d];
						nc = curr.c+dc[d];
						if(nr<0 || nr>=N || nc<0 || nc>=M || airV[nr][nc]) continue;
						
						//맵의 테두리를 만나면 공기임
						if((nr==0 || nr==N-1 || nc==0 || nc==M-1) && !isAir) isAir = true;
						
						//치즈를 만나면 기억해두기
						if(board[nr][nc]==1 && !cheeseV[nr][nc][d]) {
							cheeseV[nr][nc][d] = true;
							int cnt=0;  //현재 치즈에서 공기와 맞닿는 치즈 면의 수
							for(int dd=0; dd<4; dd++) 
								if(cheeseV[nr][nc][dd]) cnt++;
							if(cnt==2) cheese.add(new Pos(nr,nc));
						}
						else {
							airV[nr][nc] = true;
							Q.add(new Pos(nr,nc));
						}
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
