package week2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//pq 프림 방식으로 구현
public class BJ_G4_1197_최소스패닝트리 {

	static class Edge implements Comparable<Edge>{
		int node; //도착노드
		int cost;
		public Edge(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
		@Override
		public String toString() {
			return "(" + node + "," + cost + ")";
		}
		@Override
		public int compareTo(Edge o) {
			return cost-o.cost;
		}
	}
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		boolean[] visited = new boolean[V];
		
		ArrayList<ArrayList<Edge>> adjList = new ArrayList<>(); 
		for(int i=0; i<V; i++) adjList.add(new ArrayList<>());
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int cost = Integer.parseInt(st.nextToken());
			adjList.get(from).add(new Edge(to, cost));
			adjList.get(to).add(new Edge(from, cost));
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(0, 0)); //0번째 노드에서 시작

		int result = 0;
		while(!pq.isEmpty()) {
			
			//가중치 가장 낮은 노드 선택
			Edge curr = pq.poll();
			if(visited[curr.node]) continue;
			visited[curr.node] = true;
			result+=curr.cost;
			for(Edge e : adjList.get(curr.node)) {
				if(!visited[e.node]) pq.add(e);
			}
		}
		System.out.println(result);
	}

}
