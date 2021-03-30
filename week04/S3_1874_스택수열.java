package week04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//백준 실버3 1874 스택 수열
public class S3_1874_스택수열 {

	static public void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> s= new Stack<>();
		
		int input;
		int n=1;
		for(int i=0; i<N; i++) {
			 input = Integer.parseInt(br.readLine());
			 if(s.empty()) {
				 sb.append("+\n");
				 s.push(n++);
			 }
			 while(!s.empty()) {
				//출력(도출)해야할 숫자보다 현재 스택의 top이 작으면, 도출할 숫자까지의 숫자들을 스택에 삽입
				 if(s.peek() < input) {
					 sb.append("+\n");
					 s.push(n++);
				 }
				//출력(도출)해야할 숫자링 현재 스택의 top이 같으면, pop()하면 도출됨
				 if(s.peek()==input) {
					 sb.append("-\n");
					 s.pop();
					 break;
				 }
				//출력(도출)해야할 숫자보다 현재 스택의 top이 크면, 도출할 숫자는 스택의 아래에 깔려있음으로 도출할 방법이 없음.
				 if(s.peek() > input) {
					 System.out.println("NO");
					 return;
				 }
				 
			 }
		}
		System.out.println(sb);
	}
}
