package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G4_20057_마법사상어와토네이도 {

	static int N, map[][];
	static int ans=0;
	//왼 아 오 위 (토네이도 돌아가는 순서), 모래 뿌려지는 비율
	static int[][][] tornado = {
			{
				{0,0,2,0,0},
				{0,10,7,1,0},
				{5,-1,0,0,0},
				{0,10,7,1,0},
				{0,0,2,0,0}
			},
			{
				{0,0,0,0,0},
				{0,1,0,1,0},
				{2,7,0,7,2},
				{0,10,-1,10,0},
				{0,0,5,0,0}
			},
			{
				{0,0,2,0,0},
				{0,1,7,10,0},
				{0,0,0,-1,5},
				{0,1,7,10,0},
				{0,0,2,0,0}
			},			
			{
				{0,0,5,0,0},
				{0,10,-1,10,0},
				{2,7,0,7,2},
				{0,1,0,1,0},
				{0,0,0,0,0}
			}
			
	};
	public static void main(String[] args) throws NumberFormatException, IOException {

		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//solve
		spin();
		System.out.println(ans);
	}

	static int dr[] = {0,1,0,-1};
	static int dc[] = {-1,0,1,0};
	
	private static void spin() {
		
		int step = 1;
		int d = 0;
		int nr=N/2, nc=N/2;
		while(true) {
			for(int i=0; i<step; i++) {
				nr += dr[d];
				nc += dc[d];
				moveTo(nr, nc, d);
				map[nr][nc] = step;
				if(nr==0 && nc==0) return;
			}
			d=(d+1)%4;
			if(d%2==0) step++;
		}
		
	}
	
	//r, c 위치로 d방향을 통해 이동함 (모래뿌려주는 작업)
	static void moveTo(int r, int c, int d) {
		
		int ar = r+dr[d];
		int ac = c+dc[d];
		
		//a칸으로 보낼 남은 모래
		int leftSand = map[r][c];
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(tornado[d][i][j]==0) continue;
				
				int sr = r+(i-(5/2));
				int sc = c+(j-(5/2));
				
				//이번 칸으로 옮길 모래
				int moveSand = (int) (map[r][c] * tornado[d][i][j] / 100.0);
				if(sr>=0 && sr<N && sc>=0 && sc<N) {
					map[sr][sc] +=  moveSand;
				} else {
					ans += moveSand;
				}
				leftSand -= moveSand;
			}
		}
		if(ar>=0 && ar<N && ac>=0 && ac<N) {
			map[ar][ac] += leftSand;
		} else {
			ans += leftSand;
		}
		map[r][c] = 0;
	}
}
