package week03;

import java.util.Arrays;
import java.util.Scanner;

//백준 S3 11399 ATM
public class BJ_S3_11399_ATM {

	public static void main(String[] args) {		
		
		Scanner sc = new Scanner(System.in);		
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i=0; i<N; i++) arr[i]=sc.nextInt();
		
		Arrays.sort(arr);
		
		int[] time = new int[N];
		time[0]=arr[0];
		for(int i=1; i<N;i++) time[i]=time[i-1]+arr[i];
		
		int sum=0;
		for(int i=0; i<N; i++) sum+=time[i];
		System.out.println(sum);
		
		sc.close();
	}

}

