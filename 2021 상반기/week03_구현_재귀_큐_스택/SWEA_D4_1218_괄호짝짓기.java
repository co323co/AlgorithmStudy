package com.ssafy.algo;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

//SWEA D4 1218. [S/W 문제해결 기본] 4일차 - 괄호 짝짓기
public class SWEA_D4_1218_괄호짝짓기 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		for(int tc=1; tc<=10; tc++) {
			
			int Ans = 1;
			
			int N = sc.nextInt();
			String str = sc.next();	
			
			Stack<Character> s = new Stack<Character>();
			
			String open = "([{<";
			String close = ")]}>";

			for(int i=0; i<str.length(); i++) {
				
				if(open.contains(str.charAt(i)+"")) s.push(str.charAt(i));
				if(close.contains(str.charAt(i)+"")) {
					//여는 괄호가 많고, 닫는 괄호가 부족한 경우
					if(s.empty()) {
						Ans=0;
						break;
					}
					
					//괄호의 짝이 안맞는 경우
					int idx = open.indexOf(s.pop());
					if(close.charAt(idx)!=str.charAt(i)) {
						Ans=0;
						break;
					}
				}	
			}
			// 닫는 괄호가 남는 경우
			if(!s.empty()) Ans=0;
			System.out.println("#"+tc+" "+Ans);		
		}	
	}
}
