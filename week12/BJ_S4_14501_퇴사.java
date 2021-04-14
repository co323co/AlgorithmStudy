package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 실버4 14501 퇴사
public class BJ_S4_14501_퇴사 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int arr[][] = new int[N+1][2]; // [0] : 상담기간	[1] : 금액 
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N+2];
		/*
		 * d[N]는 N-1번째 날까지 상담해서 얻을 수 있는 최대 금액
		 * == d[N]은 N번째 날의 상담 시작 전까지 얻을 수 있는 최대 금액
		 * T(상담기간)가 1~5이니 5일 전까지 비교해보면 됨
		 */
		for(int i=1; i<=N+1; i++) {
			int max = 0;
			for(int j=1; j<=5; j++) {
				if(i-j < 0) continue;
				if(j==arr[i-j][0]) max = Integer.max(max, dp[i-j]+arr[i-j][1]);
				else max = Integer.max(max, dp[i-j]);
			}
			dp[i] = max;
		}
		System.out.println(dp[N+1]);
	}
	
}
