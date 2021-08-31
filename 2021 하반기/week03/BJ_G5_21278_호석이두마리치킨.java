package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 골드5 21278 호석이 두 마리 치킨
//플로이드 워샬
public class BJ_G5_21278_호석이두마리치킨 {

	static int dist[][];
	static int N, M;
	static final int INF = Integer.MAX_VALUE;
	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	//건물의 개수
		M = Integer.parseInt(st.nextToken());	//도로의 개수
		
		dist = new int[N][N];
		
		//무한대로 초기화
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==j) continue;
				dist[i][j] = INF;
			}
		}
		
		//간선 기록
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken())-1;
			int x2 = Integer.parseInt(st.nextToken())-1;
				dist[x1][x2] = 1;
				dist[x2][x1] = 1;
		}
		
		//플로이드 워샬, 경출도
		for (int k = 0; k < dist.length; k++) {
			for (int i = 0; i < dist.length; i++) {
				for (int j = 0; j < dist.length; j++) {
					if(dist[i][k]==INF || dist[k][j]==INF) continue;
					dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
				}
			}
		}
		
		//출력
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(dist[i][j]+" ");
//			}
//			System.out.println();
//		}
		combination(0, 0, new int[2]);
		System.out.print(x1+" "+x2+" "+res);
	}
	
	static int x1, x2, res=INF;
	//조합
	static void combination(int start, int k, int[] sel) {
		if(k==2) {
			int sum = 0;
			for(int i=0; i<N; i++) {
				sum += Math.min(dist[sel[0]][i], dist[sel[1]][i]);
			}
			if(sum*2 < res) {
				x1 = sel[0]+1;
				x2 = sel[1]+1;
				res = sum*2;
			}
			return;
		}
		for(int i=start; i<N; i++) {
			sel[k] = i;
			combination(i+1, k+1, sel);
		}
	}
}
