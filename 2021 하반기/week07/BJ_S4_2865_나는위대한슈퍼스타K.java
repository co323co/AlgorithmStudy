package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_S4_2865_나는위대한슈퍼스타K {

	static int N, M, K;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //참가자 수
		M = Integer.parseInt(st.nextToken()); //장르 수
		K = Integer.parseInt(st.nextToken()); //본선 진출 수
		Double[] scores = new Double[N];

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int n = Integer.parseInt(st.nextToken());
				double s = Double.parseDouble(st.nextToken());
				if(scores[n-1]==null) scores[n-1] = s;
				else scores[n-1] = Math.max(scores[n-1], s);
			}
		}
		
		Arrays.sort(scores, Collections.reverseOrder());
		double res = 0;
		for(int i=0; i<K; i++) {
			res+=scores[i];
		}
		System.out.printf("%.1f", res);
	}
}
