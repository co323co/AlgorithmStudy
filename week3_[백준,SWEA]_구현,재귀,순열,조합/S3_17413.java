package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//BJ S3 17413 단어 뒤집기 2 
public class S3_17413 {

	
	//입력이 10만길이나 되고
	//출력을 10만번이나 하니까 느려짐(+심지어 스택 출력도 있음)
	//BufferedReader와 StringBuilder를 활용해 입출력 시간을 단축하자
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
//		sb : 출력할 결과물
		StringBuilder sb = new StringBuilder();	
		
		Stack<Character> s = new Stack<Character>();
		
		for(int i=0; i<str.length(); i++) {
			
			if(str.charAt(i)=='<') {
			
//				태그 시작 전에 스택에 쌓인 단어 전부 출력해주기
				while(!s.empty()) sb.append(s.pop());
				
				while(true) {			
					sb.append(str.charAt(i++));
					if(str.charAt(i)=='>') {
						sb.append(str.charAt(i));
						break;
						}
					}
				}
			
			else {
				
				//공백을 만나면 스택 전부 출력하기!
				if(str.charAt(i)==' ') {			
					while(!s.empty()) sb.append(s.pop());		
					sb.append(" ");
					continue;
				}
				s.add(str.charAt(i));
			}
				
		}
		//아직 스택에 남은 단어 있으면 출력해주기 (마지막 단어일 경우)
		while(!s.empty()) sb.append(s.pop());
		
		System.out.println(sb);
	}
}
