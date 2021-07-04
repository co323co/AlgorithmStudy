package week04;

import java.util.Scanner;

//백준 브론즈1 2999 비밀 이메일
public class BJ_B1_2999_비밀이메일 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.next();
		
		int R = 1; 
		int C=str.length();
		
		for(int r=1; r <= C/r; r++) {
			 if(C%r == 0) R=r;
		}
	
		char[][] arr = new char[R][C/R];
		int idx=0;
		
		for(int j=0; j<arr[0].length; j++)
			for(int i=0; i<arr.length; i++) {
				arr[i][j]=str.charAt(idx++);
			}

		StringBuilder sb=new StringBuilder();

		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++)
				sb.append(arr[i][j]);	
		}
		System.out.println(sb);
		sc.close();
	}
}
