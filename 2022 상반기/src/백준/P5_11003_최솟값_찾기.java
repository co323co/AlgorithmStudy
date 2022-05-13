package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 슬라이딩 윈도우
public class P5_11003_최솟값_찾기 {

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // solve
        StringBuilder sb = new StringBuilder();

        Deque<Node> deque = new LinkedList<>();
        deque.add(new Node(0,arr[0]));
        sb.append(deque.peekFirst().num+" ");
        for(int i=1; i<N; i++){
            // 새로 범위에 들어온 숫자
            Node node = new Node(i, arr[i]);
            // 나보다 작은 애들은 다 지우고 맨 뒤로 넣음 (맨 앞은 항상 가장 작은 값으로 유지됨)
            while(!deque.isEmpty() && deque.peekLast().num>node.num){
                deque.removeLast();
            }
            deque.addLast(node);
            // 맨 앞(가장 작은 값)이랑 새로운 노드랑 L(윈도우 크기)이상 차이나면 오래된 노드 제거
            // 한 번에 하나씩만 넣기 때문에 한번만 비교하면 된다
            if(!deque.isEmpty() && node.index - deque.peekFirst().index >= L){
                deque.removeFirst();
            }
            sb.append(deque.peekFirst().num+" ");
        }
        System.out.println(sb);
    }
}
class Node {
    int index, num;
    public Node(int i, int n) {index = i; num = n;}
}