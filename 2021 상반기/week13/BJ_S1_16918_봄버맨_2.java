package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 	
 * 	0	1	2	3	4	5	...
 *	설치	X	설치	폭파	설치	폭파	...
 * 
 * ex)	5초에 터트릴 폭탄은 2초에 설치된 폭탄
 * 		2초에 설치된 폭탄은 3초에 터지지 않으니, 4초 설치 전에 존재하는 폭탄들임.
 * 		즉 5초에 터트릴 폭탄은 4초 때 설치'전'에 존재하던 폭탄들임
 */
public class BJ_S1_16918_봄버맨_2 {

	static int R, C, N;
	static boolean map[][];
	public static void main(String[] args) throws IOException {
		
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new boolean[R][C]; //남은 폭탄의 시간 저장 (0이면 폭탄 없음)
		for(int i=0; i<R; i++) {
			String line = br.readLine();
			for(int j=0; j<C; j++) {
				if(line.charAt(j)=='O') map[i][j] = true;
			}
		}

		//solve
		if(N%2==0) {
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					System.out.print('O');
				}
				System.out.println();
			} 
			return;
		}
		
		ArrayList<int[]> willBoom = new ArrayList<>();
		for(int t=1; t<=N; t++) {
			
			if(t%2==0) {	//설치
				willBoom.clear();
				for(int i=0; i<R; i++) {
					for(int j=0; j<C; j++) {
						if(map[i][j]) willBoom.add(new int[] {i,j});
						else map[i][j] = true;
					}
				}
			} else {	//폭파
				for(int[] pos : willBoom) {
					map[pos[0]][pos[1]] = false;
					for (int d = 0; d < 4; d++) { //인접한 공간도 터트림
						int nr = pos[0] + dr[d];
						int nc = pos[1] + dc[d];
						if (nr >= R || nr < 0 || nc >= C || nc < 0) continue;
						map[nr][nc] = false;
					}
				}
			}
		}
		print();
	}

	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	
	
	static void print() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(map[i][j]?'O':'.');
			}
			System.out.println();
		}
		System.out.println();
	}
}
