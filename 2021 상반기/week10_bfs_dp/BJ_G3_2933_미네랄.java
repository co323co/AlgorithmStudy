package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//백준 골드 3 2933 미네랄
/*
 *  높이, 가장 바닥 = 1 (index R-1) ~ 가장 위 R (index 0)
 * 	순서 	  왼쪽 -> 오른쪽 		/	오른쪽 -> 왼쪽 번갈아감
 * 	막대랑 만난 미네랄 파괴, 남은 클러스터들 중력 하강
 * 
 * bfs
 * 막대에 맞은 애를 기준으로 맞은자신은 .으로 바꾸고 사방으로 bfs를 돌려서
 * 		땅에 닿으면 ㄱㅊ, 땅에 안닿으면 걘 공중에 떠있는 것
 * 		공중에 떠있는 애는 아래로 내려줌, 
 * 		==하단부 애들(자신 아래가 .인 애들)이 땅이나 다른 클러스트를 만날 때까지 아래로 내림
 * 
 * 반복
 *
 * 
 */
public class BJ_G3_2933_미네랄 {

	static class Pos{
		int r,c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static char map[][];
	static int R, C;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int cnt=0; cnt<N; cnt++) {
			
			//막대를 던져서 터트릴 미네랄을 찾는다
			int height = Integer.parseInt(st.nextToken());
			
			Pos pos = null;
			int r = R-height;

			//짝수면 왼쪽->오른쪽으로, 홀수면 오른쪽->왼쪽으로 막대를 던진다
			int step = (cnt%2==0)? 1:-1;
			int i = (cnt%2==0)? 0:C-1;
			while(true) {
				if(i<0 || i>=C) break;
				if(map[r][i]=='x') {
					pos = new Pos(r, i); 
					break;
				}
				i+=step;
			}
			if(pos!=null) pop(pos);
//			System.out.println("["+height+","+step+"]");
//			print();
		}
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) System.out.print(map[i][j]);
			System.out.println();
		}
	}

	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};

	// pos 위치의 미네랄을 터트린다
	private static void pop(Pos pos) {
		
		//미네랄 터트림
		map[pos.r][pos.c] = '.';
		
		//터진 미네랄의 상하좌우에 있는 미네랄은, 이제 쪼개진 클러스터임
		for(int d=0; d<4; d++) {
			int cr = pos.r+dr[d];
			int cc = pos.c+dc[d];
			if(cr<0 || cr>=R || cc<0 || cc>=C || map[cr][cc]=='.') continue;

			Queue<Pos> Q = new LinkedList<>();
			boolean[][] v = new boolean[R][C];
			Q.add(new Pos(cr, cc));
			v[cr][cc] = true;
			
			ArrayList<Pos> cluster = new ArrayList<>();
			boolean land = false;
			while(!Q.isEmpty()) {
				Pos curr = Q.poll();
				cluster.add(new Pos(curr.r, curr.c));
				//땅에 닿음
				if(curr.r == R-1) {
					land = true;
					break;
				}
				for(int k=0; k<4; k++) {
					int nr = curr.r+dr[k];
					int nc = curr.c+dc[k];
					if(nr<0 || nr>=R || nc<0 || nc>=C || map[nr][nc]=='.' || v[nr][nc]) continue;
					Q.add(new Pos(nr,nc));
					v[nr][nc] = true;
				}
			}
			
			//공중부양함
			if(!land) {
				//내려줌
//				System.out.println("부양");
				setGravity(cluster);
				//두 개 이상의 클러스터가 동시에 떨어지는 경우는 없다.
				return;
			}
		}
	}
	
	static void setGravity(ArrayList<Pos> cluster) {
		
		//바닥찾기
		ArrayList<Pos> floor = new ArrayList<>();
		for(Pos c : cluster) {
			if(map[c.r+1][c.c]=='.') floor.add(c);
		}

		//초기화
		for(Pos c : cluster) {
			map[c.r][c.c]='.';
		}
		
		//하나씩 아래로 내림
		while(true) {
			for(Pos c : cluster) {
				c.r++;
			}
			for(Pos f : floor) {
				if(f.r==R-1 || map[f.r+1][f.c]=='x') {
//					System.out.println("내리기 성공");
					for(Pos c : cluster) {
						map[c.r][c.c]='x';
					}
					return;
				}
			}
		}
	
	}

	static void print() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.printf("%-2c", map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
