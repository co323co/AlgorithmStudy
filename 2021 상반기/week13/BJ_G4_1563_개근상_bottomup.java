package week13;

import java.util.Scanner;

/*
 * 
 * DP문제
 * dp[날짜][지각 수][연속결석 수] : 개근상을 받을 수 있는 경우의 수
 * ex) 	dp[a][b][c] : 
 * 		a일날까지 b번 지각했고 c번 연속 결석한 경우의 수
 * 
 * 개근상 X => 지각 2번이상, 결석 세번 연속
 * 지각 2번이상 => L이 2개 이상
 * 결석 세번 연속 => AAA
 * 
 * 출석의 종류 : O출석 L지각 A결석
 * 
 */
public class BJ_G4_1563_개근상_bottomup {

	static final int d = 1000000;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		// dp[날짜][지각 수][연속결석 수] : 개근상을 받을 수 있는 경우의 수
		// dp[a][b][c] : a일날까지 b번 지각했고 c번 연속 결석한 경우의 수
		int[][][] dp = new int[N+1][2][3];

		//첫날의 각 경우의 수는 무조건 1개
		dp[1][0][0] = dp[1][0][1] = dp[1][1][0] = 1;
		
		for(int i=2; i<=N; i++) {

			/*
			 * 한번 한 지각은 바뀌지 않음
			 * 연속결석은 이번에 결석하지 않으면 초기화 됨
			 * 		즉 이번에 연속결석이 0인 경우는 
			 * 		이번에 결석 안해서 연속결석이 초기화됐을 수도 있으므로 전날에 지각했던 상태도 고려해야 함
			 */
			dp[i][0][0] = (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][0][2]) % d; //이번에 출석한 경우(기존 결석 초기화)
			dp[i][0][1] = dp[i-1][0][0];
			dp[i][0][2] = dp[i-1][0][1];
			dp[i][1][0] = (dp[i-1][0][0]	//깨끗하게 살다가 이번에 지각한 경우 
						+ dp[i-1][0][1] + dp[i-1][0][2]	//그전에 결석하다가 이번에 지각한 경우(연속결석초기화) 
						+ dp[i-1][1][0] //이번에 출석함, 기존 유지
						+ dp[i-1][1][1] + dp[i-1][1][2]) //지각1번하고 결석하다가 이번에 출석한 경우(연속결석초기화) 
						% d;
			dp[i][1][1] = dp[i-1][1][0];
			dp[i][1][2] = dp[i-1][1][1];
			
		}
		
		//N번째 날의 모든 경우의 수를 합
		int result = 0;
		for(int i=0; i<dp[N].length; i++) {
			for(int j=0; j<dp[N][i].length; j++) {
				result = (result + dp[N][i][j]) % d;
			}
		}
		System.out.println(result);
		sc.close();
	}

}
