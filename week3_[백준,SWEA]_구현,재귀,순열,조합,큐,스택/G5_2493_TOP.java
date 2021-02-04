package hw_20210204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

//백준 골드5 2943 탑
//최적화 문제, 입력이 50만임
public class G5_2493_TOP {
 
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		st=new StringTokenizer(br.readLine());

		/*
		 * 생각해야 할 것
		 * 
		 * 자기 앞에있는 애부터 부딪힘. 가까운애(방금 입력한 애)부터 비교
		 * -> 제일 최근에 입력한 애부터 비교해야함. <<<즉 스택, 후입선출!>>>
		 * 
		 * 또한 앞에 얼마나 있던간에 나보다 같거나 커야만 부딪침.
		 * -> 나보다 작은 애들은 빼버림
		 * 
		 * ->그래도 되는 이유
		 * 큰애 앞에 작은 애들은 필요없음!, 뒤에서 부딪치니 큰애 앞에 애들은 부딪칠 일 없음 
		 * 
		*/
		
		/*
		 * 시간복잡도에 대해서,
		 * 안의 while문은
		 * 나보다 클떄 => 바로 while문 나감
		 * 나보다 작을때=> pop하기 떄문에
		 * 
		 */
		Stack<Pair> s = new Stack<Pair>();
		for(int i=1; i<=N; i++) {
			Pair p  = new Pair(Integer.parseInt(st.nextToken()),i);
			
			while(true) {
				if(s.empty()) {
					sb.append("0 ");
					break;
				}
				
				Pair b = s.pop();
				if(b.height>=p.height) {
					//나보다 큰 놈이면 나중에 쓸만한 애니까 빼지마, 다시 넣어!
					//뒤에 나보다 큰놈이 왔을 떈 나를 지나쳐서 얘(b)한테 부딪혀야 함!
					//나보다 큰놈이 있지만 나(p)를 안뺴는 이유는, 내 바로 뒤에 애가 나보다 작으면 나한테 부딪히잖아.  
					s.push(b);
					sb.append(b.idx + " ");
					break;
				}
				
			}
			s.push(p);			
		}
		System.out.print(sb);
	}

}

class Pair{
	int height;
	int idx;
	public Pair(int h, int i) {
		this.height=h;
		this.idx = i;
	}
	@Override
	public String toString() {
		return height+"";
	}
	
}
