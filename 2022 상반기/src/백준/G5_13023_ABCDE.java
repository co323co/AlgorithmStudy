import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G5_13023_ABCDE {
    static List<List<Integer>> adjList;
    static boolean isPossible;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList();
        for(int i=0; i<N; i++){
            adjList.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

        for(int i=0; i<N; i++){
            if(isPossible) break;
            boolean[] visited = new boolean[N];
            bfs(i, 1, visited);

        }
        System.out.println(isPossible?1:0);
    }
        public static void bfs(int curr, int cnt, boolean[] visited){
        if(cnt==5) {
            isPossible = true;
            return;
        }
        visited[curr] = true;
        for(int v : adjList.get(curr)){
            if(visited[v]) continue;
            cnt++;
            bfs(v, cnt, visited);
            cnt--;
        }
        visited[curr] = false;
    }
}
