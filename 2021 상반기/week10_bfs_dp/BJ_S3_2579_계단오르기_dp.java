package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//백준 실버3 2579 계단오르기
/*
 * dp 문제. 푸는데 어려웠음. 혼자 못풀었음
 */
public class BJ_S3_2579_계단오르기_dp {

	/*
	 * 
	 * N의 min값이 안정해져 있음. 범위 체크 잘하기!
	 * 
	 * N번째 계단을 올랐을 때의 점수
	 * 
	 * 다음 중 max + s[N] 
	 * 
	 *	한칸 전에서 온거
	 * 	1. memo[N-1] 
	 * 		1-1. memo[N-1-1]+s[N-1] 인 경우 => 3칸 연속으로 올라서 불가능!
	 * 		1-2. memo[N-1-2]+s[N-1] 인 경우 => @@@이거나@@@
	 * 	두칸 전에서 온거
	 * 	2. memo[N-2] => @@@이거@@@
	 * 
	 * 1-1번인 경우는 안되고, 1-2번, 2번인 경우가 된다. 
	 * 그래서 memo[N]은 Math.max(memo[N-3]+s[N-1], memo[N-2])+s[N] 이다. 
	 * 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] stage = new int[N+1];
		for(int i=1; i<N+1; i++) stage[i] = Integer.parseInt(br.readLine());
		
		int[] memo = new int[N+1];
		
		if(N<3) {
			if(N==0) System.out.println(0);
			else if(N==1) System.out.println(stage[1]);
			else if(N==2) System.out.println(stage[1]+stage[2]);
			return;
		}
		
		memo[1] = stage[1];
		memo[2] = stage[1] + stage[2];
		
		for(int i=3; i<N+1; i++) {
			memo[i] = Math.max(memo[i-3]+stage[i-1], memo[i-2]) + stage[i];
		}
		System.out.println(memo[N]);
	}
}
