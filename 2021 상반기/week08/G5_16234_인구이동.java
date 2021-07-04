package week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//백준 골드 5 16234 인구이동
public class G5_16234_인구이동 {

	static int N, L, R;
	static int[][] map;
	static boolean[][] union; //연합인지 체크하는 배열
	public static void main(String[] args) throws NumberFormatException, IOException {

		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		union = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int ans = 0;
		while(true) {
			boolean flag=false;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					//인근에 맺을 수 있는 연합이 하나라도 있을 때만 bfs를 시행한다(가지치기)
					if(!union[i][j] && hasUnion(i, j)) {
						//반복문 중 이동이 한번이라도 있었다면 flag는 true가 된다.
						if(bfs(i,j)) flag=true;//i,j위치의 연합들 처리
					}
				}
			}
			//연합체크 초기화
			for(int i=0; i<N; i++) Arrays.fill(union[i], false);

			// //flag가 flase라는 건 오늘 이동이 한번도 없었다는 뜻. 이제 국경선은 그만 연다.
			if(!flag) break;
			
			//국경선 하루 더 열기
			ans++;
		}
		System.out.println(ans);
		
	}
	
	//i,j의 상하좌우에 맺을 수 있는 연합이 하나라도 있는지 검사한다.
	static boolean hasUnion(int i, int j) {
		boolean flag = false;
		int[] next = new int[2];
		for(int d=0; d<4; d++) {
			next[0] = i+dr[d];
			next[1] = j+dc[d];
			// 범위체크, 방문체크
			if(next[0] < 0 || next[0] >= N || next[1] < 0 || next[1] >= N || union[next[0]][next[1]]) continue; 
			boolean canOpen = Math.abs(map[i][j] - map[next[0]][next[1]]) >= L 
					&& Math.abs(map[i][j] - map[next[0]][next[1]]) <= R;
			if(canOpen) flag=true;
		}
		//상하좌우 인근 연합이 하나라도 있으면 flag는 true
		return flag; 
	}
		
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	//r,c나라의 연합(인접하고 인구차가 L이상 R이하)을 구하고 값들을 연합의 평균으로 바꿔줌
	static boolean bfs(int r, int c) {
		
		List<int[]> uni = new ArrayList<int[]>();
		int sum = 0;
		
		Queue<int[]> q = new LinkedList<int[]>();
		int[] curr, next;
		q.add(new int[] {r,c});
		union[r][c] = true;
		uni.add(new int[] {r,c});
		sum+=map[r][c];
		
		while(!q.isEmpty()) {
			curr = q.poll();
			for(int d=0; d<4; d++) {
				next = new int[] { curr[0]+dr[d], curr[1]+dc[d]};
				// 범위체크, 방문체크
				if(next[0] < 0 || next[0] >= N || next[1] < 0 || next[1] >= N || union[next[0]][next[1]]) continue; 
				boolean canOpen = Math.abs(map[curr[0]][curr[1]] - map[next[0]][next[1]]) >= L 
						&& Math.abs(map[curr[0]][curr[1]] - map[next[0]][next[1]]) <= R;
				/*
				 * next를 그대로 넣어버리니 주솟값 참조때매 q에 들어있는 값이 바뀌는 것 조심...!!
				 * 꼭 새로 만들어줘야 함!!
				 */
				if(canOpen) {
					q.add(new int[] {next[0], next[1]}); 
					union[next[0]][next[1]] = true;
					uni.add(new int[] {next[0], next[1]});
					sum+=map[next[0]][next[1]];
				}
			}
		}
		
		int avg = sum/uni.size();
		for(int[] pos : uni) map[pos[0]][pos[1]]=avg;
		if(uni.size()<2) return false;
		return true;
	}
}
