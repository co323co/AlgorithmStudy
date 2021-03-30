package week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//백준 골드5 14502 연구소
public class BJ_G5_14502_연구소 {

	static class Pos{
		int r, c;

		public Pos() {}
		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	
	static int[][] map, tmp;
	static boolean[][] visited;
	static int N, M, ans;

	public static void main(String[] args) throws IOException {
		
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		tmp = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//실행
		buildWall_dfs(0);
		System.out.println(ans);
	}

	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	//r, c위치의 바이러스가 확산됨
	static void spread_bfs(int r, int c) {
		Queue<Pos> Q = new LinkedList<>();
		Q.add(new Pos(r, c));
		while(!Q.isEmpty()) {
			
			Pos curr = Q.poll();
			
			int nr, nc;
			for(int d=0; d<4; d++) {
				nr = curr.r + dr[d];
				nc = curr.c + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if(tmp[nr][nc]==0) {
					tmp[nr][nc] = 2;
					Q.offer(new Pos(nr,nc));
				}
			}
		}
		
	}
	
	static int calSafeArea() {
		
		//배열 복사
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) tmp[i][j]=map[i][j];
		}
		
		//바이러스 퍼트려보기
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) 
				if(tmp[i][j]==2) spread_bfs(i, j);
		}
		
		//안전구역 넓이 구하기
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) 
				if(tmp[i][j]==0) cnt++;
		}
		return cnt;
	}
	
	static void buildWall_dfs(int k) {
		
		if(k==3) {
			ans = Math.max(ans, calSafeArea());
			return;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visited[i][j]) continue;
				if(map[i][j]==0) {
					map[i][j]=1;
					visited[i][j]=true;
					buildWall_dfs(k+1);
					map[i][j]=0;
					visited[i][j]=false;
				}
			}
		}
	}
	
	static void print() {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
