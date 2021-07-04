package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_S1_16918_봄버맨 {

	static int R, C, N, map[][];
	public static void main(String[] args) throws IOException {
		
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[R][C]; //남은 폭탄의 시간 저장 (0이면 폭탄 없음)
		for(int i=0; i<R; i++) {
			String line = br.readLine();
			for(int j=0; j<C; j++) {
				if(line.charAt(j)=='O') map[i][j] = 2;
			}
		}

		//solve
		if(N%2==0) {
			setBomb();
			print();
			return;
		}
		
		for(int t=2; t<=N; t++) {
			
			nextTime();
			if(t%2==0) setBomb();
//			System.out.println(t+"초");
//			print();
		}
		print();
	}

	//빈 공간에 폭탄 설치
	static void setBomb() {
			
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]==0) map[i][j]=3;
			}
		}
		
	}
	
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	
	//시간이 1초 흐름
	static void nextTime() {
	
		ArrayList<int[]> willBoom = new ArrayList<>();
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {

				if (map[i][j] == 0)	continue;
				else if (map[i][j] == 1) { // 1초 남았던 폭탄은 이제 터져야 함
					willBoom.add(new int[] {i,j}); //터질 예약
					for (int d = 0; d < 4; d++) { //인접한 공간도 터트림
						int nr = i + dr[d];
						int nc = j + dc[d];
						if (nr >= R || nr < 0 || nc >= C || nc < 0) continue;
						willBoom.add(new int[] {nr,nc});
					}
				} else {
					map[i][j]--;
				}
			}
		}
		for(int[] pos : willBoom) {
			map[pos[0]][pos[1]] = 0;
		}
	}
	
	static void print() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(map[i][j]==0?'.':'O');
//				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
