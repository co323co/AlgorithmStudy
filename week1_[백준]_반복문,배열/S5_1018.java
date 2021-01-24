package week1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

//백준 실버5 1018 체스판 다시 칠하기
public class S5_1018 {

	static char board[][];
	
	public static void main(String[] args) throws FileNotFoundException {
		
//		System.setIn(new FileInputStream("week1/S5_1018_1.txt"));
		System.setIn(new FileInputStream("week1/S5_1018_2.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		int M = sc.nextInt();
		int N = sc.nextInt();
		
		String input[] = new String[M];
		board = new char[M][N];
		
		for(int i=0; i<M; i++) input[i]=sc.next();
		for(int i=0; i<M; i++) for(int j=0; j<N; j++) board[i][j] = input[i].charAt(j);
		
		int ans=64;
		
		//앞에서부터 보드 전체를 돌며 체크
		for(int i=0; i+8<=M; i++) for(int j=0; j+8<=N; j++) ans = Math.min(ans, solve(i,j));	
		System.out.println(ans);
		
		sc.close();
	}
	
	static int solve(int r, int c) {
		
		//맨왼쪽위에가 흰색으로 시작한다고 했을 때 바꿔줘야할 칸 수
		int cntWB=0;
		//맨왼쪽위에가 검은색으로 시작한다고 했을 때 바꿔야할 칸 수
		int cntBW=0;
	
		for(int i=r; i<r+8; i++) {	
			for(int j=c; j<c+8; j++) {
				
				//짝수행일 때
				if(i%2==0) {
								
					//짝수열일 때
					if(j%2==0) {						
						//W로 시작할 때
						if(board[i][j]!='W') cntWB++;
						//B로 시작할 때
						if(board[i][j]!='B') cntBW++;
						
					}
					
					//홀수열일 때
					else {					
						//W로 시작할 때
						if(board[i][j]!='B') cntWB++;
						//B로 시작할 때
						if(board[i][j]!='W') cntBW++;															
					}
													
				}
				
				//홀수행일 때
				else {
					
					//짝수열일 때
					if(j%2==0) {						
						//W로 시작할 때
						if(board[i][j]!='B') cntWB++;
						//B로 시작할 때
						if(board[i][j]!='W') cntBW++;
						
					}
					
					//홀수열일 때
					else {
					
						//W로 시작할 때
						if(board[i][j]!='W') cntWB++;
						//B로 시작할 때
						if(board[i][j]!='B') cntBW++;															
					}					
				}								
				
			}			
			
		}
		
		return Math.min(cntWB, cntBW);				
	}

}
