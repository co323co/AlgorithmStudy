package com.ssafy.algo;

import java.util.Scanner;

//SWEA D3 1208. [S/W 문제해결 기본] 1일차 - Flatten
public class D3_1208 {

	public static void main(String[] args) {
		
		int T=10;
		int N = 100;
		Scanner sc = new Scanner(System.in);
		
		for(int t = 1; t<=T; t++) {
			
			int dump = sc.nextInt();
			int board[] = new int[N];
			for(int i=0; i<N; i++) board[i]=sc.nextInt();				
		
			for(int d=0; d<dump; d++) {
				
				int maxIdx = findMaxIdx(board);
				int minIdx = findMinIdx(board);
				board[maxIdx]--;
				board[minIdx]++;
			}
			int res=board[findMaxIdx(board)] - board[findMinIdx(board)] ;
			
			System.out.println("#"+t+" "+res);				
		}
		
		sc.close();

	}
	
	static int findMaxIdx(int[] arr) {		
		int res=0;
		
		for(int i=0; i<arr.length; i++) {
			if(arr[i]>arr[res]) res=i;
		}
		
		return res;		
	}

	static int findMinIdx(int[] arr) {		
		int res=0;
		
		for(int i=0; i<arr.length; i++) {
			if(arr[i]<arr[res]) res=i;
		}
		
		return res;		
	}

}
	