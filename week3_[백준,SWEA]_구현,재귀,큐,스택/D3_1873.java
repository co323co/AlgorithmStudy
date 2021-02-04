package com.ssafy.algo;

import java.util.Scanner;

//SWEA D3 1873. 상호의 배틀필드
public class D3_1873 {

	static char[][] map;
	static int N, M;
	static int tr, tc;
	static int d;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static char[] t = {'^','v','<','>'};
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int test = 1; test<=T; test++) {
						
			N = sc.nextInt();
			M = sc.nextInt();
			
			map = new char[N][M];
			String[] str = new String[N];
			for(int i=0;i<N;i++) str[i] = sc.next();
		
			
			for(int i=0;i<N;i++) {
				for(int j=0; j<M; j++) {
					map[i][j]=str[i].charAt(j);
					if("<>^v".contains(map[i][j]+"")) {
						tr = i;
						tc = j;
						if(map[i][j]=='^') d=0;
						if(map[i][j]=='v') d=1;
						if(map[i][j]=='<') d=2;
						if(map[i][j]=='>') d=3;
					}
				}			
			}
			
			sc.nextInt();
			String cmd = sc.next();
			
			for(int i=0; i<cmd.length(); i++) {
			
				if(cmd.charAt(i)=='S') shoot(tr,tc);
				else move(cmd.charAt(i));
		
			}		
			System.out.print("#"+test+" ");		
			for(int j=0; j<N; j++) {
				for(int k=0; k<M; k++) System.out.print(map[j][k]);
				System.out.println();
			}	
		}		
		sc.close();
	}

	static void move(char c) {
		
		if(c=='U') d=0;
		if(c=='D') d=1;
		if(c=='L') d=2;
		if(c=='R') d=3;
		
		int nr=tr+dr[d];
		int nc=tc+dc[d];

		//전차 모양 바꾸기
		map[tr][tc] = t[d];
		
		//다음 칸이 맵 범위를 안벗어나고 평지라면 한 칸 앞으로 가기
		if(nr>=0 && nr<N && nc>=0 && nc<M && map[nr][nc]=='.') {		
			map[nr][nc]=map[tr][tc];
			map[tr][tc]='.';
			tr=nr;
			tc=nc;
		}			
	}
	
	//r,c는 포탄의 현재 위치
	static void shoot(int r, int c) {
		
		//맵밖이면 끝남
		if(r<0 || r>=N || c<0 || c>=M ) return;
		
		//벽을 만나도 끝남
		if(map[r][c]=='*' || map[r][c]=='#') {
			//벽돌 벽이면 벽 파괴해 평지화
			if(map[r][c]=='*') map[r][c] = '.';	
			return;
		}	
		shoot(r+dr[d], c+dc[d]);	
	}
}
