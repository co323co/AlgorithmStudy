package week06_IM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S2_2072_오목 {

	static int[][] board;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		board = new int[19+2][19+2]; //경계선 체크를 안하려고 일부러 위아래로 한칸씩 (총 두 칸)크게 만듦
		//돌 놓기
		StringTokenizer st;
		for(int n=1; n<=N; n++) {
			st= new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			//홀수번째는 흑
			if(n%2==1) board[r][c]=1;
			//짝수번째는 백
			else board[r][c]=-1;
			//승패가 갈리면 끝
			if(check(r,c)) {
				System.out.println(n);
				return;
			}
//			if(n==9) {
//				for(int i=0; i<21; i++) {
//					for(int j=0; j<21;j++) {
//						System.out.print(board[i][j]);
//					}
//					System.out.println();
//				}
//			}
		}
		//돌을 다 뒀는데도 승패가 갈리지 않았으면 -1 출력
		System.out.println(-1);
		
	}
	
	// 오른쪽으로 일자, 아래방향으로 일자, 좌상대각선, 우상대각선, 4방
	static int[] dr = {0, 1, -1, -1};
	static int[] dc = {1, 0,-1, 1};
	static boolean check(int r, int c) {
		boolean res = false;
		
		for(int d=0; d<4; d++) {
			//바둑돌의 시작점으로 가기
			int sr = r-dr[d];
			int sc = c-dc[d];
			while(board[sr][sc]!=0 && board[sr][sc]==board[r][c]){
				sr-=dr[d];
				sc-=dc[d];
			}
			//시작점부터 같은 돌의 갯수 세기
			int nr = sr+dr[d];
			int nc = sc+dc[d];
			int cnt=0;
			while(board[nr][nc]==board[r][c]){
				cnt++;
				nr+=dr[d];
				nc+=dc[d];
			}
			if(cnt==5) return true;
		}
		return res;
	}
}
