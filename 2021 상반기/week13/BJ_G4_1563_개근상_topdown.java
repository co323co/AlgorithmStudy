package week13;

import java.util.Scanner;

public class BJ_G4_1563_개근상_topdown {

	  static int N;
	   static int[][][] dp;

	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        N = sc.nextInt();
	        dp = new int[N + 1][2][3];
	        for (int i = 0; i <= N; i++) { // 초기화
	            for (int j = 0; j < 2; j++) {
	                for (int k = 0; k < 3; k++) {
	                    dp[i][j][k] = -1;
	                }
	            }
	        }

	        System.out.println(dfs(0, 0, 0));
	        for(int i=0; i<=N; i++) {
	        	for(int j=0; j<2; j++) {
	        		for(int k=0; k<3; k++) {
	        			System.out.print(dp[i][j][k]+" ");
	        		}
	        		System.out.println();
	        	}
	        }
	    }

	    private static int dfs(int d, int l, int a) {
	        if (l == 2 || a == 3) return 0;
	        if (d == N) return 1;

	        int ans = dp[d][l][a];
	        if (ans != -1) return ans;
	        // 출석하는 경우 + 지각하는 경우 + 결석하는 경우
	        // 지각은 누적제이므로 모든 경우에 계속 저장해서 가고.. 결석은 연속성이 있어야하므로 출석, 지각의 경우는 0으로 초기화됨
	        ans = dfs(d + 1, l, 0) + dfs(d + 1, l + 1, 0) + dfs(d + 1, l, a + 1);
	        return dp[d][l][a] = ans % 1000000;
	    }
}
