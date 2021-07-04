package week14;

import java.util.Scanner;

/*
 * |
 * dp[1]
 * 
 * ||, 〓, ㅁ
 * dp[2]
 * 
 * |||(중복), |〓, |ㅁ,  
 * |||(x), 〓|, ㅁ|
 * dp[2] * dp[1]	+	dp[1] * dp[2] - 1;
 * dp[3]
 * 
 * 즉 dp[4]는
 * dp[3] * dp[1]	+	dp[2] * dp[2] - 3; 
 *
 */
public class BJ_S3_11727_2xN타일링2 {

	public static void main(String[] args) {

		final int R = 10007;
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int dp[] = new int[N+1];
		

		dp[1] = 1;
		if(N<2) {
			System.out.println(dp[1]);
			return;
		}
		dp[2] = 3;
		if(N<3) {
			System.out.println(dp[2]);
			return;
		}
		
		dp[3] = 5;
		// 길이가 1 남았을 때? 1*2 타일 1개로 채움
        // 길이가 2 남았을 때? 2*1 타일 2개로 채움 + 2*2 타일 1개로 채움
                        // 1*2 타일 2개로 채울 수 있지만, 이는 곧 dp[i-1]과 중복되게 된다
		for(int i=3; i<dp.length; i++) {
			dp[i] = (dp[i-1]*dp[1] + dp[i-2]*dp[2] - dp[i-2])%R;
		}
		System.out.println(dp[N]);
	}

}
