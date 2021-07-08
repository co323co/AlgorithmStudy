package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BJ_G4_1774_우주신과의교감 {

	static class Edge implements Comparable<Edge>{
		int from,to; 
		double weight;
		public Edge(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Double.compare(weight, o.weight);
	
		}
		@Override
		public String toString() {
			return "(" + from + "," + to + "," +weight+","  + ")";
		}
		
	}
	static class Node{
		int x,y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}
		
	}
	static Node[] vArray;
	static int[] parents;
	static PriorityQueue<Edge> edgePQ = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()); // 정점의 수
		int E = Integer.parseInt(st.nextToken()); // 주어진 간선의 수
		vArray = new Node[V];
		parents = new int[V];
		for(int i=0; i<V; i++) {
			st = new StringTokenizer(br.readLine());
			vArray[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			parents[i]=i;
		}
		//이미 연결되어있는 애들 연결해주기
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			union(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
		}

		for(int i=0; i<V; i++) {
			for(int j=i+1; j<V; j++) {
				Node from = vArray[i];
				Node to = vArray[j];
				double dist =Math.sqrt(Math.pow(from.x-to.x,2) + Math.pow(from.y-to.y,2)); 
				edgePQ.add(new Edge(i, j, dist));
			}
		}
		int cnt = E; //선택한 간선 수
		double result = 0;
		while(!edgePQ.isEmpty()) {
			Edge edge = edgePQ.poll();
			if(union(edge.from, edge.to)) { //싸이클이 발생하지 않았다면
				result +=edge.weight;
				if(++cnt == V-1) {
					break;
				}
			}
		}
	
		System.out.printf("%.2f", result);
	}
	
	static int findSet(int a) {
		if(parents[a]==a) return a;
		return parents[a] = findSet(parents[a]); //경로 압축
	}

	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot==bRoot) return false; //이미 같은 그룹임. 합칠 수 없음
		parents[bRoot] = aRoot; //b그룹을 a에 편입시켜버림!
		return true;
	}
}
