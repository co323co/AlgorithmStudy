## 특징
+ 무방향성
+ 신장트리 
	1. 그래프의 부분집합인 트리 
	2. 모든 정점을 포함
	3. 싸이클 X
    
>원래 3중 for문으로도 해결해야하는 것을
+ 크루스칼은 ‘정렬’ 후 작은 것부터 추가
+ 프림은 ‘갱신'
방법을 사용함으로써 더 효율적으로 해결할 수 있음 

---
## 프림
+ 간선이 많으면 프림(노드를 가지고 함)
> #### 논리
선택된 그룹 vs 선택 안 된 그룹 (방문배열) 으로 나누어서  
선택된 그룹들이 팔이 닿는 애들 중 가장 비용 적은 애를 고름

### 인접리스트 방식 (Priority Queue)
```java
public class G4_1197_최소스패닝트리 {

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
```

### 인접행렬 방식
```java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MST2_PrimTest {

	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[][] adjMatrix = new int[N][N];
		boolean[] visited = new boolean[N];
		int[] minEdge = new int[N];
		//신장트리에 연결된 정점에서 자신으로의 최소간선비용
		
		StringTokenizer st = null;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
			//처음에는 거리값 전부 무한대(최댓값)으로 주기
			minEdge[i] = Integer.MAX_VALUE;
		}
		
		int result = 0;
		//시작은 임의의 정점. 시작이 무엇이든 결과는 같음
		minEdge[0] = 0; //0을 시작 정점으로 처리하기 위해 0 세팅
		//이렇게하면 처음에 0번정점만 가중치가 0이고 나머지 정점은 무한대가 됨, 자연스럽게 당연히 0번 정점으로 연결하게 됨
		
		
		for(int c=0; c<N; c++) {
			int min = Integer.MAX_VALUE;
			int minVertex = 0;
			
			//신장트리에 연결되지 않은 정점 중 minEdge비용이 최소인 정점 찾기
			for(int i=0; i<N; i++) {
				if(!visited[i] && min>minEdge[i]) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			
			result+=min; //해당 정점의 간선비용 누적
			visited[minVertex] = true;
			
			/*
			 * 다른애로 오는 것 보다 나로 오는게 더 유리함
			 * 기존의 최소가중치보다 방금 새로 연결된(minVertex)애의 인접한 애들의 가중치가 더 적으면 갱신함
			 * 		- 원래 안닿아서 무한대였던 애가 이제 닿을 수 있게 돼서 갱신되거나.
			 * 		- 원래 갈 수 있는 길보다 새로추가한 애가 더 효율적이거나(가중치낮음)
			 */
			for(int i=0; i<N; i++) {
					if(!visited[i] && adjMatrix[minVertex][i] != 0 && minEdge[i] > adjMatrix[minVertex][i]) {
					minEdge[i] = adjMatrix[minVertex][i];
				}
				
			}
			
		}
		System.out.println(result);
	}

}
```
인접행렬 방식은 대부분의 문제에서 메모리 초과로 통과하지 못한다.
PQ를 사용하는 방법을 더 애용하자!

>1. 선택된 그룹에서 i번 노드까지 가는데 드는 비용을 저장하는 배열을 만듦
2. 전부 무한대로 초기화
3. 시작노드는 임의로 하나 줌
4. 아래 반복
-새로운 노드를 선택하면 방문체크 후 배열을 갱신함 
(못 가던 걸 가게 됐거나, 더 가깝게 갈 수 있게 됐거나)
-비용 가장 적은 노드 연결

---

## 크루스칼

+ 간선이 적으면 크루스칼(간선을 가지고 함)
(간선을 정렬해야하기 때문에 간선 숫자가 많으면 정렬 시간이 오래걸림)
+ 간선리스트 사용, Union-Find 사용
> #### 논리
비용이 적은 간선 순으로 선택. 단 싸이클이 생기면 선택 안함.
	1. 간선 오름차순 정렬
	2. 싸이클 없는 애로 선택(유파 사용)
	3. n-1개 되면 끝

### Unin-Find


### 크루스칼 구현 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//서로소집합
public class MST1_KruskalTest {

	static class Edge implements Comparable<Edge>{
		int from,to,weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			//오버플로우, 언더플로우까지 고려 
			return Integer.compare(this.weight,o.weight);
		}
	}
	static int V, E;
	//find가 일어나지 않으면 진짜 부모를 가리키지 않음, 그래서 진짜 부모를 가리킬 수도, 아님 위에 놈을 가리킬 수도 있음
	static int[] parents; //부모를 가리킬 배열 
	static Edge[] edgeList;
	
	//make set, 크기가 1인 단위집합 만들기
	static void make() {
		for(int i=0; i<V; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		//내가 대표자
		if(parents[a]==a) return a;
//		return findSet(parents[a]); // path compression 전
		return parents[a] = findSet(parents[a]); // path compression 후
	}
	
	static boolean union(int a, int b) {
		
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false; //합치기 실패. 이미 같은 집합임
		
		parents[bRoot] = aRoot; //b집합의 우두머리가 a의 우두머리를 섬기게 한다.
		/*
		 * 만약 랭크를 이용한다면, 랭크를 이용해서 더 긴쪽에 짧은쪽을 붙여야함. 
		 * 다만 path압축과 rank를 둘 다 관리하는건 힘듦. path 압축 할 때마다 rank가 바뀌기 때문.
		 * 이 둘은 같이 하기 힘듦. 관리가 어려움!
		 */
		return true;
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		parents = new int[V];
		edgeList = new Edge[E];
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		} //간선리스트	
		
		//1. 간선리스트 가중치 기준 오름차순 정렬
		Arrays.sort(edgeList);
		
		make();
		int result = 0;
		int count = 0; //선택한 간선 수
		
		for(Edge edge : edgeList) {
			if(union(edge.from, edge.to)) { //싸이클이 발생하지 않았다면
				result +=edge.weight;
				if(++count == V-1) break;
			}
		}
		System.out.println(result);
	}

}
```
---

## 관련 문제
+ [백준 골드4 1197 최소 스패닝 트리](https://www.acmicpc.net/problem/1197)
+ [백준 골드4 1647 도시 분할 계획](https://www.acmicpc.net/problem/1647)
