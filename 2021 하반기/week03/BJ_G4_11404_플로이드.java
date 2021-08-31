package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//백준 골드4 11404 플로이드
//플로이드 워샬 문제
public class BJ_G4_11404_플로이드 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		final int INF = Integer.MAX_VALUE;
		
		int[][] dist = new int[N][N];
		for(int i=0; i<N; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int cost = Integer.parseInt(st.nextToken());
			if(dist[from][to] > cost) dist[from][to] = cost;
		}
		//경출도
		for (int k = 0; k < dist.length; k++) {
			for (int i = 0; i < dist.length; i++) {
				for (int j = 0; j < dist.length; j++) {
					if(dist[i][k]==INF || dist[k][j]==INF) continue;
					dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]); 
				}
			}
		}
		
		//출력
		for(int i=0; i<dist.length; i++) {
			for(int j=0; j<dist.length; j++) {
				if(dist[i][j]==INF) System.out.print("0 ");
				else System.out.print(dist[i][j]+" ");
			}
			System.out.println();
		}
	}

}
