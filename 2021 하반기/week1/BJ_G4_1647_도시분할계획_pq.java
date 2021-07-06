package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G4_1647_도시분할계획_pq {

	static class Edge implements Comparable<Edge>{
		int node, cost;
		public Edge(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return cost-o.cost;
		}
		@Override
		public String toString() {
			return "(" + node + "," + cost + ")";
		}
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//인접리스트 만들기
		ArrayList<ArrayList<Edge>> adjList = new ArrayList<>();
		for(int i=0; i<N; i++) adjList.add(new ArrayList<>());
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int cost = Integer.parseInt(st.nextToken());
			adjList.get(from).add(new Edge(to, cost));
			adjList.get(to).add(new Edge(from, cost));
		}

		boolean[] v = new boolean[N];

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		//0번째 정점부터 시작
		pq.add(new Edge(0, 0));
		
		int sum=0, max=0;
		while(!pq.isEmpty()) {
//			System.out.println(pq);
			Edge curr = pq.poll();
			if(v[curr.node]) continue;	//넣을때는 방문안했어도, 그 사이에 쌓여서 나중에 poll했을 때는 이미 방문한 애일 수 있음
			v[curr.node] = true;
//			System.out.println(curr.node+","+curr.cost);
			sum += curr.cost;
			max = Math.max(curr.cost, max);
			for(Edge e : adjList.get(curr.node)) {
				if(!v[e.node]) pq.add(e);
			}
		}
		System.out.println(sum-max);
	}

}
