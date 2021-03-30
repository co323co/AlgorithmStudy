package week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//백준 골드5 11599 Puyo Puyo 
public class BJ_G5_11599_PuyoPuyo {

	static char[][] board;
	static int R = 12, C = 6;
	static int ans;
	public static void main(String[] args) throws IOException {

		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new char[R][C];
		
		for(int i=0; i<R; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		dfs(0);
		System.out.println(ans);
	
	}
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	//bfs
	//r,c의 뿌요와 같은 뿌요가 상하좌우로 4개 이상 연결되어있으면 터뜨림
	//뿌요를 터뜨리지 못하면 false 반환
	static boolean bomb(int r, int c) {
		
		List<int[]> puyoList = new ArrayList<int[]>();
		
		//r,c와 인접한 뿌요 중 같은 모양의 뿌요를 찾음
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {r,c}); //현재 위치
		char puyo = board[r][c];
		int[] curr, next;

		while(!q.isEmpty()) {
			curr = q.poll();
			puyoList.add(curr);
			board[curr[0]][curr[1]] = '.';
			for(int d=0; d<4; d++) {
				next = new int[] {curr[0]+dr[d], curr[1]+dc[d] };
				if(next[0] < 0 || next[0] >= R || next[1] < 0 || next[1] >= C || board[next[0]][next[1]] != puyo) continue;
				q.add(next);
			}
		}
		
		//4개 이상이 아니면 원래대로 돌려놓음
		if(puyoList.size() < 4) {
			for(int[] pos : puyoList) {
				board[pos[0]][pos[1]] = puyo;
			}
			return false;
		}
		return true;
	}
	
	//중력을 줘서 뿌요를 아래로 내린다
	static void setGravity() {
		//각 열마다
		for(int j=0; j<C; j++) {
			//맨 아랫줄에서부터 검사
			for(int i=R-1; i>0; i--) {
				//현재 위치가 .인 경우
				if(board[i][j]=='.') {
					int next=i;
					//뿌요를 만날때까지 위로 올라간다
					while(board[--next][j]=='.') {
						if(next-1 < 0) break; //맨 위까지 다 올라갔는데도 뿌요를 못만났으면 break
					}
					//뿌요를 만나면 .과 뿌요의 위치를 바꾼다
					char tmp = board[next][j];
					board[next][j] = board[i][j];
					board[i][j]= tmp;
				}
			}
		}
	}
	
	//cnt : 연쇄 횟수
	static void dfs(int cnt) {
		
		boolean flag = false;
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(board[i][j]!='.') {
					if(bomb(i,j)) flag = true; //한번이라도 터뜨리면 true
				}
			}
		}
		setGravity(); //뿌요 터뜨렸으니, 중력 줘서 빈공간 없애줌
		//한번도 못터뜨렸으면 이제 그만
		if(!flag) {
			ans = cnt;
			return;
		}
		dfs(cnt+1);
	}
}
