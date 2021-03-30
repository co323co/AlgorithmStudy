package week01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

//백준 실버4 3085 사탕게임
public class BJ_S4_3085_사탕게임 {

	static char[][] board;
	static int N;
	
	public static void main(String[] args) {
		
		try {
			System.setIn(new FileInputStream("week1/s_3085.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		String[] tmp = new String[N];
		for(int i=0; i<N; i++) tmp[i] = sc.next();
		
		board = new char[N][N];
		for(int i=0; i<N; i++) for(int j=0; j<N; j++) board[i][j] = tmp[i].charAt(j);
		
		int ans = 1;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N-1; j++) {
				if(board[i][j]!=board[i][j+1]) {
					//오른쪽이랑 바꿔보기
					swap(i,j,i,j+1);
					ans = Math.max(ans, check());
					//되돌리기
					swap(i,j,i,j+1);	
				}
			}
		}
		
		for(int i=0; i<N-1; i++) {
			for(int j=0; j<N; j++) {
				if(board[i][j]!=board[i+1][j]) {
					//아래쪽이랑 바꿔보기
					swap(i,j,i+1,j);
					ans = Math.max(ans, check());
					//되돌리기
					swap(i,j,i+1,j);					
				}	
			}
		}
		
		System.out.println(ans);
		sc.close();
	}

	static int check() {
		
		int max = 1;
		int cnt;
		
		
		//행 검사
		for(int i=0; i<N; i++) {
			//다음 줄이니까 cnt 초기화~!
			cnt=1;
			char before = board[i][0];
			for(int j=1; j<N; j++) {	
				if(before==board[i][j]) {				
					cnt++;
					max = Math.max(max, cnt);
				}
				else cnt = 1;		
				before=board[i][j];
			}			
		}	
		
		//열 검사
		for(int i=0; i<N; i++) {
			cnt=1;
			char before = board[0][i];
			for(int j=1; j<N; j++) {
				if(before==board[j][i]) {
					cnt++;
					max = Math.max(max, cnt);
				}			
				else cnt = 1;		
				
				before=board[j][i];
			}		
		}
		
		return max;
	}
	

	static void swap(int i, int j, int x, int y) {
		
		char tmp = board[i][j];
		board[i][j] = board[x][y];
		board[x][y] = tmp;
		
	}
}
