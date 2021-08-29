package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//백준 골드5 14502 연구소
public class BJ_G5_14502_연구소 {

	static int N, M, map[][], ans;
	static List<int[]> virus = new ArrayList<>();
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) virus.add(new int[] {i,j});
			}
		}
		makeWall();
		System.out.println(ans);
	}

	static void makeWall() {

		for(int i=0; i<N*M; i++) {
			int r1 = i/M;
			int c1 = i%M;
			if(map[r1][c1]!=0) continue;
			map[r1][c1] = 1;
			for(int j=i+1; j<N*M; j++) {
				int r2 = j/M;
				int c2 = j%M;
				if(map[r2][c2]!=0) continue;
				map[r2][c2]=1;
				for(int k=j+1; k<N*M; k++) {
					int r3 = k/M;
					int c3 = k%M;
					if(map[r3][c3]!=0) continue;
					map[r3][c3] = 1;
					ans = Math.max(ans, getSafeArea());
					map[r3][c3] = 0;
				}
				map[r2][c2] = 0;
			}
			map[r1][c1] = 0;
		}
	}
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	private static int getSafeArea() {
		int[][] tmp = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) tmp[i][j] = map[i][j];
		}
		
		Queue<int[]> Q = new LinkedList<int[]>();
		
		for(int[] pos : virus) {
			Q.add(new int[] {pos[0],pos[1]});
		}
		
		while(!Q.isEmpty()) {
			int[] curr = Q.poll();
			for(int d=0; d<4; d++) {
				int nr = curr[0]+dr[d];
				int nc = curr[1]+dc[d];
				if(nr < 0 || nr >= N || nc<0 || nc>= M ) continue;
				if(tmp[nr][nc]==0) {
					Q.add(new int[] {nr,nc});
					tmp[nr][nc] = 2;
				}
			}
		}
		
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(tmp[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	static void printMap(int[][] map) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
