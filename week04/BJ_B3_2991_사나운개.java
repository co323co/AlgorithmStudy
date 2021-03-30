package week04;

import java.util.Scanner;

//백준 브론즈3 2991 사나운 개
public class BJ_B3_2991_사나운개 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		int D = sc.nextInt();
		
		int P = sc.nextInt()-1;
		int M = sc.nextInt()-1;
		int N = sc.nextInt()-1;

		int[] ans = new int[3];
		
		if(P%(A+B)<A) ans[0]++;
		if(P%(C+D)<C) ans[0]++;
		
		if(M%(A+B)<A) ans[1]++;
		if(M%(C+D)<C) ans[1]++;
		
		if(N%(A+B)<A) ans[2]++;
		if(N%(C+D)<C) ans[2]++;
		
		for(int i=0; i<ans.length; i++) System.out.println(ans[i]);
		sc.close();
	}

}
