package week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_6987_월드컵 {
	
	static class Fight{
		int team1, team2;
		public Fight(int team1, int team2) {
			this.team1 = team1;
			this.team2 = team2;
		}
		@Override
		public String toString() {
			return "[" + team1 + ", " + team2 + "]";
		}
		
	}

	static int[][] board;
	//붙을 수 있는 대전의 경우, ex) team1[0]과 team2[0]이 서로 경기한단 뜻
	static Fight[] fights;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		int T=4;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int tc=1; tc<=T; tc++) {
			//입력
			board = new int[6][3];
			fights = new Fight[15]; //5+4+3+2+1 총 15번 경기
			ans = 0;
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<board.length; i++) {
				for(int j=0; j<board[i].length; j++) 
					board[i][j] = Integer.parseInt(st.nextToken());
			}
			
			//붙을 수 있는 팀의 경우의 수 구하기
			int idx=0;
			for(int i=0; i<board.length; i++) {
				for(int j=i+1; j<board.length; j++) {
					fights[idx++] = new Fight(i, j);
				}
			}
			dfs(0);
			System.out.print(ans+" ");
		}
	}

	static void dfs(int idx) {

		//경기를 끝까지 다 할 수 있었던 경우
		if(idx==15) {
			//표가 전부 0이 아니라면 승패 수가 서로 안맞는거임
			for(int i=0; i<board.length; i++) {
				for(int j=0; j<board[i].length; j++) {
					if(board[i][j]!=0) return;
				}
			}
			ans = 1;
			return;
		}
		
		int team1 = fights[idx].team1;
		int team2 = fights[idx].team2;
		
		//team1이 이게는게 가능하면 다음 경기로 //백트래킹
		if(board[team1][0] > 0 && board[team2][2] > 0) {

			//team1이 이김
			board[team1][0]--; //team1의 승리 -1
			board[team2][2]--; //team2의 패배 -1
			
			dfs(idx+1);
			
			//원래대로 되돌리기
			board[team1][0]++;
			board[team2][2]++;
		}

		///team2가 이기는게 가능하면 다음 경기로 //백트래킹
		if(board[team2][0] > 0 && board[team1][2] > 0) {
		
			board[team2][0]--; //team1의 승리 -1
			board[team1][2]--; //team2의 패배 -1
			
			dfs(idx+1);

			//원래대로 되돌리기
			board[team2][0]++;
			board[team1][2]++;
		}
		
		//무승부가 가능하면 다음 경기로 //백트래킹
		if(board[team1][1] > 0 && board[team2][1] > 0) {
			//무승부
			board[team1][1]--; //team1의 승리 -1
			board[team2][1]--; //team2의 패배 -1

			dfs(idx+1);

			//원래대로 되돌리기
			board[team1][1]++;
			board[team2][1]++;
		}
	}
}

