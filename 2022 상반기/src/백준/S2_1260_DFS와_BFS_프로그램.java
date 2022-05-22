package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S2_1260_DFS와_BFS_프로그램 {
    static int N,M,V;
    static ArrayList<ArrayList<Integer>> adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

       adjList = new ArrayList<>();
        for(int i=0; i<N+1; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

        // 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문
        for(int i=0; i<N+1; i++){
            Collections.sort(adjList.get(i));
        }

        StringBuilder sb = new StringBuilder();
        dfs(sb, new boolean[N+1], V);
        sb.append("\n");
        bfs(sb, new boolean[N+1], V);
        System.out.println(sb);
    }

    static void dfs(StringBuilder sb, boolean[] v, int curr){
        v[curr] = true;
        sb.append(curr+" ");
        for(int node : adjList.get(curr)){
            if(!v[node]) dfs(sb, v, node);
        }
    }

    static void bfs(StringBuilder sb, boolean[] v, int start){

        Queue<Integer> Q = new LinkedList();
        Q.add(start);
        v[start] = true;
        while (!Q.isEmpty()){
            int curr = Q.poll();
            sb.append(curr+" ");
            for(int node : adjList.get(curr)){
                if(v[node]) continue;
                Q.add(node);
                v[node] = true;
            }
        }
    }
}
