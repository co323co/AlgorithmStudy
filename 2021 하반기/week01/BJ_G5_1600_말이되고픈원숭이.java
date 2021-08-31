package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G5_1600_말이되고픈원숭이 {

	static int[][] horse = {
			{0,1,0,1,0},
			{1,0,0,0,1},
			{0,0,0,0,0},
			{1,0,0,0,1},
			{0,1,0,1,0}
	};
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int K,R,C, map[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(bfs());
	}

	static class Point{
		int r, c, k, cnt;
		public Point(int r, int c, int k, int cnt) {
			this.r = r;
			this.c = c;
			this.k = k;
			this.cnt = cnt;
		}
	}

	static int bfs() {
		
		Queue<Point> Q = new LinkedList<>();
		boolean[][][] v = new boolean[R][C][K+1]; //이전에 먼저왔어도 말을 다써서 온거면 꼭 유리한건 아님,[][][남은 말 움직임 수]

		Q.add(new Point(0,0,K,0));
		v[0][0][K] = true;
		
		while(!Q.isEmpty()) {
			
			Point curr = Q.poll();
//			System.out.println(curr.r+","+curr.c+","+curr.k+","+curr.cnt);
			if(curr.r==R-1 && curr.c==C-1) return curr.cnt;
			//말의 이동
			if(curr.k>0) {
				for(int i=-2; i<=2; i++) {
					for(int j=-2; j<=2; j++) {
						if(horse[i+2][j+2]==0) continue;
						Point next = new Point(curr.r+i, curr.c+j, curr.k-1, curr.cnt+1);
						if(next.r<0 || next.r >= R || next.c<0 || next.c>=C || map[next.r][next.c]==1 || v[next.r][next.c][next.k]) continue;
						Q.add(next);
						v[next.r][next.c][next.k] = true;
					}
				}
			}
			//그냥 이동
			for(int d=0; d<4; d++) {
				Point next = new Point(curr.r+dr[d], curr.c+dc[d], curr.k, curr.cnt+1);
				if(next.r<0 || next.r >= R || next.c<0 || next.c>=C || map[next.r][next.c]==1 || v[next.r][next.c][next.k]) continue;
				Q.add(next);
				v[next.r][next.c][next.k] = true;
			}
			
		}
		return -1;
	}
}
