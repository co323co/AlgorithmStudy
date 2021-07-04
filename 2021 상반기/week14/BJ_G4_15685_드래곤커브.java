package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_G4_15685_드래곤커브 {

	//→ ↑ ← ↓
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {1, 0, -1, 0};
	
	static final int SIZE = 101;
	static boolean[][] map = new boolean[SIZE][SIZE];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		//현재 드래곤커브의 좌표
		for(int i=0; i<N; i++) {
			// 시작점부터 어떤 방향으로 전진해왔는지로 표현한 드래곤커브, 방향배열
			ArrayList<Integer> dragonCurve = new ArrayList<>(); 	
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			curve(dragonCurve, r, c, d, g);
		}
		
		int result = 0;
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				if(hasFour(i,j)) result++;
			}
		}
		System.out.println(result);
	}
	private static void curve(ArrayList<Integer> DCurve, int sr, int sc, int d, int g) {

		if(!map[sr][sc]) map[sr][sc]=true;
		
		// 0세대 드래곤 커브
		int nr=sr+dr[d], nc=sc+dc[d];
		DCurve.add(d);	
		if(!map[nr][nc]) map[nr][nc]=true;	//점찍기
		
		// i번세대 드래곤 커브
		for(int i=1; i<=g; i++) {	
			ArrayList<Integer> newCurve = new ArrayList<>();
			// < N세대 드래곤커브 방향수열 >은  < N-1세대 드래곤커브를 뒤집은 후 방향을 +1>한 수열
			for(int j=DCurve.size()-1; j>=0; j--) { //뒤집
				int nd = (DCurve.get(j) + 1)%4;	//방향+1
				newCurve.add(nd);
				nr += dr[nd];
				nc += dc[nd];
				if(!map[nr][nc]) map[nr][nc]=true;	//점찍기
			}
			DCurve.addAll(newCurve);
		}
	}

	private static boolean hasFour(int r, int c) {
		if(r+1 >= SIZE || c+1 >= SIZE) return false;
		if(map[r][c] && map[r][c+1] && map[r+1][c] && map[r+1][c+1]) return true;
		return false;
	}
}
