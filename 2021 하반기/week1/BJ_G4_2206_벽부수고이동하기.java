package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//백준 골드4 2206 벽 부수고 이동하기
public class BJ_G4_2206_벽부수고이동하기 {

	static int N, M;
	static char[][] map;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
//		printMap(map);
		
		System.out.println(bfs());
	}

	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static class Point{
		int r, c, dist, flag;
		public Point(int r, int c, int dist, int flag) {
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.flag = flag;
		}
	}
	
	static int bfs() {
		
		Queue<Point> Q = new LinkedList<>();
		boolean[][][] v = new boolean[N][M][2]; //이전에 벽 부순상태/안부순상태로 와봤는지까지 체크
		
		Q.add(new Point(0,0,1,0));
		v[0][0][0] = true;
		
		while(!Q.isEmpty()) {
		
			Point curr = Q.poll();
//			System.out.println(curr.r+","+curr.c+","+curr.dist+","+curr.flag);
			if(curr.r==N-1 && curr.c==M-1) return curr.dist;
			
			for(int d=0; d<4; d++){
				Point next = new Point(curr.r+dr[d], curr.c+dc[d], curr.dist+1, curr.flag);
				if(next.r<0 || next.r>=N || next.c<0 || next.c>=M) continue;
				if(v[next.r][next.c][next.flag]) continue;
				if(map[next.r][next.c]=='0') {
					Q.add(next);
					v[next.r][next.c][next.flag] = true;
				}
				else {
					if(next.flag==0) {
						next.flag = 1;
						Q.add(next);
						v[next.r][next.c][next.flag] = true;
					}
				}
			}
		}
		return -1;
	}
	
	static void printMap(char[][] map) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) System.out.print(map[i][j]);
			System.out.println();
		}
	}
	
}
