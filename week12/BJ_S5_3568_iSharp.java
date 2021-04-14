package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//백준 실버5 3568 iSharp
public class BJ_S5_3568_iSharp {

	public static void main(String[] args) throws IOException {
			
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String line = br.readLine();
		line = line.replace(",", "");
		line = line.replace("[]", "[");
		line = line.substring(0, line.length()-1);
		
		//단어(변수)별로 쪼갬
		String[] strs = line.split(" ");
		
		for(int i=1; i<strs.length; i++) {
			//맨 앞엔 공통 자료형 붙임
			sb.append(strs[0]);
			//큐 : 변수명 담음
			Queue<Character> Q = new LinkedList<>();
			//스택 : ([], &, *) 자료형 담음
			Stack<Character> S = new Stack<>();
			for(int j=0; j<strs[i].length(); j++) {
				char ch = strs[i].charAt(j);
				if(isAlpha(ch)) Q.add(ch);
				else S.add(ch);
			}
			while(!S.isEmpty()) sb.append(S.pop());
			sb.append(" ");
			while(!Q.isEmpty()) sb.append(Q.poll());
			sb.append(";\n");
		}
		sb = new StringBuilder(sb.toString().replace("[", "[]"));
		System.out.println(sb);
	}
	
	static boolean isAlpha(char ch) {
		if(ch>='A' && ch<='Z') return true;
		 if(ch>='a' && ch <='z') return true;
		return false;
	}
}
