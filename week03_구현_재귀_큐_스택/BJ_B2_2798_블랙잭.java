package week03;

import java.util.Scanner;

//백준 브론즈2 2798 블랙잭
public class BJ_B2_2798_블랙잭 {
	
	static int ans;
	static int M;
	static int[] arr;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N];
		for(int i=0; i<N; i++) arr[i]=sc.nextInt();
		
		combination(0, 0, new int[3]);
		
		System.out.println(ans);
		sc.close();
	}

	static void combination(int start, int k, int[] sel) {
		
		if(k==sel.length) {
			int sum=0;
			for(int i=0; i<sel.length; i++) sum+=sel[i];
			if(sum<=M) ans = Math.max(ans, sum);
			return;
		}
		
		for(int i = start; i<arr.length; i++) {
			sel[k]=arr[i];
			combination(i+1, k+1, sel);
		}
		
	}
}
	