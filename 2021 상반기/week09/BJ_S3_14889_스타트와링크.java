package week09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_S3_14889_스타트와링크 {

	static int[][] S;
	static int ans;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		S = new int[N][N];
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) S[i][j] = Integer.parseInt(st.nextToken());
 		}

		ans = Integer.MAX_VALUE;
		//solve
		combination(0, 0, new boolean[N]);
		System.out.println(ans);
	}
	private static void combination(int k, int idx, boolean[] sel) {
		
		//N개 중 절반을 뽑아서 true로 만듦
		if(k == S.length/2) {
			
			//true인 애들은 1팀, false인 애들은 2팀
			ArrayList<Integer> team1 = new ArrayList<>();
			ArrayList<Integer> team2 = new ArrayList<>();
			for(int i=0; i<sel.length; i++) {
				if(sel[i]) team1.add(i);
				else team2.add(i);
			}
			
			//자기 팀 애들끼리 전부 서로의 시너지 더함
			int sum1=0, sum2=0;
			for(int i=0; i<sel.length/2; i++) {
				for(int j=0; j<sel.length/2; j++) {
					sum1 += S[team1.get(i)][team1.get(j)];
					sum2 += S[team2.get(i)][team2.get(j)];
				}
			}
			ans = Math.min(ans, Math.abs(sum1-sum2));
			return;
		}
		
		if(idx==S.length) return;
		
		sel[idx] = true;
		combination(k+1, idx+1, sel);
		sel[idx] = false;
		combination(k, idx+1, sel);
	}
}
