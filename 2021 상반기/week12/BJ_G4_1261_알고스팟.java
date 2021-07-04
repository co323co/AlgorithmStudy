package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//백준 골드4 1261 알고스팟
public class BJ_G4_1261_알고스팟 {

	static int N, M, map[][];
	public static void main(String[] args) throws IOException {

		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<M; j++) map[i][j] = line.charAt(j)-'0';
		}
		
		//solve
		System.out.println(solve());
		
		
	}

	static class Pos{
		int r,c,cnt;

		public Pos(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
		
	}
	static int solve() {
		
		int result = Integer.MAX_VALUE;
		
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,-1,1};
		
		int dist[][] = new int[N][M];
		for(int i=0; i<N; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
		
		Queue<Pos> Q = new LinkedList<>();
		Q.add(new Pos(0, 0, 0));
		dist[0][0] = 0;
		
		while(!Q.isEmpty()) {
			
			Pos curr = Q.poll();
			
			if(curr.r == N-1 && curr.c == M-1) {
				result = Math.min(result, curr.cnt);
			}
			
			for(int d=0; d<4; d++) {
				int nr = curr.r+dr[d];
				int nc = curr.c+dc[d];
				if(nr>=N || nr<0 || nc>=M || nc<0) continue;
				Pos next;
				if(map[nr][nc]==1) next = new Pos(nr, nc, curr.cnt+1);
				else next = new Pos(nr, nc, curr.cnt);
				if(next.cnt < dist[nr][nc]) {
					dist[nr][nc] = next.cnt;
					Q.add(next);
				}
			}
			
			
		}
		if(result==Integer.MAX_VALUE) return 0;
		return result;
	}
}
