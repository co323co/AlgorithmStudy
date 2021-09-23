package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G5_1916_최소비용구하기 {

	static int V,E; //도시의 개수, 버스의 개수
	static int start, end;
	public static void main(String[] args) throws IOException {
		
		class Edge implements Comparable<Edge>{
			int node; //도착 노드
			int cost; //드는 비용
			public Edge(int _n, int _c) {
				node=_n;
				cost=_c;
			}
			public int compareTo(Edge o) {
				return Integer.compare(cost, o.cost);
			}
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		ArrayList<Edge> adjList[] = new ArrayList[V+1];
		for(int i=0; i<V+1; i++) adjList[i] = new ArrayList();
		//인접리스트 생성
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adjList[from].add(new Edge(to, cost));
		}
		
		//시작도시, 도착도시
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		//다익스트라
		PriorityQueue<Edge> pq = new PriorityQueue();
		int[] dist = new int[V+1];
		boolean[] v = new boolean[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		pq.add(new Edge(start, 0));
		dist[start] = 0;
		while(!pq.isEmpty()) {
			Edge curr = pq.poll();
			if(v[curr.node]) continue;
			v[curr.node] = true;
			
			for(Edge e : adjList[curr.node]) {
				int newDist = dist[curr.node]+e.cost;
				if(dist[e.node] > newDist) {
					dist[e.node] = newDist;
					pq.add(new Edge(e.node,newDist));
				}
			}
		}
		System.out.println(dist[end]);
	}

}
