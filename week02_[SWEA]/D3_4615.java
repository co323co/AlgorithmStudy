package week2;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Stack;

//SWEA D3 4615. 재미있는 오셀로 게임
public class D3_4615 {

	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("week2/D3_4615.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			char[][] board = new char[N][N];
			
			//초기 돌 배치
			board[N/2-1][N/2-1] ='W';
			board[N/2-1][N/2] ='B';
			board[N/2][N/2-1] ='B';
			board[N/2][N/2] ='W';
			

			for(int m=0; m<M; m++) {
				
				int c=sc.nextInt()-1;
				int r=sc.nextInt()-1;
				
				char myDoll = (sc.nextInt()==1)?'B':'W';
				
				board[r][c]=myDoll;

				//상 하 좌 우 좌상 좌하 우상 우하
				int dr[] = {-1,1,0,0,-1,1,-1,1};
				int dc[] = {0,0,-1,1,-1,-1,1,1};

				//방금 둔 돌을 체크해서 먹은 돌을 뒤집는다.
				for(int i=0; i<8; i++) {
					
					//뒤집을 애들 저장
					Stack<Pair> s = new Stack<Pair>();
					int nr = r;
					int nc = c;
					
					while(true) {
					
						nr = nr+dr[i];
						nc = nc+dc[i];
						
						//범위 벗어남
						if(nr>=N || nc>=N || nr<0 || nc<0) break;
						if(board[nr][nc]!='B' && board[nr][nc]!='W') break;
						//같은 색 돌이 나옴
						if(board[nr][nc]==myDoll) {
							//내 돌색이 두번나옴. 변하는게 없음
							if(board[nr-dr[i]][nc-dc[i]]==myDoll) break;
							//상대편 돌이 나오다가 내 돌이 나옴 -> 뒤집어줌
							else {
								while(!s.empty()) {
									Pair p =s.pop();
									board[p.r][p.c] = myDoll;
								}				
								break;
							}
						}
						//상대편 돌이 나옴. 내 돌이 나올 때까지 계속 체크해가야함
						else s.add(new Pair(nr,nc));
																						
					}				
					
				}
									
//				
//				System.out.println("[처리후]");
//				for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) System.out.print(board[i][j]+" ");
//				System.out.println();
//				}
				
			}
			
			int cntB=0;
			int cntW=0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(board[i][j]=='B') cntB++;
					if(board[i][j]=='W') cntW++;
				}
			}
			System.out.println("#"+tc+" "+cntB+" "+cntW);
		}
		
		sc.close();
	}

}

class Pair {
	int r, c;
	Pair(int r, int c){
		this.r=r; this.c=c;
	}
}
