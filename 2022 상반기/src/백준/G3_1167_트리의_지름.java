package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
아이디어가 중요한 문제
임의의 노드에서 가장 긴 경로로 연결돼 있는 노드는 트리의 지름에 해당하는 두 노드 중 하나다
위 사실을 알고 있어야 수월하게 풀 수 있다
 */
public class G3_1167_트리의_지름 {
    static int V;
    static ArrayList<ArrayList<Node>> adjList;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());

        adjList = new ArrayList<>();
        for(int i=0; i<V+1; i++) adjList.add(new ArrayList<>());
        for(int i=0; i<V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            while(true) {
                int b = Integer.parseInt(st.nextToken());
                if(b==-1) break;
                int w = Integer.parseInt(st.nextToken());
                adjList.get(a).add(new Node(b,w));
            }
        }
        // 임의의 노드에서 가장 먼 노드는 트리 지름에 해당하는 노드 중 하나
        Node tmp = bfs(1);
        // 지름에 해당하는 노드로 다시 bfs
        Node ans = bfs(tmp.v);
        System.out.println(ans.w);
    }

    // 가장 먼 노드를 반환
    static Node bfs(int start){
        boolean v[] = new boolean[V+1];
        int[] dist = new int[V+1];
        Queue<Node> Q = new LinkedList();
        Q.add(new Node(start,0));
        v[start] = true;

        while(!Q.isEmpty()){
            Node curr = Q.poll();
            for(Node adj : adjList.get(curr.v)){
                if(v[adj.v]) continue;
                int d = dist[curr.v] + adj.w;
                dist[adj.v] = d;
                Q.add(new Node(adj.v, d));
                v[adj.v] = true;
            }
        }

        Node max = new Node(0,0);
        for(int i=0; i<V+1; i++){
            if(dist[i] > max.w){
                max.v = i;
                max.w = dist[i];
            }
        }
        return max;
    }

    static class Node{
        int v;
        int w;
        public Node(int v, int w){
            this.v=v;
            this.w=w;
        }
    }
}
