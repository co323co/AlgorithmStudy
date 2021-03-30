package week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;

//백준 골드5 3190 뱀
public class BJ_G5_3190_뱀 {

	static class Pos{
		int r, c;
		
		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	static int[][] map;
	static Map<Integer, Character> time_dir;
	static LinkedList<Pos> snake = new LinkedList<>(); //뱀 요소 위치
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		//////////////입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		map = new int[N][N];
		//뱀은 1
		map[0][0] = 1;
		snake.add(new Pos(0,0));
		//사과 2로 표시
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r-1][c-1] = 2;
		}
		//뱀의 시간 별 회전할 방향
		int L = Integer.parseInt(br.readLine());
		time_dir = new HashMap<Integer, Character>();
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			time_dir.put(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
		}
		
		/////////////실행
		//뱀은 0,0에서 오른쪽을 보고 시작
		move(1, snake, 0);
	}
	
	static void print() {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map.length; j++) System.out.print(map[i][j]+" ");
			System.out.println();
		}
	}
	//'우'부터 시작해서 오른쪽으로 90도씩
	static int[] dr = { 0, 1, 0, -1};
	static int[] dc = { 1, 0, -1, 0};
	
	static void move(int time, LinkedList<Pos> snake, int d) {
		int nr = snake.peekFirst().r + dr[d];
		int nc = snake.peekFirst().c + dc[d];
		
		if(nr<0 || nr>=map.length|| nc<0 || nc>=map.length || map[nr][nc]==1) 
		{
			System.out.println(time);
			return;
		}

		//방향 변환 체크해 줌
		if(time_dir.containsKey(time)) {
			if(time_dir.get(time)=='D') d=(d+1)%4;
			else {
				d=(d-1+4)%4;
			}
		}
		
		//사과 안먹었으면 꼬리 빼주기
		if(map[nr][nc]!=2) {
			Pos tail = snake.pollLast();
			map[tail.r][tail.c] = 0;
		}
		
		//앞으로 한칸 전진~
		map[nr][nc] = 1;
		snake.addFirst(new Pos(nr,nc));
		move(time+1, snake, d);
	}
}
