package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//백준 골드5 16973 직사각형 탈출
public class BJ_G5_16973_직사각형탈출 {

	static class Pos{
		int r, c, d;
		public Pos(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	static int R,C,H,W;
	static Pos start, end;
	static int[][] board;
	public static void main(String[] args) throws IOException {
		
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new int[R][C];
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		start = new Pos(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1,0);
		end = new Pos(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1,0);

		//solve
		System.out.println(bfs());
	}
	
	static int bfs() {
		
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,-1,1};
		
		Queue<Pos> Q = new LinkedList<>();
		boolean[][] v = new boolean[R][C];
		
		Q.add(start);
		v[start.r][start.c]= true; 
			
		while(!Q.isEmpty()) {
			
			Pos curr =  Q.poll();

			//도착지에 도착
			if(curr.r == end.r && curr.c == end.c) {
				return curr.d;
			}
			
			for(int d=0; d<4; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];
				
				if(setCanMove(nr,nc,d) && !v[nr][nc]) {
					Q.add(new Pos(nr, nc, curr.d+1));
					v[nr][nc] = true;
				}
			}
		}		
		
		return -1;
	}
	
	// nr,nc 위치로 d방향을 통해 시작점이 이동됐을 때, 직사각형이 벽에 걸리는지
	static boolean setCanMove(int nr, int nc, int d) {
		
		if( nr < 0 || nr+H-1 >= R || nc < 0 || nc+W-1 >=C) return false; //직사각형이 격자판을 벗어남

		if(d==0) { //nr, nc위치에서 직사각형 안에 벽이 존재하는지를 검사
			for(int j=nc; j < nc + W; j++) { //시작점 계산
				if(board[nr][j]==1) return false; //이동하려는 위치에 벽이 존재함
			}
		}
		else if(d==1) {
			for(int j=nc; j < nc + W; j++) {
				if(board[nr+H-1][j]==1) return false;
			}
		}
		else if(d==2) {
			for(int i=nr; i < nr + H; i++) {
				if(board[i][nc]==1) return false;
			}
		}
		else if(d==3) {
			for(int i=nr; i < nr + H; i++) { 
				if(board[i][nc+W-1]==1) return false;
			}
		}
		return true;
	}
}
