package com.ssafy.algo;

import java.util.Arrays;
import java.util.Scanner;

//SWEA D2 1954. 달팽이 숫자
public class D2_1954_snail {

	static int[][] board;
	static int N;
	static int d = 0; //오른쪽으로 시작
	static int[] dr = {0,1,0,-1};//오아왼위
	static int[] dc = {1,0,-1,0};
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
					
			N = sc.nextInt();
			board = new int[N][N];
			
			//초기화
			for(int i=0; i<N; i++) Arrays.fill(board[i], 0);
			d=0;
			recursive(0, 0, 1);
			
			System.out.println("#"+tc);
			for(int i=0; i<N; i++) {
				for(int j=0;j<N; j++) System.out.print(board[i][j]+" ");
				System.out.println();
			}		
		}
		
		sc.close();
	}

	//go
	static void recursive(int r,int c, int num) {
		
		//기저조건, 숫자 다 쓰면 그만!
		if(num > N*N) return;
		
		board[r][c] = num;
			
		//다음칸이 벽이거나, 이미 숫자가 있으면 다음 방향으로 바꾼다
		if(r+dr[d]==N || c+dc[d]==N || r+dr[d]<0||c+dc[d]<0 || board[r+dr[d]][c+dc[d]]!=0) d = (d+1)%4;
		
		recursive(r+dr[d], c+dc[d], ++num);

	}
}
