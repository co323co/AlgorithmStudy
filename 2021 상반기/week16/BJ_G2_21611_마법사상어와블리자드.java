package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G2_21611_마법사상어와블리자드 {

	static int ans[] = new int[4]; 
	static int M, N, map[][];
	static int sr, sc;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		sr = sc = N/2;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		move();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			if(d==1) d = 3;
			else if(d==2) d = 1;
			else if(d==3) d = 0;
			else d = 2;
			int s = Integer.parseInt(st.nextToken());
			
			blizzard(s,d);	//마법
			Boom();			//남은 구슬 폭발
			change();		//구슬 변화
		}
		System.out.println(ans[1] + ans[2]*2 + ans[3]*3);
	}
	
	private static void change() {
		Queue<Integer> q = new LinkedList<>();
		//채우기
		int nr = sr, nc = sc, d = 0, step = 0;
		int cnt = 0;				//연속한 구슬 개수
		int num = map[sr][sc-1];	//연속한 구슬 번호
		L : while(true) {
			if(d%2==0) step++; //좌,우일 때만 step을 한칸 늘린다
			for(int i=0; i<step; i++) {
				nr += dr[d];
				nc += dc[d];
				//처리
				if(map[nr][nc]==num) {
					cnt++;
					map[nr][nc] = 0;
				} else {
					q.add(cnt);
					q.add(num);
					cnt = 1;
					num = map[nr][nc];
					map[nr][nc] = 0;
				}
				//탈출 조건, 맵 한바퀴 다 돔
				if(nr==0 && nc==0) break L;
			}
			d=(d+1)%4;
		}
		//다시 담기
		nr = nc = sc; d = 0; step = 0;
		L : while(!q.isEmpty()) {
			if(d%2==0) step++; //좌,우일 때만 step을 한칸 늘린다
			for(int i=0; i<step; i++) {
				nr += dr[d];
				nc += dc[d];
				//처리
				if(!q.isEmpty()) map[nr][nc]=q.poll();
				//탈출 조건, 맵 한바퀴 다 돔
				if(nr==0 && nc==0) break L;
			}
			d=(d+1)%4;
		}
		move();
	}

	//좌 하 우 상
	static int[] dr = {0,1,0,-1};
	static int[] dc = {-1,0,1,0};
	private static void blizzard(int s, int d) {
		//얼음 파편 날리기
		for(int i=1; i<=s; i++) {
			int nr = sr + (dr[d]*i);
			int nc = sc + (dc[d]*i);
			map[nr][nc] = 0;
		}
		move();	
	}

	//구슬 정렬하기
	private static void move() {
		Queue<Integer> q = new LinkedList<>();
		//채우기
		int nr = sr, nc = sc, d = 0, step = 0;
		L : while(true) {
			if(d%2==0) step++; //좌,우일 때만 step을 한칸 늘린다
			for(int i=0; i<step; i++) {
				nr += dr[d];
				nc += dc[d];
				//처리
				if(map[nr][nc]!=0) {
					q.add(map[nr][nc]);
					map[nr][nc] = 0;
				}
				//탈출 조건, 맵 한바퀴 다 돔
				if(nr==0 && nc==0) break L;
			}
			d=(d+1)%4;
		}
		//다시 담기
		nr = nc = sc; d = 0; step = 0;
		L : while(!q.isEmpty()) {
			if(d%2==0) step++; //좌,우일 때만 step을 한칸 늘린다
			for(int i=0; i<step; i++) {
				nr += dr[d];
				nc += dc[d];
				//처리
				if(!q.isEmpty()) map[nr][nc]=q.poll();
				//탈출 조건, 맵 한바퀴 다 돔
				if(nr==0 && nc==0) break L;
			}
			d=(d+1)%4;
		}
	}


	private static void Boom() {
		boolean hasBoom = false;
		//좌 방향부터
		int nr = sr, nc = sc, d = 0, step = 0;
		int cnt = 0;				//연속한 같은 구슬 개수
		int num = map[sr][sc-1];	//연속한 구슬 번호
		ArrayList<int[]> pos = new ArrayList<>(); 
		L : while(true) {
			if(d%2==0) step++; //좌,우일 때만 step을 한칸 늘린다
			for(int i=0; i<step; i++) {
				nr += dr[d];
				nc += dc[d];
				//처리
				if(map[nr][nc]==0) break L; //구슬배열이 끝남
				else if(map[nr][nc]==num) {	//구슬 번호가 연속하면
					cnt++;
					pos.add(new int[] {nr,nc});
				}
				else {	//구슬 번호가 다르면
					if(cnt>=4) {	//여태까지 연속한 개수가 4개 이상이면
						if(!hasBoom)hasBoom = true;
						for(int[] p : pos) {
							ans[map[p[0]][p[1]]]++;	//해당 번호의 폭발한 구슬 개수 증가
							map[p[0]][p[1]] = 0;	//구슬 폭발함
						}
					}
					//연속하는 구슬 관리 초기화
					pos.clear();
					cnt = 1;
					num = map[nr][nc];
					pos.add(new int[] {nr,nc});
				}
				//탈출 조건, 맵 한바퀴 다 돔
				if(nr==0 && nc==0) break L;
			}
			d=(d+1)%4;
		}
		move();
		if(hasBoom) Boom();
	}

	private static void print(int[][] map) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.printf("%3d",map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
