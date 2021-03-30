package week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

//백준 골드5 17471 게리맨더링
public class BJ_G5_17471_게리맨더링 {

	static class City{
		int vertex;
		City next;

		public City(int vertex, City next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}
	}
	
	static City[] cities;
	static int[] population;
	static int ans = -1;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		population = new int[N];
		cities = new City[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) population[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for(int j=0; j<n; j++) {
				int adj= Integer.parseInt(st.nextToken())-1;
				//어차피 서로에게 다 줘서 인접한 애한테도 넣어줄 필요 x
				cities[i] =  new City(adj, cities[i]);
			}
		}
		
		powerSet(0, 0, 0, new int[N], new int[N], 0, 0);
		System.out.println(ans);
	}
	
	static void powerSet(int k1, int k2, int idx, int[] arr1, int[] arr2, int sum1, int sum2) {
		
		if(idx==cities.length) {
			//선거구는 하나 이상되어야함
			if(k1==0 || k2==0) return;
			
			boolean[] visited = new boolean[cities.length];
			//arr1 인접한지 체크
			dfs(arr1[0], arr1, k1, visited);
			//arr2 인접한지 체크
			dfs(arr2[0], arr2, k2, visited);
			//둘 그룹 다 각자 인접했으면 visited가 모두 true여야 함
			for(int i=0; i<visited.length; i++) {
				if(!visited[i]) return;
			}
			if(ans==-1) ans = Math.abs(sum1-sum2);
			else ans = Math.min(ans, Math.abs(sum1-sum2));

			return;
		}
		
		//선거구1에 넣기
		arr1[k1] = idx;
		powerSet(k1+1, k2, idx+1, arr1, arr2, sum1+population[idx], sum2);

		//선거구2에 넣기
		arr2[k2] = idx;
		powerSet(k1, k2+1, idx+1, arr1, arr2, sum1, sum2+population[idx]);
	}
	
	static void dfs(int vertex, int arr[], int len, boolean[] v) {

		v[vertex] = true;
		
		for(City city = cities[vertex]; city!=null; city = city.next) {
			if(v[city.vertex]) continue;
			//인접한 애가 그룹 구성원 중 하나면 걔의 인접한 애도 체크
			for(int i=0; i<len; i++) {
				if(arr[i] == city.vertex) {
					dfs(city.vertex, arr, len, v);
					break;
				}
			}
		}
	}
}
